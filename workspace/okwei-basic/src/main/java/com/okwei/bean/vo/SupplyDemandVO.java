package com.okwei.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.okwei.bean.vo.user.ChannelRegionVO;
import com.okwei.bean.vo.user.DemandRequiredVO;
import com.okwei.bean.vo.user.RequiredKVVO;

public class SupplyDemandVO implements Serializable {

	/**
	 * 招商需求ID
	 */
	private Integer demandId;	
	/**
	 * 平台号品牌号ID
	 */
	private Long weiId;
	
	private String weiName;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	private String weiImg;
	
	/**
	 * 招商需求标题
	 */
	private String title;
	/**
	 * 招商需求主图
	 */
	private String mainImage;
	/**
	 * 代理条件
	 */
	private String agentRequired;
	/**
	 * 扶持政策
	 */
	private String support;
	/**
	 * 量化要求
	 */
	private String numberRequired;
	/**
	 * 代理合同
	 */
	private String contract;
	/**
	 * 首单要求
	 */
	private Double orderAmout;
	/**
	 * 落地店 悬赏
	 */
	private Double shopReward;
	
	private Double minAgentReward;
	private Double maxAgentReward;
	/**
	 * PC详情
	 */
	private String pcDetails;
	/**
	 * APP 详情
	 */
	private String appDetails;
	/**
	 * 状态
	 */
	private Short state;
	/**
	 * 时间
	 */
	private Date createTime;
	
	private Date auditTime;

	/**
	 * 代理商数量
	 */
	private Integer agentCount =0;
	
	/**
	 * 落地点数量
	 */
	private Integer shopCount =0;
	
	/**
	 * 当前登录人ID
	 */
	private Long loginUserID;
	
	/**
	 * 是否已经代理
	 */
	private Boolean agented = false;
	
	private Boolean attened = false;
	
	/**
	 * 商品数量统计
	 */
	private Integer pCount =0;
	
	/**
	 * 代理需求下的商品列表
	 */
	private List<DemandProductVO> dProductVOs;
	
	/**
	 * 招商需求的地区条件列表
	 */
	private List<DemandRequiredVO> dRequiredVOs;
	
	/**
	 * 招商需求的量化要求列表
	 */
	private List<RequiredKVVO> dRkvVOs;
	
	/**
	 * 招商需求的 区域列表
	 */
	private List<ChannelRegionVO> RegionVOs;
	
	/**
	 * 地区名称
	 */
	private String areaString;
	
	private String createTimeString;
	/**
	 * 主营
	 */
	private String saleType;
	/**
	 * 行业
	 */
	private String busCategoryStr;
		
	private String noPassReason;	
	
	private List<String> qqList;
	
	public Integer getpCount() {
		return pCount;
	}
	public void setpCount(Integer pCount) {
		this.pCount = pCount;
	}
	public List<DemandProductVO> getdProductVOs() {
		return dProductVOs;
	}
	public void setdProductVOs(List<DemandProductVO> dProductVOs) {
		this.dProductVOs = dProductVOs;
	}
	public Integer getDemandId() {
		return demandId;
	}
	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}
	public Long getWeiId() {
		return weiId;
	}
	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getAgentRequired() {
		return agentRequired;
	}
	public void setAgentRequired(String agentRequired) {
		this.agentRequired = agentRequired;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public String getNumberRequired() {
		return numberRequired;
	}
	public void setNumberRequired(String numberRequired) {
		this.numberRequired = numberRequired;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public Double getOrderAmout() {
		return orderAmout;
	}
	public void setOrderAmout(Double orderAmout) {
		this.orderAmout = orderAmout;
	}
	public Double getShopReward() {
		return shopReward;
	}
	public void setShopReward(Double shopReward) {
		this.shopReward = shopReward;
	}
	public String getPcDetails() {
		return pcDetails;
	}
	public void setPcDetails(String pcDetails) {
		this.pcDetails = pcDetails;
	}
	public String getAppDetails() {
		return appDetails;
	}
	public void setAppDetails(String appDetails) {
		this.appDetails = appDetails;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAgentCount() {
		return agentCount;
	}
	public void setAgentCount(Integer agentCount) {
		this.agentCount = agentCount;
	}
	public Integer getShopCount() {
		return shopCount;
	}
	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}
	public Long getLoginUserID() {
		return loginUserID;
	}
	public void setLoginUserID(Long loginUserID) {
		this.loginUserID = loginUserID;
	}
	public Boolean getAgented() {
		return agented;
	}
	public void setAgented(Boolean agented) {
		this.agented = agented;
	}
	public String getWeiName() {
		return weiName;
	}
	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}
	public List<DemandRequiredVO> getdRequiredVOs() {
		return dRequiredVOs;
	}
	public void setdRequiredVOs(List<DemandRequiredVO> dRequiredVOs) {
		this.dRequiredVOs = dRequiredVOs;
	}
	public List<RequiredKVVO> getdRkvVOs() {
		return dRkvVOs;
	}
	public void setdRkvVOs(List<RequiredKVVO> dRkvVOs) {
		this.dRkvVOs = dRkvVOs;
	}

	public String getAreaString() {
		return areaString;
	}
	public void setAreaString(String areaString) {
		this.areaString = areaString;
	}
	public String getCreateTimeString() {
		return createTimeString;
	}
	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getBusCategoryStr() {
		return busCategoryStr;
	}
	public void setBusCategoryStr(String busCategoryStr) {
		this.busCategoryStr = busCategoryStr;
	}
	public List<ChannelRegionVO> getRegionVOs() {
		return RegionVOs;
	}
	public void setRegionVOs(List<ChannelRegionVO> regionVOs) {
		RegionVOs = regionVOs;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public Double getMaxAgentReward() {
		return maxAgentReward;
	}
	public void setMaxAgentReward(Double maxAgentReward) {
		this.maxAgentReward = maxAgentReward;
	}
	public Double getMinAgentReward() {
		return minAgentReward;
	}
	public void setMinAgentReward(Double minAgentReward) {
		this.minAgentReward = minAgentReward;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
	public String getWeiImg() {
		return weiImg;
	}
	public void setWeiImg(String weiImg) {
		this.weiImg = weiImg;
	}
	public List<String> getQqList() {
	    return qqList;
	}
	public void setQqList(List<String> qqList) {
	    this.qqList = qqList;
	}
	public Boolean getAttened() {
		return attened;
	}
	public void setAttened(Boolean attened) {
		this.attened = attened;
	}
	public String getCompanyName() {
	    return companyName;
	}
	public void setCompanyName(String companyName) {
	    this.companyName = companyName;
	}	
	
}
