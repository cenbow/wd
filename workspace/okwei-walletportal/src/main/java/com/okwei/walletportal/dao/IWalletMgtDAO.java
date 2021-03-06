package com.okwei.walletportal.dao;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.UCancleOrderAmoutDetail;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UTuizhu;
import com.okwei.bean.domain.UWalletDetails;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.walletportal.bean.vo.SettleAccountDetailVO;

public interface IWalletMgtDAO {
	/**
	 * 获得当前微店主钱包的收支明细
	 * @param userId
	 * @param limit
	 * @param detailType
	 * @return
	 */
	PageResult<UWalletDetails> getMyWalletDetailPage(Long userId, Limit limit,int detailType,String fromDate,String toDate);
	/**
	 * 获得当前微店主钱包的结算明细
	 * @param userId
	 * @param limit
	 * @param statusType
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	PageResult<UTradingDetails> getMyWalletSettleAccountPage(Long userId, Limit limit,int statusType,String fromDate,String toDate);
	/**
	 * 获得当前微店主钱包的提现记录
	 * @param userId
	 * @param limit
	 * @param statusType
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	PageResult<UCancleOrderAmoutDetail> getMyWalletWithdrawRecordPage(Long userId, Limit limit,String fromDate,String toDate);
	
	SettleAccountDetailVO getSettleAccountDetail(UTradingDetails tradeDetail);
	UTradingDetails getTradeDetails(Long detailId,Long weiId);
	
	UTuizhu getTuizhuRecord(long weiId, Short type);
	
	public OPayOrder getPayOrderByCondition(long weiID, Short typeState,Short state);
	
}
