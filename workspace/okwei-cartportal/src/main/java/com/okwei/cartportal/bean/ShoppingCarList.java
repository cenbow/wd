package com.okwei.cartportal.bean;

import java.util.List;

/**
 * 购物车返回列表
 * @author yangjunjun
 *
 */
public class ShoppingCarList { 
	private String companyName;//公司名
	private List<ShoppingModel> prodList;//购物车列表
	
	public List<ShoppingModel> getProdList() {
		return prodList;
	}
	public void setProdList(List<ShoppingModel> prodList) {
		this.prodList = prodList;
	}
	/**
	 * 设置公司名
	 * */
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	/**
	 * 获取公司名
	 * */
	public String getCompanyName(){
		return companyName;
	}
}
