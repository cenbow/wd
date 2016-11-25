package com.okwei.appinterface.service.wallet;

import com.okwei.bean.vo.ReturnModel;


public interface IWalletService { 
	
	/**
	 * @param weiId
	 * @param isPayReward 现金劵状态
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel getCashCoupon(Long weiId,int isPayReward,int pageIndex, int pageSize);

	/**
	 * 平台服务费列表
	 * @param isPayReward 是否支付 0 未支付  1已支付
	 * @param dateStr	最后的日期
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel getServiceFeeList(Long weiId,String isPayReward, String dateStr,int pageIndex, int pageSize);
	/**
	 * 平台服务费详情
	 * @param feeId
	 * @return
	 */
	public ReturnModel getServiceFeeDetail( Long feeId);
	
	/**
	 * 悬赏 列表
	 * @param weiId
	 * @param isPayReward //-1所有0未支付1已支付
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel getMyPayRewards(Long weiId,String dateStr, Short isPayReward, int pageIndex, int pageSize) ;
	/**
	 * 悬赏 详情
	 * @param weiId
	 * @param channelId 悬赏渠道表 U_SupplyChannel
	 * @return
	 */
	public ReturnModel getRewardDetail(Long weiId,Integer channelId);
	
	/**
	 * 我的 返现 列表
	 * @param weiId
	 * @param dateStr	最后一个返回的时间标示
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel findTradingRebates(Long weiId, String dateStr, int pageIndex, int pageSize) ;
	/**
	 * 返现详情
	 * @param detailId  
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel getMyTradingRebatesDetails(Long detailId,Long weiId,int pageIndex,int pageSize);
	/**
	 * 获取用户收支列表
	 * @param weiid
	 * @param dateStr	上一页最后一条纪录的dateStr 第一次可传null
	 * @param pageIndex	开始页数
	 * @param pageSize	每页展示条数
	 * @return
	 */
	public ReturnModel getWalletList(long weiid, String dateStr, int pageIndex, int pageSize);

	} 
