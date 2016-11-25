package com.okwei.supplyportal.bean.vo;

public class BaseProduct {

	/**
	 * 产品编号
	 */
	private long productId;
	/**
	 * 产品标题
	 */
	private String productTitle;
	private String productMinTitle;
	/**
	 * 产品默认图片
	 */
	private String productImg;
	/**
	 * 价格
	 */
	private double price;
	/**
	 * 佣金
	 */
	private double commission;
	/**
	 * 供应商weiid
	 */
	private long supplyerWeiid;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductMinTitle() {
		return productMinTitle;
	}
	public void setProductMinTitle(String productMinTitle) {
		this.productMinTitle = productMinTitle;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public long getSupplyerWeiid() {
		return supplyerWeiid;
	}
	public void setSupplyerWeiid(long supplyerWeiid) {
		this.supplyerWeiid = supplyerWeiid;
	}
}
