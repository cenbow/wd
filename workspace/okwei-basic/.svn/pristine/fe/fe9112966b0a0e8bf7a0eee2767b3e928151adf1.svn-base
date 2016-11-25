package com.okwei.service.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javassist.expr.NewArray;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UAgentApplyArea;
import com.okwei.bean.domain.UAgentApplyFollowLog;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UDemandRequired;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.domain.UYunVerifier;
import com.okwei.bean.dto.user.ApplyAgentDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.JoinTypeEnum;
import com.okwei.bean.enums.PayedRewardStatuus;
import com.okwei.bean.enums.ProductShelveStatu;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.VerifierRegionEnum;
import com.okwei.bean.enums.VerifierTypeEnum;
import com.okwei.bean.enums.YunVerifyTypeEnum;
import com.okwei.bean.vo.AgentDetailVO;
import com.okwei.bean.vo.ApplyAgentResultVO;
import com.okwei.bean.vo.AreaDetailVO;
import com.okwei.bean.vo.AreaVO;
import com.okwei.bean.vo.BaseModleResultVO;
import com.okwei.bean.vo.DemandsVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.AgentApplyInfoVO;
import com.okwei.bean.vo.user.AgentFollowLogVO;
import com.okwei.bean.vo.user.AgentVerifierInfoVO;
import com.okwei.bean.vo.user.ApplyResultVO;
import com.okwei.bean.vo.user.ChannelCountRewardVO;
import com.okwei.bean.vo.user.MerchantVO;
import com.okwei.bean.vo.user.UProductAgentVO;
import com.okwei.bean.vo.user.UProductShopVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.user.IAgentDAO;
import com.okwei.dao.user.ILoginDAO;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.TRegionalService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.impl.product.BasicProductService;
import com.okwei.service.product.IBasicProductService;
import com.okwei.service.user.IAgentService;
import com.okwei.service.user.IUserIdentityManage;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;
import com.opensymphony.xwork2.util.finder.Test;

/**
 * 代理商功能实现
 */
@Service
public class AgentService extends BaseService implements IAgentService
{
    @Autowired
    IAgentDAO agentDao;
    @Autowired
    IBaseCommonService commonService;
    @Autowired
    TRegionalService regService;
    @Autowired
    ILoginDAO loginDao;
    @Autowired
    IUserIdentityManage userDao;

    @Autowired
    IBasicProductService basicProductService;

    /**
     * 判断申请的地址是否在招商申请区域
     * 
     * @param demandID
     *            招商需求ID
     * @param area
     *            申请（市）的区域代码
     * @return
     */
    @Override
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
        Object lock = RedisUtil.getObject("agentapply_" + weiId);
        if(lock != null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("正在处理请求，请稍后...");
            return rm;
        }
        RedisUtil.setObject("agentapply_" + weiId,weiId,10);
        // 查看这个区域自己有没有申请过
        List<UAgentApply> aglist = agentDao.getAgentApply(weiId,demandID);
        if(aglist != null && aglist.size() > 0)
        {
            List<Integer> applyIds = new ArrayList<Integer>();
            for(UAgentApply aa : aglist)
            {
                // 所有不是未通过的区域集合
                if(aa.getState().intValue() == Short.parseShort(ApplyAgentStatusEnum.Applying.toString())
                        || aa.getState().intValue() == Short.parseShort(ApplyAgentStatusEnum.Pass.toString()))
                {
                    applyIds.add(aa.getApplyId());
                }
            }
            List<UAgentApplyArea> aaalist = agentDao.getAgentApplyAreas(applyIds);
            if(aaalist != null && aaalist.size() > 0)
            {
                for(UAgentApplyArea aaa : aaalist)
                {
                    if(area.intValue() == aaa.getCode())
                    {
                        rm.setStatu(ReturnStatus.DataError);
                        rm.setStatusreson("您已经申请了该区域的代理");
                        return rm;
                    }
                }
            }
        }
        List<USupplyDemandArea> list = agentDao.getSupplyDemandAreaList(demandID);
        if(list == null || list.size() < 1)
        {
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("您所在的区域暂不属于商城的招商范围，请耐心等待商城审核，是否给予代理权！");
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
                            if(area == aa.getCode())
                            {
                                rm.setStatu(ReturnStatus.Success);
                                rm.setStatusreson("代理申请提交成功，由于您申请代理的产品在该区域已有独家代理，需要平台商城确认是否给予代理权，请耐心等待！");
                                return rm;
                            }
                        }
                    }
                }
                rm.setStatu(ReturnStatus.Success);
                rm.setStatusreson("您的代理申请提交成功，请等待平台商城审核！");
                return rm;
            }
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("您的代理申请提交成功，请等待平台商城审核！");// 该地区属于平台号品牌号的招商区域：且没有代理商
        return rm;
    }

    public ReturnModel judgeAreaExist2(long weiId,int demandID,Integer area)
    {

        ReturnModel rm = new ReturnModel();
        if(area == null || area < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("判断的区域不能为空");
            return rm;
        }
        if(demandID < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("查询的招商需求不存在");
            return rm;
        }
        List<USupplyDemandArea> list = agentDao.getSupplyDemandAreaList(demandID);
        if(list == null || list.size() < 1)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该地区已不再招募代理商，无法审核通过！");
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
                                rm.setStatu(ReturnStatus.Success);
                                rm.setStatusreson("该地区已有代理商，请先取消该地区的代理商身份！");
                                BaseModleResultVO vo = new BaseModleResultVO();
                                vo.setResult(12);
                                rm.setBasemodle(vo);
                                return rm;
                            }
                        }
                    }
                }
                rm.setStatu(ReturnStatus.Success);
                rm.setStatusreson("校验成功");
                BaseModleResultVO vo = new BaseModleResultVO();
                vo.setResult(11);
                rm.setBasemodle(vo);
                return rm;
            }
        }
        rm.setStatu(ReturnStatus.DataError);
        rm.setStatusreson("申请的招商区域不存在");
        return rm;
    }

    @Override
    public String getRegName(Integer code)
    {
        if(code == null)
        {
            return "";
        }
        TRegional reg = agentDao.getTRegional(code);
        if(reg == null)
        {
            return "";
        }
        String str = reg.getName();
        TRegional reg2 = agentDao.getTRegional(reg.getParent());
        if(reg2 == null)
        {
            return str;
        }
        str = reg2.getName() + str;
        return str;
    }

    /**
     * 用户申请成为代理商
     * 
     * @param dto
     *            申请对象数据
     * @return
     */
    @Override
    public ReturnModel applyAgent(ApplyAgentDTO dto)
    {
        ReturnModel rm = checkApplyDTO(dto);// 校验数据
        String msg = rm.getStatusreson();
        if(rm.getStatu() != ReturnStatus.Success)
        {
            return rm;// 如果数据有问题，返回给前端
        }
        USupplyDemand dem = agentDao.getSupplyDemand(dto.getDemandIds()[0]);
        if(dem == null || dem.getState() != Short.parseShort(DemandStateEnum.Showing.toString()))// 状态没有定，设1是启用
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("申请的招商需求不存在或招商需求已下架");
            return rm;
        }
        UAgentApply agent = new UAgentApply();
        UAgentApplyArea area = new UAgentApplyArea();
        // TRegional areaCode = agentDao.getTRegional(dto.getLocationCode());
        // TRegional cityCode = agentDao.getTRegional(areaCode.getParent());
        agent.setVerifier(getSourceWeiID(dto.getDevelopmentWeiId(),dto.getDemandIds()[0],dto.getAgentAreas()[0],dto.getWeiId()));// 获取校验来源微店号
        if(agent.getVerifier().longValue() == dem.getWeiId())
        {
            agent.setFollowVerifier(getSystemZsVerify());// 获取校验正式认证员
        }
        else
        {
            agent.setFollowVerifier(getVerifyWeiID(agent.getVerifier(),dto.getAgentAreas()[0]));// 获取校验正式认证员
        }
        agent.setAddress(dto.getAddress());
        agent.setWeiId(dto.getWeiId());
        agent.setCompanyName("");
        agent.setCreateTime(new Date());
        agent.setDemandId(dto.getDemandIds()[0]);
        agent.setDetails(dto.getIntro());
        // agent.setFollowVerifier(dto.getDevelopmentWeiId());// 到时候要改
        agent.setLicenseImg(dto.getImgs()[0]);
        agent.setLinkMan(dto.getLinkname());
        agent.setPhone(dto.getPhone());
        agent.setRegistNum("");
        agent.setState(Short.parseShort(ApplyAgentStatusEnum.Applying.toString()));
        agent.setJoinType(Short.parseShort(JoinTypeEnum.oneself.toString()));
        agent.setSupplyId(dem.getWeiId());
        agent.setReward(getAgentReward(dem.getDemandId(),dto.getAgentAreas()[0]));
        TRegional cCode = agentDao.getTRegional(dto.getAgentAreas()[0]);
         //agent.setArea(areaCode.getCode());
         agent.setCity(dto.getAgentAreas()[0]);
         agent.setProvice(cCode.getParent());
        agentDao.insertUAgentApply(agent);
        area.setApplyId(agent.getApplyId());
        area.setProvice(cCode.getParent());
        area.setCity(cCode.getCode());
        area.setCode(cCode.getCode());
        area.setCreateTime(new Date());
        agentDao.insertUAgentApplyArea(area);
        rm.setStatu(ReturnStatus.Success);
        ApplyAgentResultVO vo = new ApplyAgentResultVO();
        vo.setAgentId(agent.getApplyId());
        rm.setBasemodle(vo);
        rm.setStatusreson(msg);
        RedisUtil.delete("agentapply_" + dto.getWeiId());// 删除锁
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
        // 判断用户所在区域（区）
        // Integer areaCode = dto.getLocationCode();
        // if(areaCode == null || areaCode < 1)
        // {
        // rm.setStatu(ReturnStatus.ParamError);
        // rm.setStatusreson("用户所在区域有误");
        // return rm;
        // }
        // else
        // {
        // TRegional areaRegional = agentDao.getTRegional(areaCode);
        // if(areaRegional == null)
        // {
        // rm.setStatu(ReturnStatus.ParamError);
        // rm.setStatusreson("用户地址中选择的县/区有误");
        // return rm;
        // }
        // TRegional cityRegional = agentDao.getTRegional(areaRegional.getParent());
        // if(cityRegional == null || cityRegional.getParent() == null)
        // {
        // rm.setStatu(ReturnStatus.ParamError);
        // rm.setStatusreson("用户地址中选择的市有误");
        // return rm;
        // }
        // }
        UUserAssist assist = loginDao.getUUserAssist(dto.getWeiId());
        if(assist != null && assist.getIdentity() != null)
        {
            if((assist.getIdentity() & Integer.parseInt(UserIdentityType.BrandSupplier.toString())) > 0)
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("品牌号不能成为代理商");
                return rm;
            }
            if((assist.getIdentity() & Integer.parseInt(UserIdentityType.PlatformSupplier.toString())) > 0)
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("平台号不能成为代理商");
                return rm;
            }
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
            Integer agentAreas = dto.getAgentAreas()[0];// 申请的区域
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
     * 获取来源微店号
     * 
     * @param sourceID
     *            用户填写的来源微店号
     * @param demandID
     *            申请的招商需求
     * @param code
     *            申请的区域
     * @param weiID
     *            用户的微店号
     * @return
     */
    @Override
    public Long getSourceWeiID(Long sourceID,int demandID,int code,long weiID)
    {
        List<UAgentApply> applyList = agentDao.getAgentApply(weiID,demandID);// 获取用户申请
        if(applyList == null || applyList.size() < 1)
        {
            // 如果用户没有申请过
            if(sourceID == null || sourceID < 1)
            {
                // 如果来源没有填写。返回微店网自己的见习
                return getSystemJxVerify();
            }
            UWeiSeller seller = loginDao.getUWeiSeller(sourceID);
            if(seller != null)
            {
                return sourceID;
            }
            else
            {
                // 如果来源没有填写。返回微店网自己的见习
                return getSystemJxVerify();
            }
        }
        for(UAgentApply a : applyList)
        {
            UAgentApplyArea area = agentDao.getAgentApplyAreas(a.getApplyId());// 获取用户的申请区域
            if(area == null)
            {
                // 容错
                continue;
            }
            // 如果这个区域已经申请过
            if(code == area.getCode())
            {
                // 已经申请的，返回上次初次申请的来源微店号
                return a.getVerifier();
            }
        }
        // 如果用户没有申请过
        if(sourceID == null || sourceID < 1)
        {
            // 如果来源没有填写。返回微店网自己的见习
            return getSystemJxVerify();
        }
        return sourceID;
    }

    /**
     * 根据区域获取跟进正式微店号
     * 
     * @param code
     * @return
     */
    @Override
    public Long getVerifyWeiID(Long sourceID,Integer code)
    {
        if(code == null || code < 1)
        {
            // 容错
            return getSystemZsVerify();
        }

        List<UBatchVerifierRegion> regList = agentDao.getBatchVerifierRegionListByCode(code);
        if(regList == null || regList.size() < 1)
        {
            // 如果这个区域没有认证员负责，返回系统的认证员
            return getSystemZsVerify();
        }
        // 判断来源微店号是否是见习认证员
        if(sourceID != null && sourceID > 0)
        {
            UYunVerifier ver = loginDao.getUYunVerifier(sourceID);
            if(ver != null)
            {
                if(ver.getType() == Short.parseShort(YunVerifyTypeEnum.jxVerify.toString()))
                {
                    // 如果来源是见习，分配给见习的上级正式，前提是这个正式在这个区域
                    for(UBatchVerifierRegion reg : regList)
                    {
                        // 判断这个正式在不在这个区域
                        if(reg.getWeiId() == ver.getSupperAdvisor().longValue())
                        {
                            return ver.getSupperAdvisor();// 返回正式认证员
                        }
                    }
                }
                if(ver.getType() == Short.parseShort(YunVerifyTypeEnum.zsVerify.toString()))
                {
                    // 如果来源是正式，分配给自己，前提是这个正式在这个区域
                    for(UBatchVerifierRegion reg : regList)
                    {
                        // 判断这个正式在不在这个区域
                        if(reg.getWeiId() == sourceID.longValue())
                        {
                            return sourceID;// 返回他自己
                        }
                    }
                }
            }
        }
        Random rand = new Random();
        int count = regList.size();// 获取这个区域有几个正式认证员
        if(count == 1)
        {
            return regList.get(0).getWeiId();
        }
        else
        {
            int index = rand.nextInt(count - 1);// 随机索引
            return regList.get(index).getWeiId();
        }
    }

    /**
     * 获取系统的见习认证员（代理商申请来源）
     * 
     * @return
     */
    public Long getSystemJxVerify()
    {
        return 11111L;
    }

    /**
     * 获取系统正式认证员（代理商跟进认证员）
     * 
     * @return
     */
    public Long getSystemZsVerify()
    {
        return 22222L;
    }

    @Override
    public PageResult<UProductAgentVO> getUProPageResult(long weiid,int status,String key,Limit limit)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReturnModel applyAgentManage(Long supplierId,Short status,String statusReson,Integer agentId)
    {
        ReturnModel rm = checkParamStatus(supplierId,status,agentId);
        if(rm == null || rm.getStatu() != ReturnStatus.Success)
        {
            return rm;// 校验数据有问题
        }
        // 校验取消原因
        if(status == Short.parseShort(AgentStatusEnum.Cancel.toString()))
        {
            if(ObjectUtil.isEmpty(statusReson))
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("取消代理商的原因不能为空");
                return rm;
            }
        }
        // 获取代理商
        USupplyChannel sc = agentDao.getSupplyChannel(agentId);
        if(sc == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("管理的代理商不存在");
            return rm;
        }
        if(sc.getSupplyId().longValue() != supplierId)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("非法操作，该代理商不属于你");
            return rm;
        }
        if(status == Short.parseShort(AgentStatusEnum.Normal.toString()))
        {
            // 判断是否是取消的状态
            if(sc.getState() != Short.parseShort(AgentStatusEnum.Cancel.toString()))
            {
                rm.setStatu(ReturnStatus.DataError);
                rm.setStatusreson("该代理商不是取消的状态，不能恢复，操作中断");
                return rm;
            }
            if(agentDao.getPayedRewardCount(supplierId) >= 3)
            {
                rm.setStatu(ReturnStatus.Success);
                rm.setStatusreson("您有3笔悬赏金额未支付，请先支付悬赏金额。让更多的认证员为您服务");// 您有3笔悬赏金额未支付，请先支付悬赏金额。让更多的认证员为您服务
                BaseModleResultVO vo = new BaseModleResultVO();
                vo.setResult(13);
                rm.setBasemodle(vo);
                return rm;
            }
            UAgenArea uaa = agentDao.getAgenArea(sc.getDemandId(),sc.getChannelId());
            rm = judgeAreaExist2(sc.getWeiId(),sc.getDemandId(),uaa == null ? 0 : uaa.getCode());
            // 该区域已经被其他用户代理了
            if(rm.getStatusreson().equals("该区域已经被其他用户代理了"))
            {
                return rm;
            }
            // 恢复代理商
            sc.setState(status);
            // 添加权限
            userDao.addIdentity(sc.getWeiId(),UserIdentityType.Agent);
            // 上架产品
            try
            {
                basicProductService.shelveProductToAgeStore(sc.getDemandId(),sc.getSupplyId(),sc.getWeiId(),Short.parseShort(SupplyChannelTypeEnum.Agent.toString()),
                        Short.parseShort(ProductShelveStatu.OnShelf.toString()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            rm.setStatu(ReturnStatus.Success);
            BaseModleResultVO vo = new BaseModleResultVO();
            vo.setResult(11);
            rm.setBasemodle(vo);
            rm.setStatusreson("操作成功(恢复代理商)");
        }
        else if(status == Short.parseShort(AgentStatusEnum.Cancel.toString()))
        {
            int count = agentDao.getSupplyChannelLIstByWeiIDCount(sc.getWeiId(),SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);
            // 判断代理商是否支付悬赏金额，未支付，不能取消
            if(!agentDao.isPayedRewardBySupplyId(sc.getChannelId()))
            {
                rm.setStatu(ReturnStatus.DataError);
                rm.setStatusreson("代理商未支付悬赏金额，不能取消");// 代理商未支付悬赏金额，不能取消
                return rm;
            }
            // 判断是否是正常的状态
            if(sc.getState() != Short.parseShort(AgentStatusEnum.Normal.toString()))
            {
                rm.setStatu(ReturnStatus.DataError);
                rm.setStatusreson("该代理商不是正常的状态，不能取消，操作中断");
                return rm;
            }
            // 取消代理商
            sc.setState(status);
            sc.setCancelRemark(statusReson);
            // 如果只有一个
            if(count == 1)
            {
                userDao.removeIdentity(sc.getWeiId(),UserIdentityType.Agent);
            }
            // 下架架产品
            try
            {
                basicProductService.shelveProductToAgeStore(sc.getDemandId(),sc.getSupplyId(),sc.getWeiId(),Short.parseShort(SupplyChannelTypeEnum.Agent.toString()),
                        Short.parseShort(ProductShelveStatu.OffShelf.toString()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("操作成功(取消代理商)");
        }
        else if(status == Short.parseShort(AgentStatusEnum.Delete.toString()))
        {
            // 判断是否是取消的状态
            if(sc.getState() != Short.parseShort(AgentStatusEnum.Cancel.toString()))
            {
                rm.setStatu(ReturnStatus.DataError);
                rm.setStatusreson("该代理商不是取消的状态，不能删除，操作中断");
                return rm;
            }
            // 删除代理商
            sc.setState(status);
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("操作成功(删除代理商)");
        }
        else
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("非法的操作状态");
        }
        return rm;
    }

    /**
     * 审核代理商参数校验
     */
    public ReturnModel checkParamStatus(Long supplierId,Short status,Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        // 用户是否过期
        if(supplierId == null || supplierId < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("用户登陆过期");
            return rm;
        }
        // 状态是否合法
        if(status == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("状态传入不正确");
            return rm;
        }
        if(agentId == null || agentId < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("代理商的ID不能为空");
            return rm;
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("校验成功");
        return rm;
    }

    @Override
    public AgentApplyInfoVO getAgentApplyInfoVO(long weiid,int applyID)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public PageResult<AgentApplyInfoVO> getAgentApplyInfoVOList(PageResult<UAgentApply> uaalist,short status)
    {
        PageResult<AgentApplyInfoVO> list = new PageResult<AgentApplyInfoVO>();
        if(uaalist == null || uaalist.getList() == null || uaalist.getList().size() < 1)
        {
            return list;
        }
        list.setPageCount(uaalist.getPageCount());
        list.setPageIndex(uaalist.getPageIndex());
        list.setPageSize(uaalist.getPageSize());
        list.setTotalCount(uaalist.getTotalCount());
        List<UAgentApply> ualist = uaalist.getList();
        List<AgentApplyInfoVO> volist = new ArrayList<AgentApplyInfoVO>();
        for(UAgentApply ua : ualist)
        {
            AgentApplyInfoVO vo = new AgentApplyInfoVO();
            USupplyDemand sd = agentDao.getSupplyDemand(ua.getDemandId());
            UAgentApplyArea aaa = agentDao.getAgentApplyAreas(ua.getApplyId());
            vo.setJoinType(ua.getJoinType());// 申请来源
            vo.setDemandName(sd.getTitle());
            vo.setAgentId(ua.getApplyId().longValue());
            vo.setWeiId(ua.getWeiId());
            vo.setShopName(commonService.getShopNameByWeiId(ua.getWeiId()));
            vo.setWeiPicture(commonService.getShopImageByWeiId(ua.getWeiId()));
            vo.setLinkname(ua.getLinkMan());
            vo.setPhone(ua.getPhone());
            vo.setApplyTime(ua.getCreateTime());
            if(status == -1)
            {
                vo.setStatus(ua.getState());
            }
            else
            {
                vo.setStatus(status);
            }
            vo.setMerchantWeiId(ua.getSupplyId());// 上级供应商微店号
            vo.setDevelopmentWeiId(ua.getVerifier());// 发展人微店号
            vo.setFollowVerifyWeiId(ua.getFollowVerifier());// 跟进人微店号
            vo.setAddress(ua.getAddress());// 代理商地址
            vo.setDemandId(ua.getDemandId());// 代理需求Id
            vo.setAreaStr(regService.getNameByCode(aaa.getProvice() == null ? 0 : aaa.getProvice()) + "-" + regService.getNameByCode(aaa.getCity() == null ? 0 : aaa.getCity()));// 代理区域说明
            // vo.setLocationStr(regService.getNameByCode(ua.getProvice()) + "-" +
            // regService.getNameByCode(ua.getCity()) + "-" +
            // regService.getNameByCode(ua.getArea()));// 所在地区
            vo.setRewardAmount(ua.getReward());// 悬赏金额
            vo.setIsPayReward((short) 0);// 否支付悬赏0-未支付1已支付
            vo.setVerifyTime(ua.getAuditTime());// 申请时间
            vo.setRemarks(ua.getRemarks());// 审核不通过原因
            if(ua.getState() == Short.parseShort(ApplyAgentStatusEnum.Pass.toString()))
            {
                USupplyChannel sc = agentDao.getSupplyChannelByApplyId(ua.getApplyId());
                if(sc != null)
                {
                    vo.setIsPayReward(sc.getPayedReward());
                }
            }
            // vo.setTotalStore(agentDao.getChannelCount(ua.getWeiId(),SupplyChannelTypeEnum.ground));//
            // 落地店数量
            vo.setTotalStore(0);// 申请都是0
            vo.setDemandProductCount(agentDao.getProductCountByDemandID(ua.getDemandId()));// 产品数量

            UBrandSupplyer sb = agentDao.getBrandSupplyer(ua.getSupplyId());
            if(sb != null)
            {
                vo.setMerchantIdentity(2);
                vo.setMerchantWeiName(sb.getSupplyName());
                vo.setMerchantIdentityLogo("http://img1.okimgs.com//group1/M00/E3/76/wKgKPVZQF12AMkFBAAAFhzpOrcc660_12.png");// 品牌号logo
            }
            else
            {
                UPlatformSupplyer ps = agentDao.getUPlatformSupplyer(ua.getSupplyId());
                if(ps != null)
                {
                    vo.setMerchantIdentity(1);
                    vo.setMerchantWeiName(ps.getSupplyName());
                    vo.setMerchantIdentityLogo("http://img1.okimgs.com//group1/M01/E3/76/wKgKPVZQF0OAdNPuAAAFfmqRR48674_12.png");// 平台号logo
                }
                else
                {
                    vo.setMerchantIdentity(0);// 不是平台号，不是品牌号
                }
            }
            volist.add(vo);
        }
        list.setList(volist);
        return list;
    }

    @Override
    public PageResult<AgentApplyInfoVO> getAgentApPageResult(long weiid,short status,String key,Limit limit)
    {
        PageResult<UAgentApply> uaalist = agentDao.getAgentApplyListByWeiID(weiid,status,limit);
        return getAgentApplyInfoVOList(uaalist,status);
    }

    @Override
    public PageResult<AgentApplyInfoVO> getAgentApPageResultBySupply(long supplyID,short status,String key,Limit limit)
    {
        PageResult<UAgentApply> uaalist = agentDao.getAgentApplyListBySupplyId(supplyID,status,limit);
        return getAgentApplyInfoVOList(uaalist,status);

    }

    @Override
    public PageResult<UProductShopVO> getUProductShop(long weiid,int status,String key,Limit limit)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UProductShopVO getProductShopVO(long weiid,int shopID)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReturnModel auditAgentStatus(Long supplierId,Short status,String statusReson,Integer agentId)
    {
        ReturnModel rm = checkParamStatus(supplierId,status,agentId);
        if(rm == null || rm.getStatu() != ReturnStatus.Success)
        {
            return rm;// 校验数据有问题
        }
        // 检验是否审核不通过
        if(status == Short.parseShort(ApplyAgentStatusEnum.NoPass.toString()))
        {
            if(ObjectUtil.isEmpty(statusReson))
            {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("审核不通过原因不能为空");
                return rm;
            }
        }
        // 获取代理商申请
        UAgentApply uaa = agentDao.getAgentApply(agentId);
        if(uaa == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("审核的代理商不存在");
            return rm;
        }
        if(uaa.getState() != Short.parseShort(ApplyAgentStatusEnum.Applying.toString()))
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("代理商已审核");
            return rm;
        }
        if(supplierId.intValue() != uaa.getSupplyId())
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("非法操作，该代理商不属于你");
            return rm;
        }
        if(status == Short.parseShort(ApplyAgentStatusEnum.Pass.toString()))
        {
            if(agentDao.getPayedRewardCount(supplierId) >= 3)
            {
                rm.setStatu(ReturnStatus.Success);
                rm.setStatusreson("您有3笔悬赏金额未支付，请先支付悬赏金额。让更多的认证员为您服务");// 您有3笔悬赏金额未支付，请先支付悬赏金额。让更多的认证员为您服务
                BaseModleResultVO vo = new BaseModleResultVO();
                vo.setResult(13);
                rm.setBasemodle(vo);
                return rm;
            }
            // 审核通过
            UAgentApplyArea aaa = agentDao.getAgentApplyAreas(agentId);// 申请的区域
            rm = judgeAreaExist2(supplierId,uaa.getDemandId(),aaa.getCode());
            if(rm.getStatu() != ReturnStatus.Success)
            {
                return rm;// 区域已经被申请/已经取消
            }
            // 该区域已经被其他用户代理了
            if(rm.getStatusreson().equals("该区域已经被其他用户代理了"))
            {
                return rm;
            }
            uaa.setAuditTime(new Date());// 审核时间
            USupplyChannel sc = new USupplyChannel();
            sc.setDemandId(uaa.getDemandId());
            sc.setWeiId(uaa.getWeiId());
            sc.setSupplyId(supplierId);
            sc.setVerifier(uaa.getVerifier());
            sc.setFollowVerifier(uaa.getFollowVerifier());
            sc.setChannelType(Short.parseShort(SupplyChannelTypeEnum.Agent.toString()));
            sc.setUpWeiId(uaa.getSupplyId());
            sc.setState(Short.parseShort(AgentStatusEnum.Normal.toString()));
            sc.setReward(uaa.getReward());// 悬赏
            sc.setPayedReward(Short.parseShort(PayedRewardStatuus.No.toString()));
            sc.setCreateTime(new Date());
            sc.setApplyID(uaa.getApplyId());
            sc.setApplyTime(uaa.getCreateTime());
            sc.setJoinType(Short.parseShort(JoinTypeEnum.oneself.toString()));// 來源是自己申請的
            Integer cid = agentDao.insertUSupplyChannel(sc);
            if(cid == null || cid < 1)
            {
                rm.setStatu(ReturnStatus.DataError);
                rm.setStatusreson("添加代理商渠道失败");
                return rm;
            }
            // 添加权限
            userDao.addIdentity(sc.getWeiId(),UserIdentityType.Agent);
            // 上架产品
            try
            {
                basicProductService.shelveProductToAgeStore(uaa.getDemandId(),uaa.getSupplyId(),uaa.getWeiId(),Short.parseShort(SupplyChannelTypeEnum.Agent.toString()),
                        Short.parseShort(ProductShelveStatu.OnShelf.toString()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            UProductAgent pa = new UProductAgent();
            pa.setChannelId(cid);
            pa.setDemandId(uaa.getDemandId());
            pa.setSupplyId(supplierId);
            pa.setWeiId(uaa.getWeiId());
            pa.setCompanyName(uaa.getCompanyName());
            pa.setLinkMan(uaa.getLinkMan());
            pa.setPhone(uaa.getPhone());
            pa.setDetails(uaa.getDetails());
            pa.setAdvantage(uaa.getDetails());
            pa.setLicenseImg(uaa.getLicenseImg());
            pa.setRegistNum(uaa.getRegistNum());
            pa.setProvice(uaa.getProvice());
            pa.setCity(uaa.getCity());
            pa.setArea(uaa.getArea());
            pa.setAddress(uaa.getAddress());
            pa.setCreateTime(new Date());
            pa.setWeiName(commonService.getShopNameByWeiId(uaa.getWeiId()));
            agentDao.insertUProductAgent(pa);// 添加代理商表？？？？？？？？？？
            UAgenArea agen = new UAgenArea();
            agen.setDemandId(uaa.getDemandId());
            agen.setChannelId(cid);
            agen.setCode(aaa.getCode());
            agen.setRegionStr(getRegName(aaa.getCode()));
            agen.setProvice(aaa.getProvice());
            agen.setCity(aaa.getCity());
            agen.setArea(aaa.getArea());
            agen.setCreateTime(new Date());
            agentDao.insertUAgentArea(agen);
            uaa.setState(status); // 修改申请表状态
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("审核成功(审核通过)");
            BaseModleResultVO vo = new BaseModleResultVO();
            vo.setResult(11);
            vo.setAgentId(sc.getChannelId());
            rm.setBasemodle(vo);
        }
        else if(status == Short.parseShort(ApplyAgentStatusEnum.NoPass.toString()))
        {
            if(uaa.getState() == Short.parseShort(ApplyAgentStatusEnum.Pass.toString()))
            {
                rm.setStatu(ReturnStatus.DataError);
                rm.setStatusreson("审核失败，该代理商已经审核通过");
                return rm;
            }
            // 审核不通过
            uaa.setState(status);
            uaa.setAuditTime(new Date());
            uaa.setRemarks(statusReson);
            BaseModleResultVO vo = new BaseModleResultVO();
            vo.setResult(11);
            rm.setBasemodle(vo);
            rm.setStatu(ReturnStatus.Success);
            rm.setStatusreson("审核成功(审核不通过)");
        }
        else
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("非法的审核状态");
        }
        return rm;
    }

    @Override
    public ReturnModel getAgentDetailVO(long supplyId,Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        if(supplyId < 1 || agentId == null || agentId < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("传入的参数有误");
            return rm;
        }
        UAgentApply uaa = agentDao.getAgentApply(agentId);
        if(uaa == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("代理商不存在");
            return rm;
        }
        if(uaa.getSupplyId() != supplyId && uaa.getWeiId() != supplyId)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该代理商不不属于你");
            return rm;
        }
        USupplyDemand sd = agentDao.getSupplyDemand(uaa.getDemandId());
        if(sd == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该招商条件不存在/已经下架");
            return rm;
        }
        UAgentApplyArea applyarea = agentDao.getAgentApplyAreas(uaa.getApplyId());
        if(applyarea == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("申请的区域为空");
            return rm;
        }
        // 设置详情
        AgentDetailVO vo = new AgentDetailVO();
        vo.setAgentId(agentId);
        vo.setWeiId(uaa.getWeiId());
        vo.setShopName(commonService.getShopNameByWeiId(uaa.getWeiId()));
        vo.setWeiPicture(commonService.getShopImageByWeiId(uaa.getWeiId()));
        vo.setLinkname(uaa.getLinkMan());
        vo.setPhone(uaa.getPhone());
        vo.setImgs(new String[]
        {ImgDomain.GetFullImgUrl(uaa.getLicenseImg())});
        vo.setBigImgs(new String[]
        {ImgDomain.GetFullImgUrl(uaa.getLicenseImg(),75)});
        vo.setIntro(uaa.getDetails());
        vo.setMerchantWeiId(uaa.getSupplyId());
        vo.setMerchantShopName(commonService.getShopNameByWeiId(uaa.getWeiId()));
        vo.setStatus(uaa.getState());
        vo.setReason(uaa.getRemarks());
        vo.setFollowVerifier(uaa.getFollowVerifier());
        if(uaa.getVerifier() != null)
        {
            UVerifier ver = loginDao.getUVerifier(uaa.getFollowVerifier());
            if(ver != null)
            {
                vo.setDevelopmentWeiId(uaa.getVerifier());
                vo.setDevelopmentName(ver.getName());
                vo.setDevelopmentPhone(ver.getPhone());
            }
        }
        vo.setApplyTime(uaa.getCreateTime());
        vo.setAddress(uaa.getAddress());
        // 所在地区
        // AreaDetailVO area = new AreaDetailVO();// 区
        // AreaDetailVO city = new AreaDetailVO();// 市
        // AreaDetailVO provice = new AreaDetailVO();// 省
        // if(uaa.getArea()!=null){
        // area.setAreaId(uaa.getArea());
        // area.setAreaName(regService.getNameByCode(uaa.getArea()));
        // city.setAreas(area);
        // }
        // city.setAreaId(uaa.getCity());
        // city.setAreaName(regService.getNameByCode(uaa.getCity()));
        // provice.setAreaId(uaa.getProvice());
        // provice.setAreaName(regService.getNameByCode(uaa.getProvice()));
        // provice.setAreas(city);
        // vo.setLocation(provice);
        // 代理地区(一个申请一个区域)
        List<AreaVO> arealist = new ArrayList<AreaVO>();// 区
        List<AreaVO> citylist = new ArrayList<AreaVO>();// 市
        List<AreaVO> provicelist = new ArrayList<AreaVO>();// 省
        arealist.add(new AreaVO());
        if(applyarea.getArea() != null)
        {
            arealist.get(0).setAreaId(applyarea.getArea());
            arealist.get(0).setAreaName(regService.getNameByCode(applyarea.getArea() == null ? 0 : applyarea.getArea()));
        }
        citylist.add(new AreaVO());
        citylist.get(0).setAreaId(applyarea.getCity());
        citylist.get(0).setAreaName(regService.getNameByCode(applyarea.getCity() == null ? 0 : applyarea.getCity()));
        citylist.get(0).setAreas(arealist);
        provicelist.add(new AreaVO());
        provicelist.get(0).setAreaId(applyarea.getProvice());
        provicelist.get(0).setAreaName(regService.getNameByCode(applyarea.getProvice() == null ? 0 : applyarea.getProvice()));
        provicelist.get(0).setAreas(citylist);
        vo.setAgentAreas(provicelist);
        // /落地店数量
        vo.setStoreCount(agentDao.getGroundCountByArea(applyarea.getCode(),uaa.getDemandId()));
        // 代理需求
        List<DemandsVO> dem = new ArrayList<DemandsVO>();
        dem.add(new DemandsVO());
        dem.get(0).setInvestmentDemandId(uaa.getDemandId());
        dem.get(0).setInvestmentDemandName(sd.getTitle());
        vo.setDemands(dem);
        List<DemandsVO> already = getDemandsVOList(uaa.getWeiId(),supplyId);// 已经代理的区域
        vo.setHasDemands(already);
        vo.setRewardAmount(uaa.getReward());
        vo.setIsPayReward((short) 0);
        if(uaa.getState() == Short.parseShort(ApplyAgentStatusEnum.Pass.toString()))
        {
            USupplyChannel sc = agentDao.getSupplyChannelByApplyId(uaa.getApplyId());
            if(sc != null)
            {
                vo.setIsPayReward(sc.getPayedReward());
            }
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setBasemodle(vo);
        rm.setStatusreson("成功");
        return rm;
    }

    /**
     * 获取代理同一个需求下的代理区域有多少个
     * 
     * @param weiid
     *            代理商微店号
     * @param demandId
     *            招商需求 ID
     * @return
     */
    public List<AreaVO> getAreaListBydemandId(long weiid,int demandId)
    {
        return null;
    }

    @Override
    public ReturnModel getAgentDetailVOByPth(long supplyId,Integer agentId)
    {
        ReturnModel rm = new ReturnModel();
        if(supplyId < 1 || agentId == null || agentId < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("传入的参数有误");
            return rm;
        }
        USupplyChannel sc = agentDao.getSupplyChannel(agentId);// 代理商渠道表
        if(sc == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该代理商渠道不存在");
            return rm;
        }
        UProductAgent pa = agentDao.getProductAgent(agentId);// 代理商表
        if(pa == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该代理商不存在");
            return rm;
        }
        USupplyDemand sd = agentDao.getSupplyDemand(sc.getDemandId());// 代理需求
        UAgenArea uarea = agentDao.getAgenArea(sc.getDemandId(),sc.getChannelId());// 申请的区域
        // 设置详情
        AgentDetailVO vo = new AgentDetailVO();
        vo.setAgentId(agentId);
        vo.setWeiId(sc.getWeiId());
        vo.setShopName(commonService.getShopNameByWeiId(sc.getWeiId()));
        vo.setWeiPicture(commonService.getShopImageByWeiId(sc.getWeiId()));
        vo.setLinkname(pa.getLinkMan());
        vo.setPhone(pa.getPhone());
        vo.setImgs(new String[]
        {pa.getLicenseImg()});
        vo.setIntro(pa.getDetails());
        vo.setMerchantWeiId(sc.getSupplyId());
        vo.setMerchantShopName(commonService.getShopNameByWeiId(sc.getWeiId()));
        vo.setStatus(sc.getState());// 这个状态不一样
        vo.setDevelopmentWeiId(sc.getVerifier());
        vo.setApplyTime(sc.getCreateTime());
        vo.setAddress(pa.getAddress());
        vo.setFollowVerifier(sc.getFollowVerifier());
        vo.setReason(sc.getCancelRemark());// 取消的原因
        // 所在地区
        AreaDetailVO area = new AreaDetailVO();// 区
        AreaDetailVO city = new AreaDetailVO();// 市
        AreaDetailVO provice = new AreaDetailVO();// 省
        area.setAreaId(pa.getArea());
        area.setAreaName(regService.getNameByCode(pa.getArea() == null ? 0 : pa.getArea()));
        city.setAreaId(pa.getCity());
        city.setAreaName(regService.getNameByCode(pa.getCity() == null ? 0 : pa.getCity()));
        city.setAreas(area);
        provice.setAreaId(pa.getProvice());
        provice.setAreaName(regService.getNameByCode(pa.getProvice() == null ? 0 : pa.getProvice()));
        provice.setAreas(city);
        vo.setLocation(provice);
        // 代理地区(一个申请一个区域)
        List<AreaVO> arealist = new ArrayList<AreaVO>();// 区
        List<AreaVO> citylist = new ArrayList<AreaVO>();// 市
        List<AreaVO> provicelist = new ArrayList<AreaVO>();// 省
        arealist.add(new AreaVO());
        arealist.get(0).setAreaId(uarea.getArea() == null ? 0 : uarea.getArea());
        arealist.get(0).setAreaName(regService.getNameByCode(uarea.getArea() == null ? 0 : uarea.getArea()));
        citylist.add(new AreaVO());
        citylist.get(0).setAreaId(uarea.getCity());
        citylist.get(0).setAreaName(regService.getNameByCode(uarea.getCity() == null ? 0 : uarea.getCity()));
        citylist.get(0).setAreas(arealist);
        provicelist.add(new AreaVO());
        provicelist.get(0).setAreaId(uarea.getProvice());
        provicelist.get(0).setAreaName(regService.getNameByCode(uarea.getProvice() == null ? 0 : uarea.getProvice()));
        provicelist.get(0).setAreas(citylist);
        vo.setAgentAreas(provicelist);
        // /落地店数量
        vo.setStoreCount(agentDao.getGroundCountByArea(uarea.getCode(),sc.getDemandId()));//
        // 代理需求
        List<DemandsVO> dem = new ArrayList<DemandsVO>();
        dem.add(new DemandsVO());
        dem.get(0).setInvestmentDemandId(sc.getDemandId());
        dem.get(0).setInvestmentDemandName(sd.getTitle());
        vo.setDemands(dem);
        // 已经代理的区域
        List<DemandsVO> already = getDemandsVOList(sc.getWeiId(),supplyId);
        vo.setHasDemands(already);
        // 认证员信息
        if(sc.getVerifier() != null)
        {
            UVerifier ver = loginDao.getUVerifier(sc.getVerifier());
            if(ver != null)
            {
                vo.setDevelopmentWeiId(sc.getVerifier());
                vo.setDevelopmentName(ver.getName());
                vo.setDevelopmentPhone(ver.getPhone());
            }
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setBasemodle(vo);
        rm.setStatusreson("成功");
        vo.setRewardAmount(sc.getReward());
        vo.setIsPayReward(sc.getPayedReward());
        return rm;
    }

    /**
     * 获取微店已经代理的区域
     * 
     * @param weiid
     * @return
     */
    public List<DemandsVO> getDemandsVOList(long weiID,long supplyID)
    {
        List<USupplyChannel> sclist = agentDao.getSupplyChannelLIstByWeiID(weiID,SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);
        if(sclist != null && sclist.size() > 0)
        {
            List<Integer> dems = new ArrayList<Integer>();
            for(USupplyChannel sc : sclist)
            {
                if(sc.getSupplyId() != null && supplyID == sc.getSupplyId())
                {
                    dems.add(sc.getDemandId());
                }
            }
            List<USupplyDemand> sdlist = agentDao.getsupSupplyDemandsByIds(dems);
            if(sdlist != null && sdlist.size() > 0)
            {
                List<DemandsVO> voList = new ArrayList<DemandsVO>();
                for(USupplyDemand sd : sdlist)
                {
                    DemandsVO vo = new DemandsVO();
                    vo.setInvestmentDemandId(sd.getDemandId());
                    vo.setInvestmentDemandName(sd.getTitle());
                    voList.add(vo);
                }
                return voList;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ReturnModel getAgentApplyFollowLog(long supplyId,Integer applyId)
    {
        ReturnModel rm = new ReturnModel();
        if(supplyId < 1)
        {
            rm.setStatu(ReturnStatus.LoginError);
            rm.setStatusreson("登陆过期");
            return rm;
        }
        if(applyId == null || applyId < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("参数错误");
            return rm;
        }
        UAgentApply uaa = agentDao.getAgentApply(applyId);
        if(uaa == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该申请不存在");
            return rm;
        }

        Boolean havePower = false;
        if(uaa.getSupplyId() != null && supplyId == uaa.getSupplyId())
        {
            havePower = true;
        }

        if(uaa.getVerifier() != null && supplyId == uaa.getVerifier())
        {
            havePower = true;
        }

        if(uaa.getFollowVerifier() != null && supplyId == uaa.getFollowVerifier())
        {
            havePower = true;
        }
        if(!havePower)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("该代理商不是你的代理商");
            return rm;
        }
        List<UAgentApplyFollowLog> list = agentDao.getAgentApplyLog(applyId);
        if(list != null && list.size() > 0)
        {
            List<AgentFollowLogVO> volist = new ArrayList<AgentFollowLogVO>();
            for(UAgentApplyFollowLog f : list)
            {
                AgentFollowLogVO vo = new AgentFollowLogVO();
                vo.setFollowContent(f.getRemaks());
                vo.setFollowTime(f.getCreateTime());
                vo.setFollowWeiId(f.getWeiId());
                volist.add(vo);
            }
            rm.setBasemodle(volist);
        }
        rm.setBasemodle(list);
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return rm;
    }

    @Override
    public PageResult<AgentApplyInfoVO> getManageAgentPageResult(long supplyId,AgentStatusEnum status,String keyword,Limit limit)
    {
        PageResult<USupplyChannel> sclist = agentDao.getSupplyChannelPageResult(supplyId,status,limit,SupplyChannelTypeEnum.Agent);
        PageResult<AgentApplyInfoVO> list = new PageResult<AgentApplyInfoVO>();
        if(sclist == null || sclist.getList() == null || sclist.getList().size() < 1)
        {
            return list;
        }
        list.setPageCount(sclist.getPageCount());
        list.setPageIndex(sclist.getPageIndex());
        list.setPageSize(sclist.getPageSize());
        list.setTotalPage(sclist.getTotalPage());
        list.setTotalCount(sclist.getTotalCount());
        List<USupplyChannel> uclist = sclist.getList();
        List<AgentApplyInfoVO> volist = new ArrayList<AgentApplyInfoVO>();
        for(USupplyChannel ua : uclist)
        {
            AgentApplyInfoVO vo = new AgentApplyInfoVO();
            // USupplyDemand sd = agentDao.getSupplyDemand(ua.getDemandId());//招商需求详情
            UProductAgent pa = agentDao.getProductAgent(ua.getChannelId());// 代理商信息表
            UAgenArea aaa = agentDao.getAgenArea(ua.getDemandId(),ua.getChannelId());// 代理区域
            if(pa == null)
            {
                pa = new UProductAgent();
            }
            if(aaa == null)
            {
                aaa = new UAgenArea();
            }
            vo.setAgentId(ua.getChannelId().longValue());
            vo.setWeiId(ua.getWeiId());
            vo.setShopName(commonService.getShopNameByWeiId(ua.getWeiId()));
            vo.setWeiPicture(commonService.getShopImageByWeiId(ua.getWeiId()));
            vo.setLinkname(pa.getLinkMan());
            vo.setPhone(pa.getPhone());
            vo.setMerchantWeiId(ua.getUpWeiId());// 上级供应商微店号
            vo.setDevelopmentWeiId(ua.getVerifier());// 发展人微店号
            vo.setAddress(pa.getAddress());// 代理商地址
            vo.setDemandId(ua.getDemandId());// 代理需求Id
            vo.setFollowVerifyWeiId(ua.getFollowVerifier());// 跟进人微店号
            vo.setAreaStr(regService.getNameByCode(aaa.getProvice() == null ? 0 : aaa.getProvice()) + "-" + regService.getNameByCode(aaa.getProvice() == null ? 0 : aaa.getCity()));// 代理区域说明
            vo.setTotalStore(agentDao.getGroundCountByArea(aaa.getCode() == null ? 0 : aaa.getCode(),ua.getDemandId()));// 落地店数量
            // vo.setLocationStr(regService.getNameByCode(pa.getProvice()) + "-" +
            // regService.getNameByCode(pa.getCity()) + "-" +
            // regService.getNameByCode(pa.getArea()));// 所在地区
            vo.setRewardAmount(ua.getReward());// 悬赏金额
            vo.setIsPayReward(ua.getPayedReward());// 否支付悬赏0-未支付1已支付
            // vo.setTotalStore(agentDao.getChannelCount(ua.getWeiId(),SupplyChannelTypeEnum.ground));//
            // 落地店数量
            vo.setApplyTime(ua.getApplyTime());
            vo.setVerifyTime(ua.getCancelTime());
            vo.setJoinType(ua.getJoinType());
            if(status == AgentStatusEnum.Cancel)
            {
                vo.setStatus((short) 3);
            }
            else
            {
                vo.setStatus(ua.getState());
            }
            vo.setRemarks(ua.getCancelRemark());
            volist.add(vo);
        }
        list.setList(volist);
        return list;
    }

    /**
     * 获取悬赏金额
     * 
     * @param did
     * @param code
     * @return
     */
    public double getAgentReward(Integer did,Integer code)
    {
        USupplyDemandArea area = agentDao.getSupplyDemandArea(did,code);
        if(area == null)
        {
            return 0D;
        }
        UDemandRequired dr = agentDao.getDemandRequired(area.getRequiredId());
        if(dr == null)
        {
            return 0D;
        }
        return dr.getAgentReward();
    }

    @Override
    public PageResult<AgentApplyInfoVO> getSuperiorAgentPageResult(long weiid,String key,Limit limit)
    {
        PageResult<USupplyChannel> sclist = agentDao.getSuperiorAgentPageResult(weiid,limit);
        PageResult<AgentApplyInfoVO> list = new PageResult<AgentApplyInfoVO>();
        if(sclist == null || sclist.getList() == null || sclist.getList().size() < 1)
        {
            return list;
        }
        list.setPageCount(sclist.getPageCount());
        list.setPageIndex(sclist.getPageIndex());
        list.setPageSize(sclist.getPageSize());
        List<USupplyChannel> uclist = sclist.getList();
        List<AgentApplyInfoVO> volist = new ArrayList<AgentApplyInfoVO>();
        for(USupplyChannel ua : uclist)
        {
            AgentApplyInfoVO vo = new AgentApplyInfoVO();
            // USupplyDemand sd = agentDao.getSupplyDemand(ua.getDemandId());//招商需求详情
            UProductAgent pa = agentDao.getProductAgent(ua.getChannelId());// 代理商信息表
            UAgenArea aaa = agentDao.getAgenArea(ua.getDemandId(),ua.getChannelId());// 代理区域
            vo.setAgentId(ua.getWeiId());
            vo.setWeiId(ua.getWeiId());
            vo.setShopName(commonService.getShopNameByWeiId(ua.getWeiId()));
            vo.setWeiPicture(commonService.getShopImageByWeiId(ua.getWeiId()));
            vo.setLinkname(pa.getLinkMan());
            vo.setPhone(pa.getPhone());
            vo.setMerchantWeiId(ua.getUpWeiId());// 上级供应商微店号
            vo.setDevelopmentWeiId(ua.getVerifier());// 发展人微店号
            vo.setAddress(pa.getAddress());// 代理商地址
            vo.setDemandId(ua.getDemandId());// 代理需求Id
            vo.setAreaStr(regService.getNameByCode(aaa.getProvice() == null ? 0 : aaa.getProvice()) + "-" + regService.getNameByCode(aaa.getCity() == null ? 0 : aaa.getCity()));// 代理区域说明
            vo.setLocationStr(regService.getNameByCode(pa.getProvice() == null ? 0 : pa.getProvice()) + "-" + regService.getNameByCode(pa.getCity() == null ? 0 : pa.getCity())
                    + "-" + regService.getNameByCode(pa.getArea() == null ? 0 : pa.getArea()));// 所在地区
            vo.setRewardAmount(ua.getReward());// 悬赏金额
            vo.setIsPayReward(ua.getPayedReward());// 否支付悬赏0-未支付1已支付
            // vo.setTotalStore(agentDao.getChannelCount(ua.getWeiId(),SupplyChannelTypeEnum.ground));//
            // 落地店数量
            vo.setTotalStore(agentDao.getGroundCountByArea(aaa.getCode(),ua.getDemandId()));// 落地店数量
            vo.setApplyTime(ua.getApplyTime());
            vo.setStatus(ua.getState());// 这个不知道要不要转换一下????
            volist.add(vo);
        }
        list.setList(volist);
        return list;
    }

    @Override
    public ReturnModel getVerifierInfo(Long weiid)
    {
        ReturnModel rm = new ReturnModel();
        if(weiid == null || weiid < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("参数错误");
            return rm;
        }
        UVerifier ver = loginDao.getUVerifier(weiid);// 获取跟进人微店信息
        AgentVerifierInfoVO vo = new AgentVerifierInfoVO();
        if(ver != null)
        {
            vo.setPhone(ver.getPhone());
            vo.setWeiId(weiid);
            vo.setWeiName(ver.getName());
            vo.setImage(ImgDomain.GetFullImgUrl(ver.getFacePic()));
        }
        else
        {
            UPlatformSupplyer platform = loginDao.getPlatform(weiid);
            if(platform != null)
            {
                vo.setPhone(platform.getMobilePhone());
                vo.setWeiId(weiid);
                vo.setWeiName(platform.getLinkMan());
                vo.setImage(platform.getLogo());
            }
            else
            {
                UBrandSupplyer brand = loginDao.getBrand(weiid);
                if(brand != null)
                {
                    vo.setPhone(brand.getMobilePhone());
                    vo.setWeiId(weiid);
                    vo.setWeiName(brand.getLinkMan());
                    vo.setImage(brand.getLogo());
                }
            }
        }
        rm.setBasemodle(vo);
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return rm;
    }

    @Override
    public ReturnModel getApplyResult(long weiId,Integer applyId)
    {
        ReturnModel rm = new ReturnModel();
        if(weiId < 1 || applyId == null || applyId < 1)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("参数错误");
            return rm;
        }
        UAgentApply uaa = agentDao.getAgentApply(applyId);
        if(uaa == null)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("申请不存在");
            return rm;
        }
        if(uaa.getSupplyId() != weiId && uaa.getWeiId() != weiId)
        {
            rm.setStatu(ReturnStatus.ParamError);
            rm.setStatusreson("该申请不属于你");
            return rm;
        }
        ApplyResultVO vo = new ApplyResultVO();
        vo.setAgentId(uaa.getApplyId());
        vo.setAgentWeiId(uaa.getWeiId());
        vo.setShopName(commonService.getShopNameByWeiId(uaa.getWeiId()));
        vo.setApplyTime(uaa.getCreateTime());
        vo.setMerchantWeiId(uaa.getSupplyId());
        vo.setDevelopmentWeiId(uaa.getVerifier());
        vo.setStatus(uaa.getState());
        vo.setStatusReson(uaa.getRemarks());
        vo.setFollowVerifier(uaa.getFollowVerifier());
        UAgentApplyArea applyArea = agentDao.getAgentApplyAreas(uaa.getApplyId());
        if(applyArea == null)
        {
            rm.setStatu(ReturnStatus.DataError);
            rm.setStatusreson("申请的区域为空");
            return rm;
        }
        Integer code = applyArea.getCode();// 申请区域
        // 判断这个区域是否有代理商
        List<UAgenArea> agentList = agentDao.getUAgenAreasByDemandId(uaa.getDemandId());
        vo.setCurrentAreaHasAgent((short) 0);
        if(agentList != null && agentList.size() > 0)
        {
            for(UAgenArea area : agentList)
            {
                if(area.getCode().intValue() == code)
                {
                    vo.setCurrentAreaHasAgent((short) 1);
                }
            }
        }
        // 该地区是否在招商
        vo.setCurrentAreaHasRecruitment((short) 0);
        List<USupplyDemandArea> sdalist = agentDao.getSupplyDemandAreaList(uaa.getDemandId());
        if(sdalist != null && sdalist.size() > 0)
        {
            for(USupplyDemandArea sda : sdalist)
            {
                if(sda.getCode().intValue() == code)
                {
                    vo.setCurrentAreaHasRecruitment((short) 1);
                }
            }
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        rm.setBasemodle(vo);
        return rm;
    }

    @Override
    public ReturnModel getSupplyInfo(long supply)
    {
        ReturnModel rm = new ReturnModel();
        UBrandSupplyer sb = agentDao.getBrandSupplyer(supply);
        UShopInfo info = loginDao.getUShopInfo(supply);
        USupplyer us = loginDao.getUSupplyer(supply);
        MerchantVO vo = new MerchantVO();
        if(sb != null)
        {
            vo.setLinkName(sb.getLinkMan());
            vo.setMerchantName(us != null ? us.getCompanyName() : sb.getSupplyName());
            vo.setMerchantWeiId(sb.getWeiId());
            vo.setPhone(us != null ? us.getMobilePhone() : sb.getMobilePhone());
            if(info != null && info.getShopImg() != null)
                vo.setShopImg(ImgDomain.GetFullImgUrl(info.getShopImg()));
            else
                vo.setShopImg("http://base1.okimgs.com/images/logo.jpg");
            vo.setDetails(sb.getDetails());
            vo.setBond(sb.getBond());
            vo.setShopTime(sb.getCreateTime());
            if(us != null && us.getServiceQQ() != null && us.getServiceQQ().split("\\|").length > 0)
            {
                vo.setQq(us.getServiceQQ().split("\\|")[0]);
            }
            else
            {
                vo.setQq(sb.getServiceQq());
            }
            vo.setAreaAddres(regService.getNameByCode(sb.getProvice() == null ? 0 : sb.getProvice()) + regService.getNameByCode(sb.getCity() == null ? 0 : sb.getCity()));
        }
        else
        {
            UPlatformSupplyer ps = agentDao.getUPlatformSupplyer(supply);
            if(ps != null)
            {
                vo.setLinkName(ps.getLinkMan());
                vo.setMerchantName(us != null ? us.getCompanyName() : ps.getSupplyName());
                vo.setMerchantWeiId(ps.getWeiId());
                vo.setPhone(us != null ? us.getMobilePhone() : ps.getMobilePhone());
                if(info != null && info.getShopImg() != null)
                    vo.setShopImg(ImgDomain.GetFullImgUrl(info.getShopImg()));
                else
                    vo.setShopImg("http://base1.okimgs.com/images/logo.jpg");
                vo.setDetails(ps.getDetails());
                vo.setBond(ps.getBond());
                vo.setShopTime(ps.getCreateTime());

                if(us != null && us.getServiceQQ() != null && us.getServiceQQ().split("\\|").length > 0)
                {
                    vo.setQq(us.getServiceQQ().split("\\|")[0]);
                }
                else
                {
                    vo.setQq(ps.getServiceQq());
                }
                vo.setAreaAddres(regService.getNameByCode(ps.getProvice() == null ? 0 : ps.getProvice()) + regService.getNameByCode(ps.getCity() == null ? 0 : ps.getCity()));
            }
            else
            {
                rm.setStatu(ReturnStatus.DataError);
                rm.setStatusreson("供应商不存在");
                return rm;
            }
        }
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        rm.setBasemodle(vo);
        return rm;
    }

    @Override
    public int getApplyAgentByStatus(long supplyId,ApplyAgentStatusEnum status)
    {
        return agentDao.getApplyAgentByStatus(supplyId,status);
    }

    @Override
    public int getAgentByStatus(long supplyId,SupplyChannelTypeEnum type,AgentStatusEnum status)
    {
        return agentDao.getAgentByStatus(supplyId,type,status);
    }

    @Override
    public boolean checkArea(Integer demandId,Integer area)
    {
        List<USupplyChannel> list = agentDao.getSupplyChannelList(demandId,SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);
        if(list == null || list.size() < 1)
        {
            return false;
        }
        for(USupplyChannel a : list)
        {
            UAgenArea uaa = agentDao.getAgenArea(demandId,a.getChannelId());
            if(uaa != null)
            {
                if(uaa.getCode().intValue() == area)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ReturnModel getchannelCountReward(long supplyID)
    {
        ReturnModel rm = new ReturnModel();
        ChannelCountRewardVO vo = new ChannelCountRewardVO();
        vo.setNoPayAgentReward(agentDao.getRewardCount(supplyID,SupplyChannelTypeEnum.Agent,PayedRewardStatuus.No));
        vo.setNoPayStoreReward(agentDao.getRewardCount(supplyID,SupplyChannelTypeEnum.ground,PayedRewardStatuus.No));
        // vo.setPayAgentReward(agentDao.getRewardCount(supplyID,SupplyChannelTypeEnum.Agent,PayedRewardStatuus.Yes));
        // vo.setPayStoreReward(agentDao.getRewardCount(supplyID,SupplyChannelTypeEnum.ground,PayedRewardStatuus.Yes));
        rm.setBasemodle(vo);
        rm.setStatu(ReturnStatus.Success);
        rm.setStatusreson("成功");
        return rm;
    }

    /**
     * 落地店数量
     */
    @Override
    public int getGroundCount(int areaID)
    {
        return 0;
    }

    @Override
    public int deleteAgent(long supplyID,Integer[] ids)
    {
        List<USupplyChannel> list = agentDao.getSupplyChannelListByIDS(ids);
        if(list != null && list.size() > 0)
        {
            for(USupplyChannel sc : list)
            {
                if(supplyID == sc.getSupplyId() && sc.getState() == Short.parseShort(AgentStatusEnum.Cancel.toString()))
                {
                    sc.setState(Short.parseShort(AgentStatusEnum.Delete.toString()));
                }
            }
        }
        else
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public int deleteApplyAgent(long supplyID,Integer[] ids)
    {
        List<UAgentApply> list = agentDao.getAgentApplyListByIDS(ids);
        if(list != null && list.size() > 0)
        {
            for(UAgentApply sc : list)
            {
                if(supplyID == sc.getSupplyId() && sc.getState() == Short.parseShort(ApplyAgentStatusEnum.NoPass.toString()))
                {
                    sc.setState(Short.parseShort(ApplyAgentStatusEnum.Delete.toString()));
                }
            }
        }
        else
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public List<UBatchVerifierRegion> getBatchVerifierRegionListByCode(Integer code)
    {
        return agentDao.getBatchVerifierRegionListByCode(code);
    }

    @Override
    public boolean checkVerfierByArea(long verId,int code)
    {
        return agentDao.checkVerfierByArea(verId,code);
    }

    @Override
    public List<TRegional> getRegionalByCode(Integer code,Integer demandID)
    {
        if(code == null || code < 1 || demandID == null || demandID < 1)
        {
            return null;
        }
        List<USupplyDemandArea> areaList = agentDao.getSupplyDemandAreaList(demandID);
        if(areaList == null || areaList.size() < 1)
        {
            return null;
        }
        List<TRegional> list = new ArrayList<TRegional>();
        for(USupplyDemandArea area : areaList)
        {
            if(code.intValue() == area.getProvince())
            {
                TRegional reg = agentDao.getTRegional(area.getCode());
                list.add(reg);
            }
        }
        return list;
    }

    @Override
    public List<TRegional> getRegionalByDemenId(int demandID)
    {
        List<USupplyDemandArea> areaList = agentDao.getSupplyDemandAreaList(demandID);
        if(areaList == null || areaList.size() < 1)
        {
            return null;
        }
        List<TRegional> list = new ArrayList<TRegional>();
        List<Integer> regId = new ArrayList<Integer>();
        for(USupplyDemandArea area : areaList)
        {
            if(!regId.contains(area.getProvince()))
            {
                regId.add(area.getProvince());
                TRegional reg = agentDao.getTRegional(area.getProvince());
                list.add(reg);
            }
        }
        return list;
    }

    @Override
    public UAgentApply getAgentApply(Integer applyID)
    {
        if(applyID == null || applyID < 1)
        {
            return null;
        }
        return agentDao.getAgentApply(applyID);
    }

    @Override
    public ReturnModel getSupplyInfo(long weiID,long supply)
    {
        ReturnModel rm = getSupplyInfo(supply);// 获取基本信息
        if(rm.getStatu() != ReturnStatus.Success)
        {
            return rm;
        }
        MerchantVO vo = (MerchantVO) rm.getBasemodle();
        if(vo != null)
        {
            if(agentDao.checkAttention(weiID,supply))
            {
                vo.setAttention(1);// 两者是关注
            }
            else
            {
                vo.setAttention(0); // 两者没有关注
            }
            return rm;
        }
        else
        {
            return rm;
        }
    }

    @Override
    public Long getSourceCommissionWeiIdLong(Integer demenID,Long sourceID)
    {
        if(sourceID == null || sourceID < 1)
        {
            return getSystemJxVerify();// 有问题给系统认证员
        }
        // 认证员
        UYunVerifier ver = loginDao.getUYunVerifier(sourceID);
        if(ver != null && ver.getType() != null)
        {
            if(ver.getType() == Short.parseShort(YunVerifyTypeEnum.jxVerify.toString()) || ver.getType() == Short.parseShort(YunVerifyTypeEnum.zsVerify.toString()))
            {
                return sourceID;// 如果是见习或正式，分配给他自己
            }
        }
        if(demenID == null || demenID < 1)
        {
            return getSystemJxVerify();// 有问题给系统认证员
        }
        // 代理商
        List<USupplyChannel> sclist = agentDao.getSupplyChannelLIstByWeiID(sourceID,SupplyChannelTypeEnum.Agent,AgentStatusEnum.Normal);
        if(sclist != null && sclist.size() > 0)
        {
            for(USupplyChannel uSupplyChannel : sclist)
            {
                if(demenID.intValue() == uSupplyChannel.getDemandId())
                {
                    return sourceID;
                }
            }
        }
        // 其他情况都给系统认证员
        return getSystemJxVerify();// 有问题给系统认证员
    }

}
