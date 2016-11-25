package com.okwei.bean.enums;

public enum SupplierTypeEnum {
	/// <summary>
    /// 普通微店
    /// </summary>
    CommonUser(0),
    /// <summary>
    /// 运商通供应商
    /// </summary>
    YunSupplier(2),
    /// <summary>
    /// 批发商
    /// </summary>
    BatchSupplier(4),
    /// <summary>
    /// Erp供应商
    /// </summary>
    ErpSupplier(8),
    /**
     * 平台号
     */
    PlatformSupplier(16),
    /**
     * 品牌号
     */
    BrandSupplier(32);
    private final int step; 
    private SupplierTypeEnum(int step) { 

        this.step = step; 
   }
   @Override
   public String toString()
   {
   	return String.valueOf(this.step);         	
   }
}
