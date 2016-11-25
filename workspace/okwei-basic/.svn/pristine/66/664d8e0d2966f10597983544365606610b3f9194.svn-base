package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UTsupplierBusCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_TSupplierBusCategory")
public class UTsupplierBusCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7667945996824732135L;
	private Integer busId;
	private Integer tempId;
	private Integer categoryId;
	private Short type;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UTsupplierBusCategory() {
	}

	/** minimal constructor */
	public UTsupplierBusCategory(Integer busId) {
		this.busId = busId;
	}

	/** full constructor */
	public UTsupplierBusCategory(Integer busId, Integer tempId,
			Integer categoryId, Short type, Date createTime) {
		this.busId = busId;
		this.tempId = tempId;
		this.categoryId = categoryId;
		this.type = type;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "BusID", unique = true, nullable = false)
	public Integer getBusId() {
		return this.busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	@Column(name = "TempID")
	public Integer getTempId() {
		return this.tempId;
	}

	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}

	@Column(name = "CategoryID")
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}