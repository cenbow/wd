package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductBatchPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductBatchPrice")
public class PProductBatchPrice extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bid;
	private Long productId;
	private Long stylesId;
	private Integer count;
	private Double pirce;

	// Constructors

	/** default constructor */
	public PProductBatchPrice() {
	}

	@Id
	@GeneratedValue
	@Column(name = "BID")
	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "Count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "pirce", precision = 18)
	public Double getPirce() {
		if (this.pirce == null)
			return 0d;
		return this.pirce;
	}

	public void setPirce(Double pirce) {
		this.pirce = pirce;
	}

	@Column(name = "StylesID")
	public Long getStylesId() {
		return stylesId;
	}

	public void setStylesId(Long stylesId) {
		this.stylesId = stylesId;
	}

}