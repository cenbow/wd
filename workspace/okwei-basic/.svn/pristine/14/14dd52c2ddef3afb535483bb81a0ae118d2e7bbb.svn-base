package com.okwei.service.user;

import java.util.List;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.bean.dto.user.ApplyAgentDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ApplyAgentStatusEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.user.AgentApplyInfoVO;
import com.okwei.bean.vo.user.UProductAgentVO;
import com.okwei.bean.vo.user.UProductShopVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;

/**
 * 平台号，品牌号 代理商功能
 * 
 * @author Administrator
 *
 */
public interface IAgentService
{
    /**
     * 判断申请的地址是否在招商申请区域
     * 
     * @param demandID
     *            招商需求ID
     * @param area
     *            申请（市）的区域代码
     * @return
     */
    ReturnModel judgeAreaExist(long weiId,int demandID,Integer area);

    /**
     * 获取省市区
     * @param code 区域
     * @return
     */
    String getRegName(Integer code);
    
    /**
     * 用户申请成为代理商
     * 
     * @param dto
     *            申请对象数据
     * @return
     */
    ReturnModel applyAgent(ApplyAgentDTO dto);

    /**
     * 平台号/品牌号查询旗下代理商列表（分页。搜索）
     * 
     * @param weiid
     *            平台号/品牌号的微店号
     * @param status
     *            状态
     * @param key
     *            搜索内容
     * @param limit
     *            分页数据
     * @return
     */
    PageResult<UProductAgentVO> getUProPageResult(long weiid,int status,String key,Limit limit);

    /**
     * 平台号/品牌号管理下游代理商
     * 
     * @param supplierId
     *            平台号的微店号
     * @param status
     *            审核标识
     * @param statusReson
     *            不通过原因
     * @param weiId
     *            用户申请代理商的ID
     * @return
     */
    ReturnModel applyAgentManage(Long supplierId,Short status,String statusReson,Integer agentId);

    /**
     * 平台号/品牌号审核下游代理商
     * 
     * @param supplierId
     *            平台号的微店号
     * @param status
     *            审核标识
     * @param statusReson
     *            不通过原因
     * @param weiId
     *            用户申请代理商的ID
     * @return
     */
    ReturnModel auditAgentStatus(Long supplierId,Short status,String statusReson,Integer agentId);

    /**
     * 平台号/品牌号查询代理商申请的详细信息
     * 
     * @param weiid
     *            平台号/品牌号的微店号
     * @param applyID
     *            用户申请代理商的ID
     * @return
     */
    AgentApplyInfoVO getAgentApplyInfoVO(long weiid,int applyID);

    /**
     * 获取用户提交的代理商申请列表（分页。搜索）
     * 
     * @param weiid
     *            用户微店号
     * @param status
     *            申请的状态
     * @param key
     *            搜索的条件
     * @param limit
     *            分页条件
     * @return
     */
    PageResult<AgentApplyInfoVO> getAgentApPageResult(long weiid,short status,String key,Limit limit);

    PageResult<AgentApplyInfoVO> getAgentApPageResultBySupply(long supplyID,short status,String key,Limit limit);

    PageResult<AgentApplyInfoVO> getManageAgentPageResult(long supplyId,AgentStatusEnum status,String keyword,Limit limit);

    PageResult<AgentApplyInfoVO> getSuperiorAgentPageResult(long weiid,String key,Limit limit);

    /**
     * 平台号/品牌号审核下游落地店列表
     * 
     * @param weiid
     *            平台号/品牌号的微店号
     * @param status
     *            申请的状态
     * @param key
     *            搜索的条件
     * @param limit
     *            分页条件
     * @return
     */
    PageResult<UProductShopVO> getUProductShop(long weiid,int status,String key,Limit limit);

    /**
     * 平台号/品牌号查看下游落地店详情
     * 
     * @param weiid
     *            平台号/品牌号的微店号
     * @param shopID
     *            落地店ID
     * @return
     */
    UProductShopVO getProductShopVO(long weiid,int shopID);

    /**
     * 获取代理商 详情(审核的时候的详情)
     * 
     * @param supplyId
     *            供应商ID
     * @param agentId
     *            代理商的代理ID
     * @return
     */
    ReturnModel getAgentDetailVO(long supplyId,Integer agentId);

    /**
     * 获取代理商 详情
     * 
     * @param supplyId
     *            供应商ID
     * @param agentId
     *            代理商的代理ID
     * @return
     */
    ReturnModel getAgentDetailVOByPth(long supplyId,Integer agentId);

    /**
     * 获取代理商跟进记录
     * 
     * @param applyId
     * @return
     */
    ReturnModel getAgentApplyFollowLog(long supplyId,Integer applyId);

    /**
     * 获取代理商跟进认证员信息
     * 
     * @param supply
     * @param applyId
     * @return
     */
    ReturnModel getVerifierInfo(Long weiid);

    /**
     * 申请结果
     * 
     * @param weiId
     *            微店号
     * @param applyId
     *            申请ID
     * @return
     */
    ReturnModel getApplyResult(long weiId,Integer applyId);

    /**
     * 获取供应商联系信息
     * 
     * @param supply
     * @return
     */
    ReturnModel getSupplyInfo(long supply);

    /**
     * 获取供应商联系信息(包含是否关注)
     * 
     * @param supply
     * @return
     */
    ReturnModel getSupplyInfo(long weiID,long supply);

    /**
     * 获取申请代理商的各种数量
     * 
     * @param supplyId
     *            供应商的微店号
     * @param status
     *            状态枚举
     * @return
     */
    int getApplyAgentByStatus(long supplyId,ApplyAgentStatusEnum status);

    /**
     * 获取代理商不同状态的数量
     * 
     * @param supplyId
     *            供应商的微店号
     * @param status
     *            状态枚举
     * @return
     */
    int getAgentByStatus(long supplyId,SupplyChannelTypeEnum type,AgentStatusEnum status);

    /**
     * 判断区域是有代理商
     * 
     * @param demandId
     * @param area
     * @return
     */
    boolean checkArea(Integer demandId,Integer area);

    /**
     * 获取未支付悬赏笔数
     * 
     * @param supplyID
     *            供应商微店号
     * @return
     */
    ReturnModel getchannelCountReward(long supplyID);

    /**
     * 查询供应商落地店
     * 
     * @param areaID
     *            区域ID
     * @return
     */
    int getGroundCount(int areaID);

    /**
     * 删除代理商
     * 
     * @param supplyID
     * @param ids
     * @return
     */
    int deleteAgent(long supplyID,Integer[] ids);

    /**
     * 删除申请代理商
     * 
     * @param supplyID
     * @param ids
     * @return
     */
    int deleteApplyAgent(long supplyID,Integer[] ids);

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
     * 获取下级所有的区域
     * 
     * @param code
     * @return
     */
    List<TRegional> getRegionalByCode(Integer code,Integer demandID);

    /**
     * 根据招商需求获取所有的省
     * 
     * @param demenID
     * @return
     */
    List<TRegional> getRegionalByDemenId(int demandID);

    /**
     * 获取代理商申请
     * 
     * @param applyID
     * @return
     */
    UAgentApply getAgentApply(Integer applyID);
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
    Long getSourceWeiID(Long sourceID,int demandID,int code,long weiID);
    /**
     * 根据区域获取跟进正式微店号
     * 
     * @param code
     * @return
     */
    Long getVerifyWeiID(Long sourceID,Integer code);

    /**
     * 获取代理商来源分配金额
     * @param demenID 招商需求
     * @param sourceID 来源微店号
     * @return
     */
    Long getSourceCommissionWeiIdLong(Integer demenID,Long sourceID);
}
