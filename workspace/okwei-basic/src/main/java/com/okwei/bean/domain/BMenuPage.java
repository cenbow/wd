package com.okwei.bean.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BMenuPage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_MenuPage")
public class BMenuPage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8757192588392433424L;
	private Integer pageId;
	private String pageName;
	private String pageUrl;
	private Long createUser;
	private Date createTime;
	private Long updateUser;
	private Date updateTime;
	private Short isActive;

	// Constructors

	/** default constructor */
	public BMenuPage() {
	}

	/** full constructor */
	public BMenuPage(String pageName, String pageUrl, Long createUser,
			Date createTime, Long updateUser, Date updateTime,
			Short isActive) {
		this.pageName = pageName;
		this.pageUrl = pageUrl;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.isActive = isActive;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "PageID", unique = true, nullable = false)
	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	@Column(name = "PageName", length = 64)
	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Column(name = "PageUrl", length = 64)
	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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