package com.okwei.supplyportal.bean.vo;

import java.util.ArrayList;
import java.util.List;

import com.okwei.bean.domain.OProductOrder;

public class ReservedConfirmVO
{
    /**
     * 供应商订单id
     */
    private String supplyOrderid;
    /**
     * 当前登录者weiid(供应商weiid)
     */
    private long weiid;

    /**
     * 确定、拒绝 预订单
     */
    private boolean isSured;
    /**
     * 0不修改，1修改产品单价，2修改总价
     */
    private Integer editPriceType;
    /**
     * 修改产品单价时，需填写 订单产品id,价格
     */
    private List<OProductOrder> products = new ArrayList<OProductOrder>();
    /**
     * 修改产品单价时，需填写运费
     */
    private Double editPostPrice;
    /**
     * 修改总价时，填写
     */
    private Double editTotalPrice;
    /**
     * 0全额付款，1预付百分比，2预付指定金额
     */
    private Integer payType;
    /**
     * 预付百分比（如：20）
     */
    private Integer firstPercent;
    /**
     * 预付金额
     */
    private Double firstPrice;
    /**
     * 何时付尾款
     */
    private Integer tailPayType;
    /**
     * 给采购商的问候
     */
    private String message;
    /**
     * 预计发货时间
     */
    private String preDeliverTime;

    public String getSupplyOrderid()
    {
        return supplyOrderid;
    }

    public void setSupplyOrderid(String supplyOrderid)
    {
        this.supplyOrderid = supplyOrderid;
    }

    public long getWeiid()
    {
        return weiid;
    }

    public void setWeiid(long weiid)
    {
        this.weiid = weiid;
    }

    public boolean isSured()
    {
        return isSured;
    }

    public void setSured(boolean isSured)
    {
        this.isSured = isSured;
    }

    public Integer getEditPriceType()
    {
        return editPriceType;
    }

    public void setEditPriceType(Integer editPriceType)
    {
        this.editPriceType = editPriceType;
    }

    public List<OProductOrder> getProducts()
    {
        return products;
    }

    public void setProducts(List<OProductOrder> products)
    {
        this.products = products;
    }

    public Double getEditPostPrice()
    {
        return editPostPrice;
    }

    public void setEditPostPrice(Double editPostPrice)
    {
        this.editPostPrice = editPostPrice;
    }

    public Double getEditTotalPrice()
    {
        return editTotalPrice;
    }

    public void setEditTotalPrice(Double editTotalPrice)
    {
        this.editTotalPrice = editTotalPrice;
    }

    public Integer getPayType()
    {
        return payType;
    }

    public void setPayType(Integer payType)
    {
        this.payType = payType;
    }

    public Integer getFirstPercent()
    {
        return firstPercent;
    }

    public void setFirstPercent(Integer firstPercent)
    {
        this.firstPercent = firstPercent;
    }

    public Double getFirstPrice()
    {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice)
    {
        this.firstPrice = firstPrice;
    }

    public Integer getTailPayType()
    {
        return tailPayType;
    }

    public void setTailPayType(Integer tailPayType)
    {
        this.tailPayType = tailPayType;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getPreDeliverTime()
    {
        return preDeliverTime;
    }

    public void setPreDeliverTime(String preDeliverTime)
    {
        this.preDeliverTime = preDeliverTime;
    }

}
