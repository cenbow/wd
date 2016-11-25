package com.okwei.bean.domain;

import java.util.Date;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PClassForBrand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ClassForBrand")
public class PClassForBrand implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2234597561765724139L;
	private Long cfbId;
	private Integer brandId;
	private Integer typeId;
	private Date createTime;
	private Long weiId;

	// Constructors

	/** default constructor */
	public PClassForBrand() {
	}

	/** full constructor */
	public PClassForBrand(Integer brandId, Integer typeId, Date createTime) {
		this.brandId = brandId;
		this.typeId = typeId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "CfbID", unique = true, nullable = false)
	public Long getCfbId() {
		return this.cfbId;
	}

	public void setCfbId(Long cfbId) {
		this.cfbId = cfbId;
	}

	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "TypeID")
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	
}