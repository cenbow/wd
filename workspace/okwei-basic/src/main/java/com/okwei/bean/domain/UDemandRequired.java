package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UDemandRequired entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_DemandRequired")
public class UDemandRequired implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8992489555387115337L;
	// Fields

	private Integer requiredId;
	private Integer demandId;
	private String agentRequired;
	private String support;
	private String numRequired;
	private String contract;
	private String contractPath;
	private Double orderAmout;
	private Double agentReward;
	private Double shopReward;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UDemandRequired() {
	}

	/** full constructor */
	public UDemandRequired(Integer demandId, String agentRequired,
			String support, String numRequired, String contract,
			Double orderAmout, Double agentReward, Double shopReward,
			Date createTime) {
		this.demandId = demandId;
		this.agentRequired = agentRequired;
		this.support = support;
		this.numRequired = numRequired;
		this.contract = contract;
		this.orderAmout = orderAmout;
		this.agentReward = agentReward;
		this.shopReward = shopReward;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "RequiredID", unique = true, nullable = false)
	public Integer getRequiredId() {
		return this.requiredId;
	}

	public void setRequiredId(Integer requiredId) {
		this.requiredId = requiredId;
	}

	@Column(name = "DemandID")
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "AgentRequired", length = 1024)
	public String getAgentRequired() {
		return this.agentRequired;
	}

	public void setAgentRequired(String agentRequired) {
		this.agentRequired = agentRequired;
	}

	@Column(name = "Support", length = 1024)
	public String getSupport() {
		return this.support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	@Column(name = "NumRequired", length = 1024)
	public String getNumRequired() {
		return this.numRequired;
	}

	public void setNumRequired(String numRequired) {
		this.numRequired = numRequired;
	}

	@Column(name = "Contract", length = 128)
	public String getContract() {
		return this.contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	@Column(name = "OrderAmout", precision = 10, scale = 0)
	public Double getOrderAmout() {
		return this.orderAmout;
	}

	public void setOrderAmout(Double orderAmout) {
		this.orderAmout = orderAmout;
	}

	@Column(name = "AgentReward", precision = 10, scale = 0)
	public Double getAgentReward() {
		return this.agentReward;
	}

	public void setAgentReward(Double agentReward) {
		this.agentReward = agentReward;
	}

	@Column(name = "ShopReward", precision = 10, scale = 0)
	public Double getShopReward() {
		return this.shopReward;
	}

	public void setShopReward(Double shopReward) {
		this.shopReward = shopReward;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	@Column(name = "ContractPath", length = 128)
	public String getContractPath() {
		return contractPath;
	}

	public void setContractPath(String contractPath) {
		this.contractPath = contractPath;
	}

}