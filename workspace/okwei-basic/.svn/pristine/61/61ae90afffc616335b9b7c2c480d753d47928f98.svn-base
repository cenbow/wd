package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplyDemandArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplyDemandArea")
public class USupplyDemandArea implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6230426575646105173L;
	// Fields

	private Integer demandAreaId;
	private Integer requiredId;
	private Integer demandId;
	private Integer code;
	private Integer province;
	private Integer city;
	private Integer area;
	private Date createTime;

	// Constructors

	/** default constructor */
	public USupplyDemandArea() {
	}

	/** full constructor */
	public USupplyDemandArea(Integer requiredId, Integer demandId,
			Integer code, Integer province, Integer city, Integer area,
			Date createTime) {
		this.requiredId = requiredId;
		this.demandId = demandId;
		this.code = code;
		this.province = province;
		this.city = city;
		this.area = area;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "DemandAreaID", unique = true, nullable = false)
	public Integer getDemandAreaId() {
		return this.demandAreaId;
	}

	public void setDemandAreaId(Integer demandAreaId) {
		this.demandAreaId = demandAreaId;
	}

	@Column(name = "RequiredID")
	public Integer getRequiredId() {
		return this.requiredId;
	}

	public void setRequiredId(Integer requiredId) {
		this.requiredId = requiredId;
	}

	@Column(name = "DemandID")
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "Code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}