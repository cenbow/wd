package com.okwei.appinterface.bean.vo;

public class DlscjDomain {
	
	private Long agentId ;     //代理商微店号
	private String agentName ; //代理商名称
	private String agentArea ; //代理商区域
	private int orderTotal ;   //代理商品总数
	
	public DlscjDomain() {
		
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(String agentArea) {
		this.agentArea = agentArea;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	

}
