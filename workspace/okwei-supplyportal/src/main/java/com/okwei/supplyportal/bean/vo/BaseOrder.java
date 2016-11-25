package com.okwei.supplyportal.bean.vo;

public class BaseOrder
{
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 买家微店号
     */
    private Long buyerWeiid;
    /**
     * 商家微店号
     */
    private Long supplyerWeiid;
    /**
     * 订单状态
     */
    private int orderState;
    /**
     * 订单类型
     */
    private int orderType;
    /**
     * 订单总价
     */
    private double totalPrice;
    /**
     * 订单邮费
     */
    private double postPrice;

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public Long getBuyerWeiid()
    {
        return buyerWeiid;
    }

    public void setBuyerWeiid(Long buyerWeiid)
    {
        this.buyerWeiid = buyerWeiid;
    }

    public Long getSupplyerWeiid()
    {
        return supplyerWeiid;
    }

    public void setSupplyerWeiid(Long supplyerWeiid)
    {
        this.supplyerWeiid = supplyerWeiid;
    }

    public int getOrderState()
    {
        return orderState;
    }

    public void setOrderState(int orderState)
    {
        this.orderState = orderState;
    }

    public int getOrderType()
    {
        return orderType;
    }

    public void setOrderType(int orderType)
    {
        this.orderType = orderType;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public double getPostPrice()
    {
        return postPrice;
    }

    public void setPostPrice(double postPrice)
    {
        this.postPrice = postPrice;
    }
}
