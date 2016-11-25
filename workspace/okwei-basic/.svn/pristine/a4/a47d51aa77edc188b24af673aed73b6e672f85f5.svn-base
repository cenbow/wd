package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UBatchVerifierRegion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_BatchVerifierRegion")
public class UBatchVerifierRegion extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer brid;
	private Long weiId;
	private Integer code;
	private Short type;
	private Short status;

	// Constructors

	/** default constructor */
	public UBatchVerifierRegion() {
	}

	/** full constructor */
	public UBatchVerifierRegion(Long weiId, Integer code, Short type, Short status) {
		this.weiId = weiId;
		this.code = code;
		this.type = type;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BRID", unique = true, nullable = false)
	public Integer getBrid() {
		return this.brid;
	}

	public void setBrid(Integer brid) {
		this.brid = brid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}