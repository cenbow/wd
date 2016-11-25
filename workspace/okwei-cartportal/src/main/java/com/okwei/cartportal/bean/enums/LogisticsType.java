package com.okwei.cartportal.bean.enums;
/**
 * 物流类型枚举
 * 黄俊达
 * */
public enum LogisticsType {
	/**
	 * 快递
	 * */
	Courier(1),
	/**
	 * EMS
	 * */
	EMS(2),
	/**
	 * 平邮
	 * */
	Ordinary(3),
	/**
	 * 平邮
	 * */
	Wuliu(4);
	private final int step;  
    private LogisticsType(int step) {  
         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
