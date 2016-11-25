package com.okwei.appinterface.bean.vo;

import java.util.List;

public class AreaTree {
	
	private Integer areaId;
	private String areaName;
	
	private List<AreaTree> areas;

	
	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<AreaTree> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaTree> areas) {
		this.areas = areas;
	}
	
	
}
