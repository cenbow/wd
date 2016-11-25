package com.okwei.bean.enums;

public enum ScoreKingType {
	/**
	 * 当天
	 */
	Today(1),
	/**
	 * 七天
	 */
	Seven(2),
	/**
	 * 三十天
	 */
	Thirty(3);

	private final int step;

	private ScoreKingType(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}
