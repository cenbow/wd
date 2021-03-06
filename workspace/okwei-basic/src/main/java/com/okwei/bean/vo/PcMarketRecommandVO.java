package com.okwei.bean.vo;

import java.io.Serializable;


public class PcMarketRecommandVO implements Serializable{

	private static final long serialVersionUID = 2949260244514326478L;
	
	private Long reId;
	private Integer bmid;
	private String marketName;
	private String mimg;
	private String mainBus;
	private String address;
	public Long getReId() {
		return reId;
	}
	public void setReId(Long reId) {
		this.reId = reId;
	}
	public Integer getBmid() {
		return bmid;
	}
	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getMainBus() {
		return mainBus;
	}
	public void setMainBus(String mainBus) {
		this.mainBus = mainBus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
