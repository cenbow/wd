package com.okwei.pay.bean.vo;

import com.okwei.pay.bean.enums.PayResultStateEnum;

public class PayResult {
    /**
     * 支付订单号
     */
    private String orderNo;
    /**
     * 结果描述
     */
    private String message;
    /**
     * 结果状态
     */
    private PayResultStateEnum state;
    /**
     * 类型
     */
    private int type;

    public int getType() {
	return type;
    }

    public void setType(int type) {
	this.type = type;
    }

    public String getOrderNo() {
	return orderNo;
    }

    public void setOrderNo(String orderNo) {
	this.orderNo = orderNo;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public PayResultStateEnum getState() {
	return state;
    }

    public void setState(PayResultStateEnum state) {
	this.state = state;
    }
}
