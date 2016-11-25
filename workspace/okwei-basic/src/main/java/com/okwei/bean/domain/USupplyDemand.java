package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplyDemand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplyDemand")
public class USupplyDemand implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9051734578081989713L;
	// Fields

	private Integer demandId;
	private Long weiId;
	private String title;
	private String mainImage;
	private String pcDetails;
	private String appDetails;
	private Short state;
	private Double minShopReward;
	private Double maxShopReward;
	private Double minAgentReward;
	private Double maxAgentReward;
	private Integer agentCount;
	private Integer shopCount;
	private Integer productCount;
	private Date topTime;
	private Date auditTime;
	private String noPassReason;
	private Date createTime;
	
	private String agentRequired;
	private String contract;
	private String numberRequired;
	private Double orderAmout;
	private Double shopReward;
	private Double agentReward;
	private String support;

	// Constructors

	/** default constructor */
	public USupplyDemand() {
	}

	/** full constructor */
	public USupplyDemand(Long weiId, String title, String mainImage,
			String pcDetails, String appDetails, Short state,
			Double minShopReward, Double maxShopReward, Double minAgentReward,
			Double maxAgentReward, Integer agentCount, Integer shopCount,
			Date topTime, Date auditTime, Date createTime,
			String agentRequired, String contract, String numberRequired,
			Double orderAmout, Double shopReward, String support) {
		this.weiId = weiId;
		this.title = title;
		this.mainImage = mainImage;
		this.pcDetails = pcDetails;
		this.appDetails = appDetails;
		this.state = state;
		this.minShopReward = minShopReward;
		this.maxShopReward = maxShopReward;
		this.minAgentReward = minAgentReward;
		this.maxAgentReward = maxAgentReward;
		this.agentCount = agentCount;
		this.shopCount = shopCount;
		this.topTime = topTime;
		this.auditTime = auditTime;
		this.createTime = createTime;
		this.agentRequired = agentRequired;
		this.contract = contract;
		this.numberRequired = numberRequired;
		this.orderAmout = orderAmout;
		this.shopReward = shopReward;
		this.support = support;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "DemandID", unique = true, nullable = false)
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "MainImage", length = 128)
	public String getMainImage() {
		return this.mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	@Column(name = "PcDetails", length = 65535)
	public String getPcDetails() {
		return this.pcDetails;
	}

	public void setPcDetails(String pcDetails) {
		this.pcDetails = pcDetails;
	}

	@Column(name = "AppDetails", length = 65535)
	public String getAppDetails() {
		return this.appDetails;
	}

	public void setAppDetails(String appDetails) {
		this.appDetails = appDetails;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "MinShopReward", precision = 10, scale = 0)
	public Double getMinShopReward() {
		return this.minShopReward;
	}

	public void setMinShopReward(Double minShopReward) {
		this.minShopReward = minShopReward;
	}

	@Column(name = "MaxShopReward", precision = 10, scale = 0)
	public Double getMaxShopReward() {
		return this.maxShopReward;
	}

	public void setMaxShopReward(Double maxShopReward) {
		this.maxShopReward = maxShopReward;
	}

	@Column(name = "MinAgentReward", precision = 10, scale = 0)
	public Double getMinAgentReward() {
		return this.minAgentReward;
	}

	public void setMinAgentReward(Double minAgentReward) {
		this.minAgentReward = minAgentReward;
	}

	@Column(name = "MaxAgentReward", precision = 10, scale = 0)
	public Double getMaxAgentReward() {
		return this.maxAgentReward;
	}

	public void setMaxAgentReward(Double maxAgentReward) {
		this.maxAgentReward = maxAgentReward;
	}

	@Column(name = "AgentCount")
	public Integer getAgentCount() {
		return this.agentCount;
	}

	public void setAgentCount(Integer agentCount) {
		this.agentCount = agentCount;
	}

	@Column(name = "ShopCount")
	public Integer getShopCount() {
		return this.shopCount;
	}

	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}
	
	@Column(name = "ProductCount")
	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	@Column(name = "TopTime", length = 19)
	public Date getTopTime() {
		return this.topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	@Column(name = "AuditTime", length = 19)
	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "AgentRequired")
	public String getAgentRequired() {
		return this.agentRequired;
	}

	public void setAgentRequired(String agentRequired) {
		this.agentRequired = agentRequired;
	}

	@Column(name = "Contract", length = 128)
	public String getContract() {
		return this.contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	@Column(name = "NumberRequired")
	public String getNumberRequired() {
		return this.numberRequired;
	}

	public void setNumberRequired(String numberRequired) {
		this.numberRequired = numberRequired;
	}

	@Column(name = "OrderAmout", precision = 22, scale = 0)
	public Double getOrderAmout() {
		return this.orderAmout;
	}

	public void setOrderAmout(Double orderAmout) {
		this.orderAmout = orderAmout;
	}

	@Column(name = "ShopReward", precision = 22, scale = 0)
	public Double getShopReward() {
		return this.shopReward;
	}
	
	
	@Column(name = "AgentReward", precision = 22, scale = 0)
	public Double getAgentReward() {
		return agentReward;
	}

	public void setAgentReward(Double agentReward) {
		this.agentReward = agentReward;
	}

	public void setShopReward(Double shopReward) {
		this.shopReward = shopReward;
	}

	@Column(name = "Support")
	public String getSupport() {
		return this.support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	@Column(name = "NoPassReason", length = 256)
	public String getNoPassReason() {
		return noPassReason;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}


}