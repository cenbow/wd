package com.okwei.appinterface.bean.vo;

import java.util.List;

public class RequiredInfo {

	private String condition;	//代理条件
	private String zhengce;	//政策
	private String file;	//	合同
	private String areaIntro;//地区名称 例如： 甘肃省、青海省、成都市
	private Double minOrderTrans; //成为落地店最低采购额
	private Double agentReward; //代理悬赏
	private Double shopReward; //落地悬赏
	private List<RequiredKV> requestList;
	private List<AreaTree> areas;
	
	
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getZhengce() {
		return zhengce;
	}
	public void setZhengce(String zhengce) {
		this.zhengce = zhengce;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getAreaIntro() {
		return areaIntro;
	}
	public void setAreaIntro(String areaIntro) {
		this.areaIntro = areaIntro;
	}
	public Double getMinOrderTrans() {
		return minOrderTrans;
	}
	public void setMinOrderTrans(Double minOrderTrans) {
		this.minOrderTrans = minOrderTrans;
	}
	public List<RequiredKV> getRequestList() {
		return requestList;
	}
	public void setRequestList(List<RequiredKV> requestList) {
		this.requestList = requestList;
	}
	public List<AreaTree> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaTree> areas) {
		this.areas = areas;
	}	
}
