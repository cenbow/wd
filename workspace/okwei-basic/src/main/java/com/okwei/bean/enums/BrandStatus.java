package com.okwei.bean.enums;

public enum BrandStatus {
	/**
	 * 申请中
	 */
	apply(0),
	/**
	 * 退款申请中
	 */
	 pass(1),
	 /**
	  * 未通过
	  */
	 failed(2),
	 /**
	  * 已退出
	  */
	 exit(3);
	private final int step; 

    private BrandStatus(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
