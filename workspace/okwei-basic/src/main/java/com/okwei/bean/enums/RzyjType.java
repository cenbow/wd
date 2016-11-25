package com.okwei.bean.enums;

public enum RzyjType {
	/**
	 * 以前未区分的佣金
	 */
	NoLvTwo(0),
	/**
	 *  来源佣金
	 */
	SourceAmount(1),
	/**
	 * 跟进佣金
	 */
	FollowAmout(2),
	/**
	 * 扩展分销商佣金
	 */
	ExtendedAmout(3),
	/**
	 * 促进成交佣金
	 */
	PromoteAmout(4),
	/**
	 * 批发号供应商升级认证点
	 */
	BatchUpRenZheng(5),
	/**
	 * 批发号抽成
	 */
	BookCut(6),
	/**
	 * 平台抽点
	 */
	choudian(7),
	/**
	 * 悬赏
	 */
	Reward(8)
	;

	private final int step;

	private RzyjType(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}

}
