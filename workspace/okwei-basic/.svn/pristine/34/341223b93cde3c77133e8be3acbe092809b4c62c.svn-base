package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductRelation")
public class PProductRelation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7286955458006051463L;
	// Fields

	private Long productId;
	private Long subProductId;
	private String subWeiId;
	private String sendWeiId;
	private Double minProxyPrice;
	private Double maxProxyPrice;
	private Double minFloorPrice;
	private Double maxFloorPrice;
	private Date createTime;
	private Double proxyPrice;
	private Double floorPrice;
	private Double defaultPrice;
	private Double advicePrice;

	// Constructors

	/** default constructor */
	public PProductRelation() {
	}

	/** minimal constructor */
	public PProductRelation(Long productId) {
		this.productId = productId;
	}

	/** full constructor */
	public PProductRelation(Long productId, Long subProductId, String subWeiId,
			String sendWeiId, Double minProxyPrice, Double maxProxyPrice,
			Double minFloorPrice, Double maxFloorPrice,
			Double proxyPrice, Double floorPrice,Double defaultPrice,
			Double advicePrice,  Date createTime) {
		this.productId = productId;
		this.subProductId = subProductId;
		this.subWeiId = subWeiId;
		this.sendWeiId = sendWeiId;
		this.minProxyPrice = minProxyPrice;
		this.maxProxyPrice = maxProxyPrice;
		this.minFloorPrice = minFloorPrice;
		this.maxFloorPrice = maxFloorPrice;
		this.createTime = createTime;
		this.defaultPrice = defaultPrice;
		this.advicePrice = advicePrice;
	}

	@Id
	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@Column(name = "SubProductID", unique = true, nullable = false)
	public Long getSubProductId() {
		return this.subProductId;
	}

	public void setSubProductId(Long subProductId) {
		this.subProductId = subProductId;
	}

	@Column(name = "SubWeiId", length = 20)
	public String getSubWeiId() {
		return this.subWeiId;
	}

	public void setSubWeiId(String subWeiId) {
		this.subWeiId = subWeiId;
	}

	@Column(name = "SendWeiId", length = 20)
	public String getSendWeiId() {
		return this.sendWeiId;
	}

	public void setSendWeiId(String sendWeiId) {
		this.sendWeiId = sendWeiId;
	}

	@Column(name = "MinProxyPrice", precision = 18)
	public Double getMinProxyPrice() {
		return this.minProxyPrice;
	}

	public void setMinProxyPrice(Double minProxyPrice) {
		this.minProxyPrice = minProxyPrice;
	}

	@Column(name = "maxProxyPrice", precision = 18)
	public Double getMaxProxyPrice() {
		return this.maxProxyPrice;
	}

	public void setMaxProxyPrice(Double maxProxyPrice) {
		this.maxProxyPrice = maxProxyPrice;
	}

	@Column(name = "MinFloorPrice", precision = 18)
	public Double getMinFloorPrice() {
		return this.minFloorPrice;
	}

	public void setMinFloorPrice(Double minFloorPrice) {
		this.minFloorPrice = minFloorPrice;
	}

	@Column(name = "maxFloorPrice", precision = 18)
	public Double getMaxFloorPrice() {
		return this.maxFloorPrice;
	}

	public void setMaxFloorPrice(Double maxFloorPrice) {
		this.maxFloorPrice = maxFloorPrice;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "ProxyPrice", precision = 18)
	public Double getProxyPrice() {
		return this.proxyPrice;
	}

	public void setProxyPrice(Double proxyPrice) {
		this.proxyPrice = proxyPrice;
	}

	@Column(name = "FloorPrice", precision = 18)
	public Double getFloorPrice() {
		return this.floorPrice;
	}

	public void setFloorPrice(Double floorPrice) {
		this.floorPrice = floorPrice;
	}
	
	@Column(name = "DefaultPrice", precision = 18)
	public Double getDefaultPrice() {
		return this.defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	@Column(name = "AdvicePrice", precision = 18)
	public Double getAdvicePrice() {
		return this.advicePrice;
	}

	public void setAdvicePrice(Double advicePrice) {
		this.advicePrice = advicePrice;
	}

}