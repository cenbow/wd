package com.okwei.walletportal.service;

import com.okwei.service.IBaseService;
import com.okwei.walletportal.bean.vo.BaseResultVO;
import com.okwei.walletportal.bean.vo.WalletTxVO;

public interface IWalletTxService extends IBaseService {
	
	/**
	 * 用户是否可以使用微钱包  1.是否绑定手机 2.是否设置支付密码
	 * @return
	 */
	public BaseResultVO getIsCanUserWallt(long weiID);
	
	/**
	 * 获取提现信息
	 * @return
	 */
	public WalletTxVO getWalletTxInfo(long weiID);
	
	/**
	 * 保存提现信息
	 * @return
	 */
	public BaseResultVO saveTxInfo(long weiID,double amount,String checkCode,long cardID);
	
	/**
	 * 获取提现短信验证码
	 * @param weiID
	 * @return
	 */
	public BaseResultVO getMobileCode(long weiID);
	
	/**
	 * 判断是否是我自己的银行卡
	 * @param weiID
	 * @param cardID
	 * @return
	 */
	public boolean getIsMyBankCard(long weiID,long cardID);
	/**
	 * 获取下游分销商数量
	 * @param weiId
	 * @return
	 */
	public long getLowerSupplyerCount(long weiId);
	
	/**
	 * 获取上游分销商数量
	 * @param weiId
	 * @return
	 */
	public long getSponsorCount(long weiId);
	/**
	 * 获取成交订单数量
	 * @param weiId
	 * @return
	 */
	public long getCompletedOrderCount(long weiId);
	/**
	 * 获取认证服务点未审核的供应商数量
	 * @param weiId
	 * @return
	 */
	public long getNotAuditByWeiId(long weiId);
	/**
	 * 获取认证服务点审核未进驻的供应商数量
	 * @param weiId
	 * @return
	 */
	public long getAuditedNoPayInByWeiId(long weiId);
	
}
