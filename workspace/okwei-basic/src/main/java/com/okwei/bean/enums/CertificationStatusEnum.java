package com.okwei.bean.enums;

public enum CertificationStatusEnum {


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
	 failed(2);
	private final int step; 

    private CertificationStatusEnum(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }

}
