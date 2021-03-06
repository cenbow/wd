package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AActProducts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ActProducts")
public class AActProducts implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840209992889504520L;
	private Long actPid;
	private Long actId;
	private Long proActId;
	private Long productId;
	private Date beginTime;
	private Date endTime;
	private Short actState;
	private Integer sort;

	// Constructors

	/** default constructor */
	public AActProducts() {
	}

	/** minimal constructor */
	public AActProducts(Long actPid, Long proActId) {
		this.actPid = actPid;
		this.proActId = proActId;
	}

	/** full constructor */
	public AActProducts(Long actPid, Long proActId, Long productId,
			Date beginTime, Date endTime, Short actState, Integer sort) {
		this.actPid = actPid;
		this.proActId = proActId;
		this.productId = productId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.actState = actState;
		this.sort = sort;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ActPID", unique = true, nullable = false)
	public Long getActPid() {
		return this.actPid;
	}

	public void setActPid(Long actPid) {
		this.actPid = actPid;
	}

	@Column(name = "ProActID", nullable = false)
	public Long getProActId() {
		return this.proActId;
	}
	
	@Column(name = "ActID", nullable = false)
	public Long getActId() {
		return this.actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	public void setProActId(Long proActId) {
		this.proActId = proActId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "BeginTime", length = 0)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "EndTime", length = 0)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "ActState")
	public Short getActState() {
		return this.actState;
	}

	public void setActState(Short actState) {
		this.actState = actState;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}