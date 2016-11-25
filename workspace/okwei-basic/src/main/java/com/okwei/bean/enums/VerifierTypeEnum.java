package com.okwei.bean.enums;

/// <summary>
/// VerifierType
/// </summary>
public enum VerifierTypeEnum
{
	/**
	 * 见习认证员
	 */
	ordinary(1),
	/**
	 * 高级认证员
	 */
	percent(2),
	/**
	 * 批发号认证员
	 */
	batchverifier(4),
	/**
	 * 认证点
	 */
	batchverifierport(8),
	/**
	 * 代理商
	 */
	batchport(16);
	
	private final int step; 

    private VerifierTypeEnum(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
