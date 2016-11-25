package com.okwei.bean.vo;

import java.util.List;

public class HqlParam {
	private StringBuilder sb ;
	private List<Object> params ;
	public StringBuilder getSb() {
		return sb;
	}
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}
	public List<Object> getParams() {
		return params;
	}
	public void setParams(List<Object> params) {
		this.params = params;
	}


}
