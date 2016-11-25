package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsHotSupList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_HotSupList")
public class CtsHotSupList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4112755049547909004L;
	// Fields

	private Long hotId;
	private Long logId;
	private Long weiid;
	private String shopName;
	private Short sort;
	private Short status;
	private Short type;
	private Long creater;
	private Date creatTime;
	private String reason;

	// Constructors

	/** default constructor */
	public CtsHotSupList() {
	}

	/** full constructor */
	public CtsHotSupList(Long logId, Long weiid, Short sort, Short status, Short type, Date creatTime) {
		this.logId = logId;
		this.weiid = weiid;
		this.sort = sort;
		this.status = status;
		this.type = type;
		this.creatTime = creatTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "HotID", unique = true, nullable = false)
	public Long getHotId() {
		return this.hotId;
	}

	public void setHotId(Long hotId) {
		this.hotId = hotId;
	}

	@Column(name = "LogID")
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "Weiid")
	public Long getWeiid() {
		return this.weiid;
	}

	public void setWeiid(Long weiid) {
		this.weiid = weiid;
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

	@Column(name = "Type")
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Creater")
	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	@Column(name = "CreatTime", length = 19)
	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	@Column(name="Reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name="ShopName")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	
}