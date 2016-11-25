package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PBrand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_Brand")
public class PBrand implements java.io.Serializable {

	// Fields

	private Integer brandId;
	private Long companyNo;
	private String parentType;
	private Integer sort;
	private String brandName;
	private String brandLogo;
	private String brandCertificate;
	private String authorization;
	private String brandStory;
	private String linkMan;
	private String job;
	private String province;
	private Integer provinceId;
	private String city;
	private Integer cityId;
	private String phone;
	private Integer status;
	private Integer adminId;
	private Date reviewTime;
	private Date applyTime;
	private String reason;
	private String remark;
	private Integer shelvesCount;

	// Constructors

	/** default constructor */
	public PBrand() {
	}

	/** full constructor */
	public PBrand(Long companyNo, String parentType, Integer sort,
			String brandName, String brandLogo, String brandCertificate,
			String authorization, String brandStory, String linkMan,
			String job, String province, String city, String phone,
			Integer status, Integer adminId, Date reviewTime,
			Date applyTime, String reason, String remark,
			Integer shelvesCount) {
		this.companyNo = companyNo;
		this.parentType = parentType;
		this.sort = sort;
		this.brandName = brandName;
		this.brandLogo = brandLogo;
		this.brandCertificate = brandCertificate;
		this.authorization = authorization;
		this.brandStory = brandStory;
		this.linkMan = linkMan;
		this.job = job;
		this.province = province;
		this.city = city;
		this.phone = phone;
		this.status = status;
		this.adminId = adminId;
		this.reviewTime = reviewTime;
		this.applyTime = applyTime;
		this.reason = reason;
		this.remark = remark;
		this.shelvesCount = shelvesCount;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "BrandID", unique = true, nullable = false)
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "CompanyNo")
	public Long getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(Long companyNo) {
		this.companyNo = companyNo;
	}

	@Column(name = "ParentType", length = 20)
	public String getParentType() {
		return this.parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "BrandName", length = 100)
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name = "BrandLogo", length = 200)
	public String getBrandLogo() {
		return this.brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	@Column(name = "BrandCertificate", length = 200)
	public String getBrandCertificate() {
		return this.brandCertificate;
	}

	public void setBrandCertificate(String brandCertificate) {
		this.brandCertificate = brandCertificate;
	}

	@Column(name = "Authorization", length = 200)
	public String getAuthorization() {
		return this.authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	@Column(name = "BrandStory", length = 65535)
	public String getBrandStory() {
		return this.brandStory;
	}

	public void setBrandStory(String brandStory) {
		this.brandStory = brandStory;
	}

	@Column(name = "LinkMan", length = 50)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "Job", length = 50)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "Province", length = 50)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "City", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "Phone", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "AdminID")
	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Column(name = "ReviewTime", length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	@Column(name = "ApplyTime", length = 19)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "Reason", length = 65535)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "Remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ShelvesCount")
	public Integer getShelvesCount() {
		return this.shelvesCount;
	}

	public void setShelvesCount(Integer shelvesCount) {
		this.shelvesCount = shelvesCount;
	}
    @Column(name="CityID")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
    @Column(name="ProvinceID")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

}