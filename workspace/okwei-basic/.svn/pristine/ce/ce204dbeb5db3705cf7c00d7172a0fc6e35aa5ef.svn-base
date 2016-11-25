package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAdvisor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Advisor")
public class UAdvisor extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private String region;
	private Short type;
	private Date createTime;
	private Long createMan;
	private Long updateMan;
	private Date updateTime;
	private Short status;

	// Constructors

	/** default constructor */
	public UAdvisor() {
	}

	/** full constructor */
	public UAdvisor(String region, Short type, Date createTime, Long createMan, Long updateMan, Date updateTime, Short status) {
		this.region = region;
		this.type = type;
		this.createTime = createTime;
		this.createMan = createMan;
		this.updateMan = updateMan;
		this.updateTime = updateTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Region", length = 32)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CreateMan")
	public Long getCreateMan() {
		return this.createMan;
	}

	public void setCreateMan(Long createMan) {
		this.createMan = createMan;
	}

	@Column(name = "UpdateMan")
	public Long getUpdateMan() {
		return this.updateMan;
	}

	public void setUpdateMan(Long updateMan) {
		this.updateMan = updateMan;
	}

	@Column(name = "UpdateTime", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}