package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DBrandIndustry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_BrandIndustry")
public class DBrandIndustry implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3795679494692017511L;
	// Fields

	private Integer did;
	private Integer brandId;
	private Integer industryId;
	private String name;

	// Constructors

	/** default constructor */
	public DBrandIndustry() {
	}

	/** full constructor */
	public DBrandIndustry(Integer brandId, Integer industryId, String name) {
		this.brandId = brandId;
		this.industryId = industryId;
		this.name = name;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DID", unique = true, nullable = false)
	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "IndustryID")
	public Integer getIndustryId() {
		return this.industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	@Column(name = "Name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}