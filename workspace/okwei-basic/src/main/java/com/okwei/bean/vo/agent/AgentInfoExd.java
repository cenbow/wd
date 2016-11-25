package com.okwei.bean.vo.agent;

import com.okwei.bean.domain.DAgentInfo;

public class AgentInfoExd extends DAgentInfo {

	private static final long serialVersionUID = -4804058284436471634L;
	
	private String relation;
	private String shopName;
	private String dateStr;
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	
}
