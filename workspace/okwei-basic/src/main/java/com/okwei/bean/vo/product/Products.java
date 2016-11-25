package com.okwei.bean.vo.product;

import java.util.Date;

public class Products {
	private Long productId; // 商品Id
	private String productName;// 商品名称
	private Long merchantWeiId; // 供应商微店号
	private String merchantName;// 供应商店铺名称
	private Integer investmentDemandId; // 招商需求Id
	private String investmentDemandName;// 招商需求名称
	private String productPicture;// http;////baseimg.okwei.com/xx.jpg,//商品主图
	private Double storePrice;// 589.00,//落地价
	private Double retailPrice;// 789.00,//零售价
	private Double storeBuyAmount;// 500//落地店首次采购金额
	private Double agentPrice;// 500//代理价
	private int currentUserIsStore;// 1,//当前用户是否落地店，1是0否
	private int currentUserIsAgent;// 1,//当前用户是否代理商，1是0否
	private int ldPriceVisibility;//落地价格是否可见 0-否1-是
    private int dlPriceVisibility;//代理价是否可见
	private Double displayPrice;//原价
//	private Short agentsVisible;//代理价我的代理商可见  0-不可见1-可见
//	private Short otherAgentsVisible;//代理价其他代理商可见  0-不可见1-可见
//	private Short shopVisible;//落地价我的落地店可见  0-不可见1-可见
//	private Short otherShopVisible;//落地价其他落地店可见  0-不可见1-可见
//	private Short ldFullyOpen;//落地价完全公开  0-不可见1-可见
//	private Short dlFullyOpen;//代理价完全公开  0-不可见1-可见
//	private Short ldAgentsVisible;//落地价我的代理商可见  0-不可见1-可见

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getMerchantWeiId() {
		return merchantWeiId;
	}

	public void setMerchantWeiId(Long merchantWeiId) {
		this.merchantWeiId = merchantWeiId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getInvestmentDemandId() {
		return investmentDemandId;
	}

	public void setInvestmentDemandId(Integer investmentDemandId) {
		this.investmentDemandId = investmentDemandId;
	}

	public String getInvestmentDemandName() {
		return investmentDemandName;
	}

	public void setInvestmentDemandName(String investmentDemandName) {
		this.investmentDemandName = investmentDemandName;
	}

	public String getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(String productPicture) {
		this.productPicture = productPicture;
	}

	public Double getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Double storePrice) {
		this.storePrice = storePrice;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getStoreBuyAmount() {
		return storeBuyAmount;
	}

	public void setStoreBuyAmount(Double storeBuyAmount) {
		this.storeBuyAmount = storeBuyAmount;
	}

	public Double getAgentPrice() {
		return agentPrice;
	}

	public void setAgentPrice(Double agentPrice) {
		this.agentPrice = agentPrice;
	}


	public int getCurrentUserIsStore() {
		return currentUserIsStore;
	}

	public void setCurrentUserIsStore(int currentUserIsStore) {
		this.currentUserIsStore = currentUserIsStore;
	}

	public int getCurrentUserIsAgent() {
		return currentUserIsAgent;
	}

	public void setCurrentUserIsAgent(int currentUserIsAgent) {
		this.currentUserIsAgent = currentUserIsAgent;
	}

	public int getLdPriceVisibility() {
		return ldPriceVisibility;
	}

	public void setLdPriceVisibility(int ldPriceVisibility) {
		this.ldPriceVisibility = ldPriceVisibility;
	}

	public int getDlPriceVisibility() {
		return dlPriceVisibility;
	}

	public void setDlPriceVisibility(int dlPriceVisibility) {
		this.dlPriceVisibility = dlPriceVisibility;
	}

	public Double getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(Double displayPrice) {
		this.displayPrice = displayPrice;
	}

}
