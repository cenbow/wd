package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TAllotTemp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_AllotTemp")
public class TAllotTemp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4091148722441467865L;
	private Integer allotId;
	private Long adviser;
	private Long upAdviser;
	private Integer level;
	private Integer verAllotCount;
	private Integer supAllotCount;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TAllotTemp() {
	}

	/** full constructor */
	public TAllotTemp(Long adviser, Long upAdviser, Integer level,
			Integer verAllotCount, Integer supAllotCount, Date createTime) {
		this.adviser = adviser;
		this.upAdviser = upAdviser;
		this.level = level;
		this.verAllotCount = verAllotCount;
		this.supAllotCount = supAllotCount;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AllotID", unique = true, nullable = false)
	public Integer getAllotId() {
		return this.allotId;
	}

	public void setAllotId(Integer allotId) {
		this.allotId = allotId;
	}

	@Column(name = "Adviser")
	public Long getAdviser() {
		return this.adviser;
	}

	public void setAdviser(Long adviser) {
		this.adviser = adviser;
	}

	@Column(name = "UpAdviser")
	public Long getUpAdviser() {
		return this.upAdviser;
	}

	public void setUpAdviser(Long upAdviser) {
		this.upAdviser = upAdviser;
	}

	@Column(name = "Level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "VerAllotCount")
	public Integer getVerAllotCount() {
		return this.verAllotCount;
	}

	public void setVerAllotCount(Integer verAllotCount) {
		this.verAllotCount = verAllotCount;
	}

	@Column(name = "SupAllotCount")
	public Integer getSupAllotCount() {
		return this.supAllotCount;
	}

	public void setSupAllotCount(Integer supAllotCount) {
		this.supAllotCount = supAllotCount;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}