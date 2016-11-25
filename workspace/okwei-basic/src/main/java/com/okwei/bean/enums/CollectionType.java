package com.okwei.bean.enums;

public enum CollectionType {

    /**
     * 店铺
     * */
    Shop(1),
    /**
     * 商品
     * */
    Commodity(2),
    /**
     * 分享页
     * */
    Share(3);
    private final int step;

    private CollectionType(int step) {
	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
