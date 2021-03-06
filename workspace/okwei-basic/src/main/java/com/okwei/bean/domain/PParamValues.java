package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PParamValues entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ParamValues")
public class PParamValues extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer avid;
	private Integer attributeId;
	private String value;
	private Short isDefault;
	private Date createTime;
	private Date updateTime;
	private Short state;

	@Column(name = "SysAVID")
	private Integer sysAVID;

	// Constructors

	/** default constructor */
	public PParamValues() {
	}

	/** full constructor */
	public PParamValues(Integer attributeId, String value, Short isDefault, Date createTime, Date updateTime, Short state) {
		this.attributeId = attributeId;
		this.value = value;
		this.isDefault = isDefault;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AVID", unique = true, nullable = false)
	public Integer getAvid() {
		return this.avid;
	}

	public void setAvid(Integer avid) {
		this.avid = avid;
	}

	@Column(name = "AttributeID")
	public Integer getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	@Column(name = "Value", length = 64)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "IsDefault")
	public Short getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
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

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Integer getSysAVID() {
		return sysAVID;
	}

	public void setSysAVID(Integer sysAVID) {
		this.sysAVID = sysAVID;
	}

}