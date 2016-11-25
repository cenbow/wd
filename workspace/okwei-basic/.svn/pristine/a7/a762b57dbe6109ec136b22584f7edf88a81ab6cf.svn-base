package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UErpsupplyer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ERPSupplyer")
public class UErpsupplyer extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private Short type;

	// Constructors

	/** default constructor */
	public UErpsupplyer() {
	}

	/** full constructor */
	public UErpsupplyer(Short type) {
		this.type = type;
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

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}