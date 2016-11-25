package com.okwei.bean.enums;


public enum OperationEnum {
	 /**
	    * (没用的)取消
	    */
		NoUse(-1),
		/**
		 * 提交中（申请中）
		 */
		Submit(0),
	   /**
	    * 正常（通过）
	    */
		Ok(1),
		/**
		 * 不通过
		 */
		No(2),
		/**
		 * 已处理、已解决、已打款
		 */
		Resolved(3);
		
	    private final int step; 
	    private OperationEnum(int step) { 

	        this.step = step; 
	   }
	   @Override
	   public String toString()
	   {
	   	return String.valueOf(this.step);         	
	   }
}
