package com.okwei.pay.bean.enums;

/**
 * 供应商状态 0未审核2不通过，3审核通过 4进驻 99已退驻
 * 
 * @author Administrator
 *
 */
public enum YunSupplierStatusEnum {
    /**
     * 未审核
     */
    WeiShenHe(0),
    /**
     * 不通过
     */
    BuTongGuo(2),
    /**
     * 审核通过
     */
    ShenHeTongGuo(3),
    /**
     * 进驻
     */
    JinZhu(4),
    /**
     * 退驻
     */
    TuiZHu(99);
    private final int step;

    private YunSupplierStatusEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
