package com.okwei.bean.vo.activity;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.vo.RedWalletInfoVO;






public class RedPacMoneyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer pid;
	private Double totalAmount;//总金额
	private Double amount;//金额
	private String actTime;//红包发放活动日期
	private Double outAmount;//累计发出金额
	private Integer count;//数量
	private List<RedWalletInfoVO> pmList;
	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
	 * @return the actTime
	 */
	public String getActTime() {
		return actTime;
	}
	/**
	 * @param actTime the actTime to set
	 */
	public void setActTime(String actTime) {
		this.actTime = actTime;
	}
	/**
	 * @return the outAmount
	 */
	public Double getOutAmount() {
		return outAmount;
	}
	/**
	 * @param outAmount the outAmount to set
	 */
	public void setOutAmount(Double outAmount) {
		this.outAmount = outAmount;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the pmList
	 */
	public List<RedWalletInfoVO> getPmList() {
		return pmList;
	}
	/**
	 * @param pmList the pmList to set
	 */
	public void setPmList(List<RedWalletInfoVO> pmList) {
		this.pmList = pmList;
	}
	
}
