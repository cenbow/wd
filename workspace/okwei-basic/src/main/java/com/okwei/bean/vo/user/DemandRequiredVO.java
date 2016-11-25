package com.okwei.bean.vo.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import com.okwei.bean.vo.RegionVO;

public class DemandRequiredVO  implements Serializable{
	
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
	
	/**
	 * 地区列表
	 */
	private List<ChannelRegionVO> regionVOs;
	private String codeStr;
	private String codeName;
	
	private List<RequiredKVVO> requiredKVVOs;
	private String  requiredKVStr;
	
	public Integer getRequiredId() {
		return requiredId;
	}
	public void setRequiredId(Integer requiredId) {
		this.requiredId = requiredId;
	}
	public Integer getDemandId() {
		return demandId;
	}
	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
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
	public String getNumRequired() {
		return numRequired;
	}
	public void setNumRequired(String numRequired) {
		this.numRequired = numRequired;
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
	public Double getAgentReward() {
		return agentReward;
	}
	public void setAgentReward(Double agentReward) {
		this.agentReward = agentReward;
	}
	public Double getShopReward() {
		return shopReward;
	}
	public void setShopReward(Double shopReward) {
		this.shopReward = shopReward;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<RequiredKVVO> getRequiredKVVOs() {
		return requiredKVVOs;
	}
	public void setRequiredKVVOs(List<RequiredKVVO> requiredKVVOs) {
		this.requiredKVVOs = requiredKVVOs;
	}
	public List<ChannelRegionVO> getRegionVOs() {
		return regionVOs;
	}
	public void setRegionVOs(List<ChannelRegionVO> regionVOs) {
		this.regionVOs = regionVOs;
	}
	public String getCodeStr() {
		return codeStr;
	}
	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getRequiredKVStr() {
		return requiredKVStr;
	}
	public void setRequiredKVStr(String requiredKVStr) {
		this.requiredKVStr = requiredKVStr;
	}
	public String getContractPath() {
		return contractPath;
	}
	public void setContractPath(String contractPath) {
		this.contractPath = contractPath;
	}
	
	
}
