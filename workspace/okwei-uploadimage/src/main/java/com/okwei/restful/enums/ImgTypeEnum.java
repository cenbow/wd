package com.okwei.restful.enums;

public enum ImgTypeEnum {

	/**
	 * 什么都不做
	 */
	Nothing(0),
	/**
	 * 产品
	 */
	Product(1), 
	/**
	 *  用户头像
	 */
	Photo(2),
	/**
	 *  上传的证件
	 */
	Certify(3);

	private final int step;

	private ImgTypeEnum(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}
