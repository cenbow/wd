package com.okwei.myportal.bean.vo;

public class SupplyBookOrderStateCountVO 
{
	/**
	 * 待确认
	 */
	private int waitCount;
	/**
	 * 已确认
	 */
	private int suredCount;
	/**
	 * 未发货
	 */
	private int noFaHuoCount;
	/**
	 * 未付尾款
	 */
	private int noPayTailCount;
	/**
	 * 已完成
	 */
	private int completedCount;
	public int getWaitCount() {
		return waitCount;
	}
	public void setWaitCount(int waitCount) {
		this.waitCount = waitCount;
	}
	public int getSuredCount() {
		return suredCount;
	}
	public void setSuredCount(int suredCount) {
		this.suredCount = suredCount;
	}
	public int getNoFaHuoCount() {
		return noFaHuoCount;
	}
	public void setNoFaHuoCount(int noFaHuoCount) {
		this.noFaHuoCount = noFaHuoCount;
	}
	public int getNoPayTailCount() {
		return noPayTailCount;
	}
	public void setNoPayTailCount(int noPayTailCount) {
		this.noPayTailCount = noPayTailCount;
	}
	public int getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(int completedCount) {
		this.completedCount = completedCount;
	}
}
