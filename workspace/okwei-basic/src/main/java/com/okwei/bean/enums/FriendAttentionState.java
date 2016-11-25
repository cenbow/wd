package com.okwei.bean.enums;

public enum FriendAttentionState {
	
	/**
	 * 关注
	 */
	Attention(1),
	/**
	 * 不关注
	 */
	NoAttention(0);
		
	private final int Type;

	private FriendAttentionState(int step) {
		this.Type = step;
	}

	public String toString() {
		return String.valueOf(this.Type);
	}
}
