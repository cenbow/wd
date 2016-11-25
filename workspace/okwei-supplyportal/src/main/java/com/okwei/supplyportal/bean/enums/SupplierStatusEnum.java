package com.okwei.supplyportal.bean.enums;

public enum SupplierStatusEnum {
	 /// <summary>
    /// 取消了
    /// </summary>
    Cancel(-1),
    /// <summary>
    /// 申请中
    /// </summary>
    Applying(0),
    ///<summary>
    /// 重新申请
    ///</summary>
    ReApply(1),
    /// <summary>
    /// 审核通过
    /// </summary>
    Pass(3),
    /// <summary>
    /// 审核不通过
    /// </summary>
    Fail(2),
    /// <summary>
    /// 缴费进驻
    /// </summary>
    PayIn(4);
	private final int step; 
    private SupplierStatusEnum(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
