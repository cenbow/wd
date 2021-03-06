package com.okwei.pay.bean.enums;

/**
 * 批发号供应商状态
 * 
 * @author Administrator
 *
 */
public enum BatchSupplyStatusEnum {
    /**
     * 进驻 已支付
     */
    PayIn(4);
    private final int step;

    private BatchSupplyStatusEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
