package com.okwei.bean.vo.user;

public class LandShopListVO {
	private Long merchantWeiId;//上级供应商微店号
	private String merchantName; //供应商名称
	private String firstOrderTime;//首单下单时间
	private Integer investmentDemandId;//招商需求Id
	private String investmentDemandName;//招商加盟名称
	private Double minOrderTrans;//成为落地店最低采购额
	
	
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
	public String getFirstOrderTime() {
		return firstOrderTime;
	}
	public void setFirstOrderTime(String firstOrderTime) {
		this.firstOrderTime = firstOrderTime;
	}
	public Integer getInvestmentDemandId() {
		return investmentDemandId;
	}
	public void setInvestmentDemandId(Integer investmentDemandId) {
		this.investmentDemandId = investmentDemandId;
	}
	public String getInvestmentDemandName() {
		return investmentDemandName;
	}
	public void setInvestmentDemandName(String investmentDemandName) {
		this.investmentDemandName = investmentDemandName;
	}
	public Double getMinOrderTrans() {
		return minOrderTrans;
	}
	public void setMinOrderTrans(Double minOrderTrans) {
		this.minOrderTrans = minOrderTrans;
	}
    
}
