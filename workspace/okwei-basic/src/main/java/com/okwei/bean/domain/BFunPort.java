package com.okwei.bean.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BFunPort entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_FunPort")
public class BFunPort implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1515751898410548992L;
	private Integer portId;
	private String portName;
	private String portCode;
	private Integer modelId;
	private Long createUser;
	private Date createTime;
	private Long updateUser;
	private Date updateTime;
	private Short isActive;

	// Constructors

	/** default constructor */
	public BFunPort() {
	}

	/** full constructor */
	public BFunPort(String portName, String portCode, Integer modelId,
			Long createUser, Date createTime, Long updateUser,
			Date updateTime, Short isActive) {
		this.portName = portName;
		this.portCode = portCode;
		this.modelId = modelId;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.isActive = isActive;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "PortID", unique = true, nullable = false)
	public Integer getPortId() {
		return this.portId;
	}

	public void setPortId(Integer portId) {
		this.portId = portId;
	}

	@Column(name = "PortName", length = 64)
	public String getPortName() {
		return this.portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	@Column(name = "PortCode", length = 64)
	public String getPortCode() {
		return this.portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	@Column(name = "ModelID")
	public Integer getModelId() {
		return this.modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	@Column(name = "CreateUser")
	public Long getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateUser")
	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "IsActive")
	public Short getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Short isActive) {
		this.isActive = isActive;
	}

}