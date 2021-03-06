package com.okwei.dao.wallet;

import java.util.Map;

import com.okwei.bean.domain.UWalletDetails;
import com.okwei.bean.domain.UWeiCoinLog;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;

/**
 * @author 付豪
 */
public interface IBasicWalletDao extends IBaseDAO { 
	/**
	 * 获得当前微店主钱包的收支明细
	 * @param weiId 
	 * @param limit
	 * @param detailType   不等于1不等于2  就是查全部
	 * @param fromDate   	开始时间   可以传null
	 * @param toDate  		结束时间   可以传null
	 * @return
	 */
	PageResult<UWalletDetails> getMyWalletDetailPage(Long weiId, Limit limit,int detailType,String fromDate,String toDate);
	
	/**
	 * 现金劵列表
	 * @param weiId
	 * @param limit
	 * @param detailType  1收入 2支出 3已過期
	 * @return
	 */
	PageResult<UWeiCoinLog> find_CashCoupon(Long weiId, Limit limit,int detailType);
	
	/**
	 * 统计现金劵  每个类型的个数
	 * @param weiId
	 * @return
	 */
	public Map<String, Object> get_CountCashCoupon(Long weiId);
	/**
	 * 获取优惠券金额（订单）
	 * zy
	 * @param weiid
	 * @param payOrderid
	 * @return
	 */
	public double get_UTicketInfo(Long weiid, String payOrderid);
}
