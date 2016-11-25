package com.okwei.myportal.bean.enums;

public enum ProductBType {
	 /**
     * 正常产品
     */
    normal(0),
    /**
     * 赠品区
     */
    Gifts(1);
    private final int step;

    private ProductBType(int step)
    {
        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
