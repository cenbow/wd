package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AHomeProducts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_HomeProducts")
public class AHomeProducts implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer homeId;
	private Long productId;
	private Integer sort;
	// Constructors

	/** default constructor */
	public AHomeProducts() {
	}

	/** minimal constructor */
	public AHomeProducts(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AHomeProducts(Integer id, Integer homeId, Long productId) {
		this.id = id;
		this.homeId = homeId;
		this.productId = productId;
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

	@Column(name = "HomeID")
	public Integer getHomeId() {
		return this.homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
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
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}