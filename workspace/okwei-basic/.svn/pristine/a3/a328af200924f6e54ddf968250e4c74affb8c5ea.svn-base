package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UBatchVerifier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_BatchVerifier")
public class UBatchVerifier extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private Long agentSupplier;
	private Long supweiId;
	private Long companyWeiID;

	@Column(name = "SupWeiID")
	public Long getSupweiId() {
		return supweiId;
	}

	@Column(name = "AgentSupplier")
	public Long getAgentSupplier() {
		return agentSupplier;
	}

	public void setAgentSupplier(Long agentSupplier) {
		this.agentSupplier = agentSupplier;
	}

	public void setSupweiId(Long supweiId) {
		this.supweiId = supweiId;
	}

	public void setCompanyWeiID(Long companyWeiID) {
		this.companyWeiID = companyWeiID;
	}

	@Column(name = "CompanyWeiID")
	public Long getCompanyWeiID() {
		return companyWeiID;
	}

	@Column(name = "LevelType")
	public Short getLevelType() {
		return levelType;
	}

	public void setLevelType(Short levelType) {
		this.levelType = levelType;
	}

	private Short levelType;
	private String verifierDes;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UBatchVerifier() {
	}

	@Column(name = "VerifierDes")
	public String getVerifierDes() {
		return verifierDes;
	}

	public void setVerifierDes(String verifierDes) {
		this.verifierDes = verifierDes;
	}

	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

}