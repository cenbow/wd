package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AActProductExp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ActProductExp")
public class AActProductExp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Long proActId;
	private Long productId;
	private Integer sellerCount;
	private Double sellerAmount;

	// Constructors

	/** default constructor */
	public AActProductExp() {
	}

	/** full constructor */
	public AActProductExp(Long proActId, Long productId, Integer sellerCount,
			Double sellerAmount) {
		this.proActId = proActId;
		this.productId = productId;
		this.sellerCount = sellerCount;
		this.sellerAmount = sellerAmount;
	}


	@Id
	@Column(name = "ProActID", unique = true, nullable = false)
	public Long getProActId() {
		return this.proActId;
	}

	public void setProActId(Long proActId) {
		this.proActId = proActId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "SellerCount")
	public Integer getSellerCount() {
		return this.sellerCount;
	}

	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}

	@Column(name = "SellerAmount", precision = 18, scale = 2)
	public Double getSellerAmount() {
		return this.sellerAmount;
	}

	public void setSellerAmount(Double sellerAmount) {
		this.sellerAmount = sellerAmount;
	}

}