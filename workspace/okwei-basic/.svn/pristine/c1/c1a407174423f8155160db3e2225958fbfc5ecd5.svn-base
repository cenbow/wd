package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplyer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Supplyer")
public class USupplyer extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private String inChargeMan;
	private String mobilePhone;
	private String id;
	private String idnum;
	private String businessLicence;
	private String businessLicenceNo;
	private String companyName;
	private String supplierLogo;
	private Short type;
	private String serviceQQ;
	private String busContent;
	private Short activityType;//活动类型供应商标志
	// Constructors

	/** default constructor */
	public USupplyer() {
	}

	/** full constructor */
	public USupplyer(String inChargeMan, String mobilePhone, String id, String idnum, String businessLicence, String businessLicenceNo, String companyName,
			String supplierLogo, Short type,Short activityType) {
		this.inChargeMan = inChargeMan;
		this.mobilePhone = mobilePhone;
		this.id = id;
		this.idnum = idnum;
		this.businessLicence = businessLicence;
		this.businessLicenceNo = businessLicenceNo;
		this.companyName = companyName;
		this.supplierLogo = supplierLogo;
		this.type = type;
		this.activityType=activityType;
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

	@Column(name = "InChargeMan", length = 32)
	public String getInChargeMan() {
		return this.inChargeMan;
	}

	public void setInChargeMan(String inChargeMan) {
		this.inChargeMan = inChargeMan;
	}

	@Column(name = "MobilePhone", length = 32)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "ID", length = 128)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "IDNum", length = 32)
	public String getIdnum() {
		return this.idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	@Column(name = "BusinessLicence", length = 128)
	public String getBusinessLicence() {
		return this.businessLicence;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}

	@Column(name = "BusinessLicenceNo", length = 32)
	public String getBusinessLicenceNo() {
		return this.businessLicenceNo;
	}

	public void setBusinessLicenceNo(String businessLicenceNo) {
		this.businessLicenceNo = businessLicenceNo;
	}

	@Column(name = "CompanyName", length = 128)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "SupplierLogo", length = 128)
	public String getSupplierLogo() {
		return this.supplierLogo;
	}

	public void setSupplierLogo(String supplierLogo) {
		this.supplierLogo = supplierLogo;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}
	
	@Column(name = "ActivityType")
	public Short getActivityType() {
		return this.activityType;
	}

	public void setActivityType(Short activityType) {
		this.activityType = activityType;
	}
	
	
	
    @Column(name="ServiceQQ",length=128)
	public String getServiceQQ() {
		return serviceQQ;
	}

	public void setServiceQQ(String serviceQQ) {
		this.serviceQQ = serviceQQ;
	}
	@Column(name="BusContent",length=128)
	public String getBusContent() {
		return busContent;
	}

	public void setBusContent(String busContent) {
		this.busContent = busContent;
	}

}