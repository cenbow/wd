package com.okwei.appinterface.enums;
/**
 * 经营类目类型
 * @author yangjunjun
 *
 */
public enum SupplierBusCategoryTypeEnum {
    
    /// <summary>
    /// 云商通
    /// </summary>
    Yun(0),
    ///<summary>
    /// 批发号
    ///</summary>
    Bacht(1);
	private final int step; 
    private SupplierBusCategoryTypeEnum(int step) { 

         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
