package com.okwei.bean.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ARecruitPartner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_RecruitPartner")
public class ARecruitPartner implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer partnerId;
	private String name;
	private String phone;
	private Integer province;
	private Integer city;
	private String advantage;
	private Long referee;
	private String classType;
	private Date createTime;

	// Constructors

	/** default constructor */
	public ARecruitPartner() {
	}

	/** full constructor */
	public ARecruitPartner(String name, Integer province, Integer city,
			String advantage, Long referee, String classType,
			Date createTime) {
		this.name = name;
		this.province = province;
		this.city = city;
		this.advantage = advantage;
		this.referee = referee;
		this.classType = classType;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PartnerID", unique = true, nullable = false)
	public Integer getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	@Column(name = "Name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "Advantage", length = 512)
	public String getAdvantage() {
		return this.advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	@Column(name = "Referee")
	public Long getReferee() {
		return this.referee;
	}

	public void setReferee(Long referee) {
		this.referee = referee;
	}

	@Column(name = "ClassType", length = 32)
	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Phone", length = 16)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}