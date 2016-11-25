package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DBrandsExt entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_BrandsExt")
public class DBrandsExt implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2463148161890641594L;
	// Fields

	private Integer brandId;
	private Integer castellanInCount;
	private Integer castellanOutCount;
	private Integer viceCastellanInCount;
	private Integer viceCastellanOutCount;
	private Integer agentInCount;
	private Integer agentOutCount;
	private Date createTime;
	private Integer agentOneCount;
	private Integer agentTwoCount;
	private Integer agentThreeCount;

	// Constructors

	/** default constructor */
	public DBrandsExt() {
	}

	/** minimal constructor */
	public DBrandsExt(Integer brandId) {
		this.brandId = brandId;
	}

	/** full constructor */
	public DBrandsExt(Integer brandId, Integer castellanInCount,
			Integer castellanOutCount, Integer viceCastellanInCount,
			Integer viceCastellanOutCount, Integer agentInCount,
			Integer agentOutCount, Date createTime) {
		this.brandId = brandId;
		this.castellanInCount = castellanInCount;
		this.castellanOutCount = castellanOutCount;
		this.viceCastellanInCount = viceCastellanInCount;
		this.viceCastellanOutCount = viceCastellanOutCount;
		this.agentInCount = agentInCount;
		this.agentOutCount = agentOutCount;
		this.createTime = createTime;
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

	@Column(name = "CastellanInCount")
	public Integer getCastellanInCount() {
		return this.castellanInCount;
	}

	public void setCastellanInCount(Integer castellanInCount) {
		this.castellanInCount = castellanInCount;
	}

	@Column(name = "CastellanOutCount")
	public Integer getCastellanOutCount() {
		return this.castellanOutCount;
	}

	public void setCastellanOutCount(Integer castellanOutCount) {
		this.castellanOutCount = castellanOutCount;
	}

	@Column(name = "ViceCastellanInCount")
	public Integer getViceCastellanInCount() {
		return this.viceCastellanInCount;
	}

	public void setViceCastellanInCount(Integer viceCastellanInCount) {
		this.viceCastellanInCount = viceCastellanInCount;
	}

	@Column(name = "ViceCastellanOutCount")
	public Integer getViceCastellanOutCount() {
		return this.viceCastellanOutCount;
	}

	public void setViceCastellanOutCount(Integer viceCastellanOutCount) {
		this.viceCastellanOutCount = viceCastellanOutCount;
	}

	@Column(name = "AgentInCount")
	public Integer getAgentInCount() {
		return this.agentInCount;
	}

	public void setAgentInCount(Integer agentInCount) {
		this.agentInCount = agentInCount;
	}

	@Column(name = "AgentOutCount")
	public Integer getAgentOutCount() {
		return this.agentOutCount;
	}

	public void setAgentOutCount(Integer agentOutCount) {
		this.agentOutCount = agentOutCount;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "AgentOneCount")
	public Integer getAgentOneCount() {
		return agentOneCount;
	}

	/**
	 * @param agentOneCount the agentOneCount to set
	 */
	public void setAgentOneCount(Integer agentOneCount) {
		this.agentOneCount = agentOneCount;
	}

	@Column(name = "AgentTwoCount")
	public Integer getAgentTwoCount() {
		return agentTwoCount;
	}

	/**
	 * @param agentTwoCount the agentTwoCount to set
	 */
	public void setAgentTwoCount(Integer agentTwoCount) {
		this.agentTwoCount = agentTwoCount;
	}

	@Column(name = "AgentThreeCount")
	public Integer getAgentThreeCount() {
		return agentThreeCount;
	}

	/**
	 * @param agentThreeCount the agentThreeCount to set
	 */
	public void setAgentThreeCount(Integer agentThreeCount) {
		this.agentThreeCount = agentThreeCount;
	}
	
}