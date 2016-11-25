package com.okwei.bean.enums;

public enum FromTypeEnum {

    PC(0),
    APP(1),
    WAP(2);
	private final int step; 
    private FromTypeEnum(int step) { 
         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
