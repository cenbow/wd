package com.okwei.pay.bean.vo;

import com.okwei.pay.bean.enums.BaseResultStateEnum;




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
