package com.okwei.bean.vo.user;

import java.util.List;

import com.okwei.bean.enums.SupplierIndustryType;

public class ChannelSupplyVO {
	
	private Long weiID;
	private String weiName;
	private String weiImg;
	
	private SupplierIndustryType type;
	private String companyName;
	private String pcDetails;
	
	private List<String> supplyImgList;

	public Long getWeiID() {
		return weiID;
	}

	public void setWeiID(Long weiID) {
		this.weiID = weiID;
	}

	public String getWeiName() {
		return weiName;
	}

	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}

	public String getWeiImg() {
		return weiImg;
	}

	public void setWeiImg(String weiImg) {
		this.weiImg = weiImg;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPcDetails() {
		return pcDetails;
	}

	public void setPcDetails(String pcDetails) {
		this.pcDetails = pcDetails;
	}

	public List<String> getSupplyImgList() {
		return supplyImgList;
	}

	public void setSupplyImgList(List<String> supplyImgList) {
		this.supplyImgList = supplyImgList;
	}

	public SupplierIndustryType getType() {
		return type;
	}

	public void setType(SupplierIndustryType type) {
		this.type = type;
	}
	
	
}
