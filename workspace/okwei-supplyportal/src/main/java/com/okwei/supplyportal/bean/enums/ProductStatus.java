package com.okwei.supplyportal.bean.enums;

public enum ProductStatus {
	/**
	 * 提交中 
	 */
	Submitting{public short getNo(){return -1;}},
	/**
	 * 展示中
	 */
	Showing{public short getNo(){return 1;}},
	/**
	 * 不通过
	 */
	Fail{public short getNo(){return 2;}},
	/**
	 * 草稿
	 */
	OutLine{public short getNo(){return 3;}},
	/**
	 * 已下架
	 */
	Drop{public short getNo(){return 4;}},
	/**
	 * 已删除
	 */
	Deleted{public short getNo(){return 5;}};
	
	public abstract short getNo();
}
