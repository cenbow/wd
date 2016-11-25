package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DBrandAgentInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_BrandAgentInfo")
public class DBrandAgentInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471673993863846189L;
	// Fields

	private Integer brandId;
	private String summaryOne;
	private String summaryTwo;
	private String summaryThree;
	private String institution;
	private Double agencyOne;
	private Double agencyTwo;
	private Double agencyThree;

	// Constructors

	/** default constructor */
	public DBrandAgentInfo() {
	}

	/** minimal constructor */
	public DBrandAgentInfo(Integer brandId) {
		this.brandId = brandId;
	}

	/** full constructor */
	public DBrandAgentInfo(Integer brandId, String summaryOne,
			String summaryTwo, String summaryThree, String institution,
			Double agencyOne, Double agencyTwo, Double agencyThree) {
		this.brandId = brandId;
		this.summaryOne = summaryOne;
		this.summaryTwo = summaryTwo;
		this.summaryThree = summaryThree;
		this.institution = institution;
		this.agencyOne = agencyOne;
		this.agencyTwo = agencyTwo;
		this.agencyThree = agencyThree;
	}

	// Property accessors
	@Id
	@Column(name = "BrandID", unique = true, nullable = false)
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "SummaryOne", length = 256)
	public String getSummaryOne() {
		return this.summaryOne;
	}

	public void setSummaryOne(String summaryOne) {
		this.summaryOne = summaryOne;
	}

	@Column(name = "SummaryTwo", length = 256)
	public String getSummaryTwo() {
		return this.summaryTwo;
	}

	public void setSummaryTwo(String summaryTwo) {
		this.summaryTwo = summaryTwo;
	}

	@Column(name = "SummaryThree", length = 256)
	public String getSummaryThree() {
		return this.summaryThree;
	}

	public void setSummaryThree(String summaryThree) {
		this.summaryThree = summaryThree;
	}

	@Column(name = "Institution", length = 65535)
	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	@Column(name = "AgencyOne", precision = 18)
	public Double getAgencyOne() {
		return this.agencyOne;
	}

	public void setAgencyOne(Double agencyOne) {
		this.agencyOne = agencyOne;
	}

	@Column(name = "AgencyTwo", precision = 18)
	public Double getAgencyTwo() {
		return this.agencyTwo;
	}

	public void setAgencyTwo(Double agencyTwo) {
		this.agencyTwo = agencyTwo;
	}

	@Column(name = "AgencyThree", precision = 18)
	public Double getAgencyThree() {
		return this.agencyThree;
	}

	public void setAgencyThree(Double agencyThree) {
		this.agencyThree = agencyThree;
	}

}