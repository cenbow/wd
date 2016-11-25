package com.okwei.bean.vo.product;

import java.util.List;
import java.util.Map;

public class SellAtrrEdit {
	private String name;
	private List<Map<String,String>> vals;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Map<String, String>> getVals() {
		return vals;
	}
	public void setVals(List<Map<String, String>> vals) {
		this.vals = vals;
	}
	

}
