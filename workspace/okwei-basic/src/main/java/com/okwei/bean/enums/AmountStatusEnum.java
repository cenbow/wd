package com.okwei.bean.enums;

public enum AmountStatusEnum {
	/**
	 *  未放款
	 * @return
	 */
    weiFangKuan (0),
   /**
    * 已到账
    */
    yiFangKuan (1),
    /**
     * 退款中
     */
    tuiKuanZhong (2),
    /**
     * 已退款
     */
    yiTuiKuan (3),
    /**
     *  收货冻结
     */
    shouHuoDongjie (4),
    /**
     * 等待扣税
     */
    waitTax (5);
	private final int Type; 

    private AmountStatusEnum(int step) 
    { 
         this.Type = step; 
    }
    
    public String toString()
    {
    	return String.valueOf(this.Type);         	
    }
}
