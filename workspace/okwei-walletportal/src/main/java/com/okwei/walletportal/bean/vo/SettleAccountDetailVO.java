package com.okwei.walletportal.bean.vo;

import java.io.Serializable;
import java.util.Date;

import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UWeiSeller;

public class SettleAccountDetailVO implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private UTradingDetails tradingDetails;
	private String shop;   //进驻商家
	private Double totalPrice;   //总金额
	private String comment;   //备注
	private Date createTime;   //交易时间
	private String orderNo;  //订单号
	private Double commision;   //佣金
	

	public UTradingDetails getTradingDetails() {
		return tradingDetails;
	}

	public void setTradingDetails(UTradingDetails tradingDetails) {
		this.tradingDetails = tradingDetails;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	 
}
