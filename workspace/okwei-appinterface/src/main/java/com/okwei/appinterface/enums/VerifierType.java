package com.okwei.appinterface.enums;

/// <summary>
/// VerifierType
/// </summary>
public enum VerifierType
{
	/**
	 * 见习认证员
	 */
	ordinary(1),
	/**
	 * 工厂号正式认证员
	 */
	percent(2),
	/**
	 * 批发号认证员
	 */
	batchverifier(4),
	/**
	 * 认证点
	 */
	batchverifierport(8);
	
	private final int step; 

    private VerifierType(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
