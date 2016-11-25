package com.okwei.appinterface.bean.dto;

@SuppressWarnings("serial")
public class VerifierProductAgentFollowParam extends BaseParam{
	private String content; //跟进内容
	private Integer agentId;  //代理商的id
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	 
	
}
