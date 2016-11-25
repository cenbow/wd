package com.okwei.bean.domain;
 
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAgenArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_AgenArea")
public class UAgenArea implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2771434281290094347L;
	// Fields

	private Integer aid;
	private Integer demandId;
	private Integer channelId;
	private Long supplyId;
	private Integer code;
	private Integer provice;
	private Integer city;
	private Integer area;
	private Date createTime;
	private String regionStr;
	

	// Constructors

	/** default constructor */
	public UAgenArea() {
	}


	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "AID", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "DemandID")
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "ChannelID")
	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	@Column(name = "Code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	@Column(name = "SupplyID")
	public Long getSupplyId() {
		return supplyId;
	}


	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
	}

}