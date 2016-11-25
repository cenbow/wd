package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsWebRecLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_WebRecLog")
public class CtsWebRecLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 843927720938615285L;
	private Long logId;
	private Short recType;
	private String recName;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Long creater;
	private Short status;

	// Constructors

	/** default constructor */
	public CtsWebRecLog() {
	}

	/** full constructor */
	public CtsWebRecLog(Short recType, String recName, Date beginTime,
			Date endTime, Date createTime, Long creater, Short status) {
		this.recType = recType;
		this.recName = recName;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.creater = creater;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "LogID", unique = true, nullable = false)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "RecType")
	public Short getRecType() {
		return this.recType;
	}

	public void setRecType(Short recType) {
		this.recType = recType;
	}

	@Column(name = "RecName", length = 32)
	public String getRecName() {
		return this.recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	@Column(name = "BeginTime", length = 19)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "EndTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Creater")
	public Long getCreater() {
		return this.creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}