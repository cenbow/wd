package com.okwei.appinterface.bean.vo.wallet;

import java.util.List;

import com.okwei.appinterface.bean.vo.order.BasePageModle;

public class TradingRebatesDetailsVO extends BasePageModle {

	/**
	 * 返现Id
	 */
	private String rebateId; 
	/**
	 * 月采购额
	 */
	private double monthAmount; 
	/**
	 * // 9",//月
	 */
	private String month;	
	/**
	 *	"9月",//月名 
	 */
	private String monthName;
	/**
	 * 返现金额
	 */
	private double amount; 
	/**
	 * 年 （如 2015）
	 */
	public String year; 
	/**
	 * 年名称 （如 2015年）
	 */
	public String yearName;
	
	public String image;
	/**
	 * 月订单列表
	 */
	public List<MyTradingRebatesVO> orderList; 
	
	public String getRebateId() {
		return rebateId;
	}
	public void setRebateId(String rebateId) {
		this.rebateId = rebateId;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 月采购额
	 */
	public double getMonthAmount() {
		return monthAmount;
	}
	public void setMonthAmount(double monthAmount) {
		this.monthAmount = monthAmount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getYearName() {
		return yearName;
	}
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	public List<MyTradingRebatesVO> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<MyTradingRebatesVO> orderList) {
		this.orderList = orderList;
	}
	
}
