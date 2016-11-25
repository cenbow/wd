package com.okwei.service.user;

import com.okwei.bean.dto.MyAgentOrProductShopListDTO;
import com.okwei.bean.vo.MyAgentOrProductShopListVO;
import com.okwei.bean.vo.PlatformProductShopListPCVO;
import com.okwei.bean.vo.PlatformProductShopListVO;
import com.okwei.bean.vo.ProductAgentDataVO;
import com.okwei.bean.vo.ProductShopDataVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.user.LandShopListVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;

public interface IBasicAgentOrProductShopService extends IBaseService {
	/**
	 * 获得我发展的代理商
	 * @param limit
	 * @param queryParam
	 * @return
	 */
	PageResult<MyAgentOrProductShopListVO> getMyDevelopAgent(Limit limit,MyAgentOrProductShopListDTO queryParam);
	/**
	 * 获得我发展的代理商的详情
	 * @param parid
	 * @return
	 */
	MyAgentOrProductShopListVO getMyDevelopDetail(Integer parid);
	/**
	 * 获得我发展的落地店(PC版)
	 * @param limit
	 * @param queryParam
	 * @return
	 */
	PageResult<PlatformProductShopListPCVO> getMyDevelopProductShop(Limit limit,MyAgentOrProductShopListDTO queryParam);
	/**
	 * 获得落地店的资料
	 * @param shopId
	 * @return
	 */
	ProductShopDataVO getProductShopDataVO(Integer shopId);
	/**
	 * 获得代理商的跟进资料
	 * @param parId
	 * @return
	 */
	ProductAgentDataVO getProductAgentDataVO(Integer parId,int pcOrApp);
	/**
	 * 代理商的跟进
	 * @param parId
	 * @param remark
	 * @return
	 */
	ReturnModel followAgent(Integer parId,String remark,Long weiId);
	/**
	 * 获得平台号或认证员的落地店列表(APP接口版)
	 * @param supplyWeiId
	 * @param state
	 * @param limit
	 * @return
	 */
	PageResult<PlatformProductShopListVO> getProductShopList(Long weiId,Short state,Limit limit,int platformOrVerifier);
	
	PageResult<PlatformProductShopListPCVO> getProductShopPCList(Long weiId,Short state,Limit limit,int platformOrVerifier);
	
	/**
	 * 获得平台号或认证员的落地店列表(APP接口版)
	 * @param supplyWeiId
	 * @param state
	 * @param limit
	 * @return
	 */
	PageResult<PlatformProductShopListVO> getProductShopListByDemandID(Long weiId,Short state,Limit limit,int demandId,int areaId);
	/**
	 * 获得下游落地店的详情
	 * @param shopId
	 * @return
	 */
	ReturnModel getProductShopDetail(Integer shopId);
	/**
	 * 修改落地店的状态(取消、恢复、删除落地店)
	 * @param shopId
	 * @param cancelReason
	 * @param state
	 * @return
	 */
	ReturnModel updateProductShopState(Integer shopId,String cancelReason,Short state);
	/**
	 * 批量修改落地店的状态(恢复和删除)
	 * @param shopId
	 * @param state
	 * @return
	 */
	ReturnModel batchUpdateProductShopState(Integer[] shopId,Short state);
	/**
	 * 添加落地店
	 * @param demandIdArr
	 * @param weiId
	 * @return
	 */
	ReturnModel addProductShop(Integer[] demandIdArr,Long weiId,Long supplyWeiId);
	/**
	 * 获得店铺名信息
	 * @param weiId
	 * @param supplyWeiId
	 * @return
	 */
	ReturnModel getShopNameInfo(Long weiId,Long supplyWeiId);
	/**
	 * 获取认证员负责的区域
	 * @param weiId
	 * @param type
	 * @return
	 */
	ReturnModel getVerifierRegion(Long weiId,Short type);
	
	/**
	 * 获取自己的落地点列表
	 * @param weiId
	 * @return
	 */
	PageResult<LandShopListVO> getMyLandShopList(Long weiId,Limit limit);
	/**
	 * 操作用户的身份标识
	 * @param shopid
	 * @param state
	 */
	void operateUserIdentity(Integer[] shopid,Short state);
}
