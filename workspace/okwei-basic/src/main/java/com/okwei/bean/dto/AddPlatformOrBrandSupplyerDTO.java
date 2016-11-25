package com.okwei.bean.dto;

import com.okwei.bean.domain.USupplyer;

public class AddPlatformOrBrandSupplyerDTO extends USupplyer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String supplyName;  //公司名
	private Long verifier;  //认证员
	private String logo;  //公司商标
	private String details;  //公司简介
	private Long bond;  //保证金
	private Integer provice;  //省
	private Integer city;  //市
	private Integer area;  //区
	private String address;  //公司的详细地址
	private String linkMan;  //联系人
	private String mobilePhone;  //手机号码
	private String telephone;  //电话号码
	
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public Long getVerifier() {
		return verifier;
	}
	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Long getBond() {
		return bond;
	}
	public void setBond(Long bond) {
		this.bond = bond;
	}
	public Integer getProvice() {
		return provice;
	}
	public void setProvice(Integer provice) {
		this.provice = provice;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
