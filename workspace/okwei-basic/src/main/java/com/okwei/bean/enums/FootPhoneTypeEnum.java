package com.okwei.bean.enums;

public enum FootPhoneTypeEnum {
	/**
	 * 招商 
	 */
	business(0),
	/**
	 * 运营
	 */
	operation(1),
	/**
	 * 客服
	 */
	kefu(2),
	/**
	 * 总部电话
	 */
	base(3),
	/**
	 * 传真
	 */
	fax(4);
	private final int Type;

	private FootPhoneTypeEnum(int step) {
		this.Type = step;
	}

	public String toString() {
		return String.valueOf(this.Type);
	}

}
