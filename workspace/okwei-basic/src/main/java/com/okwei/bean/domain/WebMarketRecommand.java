package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WebMarketRecommand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Web_MarketRecommand")
public class WebMarketRecommand implements java.io.Serializable {

	// Fields

	private Integer reId;
	private Integer bmid;
	private Integer bigType;
	private Short sort;
	private Short status;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public WebMarketRecommand() {
	}

	/** full constructor */
	public WebMarketRecommand(Integer bmid, Integer bigType, Short sort,
			Short status, Date createTime, Date updateTime) {
		this.bmid = bmid;
		this.bigType = bigType;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ReID", unique = true, nullable = false)
	public Integer getReId() {
		return this.reId;
	}

	public void setReId(Integer reId) {
		this.reId = reId;
	}

	@Column(name = "BMID")
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	@Column(name = "BigType")
	public Integer getBigType() {
		return this.bigType;
	}

	public void setBigType(Integer bigType) {
		this.bigType = bigType;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}