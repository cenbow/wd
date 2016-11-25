package com.okwei.company.bean.vo;

import java.util.List;

public class YHomePageNavagation {
    private String head;
    private Integer headcode;
    private String all;
    private Integer allcode;
    private List<BusKeyValue> typelist;
    public Integer getHeadcode() {
		return headcode;
	}
	public void setHeadcode(Integer headcode) {
		this.headcode = headcode;
	}
	public Integer getAllcode() {
		return allcode;
	}
	public void setAllcode(Integer allcode) {
		this.allcode = allcode;
	}
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public List<BusKeyValue> getTypelist() {
		return typelist;
	}
	public void setTypelist(List<BusKeyValue> typelist) {
		this.typelist = typelist;
	}
    
}
