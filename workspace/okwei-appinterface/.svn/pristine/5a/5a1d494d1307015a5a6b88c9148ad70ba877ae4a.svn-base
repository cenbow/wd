package com.okwei.appinterface.service;

import com.okwei.bean.vo.ReturnModel;


public interface IBrandSvervice {
	/**
	 * 获取各种首页数据
	 * 
	 * @param weiId
	 *            当前登录人
	 * @param pageType
	 *            结果类型 USE：PPHDataType
	 * @return
	 */
	public ReturnModel queryBrandCount(Long weiId, String pageType);
	
	/**
	 * 获取首页待处理数据
	 * 
	 * @param weiId
	 *            当前登录人
	 * @param pageType
	 *            结果类型 USE：PPHDataType
	 * @return
	 */
	public ReturnModel queryWaitDeal(Long weiId, String pageType);
	/**
	 * 获取各种首页统计数据
	 * 
	 * @param weiId
	 *            当前登录人
	 * @param pageType
	 *            结果类型 USE：PPHDataType
	 * @return
	 */
	public ReturnModel queryStatistics(Long weiId, String pageType);

	/**
	 * 获渠道分布数据
	 * 
	 * @param weiId
	 *            当前登录人
	 * @param countType
	 *            取值范围 （ 1：7天 2：30天 3：累计 ）
	 * @return
	 */
	public ReturnModel queryRankingResults(Long weiId, int countType,
			int pageIndex, int pageSize);
	
	
	/**
	 * 获取上游管理统计数据接口
	 * @param weiId
	 * @return
	 */
	public ReturnModel queryUpManagerStatic(Long weiId);

}
