package com.okwei.appinterface.bean.vo;

import java.util.Date;
import java.util.List;

public class InvestmentDemand {
	
	private Long merchantWeiId;
	private String merchantName;
	private Integer demandId;
	private String demandName;
	private Integer totalProduct;
	private String img;
	private String areaStr;
	private Integer agentCount;
	private Integer storeCount;
	private Short status;
	private Date applyTime;
	private Date auditTime;
	private String remarks;
	private List<DemandProduct> products;
	private Double firstOrderAmount;
	private Double minAgentReward;
	private Double maxAgentReward;
	
		
	private String zhuying;	
	private String industry;	
	private String details;	
	private List<RequiredInfo> areaList;
	
	
	
	
	public Long getMerchantWeiId() {
		return merchantWeiId;
	}
	public void setMerchantWeiId(Long merchantWeiId) {
		this.merchantWeiId = merchantWeiId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Integer getDemandId() {
		return demandId;
	}
	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}
	public String getDemandName() {
		return demandName;
	}
	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	public Integer getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(Integer totalProduct) {
		this.totalProduct = totalProduct;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAreaStr() {
		return areaStr;
	}
	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}
	public Integer getAgentCount() {
		return agentCount;
	}
	public void setAgentCount(Integer agentCount) {
		this.agentCount = agentCount;
	}
	public Integer getStoreCount() {
		return storeCount;
	}
	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}

	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public List<DemandProduct> getProducts() {
		return products;
	}
	public void setProducts(List<DemandProduct> products) {
		this.products = products;
	}
	public String getZhuying() {
		return zhuying;
	}
	public void setZhuying(String zhuying) {
		this.zhuying = zhuying;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public List<RequiredInfo> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<RequiredInfo> areaList) {
		this.areaList = areaList;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Double getFirstOrderAmount() {
		return firstOrderAmount;
	}
	public void setFirstOrderAmount(Double firstOrderAmount) {
		this.firstOrderAmount = firstOrderAmount;
	}

	public Double getMinAgentReward() {
		return minAgentReward;
	}
	public void setMinAgentReward(Double minAgentReward) {
		this.minAgentReward = minAgentReward;
	}
	public Double getMaxAgentReward() {
		return maxAgentReward;
	}
	public void setMaxAgentReward(Double maxAgentReward) {
		this.maxAgentReward = maxAgentReward;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
