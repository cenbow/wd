package com.okwei.bean.enums;

/// <summary>
/// 上架产品的状态
/// </summary>
public enum ProductShelveStatu {
	// / <summary>
	// / 上架中
	// / </summary>
	OnShelf(1),
	// / <summary>
	// / 已下架
	// / </summary>
	OffShelf(0),

	// 删除状态
	Deleted(-1);
	private final int step;

	private ProductShelveStatu(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
	public Short toShort(){
		return Short.parseShort(String.valueOf(this.step));
	}
}
