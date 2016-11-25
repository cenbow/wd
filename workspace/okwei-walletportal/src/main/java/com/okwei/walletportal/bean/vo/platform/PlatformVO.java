package com.okwei.walletportal.bean.vo.platform;

/**
 * @author fuhao
 *	平台服务费VO
 */
public class PlatformVO {
	public Long feeId;//平台服务费Id
	public Long buyWeiId;//购买人微店号
	public String buyShopName;//购买人微店名
	public double orderAmount;//订单总金额
	public String orderNum;//订单号
	public String orderTime;//下单时间
	public String fee;//服务费
	public int isPayReward;//是否支付0-未支付1已支付
	public String payOrder;//支付订单号
	public String merchantName;//供应商名称
	public String payTime;//支付时间 
	public Long getFeeId() {
		return feeId;
	}
	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}
	public Long getBuyWeiId() {
		return buyWeiId;
	}
	public void setBuyWeiId(Long buyWeiId) {
		this.buyWeiId = buyWeiId;
	}
	public String getBuyShopName() {
		return buyShopName;
	}
	public void setBuyShopName(String buyShopName) {
		this.buyShopName = buyShopName;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	 
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public int getIsPayReward() {
		return isPayReward;
	}
	public void setIsPayReward(int isPayReward) {
		this.isPayReward = isPayReward;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	
}
