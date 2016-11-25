package com.okwei.bean.vo;

import java.io.Serializable;

public class RedWalletInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7349805171565435812L;
	
	private Integer id;
	private Integer num;
	private Double amount;
	private Integer rtypeId;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the rtypeId
	 */
	public Integer getRtypeId() {
		return rtypeId;
	}
	/**
	 * @param rtypeId the rtypeId to set
	 */
	public void setRtypeId(Integer rtypeId) {
		this.rtypeId = rtypeId;
	}
	
}
