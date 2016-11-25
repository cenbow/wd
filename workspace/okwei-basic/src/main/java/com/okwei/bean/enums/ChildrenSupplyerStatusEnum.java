package com.okwei.bean.enums;
/**
 * 子供应商状态
 * @author xuhaowen
 *
 */
public enum ChildrenSupplyerStatusEnum {
	  /**
	    * 关闭
	    */
		close(0),
		/**
		 * 待审核
		 */
		waitAudit(2),
		/**
		 * 已通过
		 */
		pass(3),
		/**
		 * 不通过
		 */
		noPass(4);
			
		private final int step; 

	    private ChildrenSupplyerStatusEnum(int step) { 

	         this.step = step; 
	    }
	    
	    @Override
	    public String toString()
	    {
	    	return String.valueOf(this.step);         	
	    }
}
