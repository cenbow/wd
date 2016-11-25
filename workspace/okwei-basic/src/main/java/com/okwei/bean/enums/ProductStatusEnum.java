package com.okwei.bean.enums;

public enum ProductStatusEnum {
	/**
	 * 提交中
	 */
	Submitting(-1),
	/**
	 * 展示中
	 */
	Showing(1),
	/**
	 * 不通过
	 */
	Fail(2),
	/**
	 * 草稿
	 */
	OutLine(3),
	/**
	 * 已下架
	 */
	Drop(4),
	/**
	 * 已删除
	 */
	Deleted(5),

	/**
	 * 子帐号提交审核中
	 */
	Audit(6);

	private final int step;

	private ProductStatusEnum(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}

	// 根据value获取enum
	public static ProductStatusEnum setValue(int value) {
		ProductStatusEnum obj = null;
		for (ProductStatusEnum status : ProductStatusEnum.values()) {
			if (status.toString().equals(String.valueOf(value))) {
				obj = status;
				break;
			}
		}
		return obj;
	}
}
