package com.okwei.cartportal.bean.enums;
/// <summary>
/// 购物车类型
/// </summary>
public enum ShoppingCarType {
	/**
	 * 零售订单
	 * */
	Retail(1),
	/**
	 * 预订单
	 * */
	Presell(2),
	/**
	 * 批发订单
	 * */
	Wholesale(3);
	private final int step; 

    private ShoppingCarType(int step) { 
         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
