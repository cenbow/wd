package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DBrandSupplier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_BrandSupplier")
public class DBrandSupplier implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5347513566533981531L;
	// Fields

	private Long weiId;
	private Integer brandId;
	private Integer type;
	private Integer state;

	// Constructors

	/** default constructor */
	public DBrandSupplier() {
	}

	/** minimal constructor */
	public DBrandSupplier(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public DBrandSupplier(Long weiId, Integer brandId, Integer type,
			Integer state) {
		this.weiId = weiId;
		this.brandId = brandId;
		this.type = type;
		this.state = state;
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

	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "Type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "State")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}