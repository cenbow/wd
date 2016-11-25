package com.okwei.bean.enums;

public enum MobileBindEnum {

	/**
	 * 解绑
	 */
	Nobind(1),
	/**
	 * 绑定
	 */
	Bind(2);

	private final int step;

	private MobileBindEnum(int step) {
		this.step = step;
	}

	public String toString() {

		return String.valueOf(this.step);
	}
}
