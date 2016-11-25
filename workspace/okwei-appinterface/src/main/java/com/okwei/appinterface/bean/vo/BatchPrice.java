package com.okwei.appinterface.bean.vo;

import java.io.Serializable;

public class BatchPrice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3975515974737259489L;
	private Double price;
	private int count;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
