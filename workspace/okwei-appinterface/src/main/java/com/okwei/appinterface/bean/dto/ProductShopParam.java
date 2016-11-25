package com.okwei.appinterface.bean.dto;

@SuppressWarnings("serial")
public class ProductShopParam extends BaseParam {
	private Integer shopId;  //落地店id
	private Short status;  //落地店状态
	private String statusReson;  //取消原因
	
	 
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStatusReson() {
		return statusReson;
	}

	public void setStatusReson(String statusReson) {
		this.statusReson = statusReson;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
	
}
