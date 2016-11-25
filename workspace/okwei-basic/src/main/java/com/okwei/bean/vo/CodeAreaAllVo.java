package com.okwei.bean.vo;

import java.util.List;



public class CodeAreaAllVo {

	 private List<CodeAreaVo> ammList;
	 private boolean  hasChildArea;
	public List<CodeAreaVo> getAmmList() {
		return ammList;
	}
	public void setAmmList(List<CodeAreaVo> ammList) {
		this.ammList = ammList;
	}
	public boolean isHasChildArea() {
		return hasChildArea;
	}
	public void setHasChildArea(boolean hasChildArea) {
		this.hasChildArea = hasChildArea;
	}
	 

}
