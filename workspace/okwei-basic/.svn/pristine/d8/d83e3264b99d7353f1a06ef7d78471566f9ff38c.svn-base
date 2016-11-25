package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PParamKey entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ParamKey")
public class PParamKey extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer attributeId;
	private Integer mid;
	private Integer classid;
	private String attributeName;
	private Short sort;
	private Short showtype;
	private Short ischooseparam;

	@Column(name = "ShowType")
	public Short getShowtype() {
		return showtype;
	}

	public void setShowtype(Short showtype) {
		this.showtype = showtype;
	}

	@Column(name = "IsChooseParam")
	public Short getIschooseparam() {
		return ischooseparam;
	}

	public void setIschooseparam(Short ischooseparam) {
		this.ischooseparam = ischooseparam;
	}

	private Short isMust;

	@Column(name = "SysAttributeID")
	private Integer sysAttributeID;

	private Date createTime;
	private Date updateTime;

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

	public Integer getSysAttributeID() {
		return sysAttributeID;
	}

	public void setSysAttributeID(Integer sysAttributeID) {
		this.sysAttributeID = sysAttributeID;
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

	@Column(name = "ClassID")
	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

}