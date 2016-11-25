package com.okwei.appinterface.bean.vo;


public class SellPropertyValue {
	private String proteryValue;
	private int stockCount;
	private Double salePrice;
	private Double storePrice;
	private Double commision;
	private String priceImg;
	private Long stylesId;
	private SellPropertyName priceProperty;
	private String couponsPrice;
	private Double dukePrice;//城主价
	private Double deputyPrice;//副城主价
	private Double agentPrice;//代理价
	private Double brandAgentPrice;//品牌代理商价 add by zlp at 2016/10/13
	public String getProteryValue() {
		return proteryValue;
	}
	public void setProteryValue(String proteryValue) {
		this.proteryValue = proteryValue;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getCommision() {
		return commision;
	}
	public void setConmision(Double commision) {
		this.commision = commision;
	}
	public String getPriceImg() {
		return priceImg;
	}
	public void setPriceImg(String priceImg) {
		this.priceImg = priceImg;
	}
	public SellPropertyName getPriceProperty() {
		return priceProperty;
	}
	public void setPriceProperty(SellPropertyName priceProperty) {
		this.priceProperty = priceProperty;
	}
	public Long getStylesId() {
		return stylesId;
	}
	public void setStylesId(Long stylesId) {
		this.stylesId = stylesId;
	}
	public Double getAgentPrice() {
	    return agentPrice;
	}
	public void setAgentPrice(Double agentPrice) {
	    this.agentPrice = agentPrice;
	}
	public Double getStorePrice() {
	    return storePrice;
	}
	public void setStorePrice(Double storePrice) {
	    this.storePrice = storePrice;
	}
	public void setCommision(Double commision) {
	    this.commision = commision;
	}
	public String getCouponsPrice() {
	    return couponsPrice;
	}
	public void setCouponsPrice(String couponsPrice) {
	    this.couponsPrice = couponsPrice;
	}
	public Double getBrandAgentPrice() {
		return brandAgentPrice;
	}
	public void setBrandAgentPrice(Double brandAgentPrice) {
		this.brandAgentPrice = brandAgentPrice;
	}
	public Double getDeputyPrice() {
		return deputyPrice;
	}
	public Double getDukePrice() {
		return dukePrice;
	}
	public void setDeputyPrice(Double deputyPrice) {
		this.deputyPrice = deputyPrice;
	}
	public void setDukePrice(Double dukePrice) {
		this.dukePrice = dukePrice;
	}
}
