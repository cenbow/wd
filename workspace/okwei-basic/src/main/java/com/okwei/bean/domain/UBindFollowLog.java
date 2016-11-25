package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UBindFollowLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_BindFollowLog")
public class UBindFollowLog extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long weiId;
	private Short operateType;
	private String busType;
	private Date operateTime;

	// Constructors

	/** default constructor */
	public UBindFollowLog() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "OperateType")
	public Short getOperateType() {
		return this.operateType;
	}

	public void setOperateType(Short operateType) {
		this.operateType = operateType;
	}

	@Column(name = "BusType", length = 64)
	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	@Column(name = "OperateTime", length = 0)
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

}