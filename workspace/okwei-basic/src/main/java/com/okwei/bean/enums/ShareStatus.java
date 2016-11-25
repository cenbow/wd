package com.okwei.bean.enums;

/**
 * @author fh 分享表S_MainData  Status字段的枚举
 *
 */
public enum ShareStatus {
	
	/**
	 * 已删除
	 */
	delete(-1),
	/**
	 * 待审核
	 */
	Pending(0),
	
	/**
	 * 通过
	 */
	Pass (1),
	
	/**
	 * 未通过
	 */
	NotPass (2);
	
	private final int step;

	private ShareStatus(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}