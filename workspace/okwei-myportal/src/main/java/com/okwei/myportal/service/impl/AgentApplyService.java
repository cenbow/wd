package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UAgentApplyArea;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.user.ApplyAgentDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.JoinTypeEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.vo.BaseModleResultVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.dao.impl.user.LoginDAO;
import com.okwei.dao.user.IAgentDAO;
import com.okwei.dao.user.ILoginDAO;
import com.okwei.myportal.bean.vo.AgentPayVO;
import com.okwei.myportal.bean.vo.DemandAreaVO;
import com.okwei.myportal.service.IAgentApplyService;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.IRegionService;
import com.okwei.service.impl.user.LoginService;
import com.okwei.service.user.ILoginService;
import com.okwei.service.user.IUserIdentityManage;
import com.okwei.util.ObjectUtil;

@Service
public class AgentApplyService implements IAgentApplyService
{
    @Autowired
    IAgentDAO agentDao;
    @Autowired
    ILoginDAO loginDAO;
    @Autowired
    IRegionService regionService;
    @Autowired
    IBaseCommonService commonService;
    @Autowired
    ILoginService loginService;

    @Autowired
    IUserIdentityManage userDao;
    @Override
    public List<USupplyDemand> getDemandList(long supplyID)
    {
        return agentDao.getSupplyDemandListBySupplyID(supplyID,DemandStateEnum.Showing);// 展示中的需求
    }

    @Override
    public List<DemandAreaVO> getDemandAreaList(int demandID)
    {
        List<USupplyDemandArea> areaList = agentDao.getSupplyDemandAreaList(demandID);// 招商需求所有的区域
        if(areaList == null || areaList.size() <= 0)
        {
            return null;
        }
        List<DemandAreaVO> volist = new ArrayList<DemandAreaVO>();
        List<USupplyChannel> sclist = agentDao.getSupplyChannelList(demandID,SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);// 先获取所有额代理商
        List<UAgenArea> agentList = new ArrayList<UAgenArea>();
        if(sclist != null && sclist.size() > 0)
        {
            List<Integer> scids = new ArrayList<Integer>();
            for(USupplyChannel s:sclist)
            {
                scids.add(s.getChannelId());
            }
            agentList = agentDao.getUAgenAreasByChannelIds(scids);
        }
        for(USupplyDemandArea a : areaList)
        {
            DemandAreaVO vo = new DemandAreaVO();
            vo.setAreaName(regionService.getNameByCode(a.getCode()));// 名称
            vo.setCode(a.getCode());// 区域编码
            vo.setAreaID(a.getDemandAreaId());// 所在ID
            if(agentList == null || agentList.size() <= 0)
            {
                vo.setIsEnable(1);// 1表示可用
            }
            else
            {
                vo.setIsEnable(1);// 默认可用
                for(UAgenArea ua : agentList)
                {
                    if(ua.getCode().intValue() == a.getCode())
                    {
                        vo.setIsEnable(0);// 找到区域就是不可用
                    }
                }
            }
            volist.add(vo);
        }
        return volist;
    }

    @Override
    public ReturnModel insertAgentBySupplyID(long supplyID,ApplyAgentDTO dto)
    {
        ReturnModel rm = checkApplyDTO(dto);
        if(rm.getStatu() != ReturnStatus.Success)
        {
            return rm;
        }
        USupplyDemand dem = agentDao.getSupplyDemand(dto.getDemandIds()[0]);
        if(dem == null || dem.getState() != Short.parseShort(DemandStateEnum.Showing.toString()))// 状态没有定，设1是启用
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("申请的招商需求不存在或招商需求已下架");
            return rm;
        }
        if(dem.getWeiId() != supplyID)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该代理需求不属于你");
            return rm;
        }
        if(agentDao.getPayedRewardCount(supplyID) >= 3)
        {
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("您有3笔悬赏金额未支付，请先支付悬赏金额。让更多的认证员为您服务");// 您有3笔悬赏金额未支付，请先支付悬赏金额。让更多的认证员为您服务
            BaseModleResultVO vo = new BaseModleResultVO();
            vo.setResult(13);
            rm.setBasemodle(vo);
            return rm;
        }
        // 添加
        USupplyChannel sc = new USupplyChannel();
        sc.setApplyTime(new Date());
        sc.setChannelType(Short.parseShort(SupplyChannelTypeEnum.Agent.toString()));
        sc.setCreateTime(new Date());
        sc.setDemandId(dem.getDemandId());
        sc.setJoinType(Short.parseShort(JoinTypeEnum.supplier.toString()));
        sc.setPayedReward((short) 1);
        sc.setReward(0D);
        sc.setState(Short.parseShort(AgentStatusEnum.Normal.toString()));
        sc.setSupplyId(supplyID);
        sc.setUpWeiId(supplyID);
        sc.setWeiId(dto.getWeiId());
        sc.setFollowVerifier(supplyID);
        sc.setVerifier(supplyID);
        agentDao.insertUSupplyChannel(sc);// 添加代理商
        UProductAgent pa = new UProductAgent();
        pa.setChannelId(sc.getChannelId());
        pa.setCreateTime(new Date());
        pa.setDemandId(dem.getDemandId());
        pa.setLicenseImg(dto.getImgs()[0]);
        pa.setLinkMan(dto.getLinkname());
        pa.setPhone(dto.getPhone());
        pa.setSupplyId(supplyID);
        pa.setWeiId(dto.getWeiId());
        pa.setAddress(dto.getAddress());
        pa.setWeiName(commonService.getShopNameByWeiId(dto.getWeiId()));
        agentDao.insertUProductAgent(pa);// 添加代理商详情表
        TRegional tr = agentDao.getTRegional(dto.getAgentAreas()[0]);
        UAgenArea ua = new UAgenArea();
        ua.setChannelId(sc.getChannelId());
        ua.setCity(dto.getAgentAreas()[0]);
        ua.setCode(dto.getAgentAreas()[0]);
        ua.setCreateTime(new Date());
        ua.setDemandId(dem.getDemandId());
        ua.setRegionStr(regionService.getNameByCode(dto.getAgentAreas()[0]));
        ua.setSupplyId(supplyID);
        ua.setProvice(tr.getParent());
        agentDao.insertUAgentArea(ua);
        userDao.addIdentity(dto.getWeiId(),UserIdentityType.Agent);
        return rm;
    }

    /**
     * 校验申请代理商数据
     * 
     * @param dto
     *            申请数据
     * @return
     */
    public ReturnModel checkApplyDTO(ApplyAgentDTO dto)
    {
        ReturnModel rm = new ReturnModel();
        if(dto == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("提交的数据有误");
            return rm;
        }
        if(dto.getWeiId() == null || dto.getWeiId() < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("用户登陆过期");
            return rm;
        }
        if(ObjectUtil.isEmpty(dto.getLinkname()))
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("联系人姓名不能为空");
            return rm;
        }
        if(ObjectUtil.isEmpty(dto.getPhone()))
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("联系不能为空");
            return rm;
        }
        UWeiSeller seller = loginService.getUWeiSellerByWeiId(dto.getWeiId());
        if(seller == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("微店号不存在");
            return rm;
        }
        UUserAssist assist = loginDAO.getUUserAssist(dto.getWeiId());
        if(assist == null || assist.getIdentity() == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("此微店数据有误");
            return rm;
        }
        //判断是否平台号
        if((assist.getIdentity() & Integer.parseInt(UserIdentityType.PlatformSupplier.toString())) > 0)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("平台号不能成为代理商");
            return rm;
        }
        //判断是否品牌号
        if((assist.getIdentity() & Integer.parseInt(UserIdentityType.BrandSupplier.toString())) > 0)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("品牌号不能成为代理商");
            return rm;
        }
        if(dto.getDemandIds() == null || dto.getDemandIds().length < 1 || dto.getDemandIds()[0] == null || dto.getDemandIds()[0] < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("招商需求ID不能为空");
            return rm;
        }
        if(dto.getImgs() == null || dto.getImgs().length < 1)
        {
            dto.setImgs(new String[]
            {""});
        }
        // 判断代理区域
        if(dto.getAgentAreas() == null || dto.getAgentAreas().length < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("代理区域不能为空");
            return rm;
        }
        else
        {
            Integer agentAreas = dto.getAgentAreas()[0];
            if(agentAreas == null || agentAreas < 1)
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("代理区域中选择的市有误");
                return rm;
            }
            TRegional cityTRegional = agentDao.getTRegional(agentAreas);
            if(cityTRegional == null || cityTRegional.getParent() == null)
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("代理区域中选择的市有误");
                return rm;
            }
            else
            {
                // 兼容四个直辖市
                if(110000 == cityTRegional.getParent().intValue())
                {
                    dto.setAgentAreas(new Integer[]
                    {110000});// 北京市
                }
                if(120000 == cityTRegional.getParent().intValue())
                {
                    dto.setAgentAreas(new Integer[]
                    {120000});// 天津市
                }
                if(310000 == cityTRegional.getParent().intValue())
                {
                    dto.setAgentAreas(new Integer[]
                    {310000});// 上海市
                }
                if(500000 == cityTRegional.getParent().intValue())
                {
                    dto.setAgentAreas(new Integer[]
                    {500000});// 上海市
                }
            }
            // 判断招商需求地址是否正确
            int demandId = dto.getDemandIds()[0];
            rm = judgeAreaExist(dto.getWeiId(),demandId,agentAreas);
            return rm;
        }
    }

    /**
     * 判断申请的地址是否在招商申请区域
     * 
     * @param demandID
     *            招商需求ID
     * @param area
     *            申请（市）的区域代码
     * @return
     */
    public ReturnModel judgeAreaExist(long weiId,int demandID,Integer area)
    {
        ReturnModel rm = new ReturnModel();
        if(area == null || area < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("申请的区域不能为空");
            return rm;
        }
        if(demandID < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("查询的招商需求不存在");
            return rm;
        }
        // 查看这个区域自己有没有申请过
        List<UAgentApply> aglist = agentDao.getAgentApply(weiId,demandID);
        if(aglist != null && aglist.size() > 0)
        {
            List<Integer> applyIds = new ArrayList<Integer>();
            for(UAgentApply aa : aglist)
            {
                // 所有不是未通过的区域集合
//                if(aa.getState().intValue() != Short.parseShort(ApplyAgentStatusEnum.NoPass.toString()))
//                {
                    applyIds.add(aa.getApplyId());
               // }
            }
            List<UAgentApplyArea> aaalist = agentDao.getAgentApplyAreas(applyIds);
            if(aaalist != null && aaalist.size() > 0)
            {
                for(UAgentApplyArea aaa : aaalist)
                {
                    if(area.intValue() == aaa.getCode())
                    {
                        rm.setStatu(ReturnStatus.DataError);
                        rm.setStatusreson("该代理商已经申请了该区域的代理");
                        return rm;
                    }
                }
            }
        }
        List<USupplyDemandArea> list = agentDao.getSupplyDemandAreaList(demandID);
        if(list == null || list.size() < 1)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("您所在的区域暂不属于商城的招商范围，请修改招商需求，添加该地区！");
            return rm;
        }
        for(USupplyDemandArea a : list)
        {
            if(area.intValue() == a.getCode())
            {
                // 区域找到了，要找下这个区域有没有被别人申请
                List<USupplyChannel> palist = agentDao.getSupplyChannelList(demandID,SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);
                if(palist != null && palist.size() > 0)
                {
                    List<Integer> parIds = new ArrayList<Integer>();
                    for(USupplyChannel pa : palist)
                    {
                        parIds.add(pa.getChannelId());
                    }
                    // 查找已经被申请的区域
                    List<UAgenArea> aalist = agentDao.getUAgenAreasByChannelIds(parIds);
                    if(aalist != null && aalist.size() > 0)
                    {
                        for(UAgenArea aa : aalist)
                        {
                            if(area.intValue() == aa.getCode())
                            {
                                rm.setStatu(ReturnStatus.DataError);
                                rm.setStatusreson("由于该区域已有独家代理！");
                                return rm;
                            }
                        }
                    }
                }
                rm.setStatu(ReturnStatus.Success);
                rm.setStatusreson("提交成功");
                return rm;
            }
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("提交成功");// 该地区属于平台号品牌号的招商区域：且没有代理商
        return rm;
    }

    @Override
    public AgentPayVO getAgentPayVO(Long supplyID,Integer applyID)
    {
        if(applyID == null || applyID < 1)
        {
            return null;
        }
        UAgentApply uaa = agentDao.getAgentApply(applyID);
        if(uaa == null)
        {
            return null;
        }
        if(uaa.getSupplyId().longValue() != supplyID)
        {
            return null;
        }
        AgentPayVO vo = new AgentPayVO();
        vo.setAgentReward(uaa.getReward());
        vo.setApplyID(applyID);
        vo.setVerifier(uaa.getVerifier());
        vo.setVerName(commonService.getShopNameByWeiId(uaa.getVerifier()));
        return vo;
    }
}
