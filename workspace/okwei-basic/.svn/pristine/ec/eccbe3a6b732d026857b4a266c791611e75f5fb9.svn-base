package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PShevleBatchPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ShevleBatchPrice")
public class PShevleBatchPrice extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sbid;
	private Long weiIdsort;
	private Long id;
	private Integer count;
	private Double price;

	// Constructors

	/** default constructor */
	public PShevleBatchPrice() {
	}

	@Id
	@GeneratedValue
	@Column(name = "SBID")
	public Long getSbid() {
		return sbid;
	}

	public void setSbid(Long sbid) {
		this.sbid = sbid;
	}

	@Column(name = "WeiIDSort")
	public Long getWeiIdsort() {
		return weiIdsort;
	}

	public void setWeiIdsort(Long weiIdsort) {
		this.weiIdsort = weiIdsort;
	}

	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "Count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "Price", precision = 18)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}