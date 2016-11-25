package com.okwei.bean.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_UserRole")
public class BUserRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1838177029001255686L;
	private Long weiId;
	private String roleSet;
	private String funIdset;
	private Long createUser;
	private Date createTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public BUserRole() {
	}

	/** full constructor */
	public BUserRole(String roleSet, String funIdset, Long createUser,
			Date createTime, Long updateUser, Date updateTime) {
		this.roleSet = roleSet;
		this.funIdset = funIdset;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "RoleSet", length = 512)
	public String getRoleSet() {
		return this.roleSet;
	}

	public void setRoleSet(String roleSet) {
		this.roleSet = roleSet;
	}

	@Column(name = "FunIDSet", length = 512)
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

}