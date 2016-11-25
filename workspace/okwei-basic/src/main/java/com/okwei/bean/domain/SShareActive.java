package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SShareActive entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "S_ShareActive")
public class SShareActive implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5745017116426116633L;
	// Fields

	private Long activeId;
	private Long shareId;
	private Long weiId;
	private Date createTime;
	private Long makeWeiId;
	private Short status;
	
	// Constructors

	/** default constructor */
	public SShareActive() {
	}

	/** minimal constructor */
	public SShareActive(Long shareId) {
		this.shareId = shareId;
	}

	/** full constructor */
	public SShareActive(Long shareId, Long weiId, Date createTime) {
		this.shareId = shareId;
		this.weiId = weiId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ActiveID", unique = true, nullable = false)
	public Long getActiveId() {
		return this.activeId;
	}

	public void setActiveId(Long activeId) {
		this.activeId = activeId;
	}
	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	@Column(name = "ShareID", nullable = false)
	public Long getShareId() {
		return this.shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}
	
	@Column(name = "MakeWeiID", nullable = false)
	public Long getMakeWeiId() {
		return this.makeWeiId;
	}

	public void setMakeWeiId(Long makeWeiId) {
		this.makeWeiId = makeWeiId;
	}
	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}