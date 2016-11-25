package com.okwei.appinterface.bean.vo.wallet;

public class WalletDetailVO {

	
	//流水号
	private String detailId;
	//订单号
	private String orderNo;
	//收支金额
	private String amount;
	//可用金额
	private double restAmount;
	//可用金额Str(前端展示如： 余额：500)
	private String restAmountName;
	//时间 （如 2015-10-11）
	private String dateStr;
	//时间(10-11)
	private String dateName;
	//颜色值
	private String colorValue;
	//返回名称
	private String title;
	//图片
	private String img;
	//状态名称
	private String stateName;
	//wap地址
	private String wapUrl;
	
	
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getWapUrl() {
		return wapUrl;
	}
	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getColorValue() {
		return colorValue;
	}
	public void setColorValue(String colorValue) {
		this.colorValue = colorValue;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
 
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public double getRestAmount() {
		return restAmount;
	}
	public void setRestAmount(double restAmount) {
		this.restAmount = restAmount;
	}
	public String getRestAmountName() {
		return restAmountName;
	}
	public void setRestAmountName(String restAmountName) {
		this.restAmountName = restAmountName;
	}

	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getDateName() {
		return dateName;
	}
	public void setDateName(String dateName) {
		this.dateName = dateName;
	}
	
	
	
}
