package com.okwei.bean.vo;

import java.io.Serializable;

public class BaseImageMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8221626875515247720L;
	private String url;
	private int h;
	private int w;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	
}
