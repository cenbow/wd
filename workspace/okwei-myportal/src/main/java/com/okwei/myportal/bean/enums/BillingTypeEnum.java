package com.okwei.myportal.bean.enums;

/**
 * 运费类型 
 */
public enum BillingTypeEnum
{
    /**
     * 件数
     */
    Number(1),
    /**
     * 重量
     */
    Weight(2),
    /**
     * 重量
     */
    Volume(3);

    private final int step;

    private BillingTypeEnum(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
