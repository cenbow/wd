package com.okwei.appinterface.bean.vo;

import java.util.List;

public class RegionChannel {
	private Integer agentCount;
	private Integer shopCount;
	private Integer code;
	private String codeName;
	
	private List<RegionChannel>	 regList;

	

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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public List<RegionChannel> getRegList() {
		return regList;
	}

	public void setRegList(List<RegionChannel> regList) {
		this.regList = regList;
	}
	
	
}


