package com.okwei.bean.enums;
/**
 * U_SupplyChannel 表中的  PayedReward字段
 * 是否支付悬赏
 * @author xuhaowen
 *
 */
public enum IsPayReward {
	/**
	 * 没有支付
	 */
	noPayed(0),
	/**
	 * 已经支付
	 */
	payed(1),
	/**
	 * 交易未完成（不能展示的数据）
	 */
	notpayed(-1);

	private final int step; 

    private IsPayReward(int step) { 
         this.step = step; 
    }
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
