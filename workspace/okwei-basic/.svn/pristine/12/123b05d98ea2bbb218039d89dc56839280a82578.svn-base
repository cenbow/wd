package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PPreOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_PreOrder")
public class PPreOrder extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productId;
	private Double preOrderPrice;
	private String content;

	// Constructors

	/** default constructor */
	public PPreOrder() {
	}

	/** full constructor */
	public PPreOrder(Double preOrderPrice, String content) {
		this.preOrderPrice = preOrderPrice;
		this.content = content;
	}

	// Property accessors
	@Id
	@Column(name = "ProductID", unique = true, nullable = false)
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "PreOrderPrice", precision = 18)
	public Double getPreOrderPrice() {
		if (this.preOrderPrice == null)
			return 0d;
		return this.preOrderPrice;
	}

	public void setPreOrderPrice(Double preOrderPrice) {
		this.preOrderPrice = preOrderPrice;
	}

	@Column(name = "Content", length = 512)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}