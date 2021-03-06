package com.okwei.pay.bean.enums;

/**
 * 批发号认证点状态
 * 
 * @author Administrator
 *
 */
public enum BatchPortStatusEnum {
    /**
     * 进驻 已支付
     */
    PayIn(3);

    private final int step;

    private BatchPortStatusEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
