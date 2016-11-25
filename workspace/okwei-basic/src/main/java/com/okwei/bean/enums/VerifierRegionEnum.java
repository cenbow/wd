package com.okwei.bean.enums;

/**
 * 认证员负责区域类型
 * @author Administrator
 *
 */
public enum VerifierRegionEnum {
	 /// <summary>
    /// 批发号
    /// </summary>
    Batch(1),
    /// <summary>
    /// 平台号
    /// </summary>
    Platform(2);   
	private final int step; 

    private VerifierRegionEnum(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
