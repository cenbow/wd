package com.okwei.bean.dto.user;

public class SupplyChannelDTO {
	
	private String searchString;
		
	private Integer demandID;
	
	private Integer regionCode;

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Integer getDemandID() {
		return demandID;
	}

	public void setDemandID(Integer demandID) {
		this.demandID = demandID;
	}

	public Integer getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}		
}
