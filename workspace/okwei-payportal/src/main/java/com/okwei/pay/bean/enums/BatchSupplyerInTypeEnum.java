package com.okwei.pay.bean.enums;

public enum BatchSupplyerInTypeEnum {

    /**
     * 批发号供应商 或 认证点
     */
    SupplyOrPort(3),
    /**
     * 同时申请 批发号供应商 和 认证点
     */
    SupplyAndPort(4),
    /**
     * 市场整体入驻
     */
    AllIn(6);
    private final int step;

    private BatchSupplyerInTypeEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
