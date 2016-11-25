package com.okwei.bean.vo.shoppingcart;

import java.util.List;

public class ShoppingCar {
	//卖家微店号
	private long supplierWeiId;
	//公司名称
	private String companyName;
	//购物车类型
	private short buyType;
	//招商需求id
	private int demandId;
	//首单金额
	private double firstOrderAmount;
	//是否手单
	private short isFirstOrder;
	//产品列表
	private List<ShoppingCarList> shoppingCarList;
	//区
	private short source;
	//成交微店号名称
	private String shopName;
	//成交微店号
	private long sellerWeiId;
	public long getSupplierWeiId() {
		return supplierWeiId;
	}
	public void setSupplierWeiId(long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public short getBuyType() {
		return buyType;
	}
	public void setBuyType(short buyType) {
		this.buyType = buyType;
	}
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
	}
	public double getFirstOrderAmount() {
		return firstOrderAmount;
	}
	public void setFirstOrderAmount(double firstOrderAmount) {
		this.firstOrderAmount = firstOrderAmount;
	}
	public List<ShoppingCarList> getShoppingCarList() {
		return shoppingCarList;
	}
	public void setShoppingCarList(List<ShoppingCarList> shoppingCarList) {
		this.shoppingCarList = shoppingCarList;
	}
	public short getSource() {
		return source;
	}
	public void setSource(short source) {
		this.source = source;
	}
	public short getIsFirstOrder() {
		return isFirstOrder;
	}
	public void setIsFirstOrder(short isFirstOrder) {
		this.isFirstOrder = isFirstOrder;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public long getSellerWeiId() {
		return sellerWeiId;
	}
	public void setSellerWeiId(long sellerWeiId) {
		this.sellerWeiId = sellerWeiId;
	}
}
