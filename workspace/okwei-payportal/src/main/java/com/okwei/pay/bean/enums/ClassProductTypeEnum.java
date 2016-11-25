package com.okwei.pay.bean.enums;

/**
 * 上架表类型
 * 
 * @author Administrator
 */
public enum ClassProductTypeEnum {
    /**
     * 微店主上架别的供应商的
     */
    OtherSupply(0),
    /**
     * 供应商上架自己的
     */
    MySelf(1),
    /**
     * 微店主上架自己的
     */
    WeiSelf(2),
    /**
     * 未交钱的供应商上架自己的
     */
    NoPaySupply(10);
    private final int step;

    private ClassProductTypeEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
