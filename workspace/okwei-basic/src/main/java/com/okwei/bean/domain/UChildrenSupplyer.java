package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UChildrenSupplyer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ChildrenSupplyer")
public class UChildrenSupplyer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -720559831974814167L;
	// Fields

	private String childrenId;
	private String companyName;
	private Short isOrderSend;
	private Long recommendID;
	private Long verifier;
	private Integer province;
	private Integer city;
	private Integer area;
	private String address;
	
	// Constructors

	/** default constructor */
	public UChildrenSupplyer() {
	}

	/** minimal constructor */
	public UChildrenSupplyer(String childrenId) {
		this.childrenId = childrenId;
	}

	/** full constructor */
	public UChildrenSupplyer(String childrenId, String companyName,Short isOrderSend) {
		this.childrenId = childrenId;
		this.companyName = companyName;
		this.isOrderSend = isOrderSend;
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

	@Column(name = "CompanyName", length = 128)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name = "IsOrderSend")
	public Short getIsOrderSend() {
		return isOrderSend;
	}

	public void setIsOrderSend(Short isOrderSend) {
		this.isOrderSend = isOrderSend;
	}

	
	@Column(name = "Province")
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "City")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "Area")
	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Column(name = "Address", length = 128)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Verifier")
	public Long getVerifier() {
		return verifier;
	}

	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}

	@Column(name = "RecommendID")
	public Long getRecommendID() {
		return recommendID;
	}

	public void setRecommendID(Long recommendID) {
		this.recommendID = recommendID;
	}

	
	
	
}