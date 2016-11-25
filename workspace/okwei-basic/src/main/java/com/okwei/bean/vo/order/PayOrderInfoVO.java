package com.okwei.bean.vo.order;

public class PayOrderInfoVO {

	//订单号
	private String orderId;
	//订单总额
	private Double amount;
	//是否可用代金券（1可用 0不可用）
	private int useCash;
	//可用代金券金额
	private Double cashAmount;
	//能否使用微金币  （1可用 0不可用）
	private int useOkweiCoin;
	//能使用微金币金额
	private Double okweiCoin=0.0;
	//微金币
	private double okweiCoinRate=1;
	private int payTimeLimit;//限制购买数量
	
	 
	public double getOkweiCoinRate() {
		return okweiCoinRate;
	}
	public void setOkweiCoinRate(double okweiCoinRate) {
		this.okweiCoinRate = okweiCoinRate;
	}
	public int getUseOkweiCoin() {
		return useOkweiCoin;
	}
	public void setUseOkweiCoin(int useOkweiCoin) {
		this.useOkweiCoin = useOkweiCoin;
	}
	public Double getOkweiCoin() {
		return okweiCoin;
	}
	public void setOkweiCoin(Double okweiCoin) {
		this.okweiCoin = okweiCoin;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getUseCash() {
		return useCash;
	}
	public void setUseCash(int useCash) {
		this.useCash = useCash;
	}
	public Double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public int getPayTimeLimit() {
		return payTimeLimit;
	}
	public void setPayTimeLimit(int payTimeLimit) {
		this.payTimeLimit = payTimeLimit;
	}
	
	
	
	
}
