package com.okwei.bean.enums;

public enum ChildrenAccountTypeEnum {
	/**
    * 子账号员工
    */
	childrenStaff(1),
	/**
	 * 子账号供应商
	 */
	childrenSupply(2);
		
	private final int step; 

    private ChildrenAccountTypeEnum(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
