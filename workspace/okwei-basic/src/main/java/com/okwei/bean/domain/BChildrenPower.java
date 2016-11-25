package com.okwei.bean.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BChildrenPower entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_ChildrenPower")
public class BChildrenPower implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2566831199449454497L;
	// Fields

	private String childrenId;
	private Long parentId;
	private String funIdset;
	private Long createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public BChildrenPower() {
	}

	/** full constructor */
	public BChildrenPower(Long parentId, String funIdset, Long createUser,
			Date createTime, String updateUser, Date updateTime) {
		this.parentId = parentId;
		this.funIdset = funIdset;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ChildrenID", unique = true, nullable = false, length = 64)
	public String getChildrenId() {
		return this.childrenId;
	}

	public void setChildrenId(String childrenId) {
		this.childrenId = childrenId;
	}

	@Column(name = "ParentID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "FunIDSet", length = 256)
	public String getFunIdset() {
		return this.funIdset;
	}

	public void setFunIdset(String funIdset) {
		this.funIdset = funIdset;
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

	@Column(name = "UpdateUser", length = 64)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}