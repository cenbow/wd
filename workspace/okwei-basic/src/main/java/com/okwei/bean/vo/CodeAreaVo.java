package com.okwei.bean.vo;

import java.io.Serializable;
import java.util.List;

public class CodeAreaVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9038591931572962823L;
	
	private int code;
	private String name;
	private int count;

	
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public int getCount() {
		return count;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
}
