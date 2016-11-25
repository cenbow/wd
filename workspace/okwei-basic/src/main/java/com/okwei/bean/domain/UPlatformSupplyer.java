package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UPlatformSupplyer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_PlatformSupplyer")
public class UPlatformSupplyer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6359680741274534255L;
	// Fields

	private Long weiId;
	private String supplyName;
	private Long verifier;
	private Long followVerifier;
	private Long advisor;
	private String saleType;
	private String logo;
	private String details;
	private Double bond;
	private Integer provice;
	private Integer city;
	private Integer area;
	private String address;
	private String linkMan;
	private String mobilePhone;
	private String telephone;
	private String serviceQq;
	private Date createTime;
	private Double admission;
	private String brandFeature;
	private String idcard;
	private String idcardImg;
	private Integer industry;
	private String licenseImg;
	private Integer payType;
	private Date joinTime;
	private Short state;
	private String appDetails;
	private String appBrandFeature;
	// Constructors

	/** default constructor */
	public UPlatformSupplyer() {
	}

	/** minimal constructor */
	public UPlatformSupplyer(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public UPlatformSupplyer(Long weiId, String supplyName, Long verifier, String saleType, String logo, String details, Integer provice,
			Integer city, Integer area, String address, String linkMan, String mobilePhone, String telephone, String serviceQq, Date createTime,
			 String brandFeature, String idcard, String idcardImg, Integer industry, String licenseImg, Integer payType) {
		this.weiId = weiId;
		this.supplyName = supplyName;
		this.verifier = verifier;
		this.saleType = saleType;
		this.logo = logo;
		this.details = details;
		this.provice = provice;
		this.city = city;
		this.area = area;
		this.address = address;
		this.linkMan = linkMan;
		this.mobilePhone = mobilePhone;
		this.telephone = telephone;
		this.serviceQq = serviceQq;
		this.createTime = createTime;
		this.brandFeature = brandFeature;
		this.idcard = idcard;
		this.idcardImg = idcardImg;
		this.industry = industry;
		this.licenseImg = licenseImg;
		this.payType = payType;
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

	@Column(name = "SupplyName", length = 64)
	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name = "Verifier")
	public Long getVerifier() {
		return this.verifier;
	}

	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}

	@Column(name = "SaleType", length = 64)
	public String getSaleType() {
		return this.saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	@Column(name = "Logo", length = 128)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "Details", length = 65535)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "Bond", precision = 10, scale = 0)
	public Double getBond() {
		return this.bond;
	}

	public void setBond(Double bond) {
		this.bond = bond;
	}

	@Column(name = "Provice")
	public Integer getProvice() {
		return this.provice;
	}

	public void setProvice(Integer provice) {
		this.provice = provice;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "Area")
	public Integer getArea() {
		return this.area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Column(name = "Address", length = 64)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "LinkMan", length = 16)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "MobilePhone", length = 16)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "Telephone", length = 16)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "ServiceQQ", length = 64)
	public String getServiceQq() {
		return this.serviceQq;
	}

	public void setServiceQq(String serviceQq) {
		this.serviceQq = serviceQq;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Admission")
	public Double getAdmission() {
		return this.admission;
	}

	public void setAdmission(Double admission) {
		this.admission = admission;
	}

	@Column(name = "BrandFeature")
	public String getBrandFeature() {
		return this.brandFeature;
	}

	public void setBrandFeature(String brandFeature) {
		this.brandFeature = brandFeature;
	}

	@Column(name = "IDCard", length = 32)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "IDCardImg", length = 128)
	public String getIdcardImg() {
		return this.idcardImg;
	}

	public void setIdcardImg(String idcardImg) {
		this.idcardImg = idcardImg;
	}

	@Column(name = "Industry")
	public Integer getIndustry() {
		return this.industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	@Column(name = "LicenseImg", length = 128)
	public String getLicenseImg() {
		return this.licenseImg;
	}

	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}

	@Column(name = "PayType")
	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	@Column(name = "JoinTime", length = 19)
	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	@Column(name = "FollowVerifier")
	public Long getFollowVerifier() {
		return followVerifier;
	}

	public void setFollowVerifier(Long followVerifier) {
		this.followVerifier = followVerifier;
	}
	
	@Column(name = "Advisor")
	public Long getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Long advisor) {
		this.advisor = advisor;
	}
	
	@Column(name = "State")
	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "AppDetails")
	public String getAppDetails() {
		return appDetails;
	}

	public void setAppDetails(String appDetails) {
		this.appDetails = appDetails;
	}

	@Column(name = "AppBrandFeature")
	public String getAppBrandFeature() {
		return appBrandFeature;
	}

	public void setAppBrandFeature(String appBrandFeature) {
		this.appBrandFeature = appBrandFeature;
	}
	
	

}