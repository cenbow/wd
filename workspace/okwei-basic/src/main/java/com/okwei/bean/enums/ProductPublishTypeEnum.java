package com.okwei.bean.enums;

public enum ProductPublishTypeEnum {


	/**
	 * 以前分销体系的商品
	 */
	Normal(0),
	/**
	 * 代理区商品
	 */
	Agent(1);

	private final int step;

	private ProductPublishTypeEnum(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
	
	public Short toShort() {
		return Short.parseShort(String.valueOf(this.step));
	}
}
