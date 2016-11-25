package com.okwei.bean.enums;

/**
 * 子供应商产品状态
 * @author yangjunjun
 *
 */
public enum ProductSubStatusEnum {
	/**
	 * 草稿
	 */
	OutLine(0),
	/**
	 * 待审核
	 */
	Audit(1),
	/**
	 * 不通过
	 */
	Fail(2),
	/**
	 * 审核通过
	 */
	Pass(3),
	/**
	 * 已删除
	 */
	Deleted(4);


	private final int step;

	private ProductSubStatusEnum(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}

	// 根据value获取enum
	public static ProductSubStatusEnum setValue(int value) {
		ProductSubStatusEnum obj = null;
		for (ProductSubStatusEnum status : ProductSubStatusEnum.values()) {
			if (status.toString().equals(String.valueOf(value))) {
				obj = status;
				break;
			}
		}
		return obj;
	}
}
