package com.okwei.supplyportal.bean.vo;

import java.util.List;

/**
 * 退款详情VO
 */
public class RefundVO
{

    /**
     * 退款状态
     */
    private Integer refundType;
    /**
     * 退款订单ID
     */
    private long backOrder;
    /**
     * 供应商订单号
     */
    private String supOrderID;
    /**
     * 供应商名称
     */
    private String supplyerName;
    /**
     * 供应商QQ
     */
    private String supplyerQQ;
    /**
     * 退款时间
     */
    private String refundTime;
    /**
     * 流程ID
     */
    private long processId;
    /**
     * 退款方式(1退款，2退货)
     */
    private int refundWay;
    /**
     * 退款金额
     */
    private double refundPrice;
    /**
     * 退款理由
     */
    private String reason;
    /**
     * 不同意退款理由
     */
    private String reasonNo;
    /**
     * 微店网处理理由
     */
    private String reasonOkwei;
    /**
     * 产品图片列表
     */
    private List<String> proImages;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 退货地址
     */
    private String retreatAddress;
    /**
     * 备注
     */
    private String remark;
    /**
     * 物流信息
     */
    private LogisticsVO logistics;

    /**
     * 退款产品列表
     */
    private List<ProductOrderVO> proList;
    /**
     * 售后记录
     */
    private List<String> orderrecord;
    /**
     * 退款金额
     */
    private double refundAmount;
    /**
     * 订单原来的状态
     */
    private int orderType;

    public int getOrderType()
    {
        return orderType;
    }

    public void setOrderType(int orderType)
    {
        this.orderType = orderType;
    }

    public Integer getRefundType()
    {
        return refundType;
    }

    public void setRefundType(Integer refundType)
    {
        this.refundType = refundType;
    }

    public List<String> getOrderrecord()
    {
        return orderrecord;
    }

    public void setOrderrecord(List<String> orderrecord)
    {
        this.orderrecord = orderrecord;
    }

    public double getRefundAmount()
    {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount)
    {
        this.refundAmount = refundAmount;
    }

    public String getRefundTime()
    {
        return refundTime;
    }

    public void setRefundTime(String refundTime)
    {
        this.refundTime = refundTime;
    }

    public long getProcessId()
    {
        return processId;
    }

    public void setProcessId(long processId)
    {
        this.processId = processId;
    }

    public int getRefundWay()
    {
        return refundWay;
    }

    public void setRefundWay(int refundWay)
    {
        this.refundWay = refundWay;
    }

    public double getRefundPrice()
    {
        return refundPrice;
    }

    public void setRefundPrice(double refundPrice)
    {
        this.refundPrice = refundPrice;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReasonNo()
    {
        return reasonNo;
    }

    public void setReasonNo(String reasonNo)
    {
        this.reasonNo = reasonNo;
    }

    public String getReasonOkwei()
    {
        return reasonOkwei;
    }

    public void setReasonOkwei(String reasonOkwei)
    {
        this.reasonOkwei = reasonOkwei;
    }

    public List<String> getProImages()
    {
        return proImages;
    }

    public void setProImages(List<String> proImages)
    {
        this.proImages = proImages;
    }

    public String getConsignee()
    {
        return consignee;
    }

    public void setConsignee(String consignee)
    {
        this.consignee = consignee;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getRetreatAddress()
    {
        return retreatAddress;
    }

    public void setRetreatAddress(String retreatAddress)
    {
        this.retreatAddress = retreatAddress;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public LogisticsVO getLogistics()
    {
        return logistics;
    }

    public void setLogistics(LogisticsVO logistics)
    {
        this.logistics = logistics;
    }

    public long getBackOrder()
    {
        return backOrder;
    }

    public void setBackOrder(long backOrder)
    {
        this.backOrder = backOrder;
    }

    public String getSupOrderID()
    {
        return supOrderID;
    }

    public void setSupOrderID(String supOrderID)
    {
        this.supOrderID = supOrderID;
    }

    public List<ProductOrderVO> getProList()
    {
        return proList;
    }

    public void setProList(List<ProductOrderVO> proList)
    {
        this.proList = proList;
    }

    public String getSupplyerName()
    {
        return supplyerName;
    }

    public void setSupplyerName(String supplyerName)
    {
        this.supplyerName = supplyerName;
    }

    public String getSupplyerQQ()
    {
        return supplyerQQ;
    }

    public void setSupplyerQQ(String supplyerQQ)
    {
        this.supplyerQQ = supplyerQQ;
    }
}
