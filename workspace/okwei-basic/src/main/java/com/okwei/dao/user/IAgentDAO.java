package com.okwei.dao.user;

import java.util.List;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UAgentApplyArea;
import com.okwei.bean.domain.UAgentApplyFollowLog;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UDemandRequired;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.PayedRewardStatuus;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.user.AgentApplyInfoVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;

/**
 * 代理商查询
 */
public interface IAgentDAO
{
    /**
     * 根据代理商申请ID获取代理商申请实体
     * 
     * @param applyId
     *            代理商申请ID
     * @return
     */
    UAgentApply getAgentApply(int applyId);

    /**
     * 根据代理商ID获取代理商实体
     * 
     * @param parId
     *            代理商ID
     * @return
     */
    UProductAgent getProductAgent(int channelId);

    /**
     * 获取代理商渠道
     * 
     * @param channelId
     * @return
     */
    USupplyChannel getSupplyChannel(int channelId);

    /**
     * 根据申请列表获取渠道
     * 
     * @param applyId
     * @return
     */
    USupplyChannel getSupplyChannelByApplyId(int applyId);

    /**
     * 根据招商需求获取所有的正常的渠道
     * 
     * @param demandId
     * @return
     */
    List<USupplyChannel> getSupplyChannelList(int demandId,SupplyChannelTypeEnum type,AgentStatusEnum status);

    /**
     * 查看自己有多少是多少个代理商
     * @param weiID
     * @param type
     * @param status
     * @return
     */
    List<USupplyChannel> getSupplyChannelLIstByWeiID(long weiID,SupplyChannelTypeEnum type,AgentStatusEnum status);
    /**
     * 查看自己有多少是多少个代理商
     * @param weiID
     * @param type
     * @param status
     * @return
     */
    int getSupplyChannelLIstByWeiIDCount(long weiID,SupplyChannelTypeEnum type,AgentStatusEnum status);
    
    /**
     * 获取用户成为代理商的列表
     * 
     * @param weiid
     *            用户微店号
     * @param status
     *            状态
     * @return
     */
    List<UProductAgent> getProductAgentsList(long weiid,short status);

    List<UProductAgent> getProductAgentsList(long weiid);

    /**
     * 招商需求Id获取代理商列表
     * 
     * @param demandId
     *            招商需求ID
     * @return
     */
    List<UProductAgent> getProductAgentsListByDemandId(int demandId);

    /**
     * 根据落地店ID查询落地店实体
     * 
     * @param shopId
     *            落地店ID
     * @return
     */
    UProductShop getProductShop(int shopId);

    /**
     * 获取用户成为的落地店的列表
     * 
     * @param weiid
     *            用户微店号
     * @param status
     *            状态
     * @return
     */
    List<UProductShop> getProductShopsList(long weiid,short status);

    List<UProductShop> getProductShopsList(long weiid);

    /**
     * 根据招商需求ID获取招商需求实体
     * 
     * @param demandId招商需求ID
     * @return
     */
    USupplyDemand getSupplyDemand(int demandId);
  
    /**
     * 查询平台号/品牌号下面的招商需求
     * 
     * @param supplyID
     * @param state
     * @return
     */
    List<USupplyDemand> getSupplyDemandListBySupplyID(long supplyID,DemandStateEnum state);

    List<USupplyDemand> getSupplyDemandListBySupplyID(long supplyID);

    /**
     * 代理商代理的区域列表
     * 
     * @param parId
     *            代理商ID
     * @return
     */
    List<UAgenArea> getUAgenAreasByDemandId(int demandId);

    List<UAgenArea> getUAgenAreasByChannelIds(List<Integer> channelIds);

    UAgenArea getAgenArea(int demandId,int channelld);

    /**
     * 根据招商需求ID列表获取产品数量
     * 
     * @param demanIdList
     *            招商需求ID列表
     * @return
     */
    int getDemandProductCount(List<Integer> demanIdList);

    /**
     * 根据招商需求ID列表获取招商需求列表
     * 
     * @param demanIdList
     *            招商需求ID列表
     * @return
     */
    List<USupplyDemand> getsupSupplyDemandsByIds(List<Integer> demanIdList);

    /**
     * 获取用户申请列表
     * 
     * @param weiId
     * @param demandId
     * @return
     */
    List<UAgentApply> getAgentApply(long weiId,int demandId);

    /**
     * 根据用户申请ID获取申请的区域
     * 
     * @param applyIds
     *            申请id集合
     * @return
     */
    List<UAgentApplyArea> getAgentApplyAreas(List<Integer> applyIds);

    UAgentApplyArea getAgentApplyAreas(Integer applyid);

    /**
     * 添加代理申请
     * 
     * @param agent
     *            代理申请实体
     * @return
     */
    boolean insertUAgentApply(UAgentApply agent);

    /**
     * 添加代理申请区域
     * 
     * @param area
     *            代理申请区域实体
     * @return
     */
    boolean insertUAgentApplyArea(UAgentApplyArea area);

    /**
     * 添加代理商
     * 
     * @param agent
     * @return
     */
    Integer insertUProductAgent(UProductAgent agent);

    /**
     * 添加代理商代理区域
     * 
     * @param area
     * @return
     */
    boolean insertUAgentArea(UAgenArea area);

    /**
     * 根据区域代码获取区域
     * 
     * @param code
     * @return
     */
    TRegional getTRegional(Integer code);

    /**
     * 根据区域代码获取上级区域
     * 
     * @param code
     * @return
     */
    TRegional getTRegionalParent(Integer code);

    /**
     * 分页获取自己提交的代理商列表
     * 
     * @param weiid
     *            微店号
     * @param status
     *            状态
     * @param limit
     *            分页
     * @return
     */
    PageResult<UAgentApply> getAgentApplyListByPage(long weiid,short status,Limit limit);

    /**
     * 分页获取供应商提交的代理商列表
     * 
     * @param supplyId
     * @param status
     * @param limit
     * @return
     */
    PageResult<UAgentApply> getAgentApplyListBySupplyId(long supplyId,short status,Limit limit);
    

    /**
     * 分页获取自己提交的供应商提交的代理商申请列表
     * 
     * @param supplyId
     * @param status
     * @param limit
     * @return
     */
    PageResult<UAgentApply> getAgentApplyListByWeiID(long weiid,short status,Limit limit);

    /**
     * 获取代理商/落地店列表
     * 
     * @param supplyId
     *            供应商微店号
     * @param status
     *            状态
     * @param limit
     *            分页
     * @param type
     *            类型
     * @return
     */
    PageResult<USupplyChannel> getSupplyChannelPageResult(long supplyId,AgentStatusEnum status,Limit limit,SupplyChannelTypeEnum type);

    PageResult<USupplyChannel> getSuperiorAgentPageResult(long weiid,Limit limit);

    /**
     * 添加代理商渠道
     * 
     * @param sc
     * @return
     */
    Integer insertUSupplyChannel(USupplyChannel sc);

    /**
     * 查询渠道数量
     * 
     * @param weiid
     * @param channelType
     * @return
     */
    int getChannelCount(long supplyId,SupplyChannelTypeEnum channelType);

    /**
     * 申请审核记录
     * 
     * @param applyId
     * @return
     */
    List<UAgentApplyFollowLog> getAgentApplyLog(Integer applyId);

    /**
     * 招商需求地区条件
     * 
     * @param requiredID
     * @return
     */
    UDemandRequired getDemandRequired(Integer requiredID);

    /**
     * 招商需求地理区域
     * 
     * @param demandAreaID
     * @return
     */
    USupplyDemandArea getSupplyDemandArea(Integer demandAreaID);

    USupplyDemandArea getSupplyDemandArea(Integer demandID,Integer code);

    List<USupplyDemandArea> getSupplyDemandAreaList(Integer demandID);

    /**
     * 获取品牌号
     * 
     * @param weiid
     * @return
     */
    UBrandSupplyer getBrandSupplyer(long weiid);

    /**
     * 获取平台号
     * 
     * @param weiid
     * @return
     */
    UPlatformSupplyer getUPlatformSupplyer(long weiid);

    /**
     * 根据招商需求获取产品数量
     * 
     * @param DemandID
     * @return
     */
    int getProductCountByDemandID(Integer DemandID);

    /**
     * 查询供应商下面有几个没有支付悬赏金额的代理商
     * 
     * @param supplyId
     * @return
     */
    int getPayedRewardCount(long supplyId);

    /**
     * 判断代理商是否支付悬赏
     * 
     * @param weiid
     * @param demandId
     * @return
     */
    boolean isPayedRewardBySupplyId(int channelId);

    /**
     * 供应商下面申请代理商不同状态的数量
     * 
     * @param supplyId
     * @param status
     * @return
     */
    int getApplyAgentByStatus(long supplyId,ApplyAgentStatusEnum status);

    /**
     * 供应商下面代理商（已通过），不同状态的数量
     * 
     * @param supplyId
     * @param type
     * @param status
     * @return
     */
    int getAgentByStatus(long supplyId,SupplyChannelTypeEnum type,AgentStatusEnum status);
    
    /**
     * 查询供应商支付的数量
     * @param supplyID 供应商ID
     * @param type 代理商还是落地店
     * @param status 支付还是未支付
     * @return
     */
    int getRewardCount(long supplyID,SupplyChannelTypeEnum type,PayedRewardStatuus status);
    /**
     * 根据需求，区域，查询数量
     * @param areaID 区域ID
     * @param demandID 需求ID
     * @return
     */
    int getGroundCountByArea(int areaID,int demandID);
    /**
     * 根据数组获取渠道列表
     * @param ids
     * @return
     */
    List<USupplyChannel> getSupplyChannelListByIDS(Integer[] ids);
    /**
     * 根据申请ID数组获取申请代理商列表
     * @param ids
     * @return
     */
    List<UAgentApply> getAgentApplyListByIDS(Integer[] ids);


    /**
     * 根据区域，获取区域的认证员
     * 
     * @param code
     * @return
     */
    List<UBatchVerifierRegion> getBatchVerifierRegionListByCode(Integer code);

    /**
     * 判断认证员是否该区域
     * 
     * @param verId
     * @param code
     * @return
     */
    boolean checkVerfierByArea(long verId,int code);
  
    /**
     * 判断微店号和供应商是否关注关系
     * @param weiID
     * @param supplyID
     * @return
     */
    boolean checkAttention(long weiID,long supplyID);
    
    UWeiSeller getUser(Long weiId);
}
