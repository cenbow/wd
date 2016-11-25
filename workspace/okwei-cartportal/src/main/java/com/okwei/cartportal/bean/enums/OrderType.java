package com.okwei.cartportal.bean.enums;

public enum OrderType
{
    /**
     * 云商通订单
     * */
    Pt(1),
    /**
     * 供应商进驻
     * */
    YunGys(2),
    /**
     * 认证员保证金
     * */
    YunRzy(3),
    /**
     * 导航冲值
     * */
    DaoHang(4),
    /**
     * 抢票
     * */
    Qp(5),
    /**
     * 充值
     * */
    ChongZhi(6),
    /**
     * 点餐系统订单
     * */
    DingCan(7),
    /**
     * 零售订单
     * */
    BatchOrder(8),
    /**
     * 批发号批发订单
     * */
    BatchWholesale(9),
    /**
     * 预订订单首款
     * */
    BookHeadOrder(10),
    /**
     * 预定订单尾款
     * */
    BookTailOrder(11),
    /**
     * 预订单
     * */
    BookOrder(12),
    /**
     * 批发号供应商订单
     */
    BatchGys(13),
    /**
     * 批发号认证员订单
     * */
    BatchRzy(14),
    // / <summary>
    // / 预订单全款
    // / </summary>
    BookFullOrder(15),
    /**
     * 供应商和认证员一起的订单 
     */
    GysAndVerifier(16);
    private final int step;

    private OrderType(int step)
    {

        this.step = step;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.step);
    }
}
