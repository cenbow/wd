package com.okwei.myportal.bean.vo;

import java.util.List;

public class OrderProductListVO
{
    /**
     * 供应商微店号
     */
    private long supplierId;
    /**
     * 供应商QQ
     */
    private String supplierQQ;
    /**
     * 运费
     */
    private double freight;
    /**
     * 运费类型（1快递，2EMS,3平邮）
     */
    private int freightType;
    /**
     * 供应商订单类型
     */
    private int OrderType;
    /**
     * 产品列表
     */
    private List<ProductOrderVO> proList;

    public long getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(long supplierId)
    {
        this.supplierId = supplierId;
    }

    public String getSupplierQQ()
    {
        return supplierQQ;
    }

    public void setSupplierQQ(String supplierQQ)
    {
        this.supplierQQ = supplierQQ;
    }

    public double getFreight()
    {
        return freight;
    }

    public void setFreight(double freight)
    {
        this.freight = freight;
    }

    public int getFreightType()
    {
        return freightType;
    }

    public void setFreightType(int freightType)
    {
        this.freightType = freightType;
    }

    public int getOrderType()
    {
        return OrderType;
    }

    public void setOrderType(int orderType)
    {
        OrderType = orderType;
    }

    public List<ProductOrderVO> getProList()
    {
        return proList;
    }

    public void setProList(List<ProductOrderVO> proList)
    {
        this.proList = proList;
    }
}
