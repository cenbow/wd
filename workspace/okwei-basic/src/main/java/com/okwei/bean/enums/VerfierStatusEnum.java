package com.okwei.bean.enums;
/**
 * 认证员申请
 * @author Administrator
 *
 */
public enum VerfierStatusEnum {
	 /// <summary>
    /// 申请中
    /// </summary>
    Applying(0),
    /// <summary>
    /// 审核通过
    /// </summary>
    Pass(1),
    /// <summary>
    /// 审核不通过
    /// </summary>
    Fail(2),
    /// <summary> 
    /// 缴费进驻
    /// </summary>
    PayIn(3),
    /// <summary>
    /// 取消退出
    /// </summary>
    Cancel(-1),
    /**
     * 退驻
     */
    exit(99);    
	private final int step; 

    private VerfierStatusEnum(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
