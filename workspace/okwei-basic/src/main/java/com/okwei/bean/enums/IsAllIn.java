package com.okwei.bean.enums;

public enum IsAllIn {
	/**
	 * 没有整体入驻
	 */
	nopresence(0),
	/**
	 * 已经整体入驻
	 */
	presenced(1);

	private final int step; 

    private IsAllIn(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}