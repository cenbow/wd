package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * APvdayCount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_PVDayCount")
public class APvdayCount implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922228608337929452L;
	// Fields

	private Integer pvid;
	private String dateStr;
	private Integer dayCount;
	private Integer activityId;
	private Short fromType;
	private Date createTime;

	// Constructors

	/** default constructor */
	public APvdayCount() {
	}

	/** full constructor */
	public APvdayCount(String dateStr, Integer dayCount, Integer activityId,
			Date createTime) {
		this.dateStr = dateStr;
		this.dayCount = dayCount;
		this.activityId = activityId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "PVID", unique = true, nullable = false)
	public Integer getPvid() {
		return this.pvid;
	}

	public void setPvid(Integer pvid) {
		this.pvid = pvid;
	}

	@Column(name = "DateStr", length = 20)
	public String getDateStr() {
		return this.dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	@Column(name = "DayCount")
	public Integer getDayCount() {
		return this.dayCount;
	}

	public void setDayCount(Integer dayCount) {
		this.dayCount = dayCount;
	}

	@Column(name = "ActivityID")
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	
	@Column(name = "FromType")
	public Short getFromType() {
		return fromType;
	}

	public void setFromType(Short fromType) {
		this.fromType = fromType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CreateTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}