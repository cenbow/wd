package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductParamKv entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductParamKV")
public class PProductParamKv extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long kvid;
	private Long productId;
	private String paramName;
	private String paramValue;
	private Date createTime;

	@Column(name = "SysAVID")
	private Integer sysAVID;

	@Column(name = "SysAttributeID")
	private Integer sysAttributeID;

	@Column(name = "AVID")
	private Integer aVID;

	@Column(name = "AttributeID")
	private Integer attributeID;

	@Id
	@GeneratedValue
	@Column(name = "KVID")
	public Long getKvid() {
		return kvid;
	}

	public void setKvid(Long kvid) {
		this.kvid = kvid;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "ParamName", length = 32)
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "ParamValue", length = 32)
	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSysAVID() {
		return sysAVID;
	}

	public void setSysAVID(Integer sysAVID) {
		this.sysAVID = sysAVID;
	}

	public Integer getSysAttributeID() {
		return sysAttributeID;
	}

	public void setSysAttributeID(Integer sysAttributeID) {
		this.sysAttributeID = sysAttributeID;
	}

	public Integer getaVID() {
		return aVID;
	}

	public void setaVID(Integer aVID) {
		this.aVID = aVID;
	}

	public Integer getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(Integer attributeID) {
		this.attributeID = attributeID;
	}

}