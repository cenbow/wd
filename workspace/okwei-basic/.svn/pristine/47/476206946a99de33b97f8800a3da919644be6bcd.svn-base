package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UProductShop entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ProductShop")
public class UProductShop implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8729726588132867724L;
	private Integer channelId;
	private Integer demandId;
	private Long weiId;
	private String weiName;
	private String linkMan;
	private String phone;
	private Long supplyId;
	private Integer province;
	private Integer city;
	private Integer area;
	private String address;
	private Date createTime;
	private String regionStr;
	private Integer isCompleted;

	// Constructors

	/** default constructor */
	public UProductShop() {
	}

	

	// Property accessors
	@Id
	@Column(name = "ChannelID", unique = true, nullable = false)
	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	@Column(name = "DemandID")
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "SupplyID")
	public Long getSupplyId() {
		return this.supplyId;
	}

	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
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

	@Column(name = "Area")
	public Integer getArea() {
		return this.area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Column(name = "Address", length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Column(name = "RegionStr", length = 64)
	public String getRegionStr() {
		return regionStr;
	}

	public void setRegionStr(String regionStr) {
		this.regionStr = regionStr;
	}

	@Column(name = "WeiName", length = 64)
	public String getWeiName() {
		return weiName;
	}

	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}


	@Column(name = "LinkMan", length = 32)
	public String getLinkMan() {
		return linkMan;
	}



	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}


	@Column(name = "Phone", length = 16)
	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "isCompleted")
	public Integer getIsCompleted() {
		return this.isCompleted;
	}

	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}