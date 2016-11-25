package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBatchMarketDuty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BatchMarketDuty" )
public class TBatchMarketDuty extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer bmid;
	private Long verWeiId;
	private Short state;
	private Date dutyDate;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TBatchMarketDuty() {
	}

	/** full constructor */
	public TBatchMarketDuty(Integer bmid, Long verWeiId, Short state, Date dutyDate, Date createTime) {
		this.bmid = bmid;
		this.verWeiId = verWeiId;
		this.state = state;
		this.dutyDate = dutyDate;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "BMID")
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	@Column(name = "VerWeiID")
	public Long getVerWeiId() {
		return this.verWeiId;
	}

	public void setVerWeiId(Long verWeiId) {
		this.verWeiId = verWeiId;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "DutyDate", length = 0)
	public Date getDutyDate() {
		return this.dutyDate;
	}

	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}