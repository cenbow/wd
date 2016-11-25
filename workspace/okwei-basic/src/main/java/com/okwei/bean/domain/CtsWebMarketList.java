package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsWebMarketList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_WebMarketList")
public class CtsWebMarketList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6425704020885067265L;
	private Long listId;
	private Long logId;
	private Integer marketId;
	private String marketName;
	private String mimg;
	private String mainBus;
	private Short sort;
	private Short status;
	private Date createTime;
	private String reason;

	// Constructors

	/** default constructor */
	public CtsWebMarketList() {
	}

	/** full constructor */
	public CtsWebMarketList(Long logId, Integer marketId, String marketName,
			String mimg, String mainBus, Short sort, Short status,
			Date createTime) {
		this.logId = logId;
		this.marketId = marketId;
		this.marketName = marketName;
		this.mimg = mimg;
		this.mainBus = mainBus;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ListID", unique = true, nullable = false)
	public Long getListId() {
		return this.listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	@Column(name = "LogID")
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "MarketID")
	public Integer getMarketId() {
		return this.marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
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
	@Column(name="Reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}