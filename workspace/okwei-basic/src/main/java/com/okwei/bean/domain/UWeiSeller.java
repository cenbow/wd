package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UWeiSeller entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_WeiSeller")
public class UWeiSeller extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private Long sponsorWeiId;
	private String qq;
	private String email;
	private String passWord;
	private String pwd;
	private String weiName;
	private Date registerTime;
	private String realName;
	private String mobilePhone;
	private Integer province;
	private Integer city;
	private Integer district;

	@Column(name = "District")
	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	private String images;
	private String qrCodeUrl;
	private Short states;
	private Short mobileIsBind;
	private Short emailIsBind;
	private String uptree;
	private String ipAddress;

	// Constructors

	/** default constructor */
	public UWeiSeller() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "SponsorWeiID")
	public Long getSponsorWeiId() {
		return this.sponsorWeiId;
	}

	public void setSponsorWeiId(Long sponsorWeiId) {
		this.sponsorWeiId = sponsorWeiId;
	}

	@Column(name = "QQ", length = 50)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "Email", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PassWord", length = 200)
	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "WeiName", length = 100)
	public String getWeiName() {
		return this.weiName;
	}

	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}

	@Column(name = "RegisterTime", length = 0)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "RealName", length = 20)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "MobilePhone", length = 32)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "Province", length = 32)
	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "City", length = 32)
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "Images", length = 200)
	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Column(name = "qrCodeUrl", length = 200)
	public String getQrCodeUrl() {
		return this.qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	@Column(name = "States")
	public Short getStates() {
		return this.states;
	}

	public void setStates(Short states) {
		this.states = states;
	}

	@Column(name = "MobileIsBind")
	public Short getMobileIsBind() {
		return this.mobileIsBind;
	}

	public void setMobileIsBind(Short mobileIsBind) {
		this.mobileIsBind = mobileIsBind;
	}

	@Column(name = "EmailIsBind")
	public Short getEmailIsBind() {
		return this.emailIsBind;
	}

	public void setEmailIsBind(Short emailIsBind) {
		this.emailIsBind = emailIsBind;
	}

	@Column(name = "Uptree", length = 65535)
	public String getUptree() {
		return this.uptree;
	}

	public void setUptree(String uptree) {
		this.uptree = uptree;
	}

	@Column(name = "IpAddress", length = 64)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "PWD", length = 200)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}