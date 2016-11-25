package com.okwei.bean.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BRolePower entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_RolePower")
public class BRolePower implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5281491636897662821L;
	// Fields

	private Integer roleId;
	private String roleName;
	private String roleCode;
	private String funIdset;
	private Long createUser;
	private Date createTime;
	private Long updateUser;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public BRolePower() {
	}

	/** full constructor */
	public BRolePower(String roleName, String roleCode, String funIdset,
			Long createUser, Date createTime, Long updateUser,
			Date updateTime) {
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.funIdset = funIdset;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "RoleID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "RoleName", length = 64)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "RoleCode", length = 64)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "FunIDSet", length = 1024)
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