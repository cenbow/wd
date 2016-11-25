package com.okwei.bean.enums;

/**
 * @author 
 *  *	微金币 状态枚举
 */
public enum WeiDianCoinTypeEnum {
	
	//
	/**
	 * 收入
	 */
	shouru(1),
	/**
	 * 支出
	 */
	zhichu(2);
	
	private final int step;

	private WeiDianCoinTypeEnum(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}
