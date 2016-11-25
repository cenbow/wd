package com.okwei.bean.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * THouseProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_HouseProduct")
public class THouseProduct implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7119995497180592708L;
	// Fields

	private Integer id;
	private Integer houseId;
	private Long productId;
	private Integer sort;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public THouseProduct() {
	}

	/** full constructor */
	public THouseProduct(Integer houseId, Long productId, Integer sort,
			Short state, Date createTime) {
		this.houseId = houseId;
		this.productId = productId;
		this.sort = sort;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "HouseID")
	public Integer getHouseId() {
		return this.houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}