package com.okwei.myportal.bean.vo;

import java.util.Date;
import java.util.List;

public class OrderDetailsVO
{
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 订单总价
     */
    private Double totalPrice;
    /**
     * 订单类型（1零售，2预定，3批发）
     */
    private int orderType;
    /**
     * 订单状态
     */
    private int orderState;
    /**
     * 收货人
     */
    private String receivingName;
    /**
     * 联系电话
     */
    private String receivingPhone;
    /**
     * 收货详细地址
     */
    private String receivingAddress;
    /**
     * 留言
     */
    private String message;
    /**
     * 优惠（促销）
     */
    private String preferential;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 供应商微店号
     */
    private long supplierId;
    
    private Long sellerId;
    private Long buyerId;
    /**
     * 供应商QQ
     */
    private String supplierQQ;
    /**
     * 供应商电话
     */
    private String supplierPhone;
    /**
     * 运费
     */
    private double freight;
    /**
     * 支付方式
     */
    private String payWay;
    /**
     * 产品列表
     */
    private List<ProductOrderVO> proList;
    /**
     * 订单记录
     */
    private List<String> recordList;
    /**
     * 实付款
     */
    private double payment;
    
    /**
     * 还剩余的最大退款金额
     */
    private double maxPrice;
    
    /* 预订单 */
    /**
     * 支付订单号（预订单使用）
     */
    private String payOrderNo;
    
    /**
     * 供应商留言
     */
    private String supplierMessage;
    /**
     * 是否全款支付
     */
    private boolean isfullPrice;
    
    /**
     * 付款类型
     */
    private int bookPayType;
    /**
     * 首款
     */
    private double depositprice;
    /**
     * 首款百分比
     */
    private int percentage;
    /**
     * 尾款
     */
    private double finalprice;
    /**
     * 0发货前、1发货后 付尾款
     */
    private int tailPayType;
    /**
     * 物流信息
     */
    private LogisticsVO logistics;

    /* 各种时间 */
    /**
     * 下单时间(提交预定时间)
     */
    private String placeOrderTimeStr;
    /**
     * 付款时间(预定的支付款项时间)
     */
    private String paymentTimeStr;
    /**
     * 发货时间
     */
    private String deliveryTimeStr;
    /**
     * 收货时间
     */
    private String goodsTimeStr;
    /**
     * 成交时间
     */
    private String dealTime;
    /**
     * 取消时间
     */
    private String cancelTimeStr;
    /**
     * 订单过期时间
     */
    private Date expirationTime;
    /**
     * 供应商确认订单时间
     */
    private String makeOrderTimeStr;
    
    /**
     * 退款ID(只有退款的时候用到)
     */
    private long refundId;
    /**
     * 使用现金劵金额
     */
    private Double cashCoupon; 
    /**
     * 是否使用了现金劵金额
     */
    private boolean cashCouponflag=false;
    /**
     * 使用多少微金币
     */
    
    private Double weiCoin;
    
    

	public Double getWeiCoin() {
		if(weiCoin == null)
			return 0.0D;
		return weiCoin;
	}

	public void setWeiCoin(Double weiCoin) {
		this.weiCoin = weiCoin;
	}

	public boolean isCashCouponflag() {
		return cashCouponflag;
	}

	public void setCashCouponflag(boolean cashCouponflag) {
		this.cashCouponflag = cashCouponflag;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getCashCoupon() {
		return cashCoupon;
	}

	public void setCashCoupon(Double cashCoupon) {
		this.cashCoupon = cashCoupon;
	}

	public String getPlaceOrderTimeStr()
    {
        return placeOrderTimeStr;
    }

    public void setPlaceOrderTimeStr(String placeOrderTimeStr)
    {
        this.placeOrderTimeStr = placeOrderTimeStr;
    }

    public String getPaymentTimeStr()
    {
        return paymentTimeStr;
    }

    public void setPaymentTimeStr(String paymentTimeStr)
    {
        this.paymentTimeStr = paymentTimeStr;
    }

    public String getGoodsTimeStr()
    {
        return goodsTimeStr;
    }

    public void setGoodsTimeStr(String goodsTimeStr)
    {
        this.goodsTimeStr = goodsTimeStr;
    }

    public String getCancelTimeStr()
    {
        return cancelTimeStr;
    }

    public void setCancelTimeStr(String cancelTimeStr)
    {
        this.cancelTimeStr = cancelTimeStr;
    }

    public String getMakeOrderTimeStr()
    {
        return makeOrderTimeStr;
    }

    public void setMakeOrderTimeStr(String makeOrderTimeStr)
    {
        this.makeOrderTimeStr = makeOrderTimeStr;
    }

    public LogisticsVO getLogistics()
    {
        return logistics;
    }

    public void setLogistics(LogisticsVO logistics)
    {
        this.logistics = logistics;
    }

    public String getPayOrderNo()
    {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo)
    {
        this.payOrderNo = payOrderNo;
    }

    public boolean getIsfullPrice()
    {
        return isfullPrice;
    }

    public void setIsfullPrice(boolean isfullPrice)
    {
        this.isfullPrice = isfullPrice;
    }

    public double getPayment()
    {
        return payment;
    }

    public void setPayment(double payment)
    {
        this.payment = payment;
    }

    public String getPayWay()
    {
        return payWay;
    }

    public void setPayWay(String payWay)
    {
        this.payWay = payWay;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

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

    public List<ProductOrderVO> getProList()
    {
        return proList;
    }

    public void setProList(List<ProductOrderVO> proList)
    {
        this.proList = proList;
    }

    public Date getExpirationTime()
    {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime)
    {
        this.expirationTime = expirationTime;
    }

    public String getSupplierMessage()
    {
        return supplierMessage;
    }

    public void setSupplierMessage(String supplierMessage)
    {
        this.supplierMessage = supplierMessage;
    }

    public int getPercentage()
    {
        return percentage;
    }

    public void setPercentage(int percentage)
    {
        this.percentage = percentage;
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

    public int getTailPayType()
    {
        return tailPayType;
    }

    public void setTailPayType(int tailPayType)
    {
        this.tailPayType = tailPayType;
    }

    public String getDeliveryTimeStr()
    {
        return deliveryTimeStr;
    }

    public void setDeliveryTimeStr(String deliveryTimeStr)
    {
        this.deliveryTimeStr = deliveryTimeStr;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public int getOrderType()
    {
        return orderType;
    }

    public void setOrderType(int orderType)
    {
        this.orderType = orderType;
    }

    public int getOrderState()
    {
        return orderState;
    }

    public void setOrderState(int orderState)
    {
        this.orderState = orderState;
    }

    public String getReceivingName()
    {
        return receivingName;
    }

    public void setReceivingName(String receivingName)
    {
        this.receivingName = receivingName;
    }

    public String getReceivingPhone()
    {
        return receivingPhone;
    }

    public void setReceivingPhone(String receivingPhone)
    {
        this.receivingPhone = receivingPhone;
    }

    public String getReceivingAddress()
    {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress)
    {
        this.receivingAddress = receivingAddress;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getPreferential()
    {
        return preferential;
    }

    public void setPreferential(String preferential)
    {
        this.preferential = preferential;
    }

    public String getDealTime()
    {
        return dealTime;
    }

    public void setDealTime(String dealTime)
    {
        this.dealTime = dealTime;
    }

    public List<String> getRecordList()
    {
        return recordList;
    }

    public void setRecordList(List<String> recordList)
    {
        this.recordList = recordList;
    }

    public int getBookPayType() {
	return bookPayType;
    }

    public void setBookPayType(int bookPayType) {
	this.bookPayType = bookPayType;
    }

    public long getRefundId() {
	return refundId;
    }

    public void setRefundId(long refundId) {
	this.refundId = refundId;
    }

    public double getMaxPrice() {
	return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
	this.maxPrice = maxPrice;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
    
}
