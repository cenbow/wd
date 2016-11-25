package com.okwei.appinterface.bean.vo.agent;

public class ApplyListAgentVO {
	private int agentLevelId;
	private Double price;
	private String agentLevelName;
	private String description;
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the agentLevelName
	 */
	public String getAgentLevelName() {
		return agentLevelName;
	}
	/**
	 * @param agentLevelName the agentLevelName to set
	 */
	public void setAgentLevelName(String agentLevelName) {
		this.agentLevelName = agentLevelName;
	}
	public int getAgentLevelId() {
		return agentLevelId;
	}
	public void setAgentLevelId(int agentLevelId) {
		this.agentLevelId = agentLevelId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
