package com.okwei.dao.user;

import java.util.List;

import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApplyArea;
import com.okwei.bean.domain.UAgentApplyFollowLog;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.MyAgentOrProductShopListDTO;
import com.okwei.common.Limit;
import com.okwei.dao.IBaseDAO;

public interface IBasicAgentOrProductShopDAO extends IBaseDAO {
	/**
	 * 获得我(认证员)发展的代理商
	 * @param weiId
	 * @param limit
	 * @return
	 */
	List<Object[]> getMyDevelopAgent(Limit limit,MyAgentOrProductShopListDTO param);
	/**
	 * 获得我发展的代理商的详情
	 * @param parId
	 * @return
	 */
	List<Object[]> getMyDevelopAgentDetail(Integer parId);
	/**
	 * 获得我(认证员)发展的代理商的总记录数
	 * @param param
	 * @return
	 */
	long getMyDevelopAgentTotalAmount(MyAgentOrProductShopListDTO param);
	/**
	 * 获得我(认证员)发展的落地店(PC版)
	 * @param weiId
	 * @param limit
	 * @return
	 */
	List<Object[]> getMyDevelopProductShop(Limit limit,MyAgentOrProductShopListDTO param);
	/**
	 * 获得我(认证员)发展的落地店的总记录数(PC版)
	 * @param param
	 * @return
	 */
	long getMyDevelopProductShopTotalAmount(MyAgentOrProductShopListDTO param);
	/**
	 * 获得代理商的代理区域
	 * @param parid
	 * @return
	 */
	List<UAgenArea> getUAgenAreaList(Integer parid);
	
	/**
	 * 获得代理商的代理区域
	 * @param parid
	 * @return
	 */
	List<UAgentApplyArea> getUAgentApplyAreaList(Integer parid);
	/**
	 * 获得申请代理商的跟进记录
	 * @param applyId
	 * @return
	 */
	List<UAgentApplyFollowLog> getUAgentApplyFollowLogList(Integer applyId);
	 /**
	  * 获得(平台号)的落地店列表
	  * @param supplyWeiId
	  * @param state
	  * @return
	  */
	List<Object[]> getProductShopByPlatForm(Long supplyWeiId,Short state,Limit limit,String beginTime,String endTime);
	/**
	 * 获得(平台号)的落地店列表的总记录数
	 * @param supplyWeiId
	 * @param state
	 * @return
	 */
	long getProductShopByPlatFormTotalAmount(Long supplyWeiId,Short state,String beginTime,String endTime);
	/**
	 * 获得我(认证员)发展的落地店(App接口版)
	 * @param weiId
	 * @param limit
	 * @return
	 */
	List<Object[]> getMyDevelopProductShopApp(Limit limit,Long verifierWeiId,Short state,String beginTime,String endTime);
	/**
	 * 获取认证员发展的落地点列表
	 * @param limit
	 * @param verifierWeiId
	 * @param state
	 * @param demandId
	 * @return
	 */
	List<Object[]> getMyDevelopProductShopApp(Limit limit,Long verifierWeiId,Short state,Integer demandId,Integer areaId);
	/**
	 * 获得(认证员)的落地店列表的总记录数(App接口版)
	 * @param supplyWeiId
	 * @param state
	 * @return
	 */
	long getProductShopByVerifierTotalAmount(Long supplyWeiId,Short state,String beginTime,String endTime);
	/**
	 * 获取认证员发展的落地点总数
	 * @param supplyWeiId
	 * @param state
	 * @param demandId
	 * @return
	 */
	long getProductShopByVerifierTotalAmount(Long supplyWeiId,Short state,Integer demandId,Integer areaId);
	/**
	 * 取消或恢复落地店
	 * @param state
	 * @param shopId
	 */
	int cancelOrRecoverProductShop(Short state,Integer shopId,String cancelReason);
	/**
	 * 获得对应微店号的用户列表
	 * @param weiIdList
	 * @return
	 */
	List<UWeiSeller> getUWeiSellerList(List<Long> weiIdList);
	/**
	 * 获得对应代理需求的列表
	 * @param demandIdList
	 * @return
	 */
	List<USupplyDemand> getUSupplyDemandList(List<Integer> demandIdList);
	/**
	 * 获得代理需求所对应的商品数
	 * @param demandIdList
	 * @return
	 */
	List<Object[]> getUDemandProductAmount(List<Integer> demandIdList);
	/**
	 * 获得每一个代理商对应的代理区域 
	 * @param parIdList
	 * @return
	 */
	List<UAgenArea> getUAgentAreaList(List<Integer> parIdList);
	/**
	 * 获得代理商的落地店数量
	 * @param parIdList
	 * @return
	 */
	List<Object[]> getProductShopAmountByAgentId(List<Long> parIdList);
	/**
	 * 批量修改落地店的状态(恢复、删除)
	 * @param shopId
	 * @param state
	 */
	void batchUpdateProductShopState(Integer[] shopId,Short state);
	/**
	 * 获得平台号的招商需求
	 * @param weiId
	 * @return
	 */
	List<USupplyDemand> getUSupplyDemandList(Long weiId);
	/**
	 * 获得当前平台号的落地店列表
	 * @param weiId
	 * @param supplyWeiId
	 * @return
	 */
	List<UProductShop> getUProductShopList(Long weiId,Long supplyWeiId);
	/**
	 * 添加落地店
	 * @param shop
	 */
	void insertProductShop(UProductShop shop);
	/**
	 * 获取认证员负责的区域
	 * @param weiId
	 * @param type
	 * @return
	 */
	List<UBatchVerifierRegion> getVerifierRegion(Long weiId,Short type);
	
	/**
	 * 获取自己的落地点列表
	 * @param weiId
	 * @return
	 */
	List<UProductShop> getLandShopListMyself(Long weiId,Limit limit);
	
	/**
	 * 根据ID列表获取平台号供应商列表
	 * @param ids
	 * @return
	 */
	List<UPlatformSupplyer> getPlatformSupplyerListByIds(List<Long> ids);
	
	/**
	 * 根据ID列表获取招商需求类别
	 * @param ids
	 * @return
	 */
	List<USupplyDemand> getSupplyDemandListByIds(List<Integer> ids);
	/**
	 * 获取自己的落地点的总数
	 * @param weiId
	 * @return
	 */
	long getMyLandShopCount(Long weiId);
	/**
	 * 通过微店号获取落地点列表
	 * @param weiid
	 * @return
	 */
	List<USupplyChannel> getSupplyChannelList(Long weiid);
	/**
	 * 根据ID获取品牌号列表
	 * @param ids
	 * @return
	 */
	List<UBrandSupplyer> getBrandSupplyersList(List<Long> ids);
	/**
	 * 根据参数获取渠道信息
	 * @param weiId
	 * @param demandId
	 * @return
	 */
	List<USupplyChannel> getUBrandSupplyerByParam(Long weiId,Integer demandId);
}
