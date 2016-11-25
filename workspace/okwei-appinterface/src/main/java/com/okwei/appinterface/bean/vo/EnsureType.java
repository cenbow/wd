package com.okwei.appinterface.bean.vo;

import java.io.Serializable;

public class EnsureType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -544947228539467879L;
	private String name;
	private String url;
	private String desc;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}
