package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AActivityTimespans entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ActivityTimespans")
public class AActivityTimespans implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2332838746480876819L;
	private Integer id;
	private String beginTimeStr;
	private String endTimeStr;
	private Integer shours;
	private Integer sminutes;
	private Integer sseconds;
	private Integer ehours;
	private Integer eminutes;
	private Integer eseconds;

	// Constructors

	/** default constructor */
	public AActivityTimespans() {
	}

	/** minimal constructor */
	public AActivityTimespans(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AActivityTimespans(Integer id, String beginTimeStr,
			String endTimeStr, Integer shours, Integer sminutes,
			Integer sseconds, Integer ehours, Integer eminutes, Integer eseconds) {
		this.id = id;
		this.beginTimeStr = beginTimeStr;
		this.endTimeStr = endTimeStr;
		this.shours = shours;
		this.sminutes = sminutes;
		this.sseconds = sseconds;
		this.ehours = ehours;
		this.eminutes = eminutes;
		this.eseconds = eseconds;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "BeginTimeStr", length = 128)
	public String getBeginTimeStr() {
		return this.beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	@Column(name = "EndTimeStr", length = 128)
	public String getEndTimeStr() {
		return this.endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	@Column(name = "SHours")
	public Integer getShours() {
		return this.shours;
	}

	public void setShours(Integer shours) {
		this.shours = shours;
	}

	@Column(name = "SMinutes")
	public Integer getSminutes() {
		return this.sminutes;
	}

	public void setSminutes(Integer sminutes) {
		this.sminutes = sminutes;
	}

	@Column(name = "SSeconds")
	public Integer getSseconds() {
		return this.sseconds;
	}

	public void setSseconds(Integer sseconds) {
		this.sseconds = sseconds;
	}

	@Column(name = "EHours")
	public Integer getEhours() {
		return this.ehours;
	}

	public void setEhours(Integer ehours) {
		this.ehours = ehours;
	}

	@Column(name = "EMinutes")
	public Integer getEminutes() {
		return this.eminutes;
	}

	public void setEminutes(Integer eminutes) {
		this.eminutes = eminutes;
	}

	@Column(name = "ESeconds")
	public Integer getEseconds() {
		return this.eseconds;
	}

	public void setEseconds(Integer eseconds) {
		this.eseconds = eseconds;
	}

}