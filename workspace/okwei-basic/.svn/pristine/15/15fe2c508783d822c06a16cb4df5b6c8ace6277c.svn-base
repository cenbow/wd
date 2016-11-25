package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminAuthority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_authority")
public class AdminAuthority implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667626881285264286L;
	private Integer authorityid;
	private String authority;
	private Date createTime;
	private Long creater;
	private Date updateTime;
	private Long updater;

	// Constructors

	/** default constructor */
	public AdminAuthority() {
	}

	/** full constructor */
	public AdminAuthority(String authority, Date createTime, Long creater,
			Date updateTime, Long updater) {
		this.authority = authority;
		this.createTime = createTime;
		this.creater = creater;
		this.updateTime = updateTime;
		this.updater = updater;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "authorityid", unique = true, nullable = false)
	public Integer getAuthorityid() {
		return this.authorityid;
	}

	public void setAuthorityid(Integer authorityid) {
		this.authorityid = authorityid;
	}

	@Column(name = "authority", length = 512)
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "creater")
	public Long getCreater() {
		return this.creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	@Column(name = "updateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "updater")
	public Long getUpdater() {
		return this.updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

}