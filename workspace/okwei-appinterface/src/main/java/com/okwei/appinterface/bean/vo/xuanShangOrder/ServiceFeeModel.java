package com.okwei.appinterface.bean.vo.xuanShangOrder;

/**
 * 平台服务费
 * @author fuhao
 */
public class ServiceFeeModel {
	public Long feeId;// 988,//平台服务费Id
	public Long buyWeiId;// "9889232",//购买人微店号
	public String buyShopName;// "XXX微店",//购买人微店名
	public String orderAmount;// 8777.00,//订单总金额
	public String orderNum;// "323888777",//订单号
	public String orderTime;// "2015-11-03  15：00",//下单时间
	public String fee;// 87.00,//服务费
	public int isPayReward;// 0,//是否支付0-未支付1已支付
	public String payOrder;// "88899033",//支付订单号
	public String merchantName;// "xxxxx"//供应商名称
	public String payTime;//支付时间
	public String dateStr;
	/**
	 * 服务费订单详情
	 */
	private String feeUrl;
	
	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getFeeUrl() {
		return feeUrl;
	}

	public void setFeeUrl(String feeUrl) {
		this.feeUrl = feeUrl;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
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
 
 
	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
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

}
