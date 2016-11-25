package com.okwei.bean.vo.activity;

import com.okwei.bean.domain.AActProductsShowTime;

public class ActProductShowResult extends AActProductsShowTime {

	private static final long serialVersionUID = -8138093858858489238L;
	
	private String productTitle;
	private String productImg;
	private Double actPrice;
	private Double retailPrice;
	
	
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public Double getActPrice() {
		return actPrice;
	}
	public void setActPrice(Double actPrice) {
		this.actPrice = actPrice;
	}
	public Double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	
	
}
