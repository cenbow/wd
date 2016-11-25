package com.okwei.bean.vo;

import java.io.Serializable;

public class DemandProductVO implements Serializable {
	
	private Integer demandID;
	
	private Long productID;
	
	private String productImg;
	
	private String prodcutTitle;
	
	private String productMinTitle;
	
	private Double price;
	
	private Double comminss;
	
	private Double agentPrice;
	
	private Double shopPrice;
	
	private Boolean checked;
	
	private Integer sid;
	
	private String shopClassName;
	
	// 判断是否显示代理价
	private int isShowAgent;
	// 判断是否显示落地价
	private int isShowLoad;

	public Integer getDemandID() {
		return demandID;
	}

	public void setDemandID(Integer demandID) {
		this.demandID = demandID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProdcutTitle() {
		return prodcutTitle;
	}

	public void setProdcutTitle(String prodcutTitle) {
		this.prodcutTitle = prodcutTitle;
	}

	public String getProductMinTitle() {
		return productMinTitle;
	}

	public void setProductMinTitle(String productMinTitle) {
		this.productMinTitle = productMinTitle;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getComminss() {
		return comminss;
	}

	public void setComminss(Double comminss) {
		this.comminss = comminss;
	}

	public Double getAgentPrice() {
		return agentPrice;
	}

	public void setAgentPrice(Double agentPrice) {
		this.agentPrice = agentPrice;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public int getIsShowAgent() {
	    return isShowAgent;
	}

	public void setIsShowAgent(int isShowAgent) {
	    this.isShowAgent = isShowAgent;
	}

	public int getIsShowLoad() {
	    return isShowLoad;
	}

	public void setIsShowLoad(int isShowLoad) {
	    this.isShowLoad = isShowLoad;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getShopClassName() {
		return shopClassName;
	}

	public void setShopClassName(String shopClassName) {
		this.shopClassName = shopClassName;
	}
	
	
	
}
