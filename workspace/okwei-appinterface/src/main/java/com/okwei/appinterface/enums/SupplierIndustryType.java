package com.okwei.appinterface.enums;
public enum SupplierIndustryType {
	/**
	 * 云商通
	 */
    Yun(1),
    /**
     * 批发号供应商
     */
    Bacht(2);
	private final int step; 
    private SupplierIndustryType(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}