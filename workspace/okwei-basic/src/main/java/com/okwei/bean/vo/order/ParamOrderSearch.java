package com.okwei.bean.vo.order;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 订单搜索条件参数VO
 * @author fuhao
 */
public class ParamOrderSearch
{
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单状态
     */
    private Short state;
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 开始时间
     */
    @Temporal(TemporalType.DATE)
    private Date beginTime;
    /**
     * 结束时间
     */
    @Temporal(TemporalType.DATE)
    private Date endTime;
    /**
     * 购买者微店号
     */
    private Long buyerid;
    /**
     * 最小金额
     */
    private Double minPrice;
    /**
     * 最大金额
     */
    private Double maxPrice;
    /**
     * 开始时间字符串
     */
    private String beginTimeStr;
    /**
     * 结束时间字符串
     */
    private String endTimeStr;

    public String getBeginTimeStr()
    {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr)
    {
        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr()
    {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr)
    {
        this.endTimeStr = endTimeStr;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    /**
     * 获取全部时，不传
     * 
     * @return
     */
    public Short getState()
    {
        return state;
    }

    public void setState(Short state)
    {
        this.state = state;
    }

    public Integer getOrderType()
    {
        return orderType;
    }

    public void setOrderType(Integer orderType)
    {
        this.orderType = orderType;
    }

    public Long getBuyerid()
    {
        return buyerid;
    }

    public void setBuyerid(Long buyerid)
    {
        this.buyerid = buyerid;
    }

    public Double getMinPrice()
    {
        return minPrice;
    }

    public void setMinPrice(Double minPrice)
    {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice()
    {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice)
    {
        this.maxPrice = maxPrice;
    }
}
