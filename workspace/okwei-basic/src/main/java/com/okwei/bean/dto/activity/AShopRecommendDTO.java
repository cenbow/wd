package com.okwei.bean.dto.activity;

import com.okwei.bean.domain.AShopRecommend;
import com.okwei.bean.domain.AShopRecommendLog;

public class AShopRecommendDTO implements
		java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 好店推荐日志
	 */
	private String strbeginTime;
	private AShopRecommend srd;
	private String strendTime;
	private int type;
	private AShopRecommendLog asrdlog;
	public AShopRecommend getSrd() {
		return srd;
	}
	public void setSrd(AShopRecommend srd) {
		this.srd = srd;
	}

	public AShopRecommendLog getAsrdlog() {
		return asrdlog;
	}
	public void setAsrdlog(AShopRecommendLog asrdlog) {
		this.asrdlog = asrdlog;
	}
	public String getStrbeginTime() {
		return strbeginTime;
	}
	public void setStrbeginTime(String strbeginTime) {
		this.strbeginTime = strbeginTime;
	}
	public String getStrendTime() {
		return strendTime;
	}
	public void setStrendTime(String strendTime) {
		this.strendTime = strendTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
