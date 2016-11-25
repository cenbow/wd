package com.okwei.bean.vo.agent;

import com.okwei.bean.enums.agent.BaseResultStateEnum;





public class BaseResultVO {
	
	private BaseResultStateEnum state;
	private String message;
	public BaseResultStateEnum getState() {
		return state;
	}
	public void setState(BaseResultStateEnum state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
