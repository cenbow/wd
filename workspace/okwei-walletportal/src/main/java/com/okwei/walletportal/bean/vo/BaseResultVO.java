package com.okwei.walletportal.bean.vo;

import com.okwei.walletportal.bean.enums.BaseResultState;


public class BaseResultVO {
	
	private BaseResultState state;
	private String message;
	private Object obj;
	
	public BaseResultState getState() {
		return state;
	}
	public void setState(BaseResultState state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	

}
