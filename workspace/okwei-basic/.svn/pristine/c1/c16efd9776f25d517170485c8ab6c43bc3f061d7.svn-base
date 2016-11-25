package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UWeiSellerLoginLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_WeiSellerLoginLog")
public class UWeiSellerLoginLog extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long weiId;
	private Date loginTime;
	private String tiket;
	private String loginIp;
	private Short loginType;
	private Short terrminateType;
	private String companyName;
	private String email;
	private Short identityType;

	// Constructors

	/** default constructor */
	public UWeiSellerLoginLog() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "LoginTime", length = 0)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "Tiket", length = 50)
	public String getTiket() {
		return this.tiket;
	}

	public void setTiket(String tiket) {
		this.tiket = tiket;
	}

	@Column(name = "LoginIP", length = 20)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "LoginType")
	public Short getLoginType() {
		return this.loginType;
	}

	public void setLoginType(Short loginType) {
		this.loginType = loginType;
	}

	@Column(name = "TerrminateType")
	public Short getTerrminateType() {
		return this.terrminateType;
	}

	public void setTerrminateType(Short terrminateType) {
		this.terrminateType = terrminateType;
	}

	@Column(name = "CompanyName", length = 128)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "EMail", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IdentityType")
	public Short getIdentityType() {
		return this.identityType;
	}

	public void setIdentityType(Short identityType) {
		this.identityType = identityType;
	}

}