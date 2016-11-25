package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsMarketLeftList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_MarketLeftList")
public class CtsMarketLeftList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3995003406143128583L;
	private Long listId;
	private Long logId;
	private String img;
	private String linkUrl;
	private Short sort;
	private Short status;
	private Date createTime;
	private String reason;

	// Constructors

	/** default constructor */
	public CtsMarketLeftList() {
	}

	/** full constructor */
	public CtsMarketLeftList(Long logId, String img, String linkUrl,
			Short sort, Short status, Date createTime) {
		this.logId = logId;
		this.img = img;
		this.linkUrl = linkUrl;
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

	@Column(name = "Img", length = 128)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "LinkUrl", length = 128)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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