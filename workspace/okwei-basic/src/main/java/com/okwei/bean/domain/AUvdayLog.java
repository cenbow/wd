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
 * AUvdayLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_UVDayLog")
public class AUvdayLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -378340993535610389L;
	private Integer uvid;
	private Long weiId;
	private String userIp;
	private String dateStr;
	private Integer activityId;
	private Date createTime;
	private Short fromType;

	// Constructors

	/** default constructor */
	public AUvdayLog() {
	}

	/** full constructor */
	public AUvdayLog(Long weiId, String userIp, String dateStr,
			Integer activityId, Date createTime) {
		this.weiId = weiId;
		this.userIp = userIp;
		this.dateStr = dateStr;
		this.activityId = activityId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "UVID", unique = true, nullable = false)
	public Integer getUvid() {
		return this.uvid;
	}

	public void setUvid(Integer uvid) {
		this.uvid = uvid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "UserIP", length = 20)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "DateStr", length = 20)
	public String getDateStr() {
		return this.dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	@Column(name = "ActivityID")
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CreateTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "FromType")
	public Short getFromType() {
		return fromType;
	}

	public void setFromType(Short fromType) {
		this.fromType = fromType;
	}
}