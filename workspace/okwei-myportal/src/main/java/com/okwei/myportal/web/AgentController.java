package com.okwei.myportal.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.user.ApplyAgentDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.AgentDetailVO;
import com.okwei.bean.vo.AreaVO;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.AgentApplyInfoVO;
import com.okwei.bean.vo.user.AgentVerifierInfoVO;
import com.okwei.bean.vo.user.MerchantVO;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.AgentPayVO;
import com.okwei.myportal.bean.vo.DemandAreaVO;
import com.okwei.myportal.service.IAgentApplyService;
import com.okwei.myportal.service.ITest;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.order.IBasicPayService;
import com.okwei.service.user.IAgentService;
import com.okwei.service.user.ILoginService;
import com.okwei.web.base.SSOController;

/**
 * 代理商
 */
@Controller
@RequestMapping(value = "/agent")
public class AgentController extends SSOController
{
    @Autowired
    ILoginService loginService;
    @Autowired
    IBaseCommonService commonService;
    @Autowired
    IAgentApplyService agentApplyService;
    @Autowired
    IAgentService agentService;// base公共的接口
    @Autowired
    ITest test;

    @Autowired
    IBasicPayService payService;

    /**
     * 代理商管理首頁
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/agentIndex")
    public String agentIndex(@RequestParam(required = false,defaultValue = "1") int status,@RequestParam(required = false,defaultValue = "1") int pageId,
            @RequestParam(required = false,defaultValue = "10") int pageSize,Model model)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        if(user == null || (user.getPph() == 0 && user.getPth() == 0))
        {
            return "redirect:/maininfo/maininfo";// 没有权限
        }
        /** 权限验证 **/
        int waitAuditCount = agentService.getApplyAgentByStatus(user.getWeiID(),ApplyAgentStatusEnum.Applying);// 等待审核
        int throughCount = agentService.getAgentByStatus(user.getWeiID(),SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);// 审核通过
        int noThroughCount = agentService.getApplyAgentByStatus(user.getWeiID(),ApplyAgentStatusEnum.NoPass);// 审核不通过
        int cancelCount = agentService.getAgentByStatus(user.getWeiID(),SupplyChannelTypeEnum.Agent,AgentStatusEnum.Cancel); // 已取消
        model.addAttribute("waitAuditCount",waitAuditCount);
        model.addAttribute("throughCount",throughCount);
        model.addAttribute("noThroughCount",noThroughCount);
        model.addAttribute("cancelCount",cancelCount);
        model.addAttribute("status",status);
        model.addAttribute("userinfo",user);
        PageResult<AgentApplyInfoVO> list = new PageResult<AgentApplyInfoVO>();
        Limit limit = Limit.buildLimit(pageId,pageSize);// 分页设置
        if(status == 1)// 待审核
        {
            list = agentService.getAgentApPageResultBySupply(user.getWeiID(),Short.parseShort(ApplyAgentStatusEnum.Applying.toString()),"",limit);
        }
        else if(status == 2)// 审核不通过
        {
            list = agentService.getAgentApPageResultBySupply(user.getWeiID(),Short.parseShort(ApplyAgentStatusEnum.NoPass.toString()),"",limit);
        }
        else if(status == 3)// 审核通过
        {
            list = agentService.getManageAgentPageResult(user.getWeiID(),AgentStatusEnum.Normal,"",limit);// 获取查询结果
        }
        else if(status == 4)// 已取消
        {
            list = agentService.getManageAgentPageResult(user.getWeiID(),AgentStatusEnum.Cancel,"",limit);// 获取查询结果
        }
        model.addAttribute("pages",list);
        return "agent/agentIndex";
    }

    /**
     * 返回支付订单号
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOrederNo")
    public String getOrederNo(Integer agentID)
    {
        BResultMsg msg = new BResultMsg();
        LoginUser user = super.getUserOrSub(); 
        if(user == null)
        {
            msg.setState(-1);
            msg.setMsg("登录过期");
            return JsonUtil.objectToJson(msg);// 登录为空
        }
        else
        {
            if(user.getPph() == 0 && user.getPth() == 0)
            {
                msg.setState(-4);
                msg.setMsg("没有权限");
                return JsonUtil.objectToJson(msg);
            }
        }
        if(agentID == null || agentID < 1)
        {
            msg.setState(0);
            msg.setMsg("参数有误");
            return JsonUtil.objectToJson(msg);
        }
        List<String> ids = new ArrayList<String>();
        ids.add(agentID.toString());
        try
        {
            msg = payService.update_OrderPayInfo(user.getWeiID(),3,1,ids);
            if(msg == null)
            {
                msg = new BResultMsg();
                msg.setMsg("生成验证码错误");
                msg.setState(-1);
                return JsonUtil.objectToJson(msg);
            }
            else
            {
                return JsonUtil.objectToJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setState(-11);
            msg.setMsg("异常");
            return JsonUtil.objectToJson(msg);
        }
    }

    // 获取招商需求列表
    @ResponseBody
    @RequestMapping(value = "/getDemandList")
    public String getDemandList()
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        ReturnModel rm = new ReturnModel();
        /** 权限验证 **/ 
        List<USupplyDemand> list = agentApplyService.getDemandList(user.getWeiID());
        rm.setBasemodle(list);
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return JsonStr(rm);
    }

    // 根据招商需求获取代理的城市
    @ResponseBody
    @RequestMapping(value = "/getCityName")
    public String getCityName(Integer demandID)
    {
        ReturnModel rm = new ReturnModel();
        if(demandID == null || demandID <= 0)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("选择的招商需求有误");
        }
        else
        {
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("成功");
            List<DemandAreaVO> list = agentApplyService.getDemandAreaList(demandID);
            rm.setBasemodle(list);
        }
        return JsonStr(rm);
    }

    // 微店号获取店铺名
    @ResponseBody
    @RequestMapping(value = "/getWeiName")
    public String getWeiName(Long weiID)
    {
        ReturnModel rm = new ReturnModel();
        if(weiID == null || weiID <= 0)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("微店号有误");
        }
        else
        {
            UWeiSeller seller = loginService.getUWeiSellerByWeiId(weiID);
            if(seller == null)
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("微店号不存在");
            }
            else
            {
                String name = commonService.getShopNameByWeiId(weiID);
                rm.setStatu(ReturnStatus.Success);
                rm.setStatusreson("成功");
                rm.setBasemodle(name);
            }
        }
        return JsonStr(rm);
    }

    // 代理商审核
    @ResponseBody
    @RequestMapping(value = "/auditAgent")
    public String auditAgent(Integer applyId,Short status,String reson)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        if(user==null||(user.getPph()==0&&user.getPth()==0))
        {
            return loginError();
        }
        /** 权限验证 **/
        ReturnModel rm = agentService.auditAgentStatus(user.getWeiID(),status,reson,applyId);
        return JsonStr(rm);
    }

    /**
     * 平台号/品牌号添加代理商
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAgent")
    public String addAgent(Long weiId,String linkname,String phone,Integer[] demandIds,String address,Integer[] agentAreas)
    {
        ApplyAgentDTO dto = new ApplyAgentDTO();
        dto.setWeiId(weiId);
        dto.setLinkname(linkname);
        dto.setPhone(phone);
        dto.setDemandIds(demandIds);
        dto.setAgentAreas(agentAreas);
        dto.setAddress(address);
        // 获取用户
        LoginUser user = super.getUserOrSub(); 
        if(user==null||(user.getPph()==0&&user.getPth()==0))
        {
            return loginError();
        }
        /** 权限验证 **/
        ReturnModel rm = agentApplyService.insertAgentBySupplyID(user.getWeiID(),dto);
        return JsonStr(rm);
    }

    public String getRegionalList(Integer code)
    {
        return "";
    }

    // 代理商管理
    @ResponseBody
    @RequestMapping(value = "agentManage")
    public String agentManage(Short status,String reson,Integer agentId)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        if(user==null||(user.getPph()==0&&user.getPth()==0))
        {
            return loginError();
        }
        /** 权限验证 **/
        ReturnModel rm = agentService.applyAgentManage(user.getWeiID(),status,reson,agentId);
        return JsonStr(rm);
    }

    /**
     * 代理商申请详情
     * 
     * @param applyID
     *            申请ID
     * @return
     */
    @RequestMapping(value = "/agentDetails")
    public String agentDetails(Integer applyID,Model model)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        /** 权限验证 **/
        if(user == null || (user.getPph() == 0 && user.getPth() == 0))
        {
            return "redirect:/maininfo/maininfo";// 没有权限
        }
        // 参数有误
        if(applyID == null || applyID < 1)
        {
            return "redirect:/agent/agentIndex";
        }
        ReturnModel rm = new ReturnModel();
        rm = agentService.getAgentDetailVO(user.getWeiID(),applyID);
        if(rm.getStatu() != ReturnStatus.Success)
        {
            return "redirect:/agent/agentIndex?error=" + rm.getStatusreson();
        }
        model.addAttribute("model",rm.getBasemodle());
        ReturnModel rm2 = agentService.getAgentApplyFollowLog(user.getWeiID(),applyID);
        model.addAttribute("verlist",rm2.getBasemodle());
        model.addAttribute("applyID",applyID);
        model.addAttribute("userinfo",user);
        return "agent/agentDetails";
    }

    /**
     * 获取代理商支付金额
     * 
     * @param applyID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "agentPayVO")
    public String agentPayVO(Integer applyID)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        /** 权限验证 **/
        if(user==null||(user.getPph()==0&&user.getPth()==0))
        {
            return loginError();
        }
        ReturnModel rm = new ReturnModel();
        AgentPayVO vo = agentApplyService.getAgentPayVO(user.getWeiID(),applyID);
        if(vo == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("数据有误");
        }
        else
        {
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("成功");
            rm.setBasemodle(vo);
        }
        return JsonStr(rm);
    }

    /**
     * 删除
     * 
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteAgent")
    public String deleteAgent(Integer[] ids,Short status)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        /** 权限验证 **/
        if(user==null||(user.getPph()==0&&user.getPth()==0))
        {
            return loginError();
        }
        if(status == null)
        {
            return "0";
        }
        if(status == 2)
        {
            int count = agentService.deleteApplyAgent(user.getWeiID(),ids);
            return String.valueOf(count);
        }
        else if(status == 4)
        {
            int count = agentService.deleteAgent(user.getWeiID(),ids);
            return String.valueOf(count);
        }
        return "0";
    }

    /**
     * 我的代理商申请记录
     * 
     * @return
     */
    @RequestMapping(value = "/myApply")
    public String myApply(@RequestParam(required = false,defaultValue = "1") int pageId,@RequestParam(required = false,defaultValue = "10") int pageSize,Model model)
    {

        // 获取用户
        LoginUser user = super.getUserOrSub(); 
        long weiid = user.getWeiID();// 用户微店号
        Limit limit = Limit.buildLimit(pageId,pageSize);// 分页设置
        PageResult<AgentApplyInfoVO> list = agentService.getAgentApPageResult(weiid,(short) -1,"",limit);// 获取查询结果
        model.addAttribute("list",list);
        model.addAttribute("userinfo",user);
        return "agent/myApply";
    }

    /**
     * 申请结果
     * 
     * @param applyID
     * @return
     */
    @RequestMapping(value = "/applyDetails")
    public String applyDetails(Integer applyID,Model model)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        /** 权限验证 **/ 
        long weiid = user.getWeiID();// 用户微店号
        ReturnModel rm = agentService.getAgentDetailVO(weiid,applyID);
        model.addAttribute("applyID",applyID);
        if(rm.getStatu() != ReturnStatus.Success)
        {
            return "redirect:/agent/myApply?error=" + rm.getStatusreson();
        }
        AgentDetailVO vo = (AgentDetailVO) rm.getBasemodle();
        if(vo != null)
        {
            List<AreaVO> volist = vo.getAgentAreas();
            if(volist != null && volist.size() > 0)
            {
                AreaVO area = volist.get(0);
                String name = area.getAreaName();
                if(area.getAreas() != null && area.getAreas().size() > 0)
                {
                    AreaVO area2 = area.getAreas().get(0);
                    name += area2.getAreaName();
                    model.addAttribute("codeNum",area2.getAreaId());
                }
                model.addAttribute("code",name);
            }
        }
        model.addAttribute("vo",vo);
        int did = vo.getDemands().get(0).getInvestmentDemandId();
        List<TRegional> reglist = agentService.getRegionalByDemenId(did);
        model.addAttribute("reglist",reglist);
        model.addAttribute("demandId",did);
        ReturnModel rm2 = agentService.getVerifierInfo(vo.getFollowVerifier());// 认证员
        if(rm2.getBasemodle() == null)
        {
            model.addAttribute("ver",new AgentVerifierInfoVO());
        }
        else
        {
            model.addAttribute("ver",rm2.getBasemodle());
        }
        ReturnModel rm3 = agentService.getSupplyInfo(vo.getMerchantWeiId());// 供应商联系方式
        if(rm3.getBasemodle() == null)
        {
            model.addAttribute("mer",new MerchantVO());
        }
        else
        {
            model.addAttribute("mer",rm3.getBasemodle());
        }
        model.addAttribute("userinfo",user);
        return "agent/applyDetails";
    }

    /**
     * 根据招商需求ID获取省列表
     * 
     * @param demandID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getProvinceByDemandID")
    public String getProvinceByDemandID(Integer demandID)
    {
        List<TRegional> list = agentService.getRegionalByDemenId(demandID);
        ReturnModel rm = new ReturnModel();
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        rm.setBasemodle(list);
        return JsonStr(rm);
    }

    /**
     * 根据区域获取下级区域
     * 
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getRegionalListByCode")
    public String getRegionalListByCode(Integer code,Integer demandID)
    {
        List<TRegional> list = agentService.getRegionalByCode(code,demandID);
        ReturnModel rm = new ReturnModel();
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        rm.setBasemodle(list);
        return JsonStr(rm);
    }

    /**
     * 重新申请
     * 
     * @param weiId
     * @param linkname
     * @param phone
     * @param demandIds
     * @param address
     * @param agentAreas
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "againApply")
    public String againApply(String linkname,String phone,Integer applyID,String address,Integer agentAreas,String imgs,Long recommend,String intro)
    {
        // 获取用户
        LoginUser user = super.getUserOrSub();
        /** 权限验证 **/
        ApplyAgentDTO dto = new ApplyAgentDTO();
        dto.setLinkname(linkname);
        dto.setPhone(phone);
        dto.setAgentAreas(new Integer[]
        {agentAreas});
        dto.setAddress(address);
        dto.setWeiId(user.getWeiID());
        dto.setIntro(intro);
        dto.setDevelopmentWeiId(recommend);
        dto.setImgs(new String[]
        {imgs});
        ReturnModel rm = new ReturnModel();
        UAgentApply uaa = agentService.getAgentApply(applyID);
        if(uaa == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("重新申请的ID有误");
            return JsonStr(rm);
        }
        dto.setDemandIds(new Integer[]
        {uaa.getDemandId()});// 获取原先申请的ID，获取招商需求ID
        rm = agentService.applyAgent(dto); // agentApplyService.insertAgentBySupplyID(user.getWeiID(),dto);
        return JsonStr(rm);
    }
}
