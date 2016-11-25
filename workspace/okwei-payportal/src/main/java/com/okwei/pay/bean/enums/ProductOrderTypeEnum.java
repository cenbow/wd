package com.okwei.pay.bean.enums;

public enum ProductOrderTypeEnum {
    Gifi(3);
    private final int step;

    private ProductOrderTypeEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
