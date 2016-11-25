package com.okwei.bean.enums;

public enum MainDataUserType {
 
   /**
    * 普通用户
    */
    user (1),
    /**
     * 供应商
     */
    supplier (2),
    /**
     * 运营
     */
    operate (3) ;
    
	private final int userType; 

    private MainDataUserType(int step) 
    { 
         this.userType = step; 
    }
    
    public String toString()
    {
    	return String.valueOf(this.userType);         	
    }
}
