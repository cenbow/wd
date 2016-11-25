package com.okwei.bean.dto;

import com.okwei.bean.domain.UPlatformSupplyer;

public class AddCompanyDataDTO extends UPlatformSupplyer {  //添加公司资料
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] imageArr;  //公司的资历证书
	
	public String[] getImageArr() {
		return imageArr;
	}
	public void setImageArr(String[] imageArr) {
		this.imageArr = imageArr;
	}
	
	 
	
	
}
