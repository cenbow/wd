package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class SaleOrderVO implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 产品标题
     */
    private String proTitle;
    /**
     * 产品ID
     */
    private BigInteger proId;
    /**
     * 产品图片
     */
    private String proImg;
    /**
     * 产品属性
     */
    private String property;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 购买数量
     */
    private Integer count;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 供应商ID
     */
    private BigInteger supplierId;
    /**
     * 买家姓名
     */
    private String buyName;
    /**
     * 订单时间
     */
    private Date orderTime;
    /**
     * 佣金
     */
    private BigDecimal commission;
    /**
     * 订单类型
     */
    private Integer orderType;
    
    public Integer getOrderType()
    {
        return orderType;
    }
    public void setOrderType(Integer orderType)
    {
        this.orderType = orderType;
    }
    public String getOrderNo()
    {
        return orderNo;
    }
    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }
    public String getProTitle()
    {
        return proTitle;
    }
    public void setProTitle(String proTitle)
    {
        this.proTitle = proTitle;
    }
    public BigInteger getProId()
    {
        return proId;
    }
    public void setProId(BigInteger proId)
    {
        this.proId = proId;
    }
    public String getProImg()
    {
        return proImg;
    }
    public void setProImg(String proImg)
    {
        this.proImg = proImg;
    }
    public String getProperty()
    {
        return property;
    }
    public void setProperty(String property)
    {
        this.property = property;
    }
    public BigDecimal getPrice()
    {
        return price;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    public Integer getCount()
    {
        return count;
    }
    public void setCount(Integer count)
    {
        this.count = count;
    }
    public String getSupplierName()
    {
        return supplierName;
    }
    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }
    public BigInteger getSupplierId()
    {
        return supplierId;
    }
    public void setSupplierId(BigInteger supplierId)
    {
        this.supplierId = supplierId;
    }
    public String getBuyName()
    {
        return buyName;
    }
    public void setBuyName(String buyName)
    {
        this.buyName = buyName;
    }
    public Date getOrderTime()
    {
        return orderTime;
    }
    public void setOrderTime(Date orderTime)
    {
        this.orderTime = orderTime;
    }
    public BigDecimal getCommission()
    {
        return commission;
    }
    public void setCommission(BigDecimal commission)
    {
        this.commission = commission;
    }


}
