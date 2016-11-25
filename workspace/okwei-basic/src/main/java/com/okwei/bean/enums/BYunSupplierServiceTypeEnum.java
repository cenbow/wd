package com.okwei.bean.enums;

public enum BYunSupplierServiceTypeEnum {
	/**
     * 以前的 8000的
     */
    OldService(0),
    /**
     * 7000 的 1年
     */
    OneYear(1),
    /**
     * 8600 3年
     */
    ThreeYear(2);
    private final int step;

    private BYunSupplierServiceTypeEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }

}
