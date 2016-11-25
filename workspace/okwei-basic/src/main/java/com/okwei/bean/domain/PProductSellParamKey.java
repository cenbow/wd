package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductSellParamKey entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductSellParamKey")
public class PProductSellParamKey extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer attributeId;
	private Integer mid;
	private String attributeName;
	private Short sort;
	private Short isMust;
	private Short isupImg;
	private Integer classId;

	@Column(name = "IsUpImg")
	public Short getIsupImg() {
		return isupImg;
	}

	public void setIsupImg(Short isupImg) {
		this.isupImg = isupImg;
	}

	@Column(name = "ClassID")
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public PProductSellParamKey() {
	}

	/** full constructor */
	public PProductSellParamKey(Integer mid, String attributeName, Short sort, Short isMust, Date createTime, Date updateTime) {
		this.mid = mid;
		this.attributeName = attributeName;
		this.sort = sort;
		this.isMust = isMust;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AttributeID", unique = true, nullable = false)
	public Integer getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	@Column(name = "MID")
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "AttributeName", length = 64)
	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "IsMust")
	public Short getIsMust() {
		return this.isMust;
	}

	public void setIsMust(Short isMust) {
		this.isMust = isMust;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}