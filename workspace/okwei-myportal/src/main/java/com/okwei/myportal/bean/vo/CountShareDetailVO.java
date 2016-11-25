package com.okwei.myportal.bean.vo;

import com.okwei.common.PageResult;

/**
 * 统计分享详情信息
 * @author fh
 */
public class CountShareDetailVO extends CountMainDataVO implements java.io.Serializable { 
	
	PageResult<CountShareVO> productsList;

	public PageResult<CountShareVO> getProductsList() {
		return productsList;
	}

	public void setProductsList(PageResult<CountShareVO> productsList) {
		this.productsList = productsList;
	} 
	 
}
