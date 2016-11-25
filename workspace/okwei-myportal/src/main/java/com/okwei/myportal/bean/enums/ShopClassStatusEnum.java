package com.okwei.myportal.bean.enums;

public enum ShopClassStatusEnum
{
    /**
     * 启用
     */
    Enable (1),
    /**
     * 禁用
     */
    Disable(0);

    private final int step;

    private ShopClassStatusEnum(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
