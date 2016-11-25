package com.okwei.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.vo.product.ProductModel;

public class BrandShopModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;//微店号
	private String userName;//微店账号名称
	private String shopName;//店铺名称
	private String shopImg;//店铺图片地址
	private int identityType;//店铺身份标识
	private int brandId;//品牌ID
	private String brandName;//品牌名称
	private String brandLogo;//品牌LOGO
	private long saleProductsNum;//分销产品数
	private long agentPeopleNum;//代理人数
	private List<ProductModel> productList;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public int getIdentityType() {
		return identityType;
	}
	public void setIdentityType(int identityType) {
		this.identityType = identityType;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandLogo() {
		return brandLogo;
	}
	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}
	public long getSaleProductsNum() {
		return saleProductsNum;
	}
	public void setSaleProductsNum(long saleProductsNum) {
		this.saleProductsNum = saleProductsNum;
	}
	public long getAgentPeopleNum() {
		return agentPeopleNum;
	}
	public void setAgentPeopleNum(long count) {
		this.agentPeopleNum = count;
	}
	public List<ProductModel> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductModel> productList) {
		this.productList = productList;
	}
	
	
}
