package com.okwei.bean.enums;

public enum ProductTagType {
	/**
	 * 批发
	 */
	Wholesale(1),
	/**
	 * 预定
	 */
	Schedule(2),
	/**
	 * 零售
	 */
	Retail(4),
	/**
	 * 铺货
	 */
	Distirbution(8);
	
    private final int step;

    private ProductTagType(int step)
    {
        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
