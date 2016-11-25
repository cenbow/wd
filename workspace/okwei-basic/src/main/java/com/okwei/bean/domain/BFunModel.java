package com.okwei.bean.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BFunModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_FunModel")
public class BFunModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977657426690773973L;
	private Integer funId;
	private Integer modelId;
	private String funName;
	private String funCodeSet;
	private String menuSet;
	private Long createUser;
	private Date createTime;
	private Long updateUser;
	private Date updateTime;
	private Short isActive;

	// Constructors

	/** default constructor */
	public BFunModel() {
	}

	/** full constructor */
	public BFunModel(Integer modelId, String funName, String funCodeSet,
			String menuSet, Long createUser, Date createTime,
			Long updateUser, Date updateTime, Short isActive) {
		this.modelId = modelId;
		this.funName = funName;
		this.funCodeSet = funCodeSet;
		this.menuSet = menuSet;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.isActive = isActive;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "FunID", unique = true, nullable = false)
	public Integer getFunId() {
		return this.funId;
	}

	public void setFunId(Integer funId) {
		this.funId = funId;
	}

	@Column(name = "ModelID")
	public Integer getModelId() {
		return this.modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	@Column(name = "FunName", length = 64)
	public String getFunName() {
		return this.funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	@Column(name = "FunCodeSet", length = 1024)
	public String getFunCodeSet() {
		return this.funCodeSet;
	}

	public void setFunCodeSet(String funCodeSet) {
		this.funCodeSet = funCodeSet;
	}

	@Column(name = "MenuSet", length = 1024)
	public String getMenuSet() {
		return this.menuSet;
	}

	public void setMenuSet(String menuSet) {
		this.menuSet = menuSet;
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