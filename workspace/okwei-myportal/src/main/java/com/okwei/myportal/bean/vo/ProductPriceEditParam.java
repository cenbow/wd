package com.okwei.myportal.bean.vo;

import java.util.ArrayList;
import java.util.List;

import com.okwei.bean.domain.OProductOrder;

public class ProductPriceEditParam
{

    /**
     * 邮费
     */
    private Double postPrice;
    /**
     * 供应商订单号
     */
    private String supplyOrderId;
    /**
     * 供应商微店号
     */
    private long supplyWeiid;
    /**
     * 总价（不包含邮费）
     */
    private double totalPrice;
    /**
     * 产品列表
     */
    private List<OProductOrder> productParamList = new ArrayList<OProductOrder>();

    public Double getPostPrice()
    {
        return postPrice;
    }

    public void setPostPrice(Double postPrice)
    {
        this.postPrice = postPrice;
    }

    public long getSupplyWeiid()
    {
        return supplyWeiid;
    }

    public void setSupplyWeiid(long supplyWeiid)
    {
        this.supplyWeiid = supplyWeiid;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    /**
     * 供应商订单id
     * 
     * @return
     */
    public String getSupplyOrderId()
    {
        return supplyOrderId;
    }

    public void setSupplyOrderId(String supplyOrderId)
    {
        this.supplyOrderId = supplyOrderId;
    }

    /**
     * 产品价格编辑参数
     * 
     * @return
     */
    public List<OProductOrder> getProductParamList()
    {
        return productParamList;
    }

    public void setProductParamList(List<OProductOrder> productParamList)
    {
        this.productParamList = productParamList;
    }
}
