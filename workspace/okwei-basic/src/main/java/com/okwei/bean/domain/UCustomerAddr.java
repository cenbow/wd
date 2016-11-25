package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UCustomerAddr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_CustomerAddr")
public class UCustomerAddr extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer caddrId;
	private Long weiId;
	private String receiverName;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer street;
	private String detailAddr;
	private String mobilePhone;
	private String qq;
	private Short isDefault;
	private Date registerTime;
	private Date updateTime; 
	private Integer isShopAddress;
	// Constructors

	/** default constructor */
	public UCustomerAddr() {
	}

	@Id
	@GeneratedValue
	@Column(name = "CaddrID")
	public Integer getCaddrId() {
		return caddrId;
	}

	public void setCaddrId(Integer caddrId) {
		this.caddrId = caddrId;
	}

	@Column(name = "IsShopAddress")
	public Integer getIsShopAddress() {
		return this.isShopAddress;
	}
	
	public void setIsShopAddress(Integer isShopAddress) {
		this.isShopAddress = isShopAddress;
	}
	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ReceiverName", length = 64)
	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Column(name = "Province")
	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "District")
	public Integer getDistrict() {
		return this.district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@Column(name = "Street")
	public Integer getStreet() {
		return street;
	}

	public void setStreet(Integer street) {
		this.street = street;
	}

	@Column(name = "DetailAddr", length = 128)
	public String getDetailAddr() {
		return this.detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	@Column(name = "MobilePhone", length = 32)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "QQ", length = 32)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "RegisterTime", length = 0)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "UpdateTime", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "IsDefault")
	public Short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

}