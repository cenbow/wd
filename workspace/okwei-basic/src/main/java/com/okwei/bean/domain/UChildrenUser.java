package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UChildrenUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ChildrenUser")
public class UChildrenUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6369238653364309575L;
	// Fields

	private String childrenId;
	private Long parentId;
	private String passWord;
	private String userName;
	private String mobilePhone;
	private String noPassReason;
	private Integer state;
	private Integer type;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public UChildrenUser() {
	}

	/** minimal constructor */
	public UChildrenUser(String childrenId) {
		this.childrenId = childrenId;
	}

	/** full constructor */
	public UChildrenUser(String childrenId, Long parentId, String passWord,
			String userName, String mobilePhone, Integer state, Integer type,
			String createUser, Date createTime, String updateUser,
			Date updateTime) {
		this.childrenId = childrenId;
		this.parentId = parentId;
		this.passWord = passWord;
		this.userName = userName;
		this.mobilePhone = mobilePhone;
		this.state = state;
		this.type = type;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
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

	@Column(name = "PassWord", length = 64)
	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "UserName", length = 64)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "MobilePhone", length = 16)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "State")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "Type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "CreateUser", length = 64)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
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

	@Column(name = "NoPassReason", length = 256)
	public String getNoPassReason() {
		return noPassReason;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

}