package com.okwei.appinterface.bean.vo;

/**
 * @author 李耀东
 *	USE： 品牌号平台号首页渠道分布前三 & 渠道分布列表页
 */
public class Trench {
	/**
	 * 省级名称
	 */
	private String province; 
	/**
	 * 代理商数量
	 */
	private int totalAgent; 
	/**
	 * 落地店数量
	 */
	private int totalStore;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public int getTotalAgent() {
		return totalAgent;
	}
	public void setTotalAgent(int totalAgent) {
		this.totalAgent = totalAgent;
	}
	public int getTotalStore() {
		return totalStore;
	}
	public void setTotalStore(int totalStore) {
		this.totalStore = totalStore;
	}
	
	
}
