package com.okwei.myportal.bean.vo;

import java.util.List;

public class SupplyBookOrderVO extends BaseOrder
{
    /**
     * 卖家名称
     */
    private String supplyerName;
    /**
     * 买家姓名
     */
    private String buyerName;
    /**
     * 订单时间
     */
    private String createTimeStr;
    /**
     * 订单状态名称
     */
    private String orderStateName;
    
    /**
     * 订单类型
     */
    private int  orderType;
    /**
     * 订单产品列表
     */
    private List<ProductOrderVO> proList;

    /**
     *  购买者身份，1-微店主  2-落地店 3-代理商  4-供应商
     */
    private String identityId;
    /**
     * 支付金额信息
     */
    private List<String> payContent;
    /**
     * 0全额、1百分比、2预付金额
     */
    private int bookType;
    /**
     * 支付类型（0发货前付尾款 1发货后付尾款）
     */
    private int paymentType;

   
	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(int paymentType)
    {
        this.paymentType = paymentType;
    }

    public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public int getBookType()
    {
        return bookType;
    }

    public void setBookType(int bookType)
    {
        this.bookType = bookType;
    }

    public List<String> getPayContent()
    {
        return payContent;
    }

    public void setPayContent(List<String> payContent)
    {
        this.payContent = payContent;
    }

    public List<ProductOrderVO> getProList()
    {
        return proList;
    }

    public void setProList(List<ProductOrderVO> proList)
    {
        this.proList = proList;
    }

    public String getOrderStateName()
    {
        return orderStateName;
    }

    public void setOrderStateName(String orderStateName)
    {
        this.orderStateName = orderStateName;
    }

    public String getBuyerName()
    {
        return buyerName;
    }

    public void setBuyerName(String buyerName)
    {
        this.buyerName = buyerName;
    }

    public String getSupplyerName()
    {
        return supplyerName;
    }

    public void setSupplyerName(String supplyerName)
    {
        this.supplyerName = supplyerName;
    }

    public String getCreateTimeStr()
    {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }

}
