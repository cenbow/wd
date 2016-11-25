package com.okwei.appinterface.enums;

/**
 * 产品的状态 enum
 * 
 * @author Administrator
 *
 */
public enum ProductStatus 
{
	/**
	 * 提交中 
	 */
	Submitting(-1),
	/**
	 * 展示中
	 */
	Showing(1),
	/**
	 * 不通过
	 */
	Fail(2),
	/**
	 * 草稿
	 */
	OutLine(3),
	/**
	 * 已下架
	 */
	Drop(4),
	/**
	 * 已删除
	 */
	Deleted(5);
	
	private final int step; 

    private ProductStatus(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
