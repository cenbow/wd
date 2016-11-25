package com.okwei.myportal.bean.enums;
/**
 * 快递方式 
 */
public enum CourierTypeEnum
{ 
    /**
     * 快递
     */
    Express(1),
    /**
     * 物流
     */
    Logistics(4);

    private final int step;

    private CourierTypeEnum(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
