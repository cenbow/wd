package com.okwei.myportal.bean.enums;

public enum OrderStatus
{

    /**
     * 待付款
     */
    Daifukuan(0),
    /**
     * 待发货
     */
    Daifahuo(1),
    /**
     * 待收货
     */
    Daishouhuo(2),
    /**
     * 待评价
     */
    Daipingjia(3);

    private final int step;

    private OrderStatus(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
