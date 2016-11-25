package com.okwei.bean.enums;
/**
 * @author 作者:齐李平  E-mail:qiliping@okwei.com
 * @version 1.0
 * @data 创建时间：2015年3月16日 下午3:36:04
 * 
 */
public enum BookAssistChangePriceType {
	/// <summary>
    /// 不修改
    /// </summary>
	notChange(0),
	/// <summary>
    /// 修改产品单价
    /// </summary>
	changeProductPrice(1),
	/// <summary>
    /// 修改订单总价
    /// </summary>
	changeTotalAmout(2);
	
	private final int step; 

    private BookAssistChangePriceType(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
