package com.okwei.bean.enums;

public enum BondType {
	 /**
	    * 工厂号 （保证金）
	    */
		YunSupplier(1),
		/**
		 *批发号 （保证金）
		 */
		BatchSupplier(2),
	   /**
	    * 认证员 （保证金）
	    */
		RenZheng(3),
		/**
		 * 认证点
		 */
		Port(4);
		
	    private final int step; 
	    private BondType(int step) { 

	        this.step = step; 
	   }
	   @Override
	   public String toString()
	   {
	   	return String.valueOf(this.step);         	
	   }
}
