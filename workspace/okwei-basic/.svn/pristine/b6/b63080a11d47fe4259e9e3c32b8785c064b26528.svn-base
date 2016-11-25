package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PcMarketRecommand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PC_MarketRecommand")
public class PcMarketRecommand implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long reId;
	private Integer bmid;
	private String marketName;
	private String mimg;
	private String mainBus;
	private Integer bigType;
	private Short sort;
	private Short status;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public PcMarketRecommand() {
	}

	/** full constructor */
	public PcMarketRecommand(Integer bmid, String marketName, String mimg,
			String mainBus, Integer bigType, Short sort, Short status,
			Date createTime, Date updateTime) {
		this.bmid = bmid;
		this.marketName = marketName;
		this.mimg = mimg;
		this.mainBus = mainBus;
		this.bigType = bigType;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id	
	@Column(name = "ReID", unique = true, nullable = false)
	public Long getReId() {
		return this.reId;
	}

	public void setReId(Long reId) {
		this.reId = reId;
	}

	@Column(name = "BMID")
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	@Column(name = "MarketName", length = 64)
	public String getMarketName() {
		return this.marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	@Column(name = "MImg", length = 128)
	public String getMimg() {
		return this.mimg;
	}

	public void setMimg(String mimg) {
		this.mimg = mimg;
	}

	@Column(name = "MainBus", length = 128)
	public String getMainBus() {
		return this.mainBus;
	}

	public void setMainBus(String mainBus) {
		this.mainBus = mainBus;
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