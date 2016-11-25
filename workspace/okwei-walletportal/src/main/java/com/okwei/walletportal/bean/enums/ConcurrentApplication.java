package com.okwei.walletportal.bean.enums;

public enum ConcurrentApplication
{
	/**
	    * 只申请供应商或认证点
	    */
		No(1),
	   /**
	    * 同时申请认证点和供应商
	    */
		Yes(2),
		/**
		 * 整体入驻
		 */
		IsAllIn(3);
	    private final int step; 
	    private ConcurrentApplication(int step) { 

	        this.step = step; 
	   }
	   @Override
	   public String toString()
	   {
	   	return String.valueOf(this.step);         	
	   }
}
