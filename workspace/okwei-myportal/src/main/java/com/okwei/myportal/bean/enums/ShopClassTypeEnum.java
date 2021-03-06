package com.okwei.myportal.bean.enums;

public enum ShopClassTypeEnum
{
    /**
     * 供应商
     */
    Supplier(1),
    /**
     * 普通微店主
     */
    Ordinary(0);

    private final int step;

    private ShopClassTypeEnum(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
