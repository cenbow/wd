package com.okwei.myportal.bean.vo;

import java.util.List;

/**
 * 预订单
 */
public class PreOrderListVO
{
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付订单号
     */
    private String payOrderNo;
    /**
     * 卖家名称
     */
    private String supplyerName;
    /**
     * 卖家微店号
     */
    private long supplyId;
    /**
     * 订单时间
     */
    private String createTimeStr;
    /**
     * 订单状态
     */
    private int orderState;
    /**
     * 订单类型
     */
    private int orderType;
    /**
     * 付款金额
     */
    private double totalPrice;
    /**
     * 订单邮费
     */
    private double postPrice;

    /**
     * 是否全款支付
     */
    private boolean isFullPrice;
    /**
     * 首款
     */
    private double depositprice;
    /**
     * 尾款
     */
    private double finalprice;
    /**
     * 0发货前、1发货后 付尾款
     */
    private int tailPayType;
    /**
     * 发货时间
     */
    private String deliveryTimeStr;
    /**
     * 订单产品列表
     */
    private List<ProductOrderVO> proList;
    /**
     * 金额状态
     */
    private List<String> amountState;

    public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getPayOrderNo()
    {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo)
    {
        this.payOrderNo = payOrderNo;
    }

    public List<String> getAmountState()
    {
        return amountState;
    }

    public void setAmountState(List<String> amountState)
    {
        this.amountState = amountState;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getSupplyerName()
    {
        return supplyerName;
    }

    public void setSupplyerName(String supplyerName)
    {
        this.supplyerName = supplyerName;
    }

    public long getSupplyId()
    {
        return supplyId;
    }

    public void setSupplyId(long supplyId)
    {
        this.supplyId = supplyId;
    }

    public String getCreateTimeStr()
    {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }

    public int getOrderState()
    {
        return orderState;
    }

    public void setOrderState(int orderState)
    {
        this.orderState = orderState;
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

    public double getDepositprice()
    {
        return depositprice;
    }

    public void setDepositprice(double depositprice)
    {
        this.depositprice = depositprice;
    }

    public double getFinalprice()
    {
        return finalprice;
    }

    public void setFinalprice(double finalprice)
    {
        this.finalprice = finalprice;
    }

    public String getDeliveryTimeStr()
    {
        return deliveryTimeStr;
    }

    public void setDeliveryTimeStr(String deliveryTimeStr)
    {
        this.deliveryTimeStr = deliveryTimeStr;
    }

    public boolean isFullPrice()
    {
        return isFullPrice;
    }

    public void setFullPrice(boolean isFullPrice)
    {
        this.isFullPrice = isFullPrice;
    }

    public List<ProductOrderVO> getProList()
    {
        return proList;
    }

    public void setProList(List<ProductOrderVO> proList)
    {
        this.proList = proList;
    }

    public int getTailPayType()
    {
        return tailPayType;
    }

    public void setTailPayType(int tailPayType)
    {
        this.tailPayType = tailPayType;
    }
}
