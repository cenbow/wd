package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.appinterface.bean.vo.HasAgentVO;
import com.okwei.bean.dto.user.ApplyAgentDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.AgentApplyInfoVO;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.user.IAgentService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping(value = "/agent")
public class AgentController extends SSOController
{
    @Autowired
    IAgentService agentService;

    /***
     * 代理商申请
     * 
     * @param dto
     * @return
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/applyAgent")
    public String applyAgent(String agent)
    {
        ApplyAgentDTO dto = (ApplyAgentDTO) JsonUtil.jsonToBean(agent,ApplyAgentDTO.class);
        LoginUser user = getUser();
        if(user == null)
        {
            return loginError();
        }
        dto.setWeiId(user.getWeiID());
        ReturnModel rm = agentService.applyAgent(dto);
        return JsonStr(rm);
    }

    public ApplyAgentDTO getApplyAgentDTO(String str)
    {
        return null;
    }

    /**
     * 获取代理商申请列表
     * 
     * @return
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/getAgentApplyList")
    public String getAgentApplyList(@RequestParam(required = false,defaultValue = "-1") short status,@RequestParam(required = false,defaultValue = "10") int pageSize,
            @RequestParam(required = false,defaultValue = "1") int pageIndex,String key)
    {
        // 用户登录判断
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        long weiid = user.getWeiID();// 用户微店号
        Limit limit = Limit.buildLimit(pageIndex,pageSize);// 分页设置
        PageResult<AgentApplyInfoVO> list = agentService.getAgentApPageResult(weiid,status,key,limit);// 获取查询结果
        ReturnModel rm = new ReturnModel();
        rm.setBasemodle(list);
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return JsonStr(rm);
    }
    

    /**
     * 代理商管理列表
     * 
     * @param status
     * @param pageSize
     * @param pageIndex
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAgentManageList")
    public String getAgentManageList(@RequestParam(required = false,defaultValue = "1") short status,@RequestParam(required = false,defaultValue = "10") int pageSize,
            @RequestParam(required = false,defaultValue = "1") int pageIndex,String keyword)
    {
        // 用户登录判断
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        long weiid = user.getWeiID();// 用户微店号
        Limit limit = Limit.buildLimit(pageIndex,pageSize);// 分页设置
        PageResult<AgentApplyInfoVO> list = new PageResult<AgentApplyInfoVO>();
        switch(status)
        {
            case 0://待审核
                list = agentService.getAgentApPageResultBySupply(weiid,Short.parseShort(ApplyAgentStatusEnum.Applying.toString()),keyword,limit);// 获取查询结果
                break;
            case 1://已审核
                list = agentService.getManageAgentPageResult(weiid,AgentStatusEnum.Normal,keyword,limit);// 获取查询结果
                break;
            case 3://已取消
                list = agentService.getManageAgentPageResult(weiid,AgentStatusEnum.Cancel,keyword,limit);// 获取查询结果
                break;
            case 2://已拒绝（审核不通过）
                list = agentService.getAgentApPageResultBySupply(weiid,Short.parseShort(ApplyAgentStatusEnum.NoPass.toString()),keyword,limit);// 获取查询结果
                break;

            default:
                break;
        }
        ReturnModel rm = new ReturnModel();
        rm.setBasemodle(list);
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return JsonStr(rm);
    }

    /**
     * 落地店查代理商
     * 
     * @param pageSize
     * @param pageIndex
     * @param keyword
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/getSuperiorAgentList")
//    public String getSuperiorAgentList(@RequestParam(required = false,defaultValue = "10") int pageSize,@RequestParam(required = false,defaultValue = "1") int pageIndex,
//            String keyword)
//    {
//        // 用户登录判断
//        LoginUser user = getUser();
//        if(user == null)
//        {
//            return loginError();
//        }
//        long weiid = user.getWeiID();// 用户微店号
//        Limit limit = Limit.buildLimit(pageIndex,pageSize);// 分页设置
//        PageResult<AgentApplyInfoVO> list = agentService.getSuperiorAgentPageResult(weiid,keyword,limit);// 获取查询结果
//        ReturnModel rm = new ReturnModel();
//        rm.setBasemodle(list);
//        rm.setStatu(ReturnStatus.Success);
//        rm.setStatusreson("成功");
//        return JsonStr(rm);
//    }

    /**
     * 代理商审核
     * 
     * @param status
     *            审核的状态
     * @param statusReson
     *            审核不通过的理由
     * @param agentId
     *            代理商申请ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/auditAgentStatus")
    public String auditAgentStatus(Short status,String statusReson,Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        rm = agentService.auditAgentStatus(user.getWeiID(),status,statusReson,agentId);
        return JsonStr(rm);
    }

    /**
     * 代理商管理
     * 
     * @param status
     *            审核的状态
     * @param statusReson
     *            审核不通过的理由
     * @param agentId
     *            代理商申请ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateAgentStatus")
    public String updateAgentStatus(Short status,String statusReson,Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        if(status==2)
        {
            //取消
            rm = agentService.applyAgentManage(user.getWeiID(),Short.parseShort(AgentStatusEnum.Cancel.toString()),statusReson,agentId);
        }
        else if(status == 3)
        {
            //删除
            rm = agentService.applyAgentManage(user.getWeiID(),Short.parseShort(AgentStatusEnum.Delete.toString()),statusReson,agentId);
        }
        else if(status == 1){
            //恢复
            rm = agentService.applyAgentManage(user.getWeiID(),Short.parseShort(AgentStatusEnum.Normal.toString()),statusReson,agentId);
        }
        return JsonStr(rm);
    }

    /**
     * 获取下游代理商详情(审核)
     * 
     * @param agentId
     *            代理商申请ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAgentAuditDetail")
    public String getAgentAuditDetail(Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        rm = agentService.getAgentDetailVO(user.getWeiID(),agentId);
        return JsonStr(rm);
    }

    /**
     * 代理商詳情 
     * 
     * @param agentId
     *            代理商ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAgentDetail")
    public String getAgentDetail(Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        rm = agentService.getAgentDetailVOByPth(user.getWeiID(),agentId);
        return JsonStr(rm);
    }

    /**
     * 获取审核记录
     * 
     * @param applyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAgentApplyFollowLog")
    public String getAgentApplyFollowLog(Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        rm = agentService.getAgentApplyFollowLog(user.getWeiID(),agentId);
        return JsonStr(rm);
    }

    /**
     * 获取代理商跟进认证员信息
     * 
     * @param agentId
     *            申请的ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getVerifierInfoByApplyId")
    public String getVerifierInfoByApplyId(Long weiId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        rm = agentService.getVerifierInfo(weiId);
        return JsonStr(rm);
    }

    /**
     * 【校验验证员是否在这个区】
     * 
     * @param verifierId
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkVerifier")
    public String checkVerifier(Integer verifierId,Integer code)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        return "";
    }

    /**
     * 【获取代理商申请结果】
     * 
     * @param agentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getApplyResult")
    public String getApplyResult(Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        rm = agentService.getApplyResult(user.getWeiID(),agentId);
        return JsonStr(rm);
    }

    /**
     * 【获取供应商联系信息】
     * 
     * @param merchantWeiId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSupplyInfo")
    public String getSupplyInfo(Long merchantWeiId)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        rm = agentService.getSupplyInfo(merchantWeiId);
        return JsonStr(rm);
    }

    /**
     * 【判断该区域是否已有代理商】
     * 
     * @param demandId
     * @param city
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkArea")
    public String checkArea(Integer demandId,Integer city)
    {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        }
        if(demandId == null || demandId < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("demandId参数有误");
            return JsonStr(rm);
        }
        if(city == null || city < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("city参数有误");
            return JsonStr(rm);
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        HasAgentVO vo = new HasAgentVO();
        if(agentService.checkArea(demandId,city))
        {
            vo.setHasAgent(1);
        }
        else
        {
            vo.setHasAgent(0);
        }
        rm.setBasemodle(vo);
        return JsonStr(rm);
    }

    /**
     * 获取未支付悬赏笔数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getchannelCountReward")
    public String getchannelCountReward(){
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUserOrSub();
        if(user == null)
        {
            return loginError();
        } 
        rm = agentService.getchannelCountReward(user.getWeiID());
        return JsonStr(rm);
    }
 

}
