package com.okwei.bean.enums;

public enum BrandSupplyerStateEnum {
	 /**
	  * 已进驻
	  */
	 payIn(1),
	 /**
	  * 已取消
	  */
	 cancel(-1),
	 /**
	  * 已退驻
	  */
	 exit(99);
	private final int step; 

    private BrandSupplyerStateEnum(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
