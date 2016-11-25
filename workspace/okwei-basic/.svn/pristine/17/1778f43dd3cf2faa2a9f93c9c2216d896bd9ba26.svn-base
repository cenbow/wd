package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UShopInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ShopInfo")
public class UShopInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long weiId;
	private String shopImg;
	private String shopName;
	private String shopBusContent;
	private String shopSign;
	private String weiImg;
	private String weiName;
	private String verImg;
	private String verName;
	private Integer productCount;
	private Integer shelveCount;

	// Constructors

	/** default constructor */
	public UShopInfo() {
	}

	/** full constructor */
	public UShopInfo(String shopImg, String shopName, String shopBusContent,
			String shopSign, String weiImg, String weiName, String verImg,
			String verName, Integer productCount, Integer shelveCount) {
		this.shopImg = shopImg;
		this.shopName = shopName;
		this.shopBusContent = shopBusContent;
		this.shopSign = shopSign;
		this.weiImg = weiImg;
		this.weiName = weiName;
		this.verImg = verImg;
		this.verName = verName;
		this.productCount = productCount;
		this.shelveCount = shelveCount;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ShopImg", length = 200)
	public String getShopImg() {
		return this.shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}

	@Column(name = "ShopName", length = 100)
	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name = "ShopBusContent", length = 200)
	public String getShopBusContent() {
		return this.shopBusContent;
	}

	public void setShopBusContent(String shopBusContent) {
		this.shopBusContent = shopBusContent;
	}

	@Column(name = "ShopSign", length = 200)
	public String getShopSign() {
		return this.shopSign;
	}

	public void setShopSign(String shopSign) {
		this.shopSign = shopSign;
	}

	@Column(name = "WeiImg", length = 256)
	public String getWeiImg() {
		return this.weiImg;
	}

	public void setWeiImg(String weiImg) {
		this.weiImg = weiImg;
	}

	@Column(name = "WeiName", length = 64)
	public String getWeiName() {
		return this.weiName;
	}

	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}

	@Column(name = "VerImg", length = 128)
	public String getVerImg() {
		return this.verImg;
	}

	public void setVerImg(String verImg) {
		this.verImg = verImg;
	}

	@Column(name = "VerName", length = 64)
	public String getVerName() {
		return this.verName;
	}

	public void setVerName(String verName) {
		this.verName = verName;
	}

	@Column(name = "ProductCount")
	public Integer getProductCount() {
		return this.productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	@Column(name = "ShelveCount")
	public Integer getShelveCount() {
		return this.shelveCount;
	}

	public void setShelveCount(Integer shelveCount) {
		this.shelveCount = shelveCount;
	}

}