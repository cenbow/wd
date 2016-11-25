package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplierIndustryCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplierIndustryCategory")
public class USupplierIndustryCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7728591981523389997L;
	// Fields

	private Integer busId;
	private Long weiId;
	private Integer categoryId;
	private Short type;
	private Date createTime;

	// Constructors

	/** default constructor */
	public USupplierIndustryCategory() {
	}

	/** full constructor */
	public USupplierIndustryCategory(Long weiId, Integer categoryId,
			Short type, Date createTime) {
		this.weiId = weiId;
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

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
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