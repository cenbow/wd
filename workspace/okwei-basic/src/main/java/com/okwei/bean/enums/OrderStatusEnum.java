package com.okwei.bean.enums;

public enum OrderStatusEnum {
	// /0未付款 1.已付款 2.已发货 3 已收货 4 已完成 5 退款中 6 已退款 7 已取消 （往后就是预订单） 8 等待确定 9 申请取消
	// 10 已确定 11 已拒绝 12 已支付定金 13 已过期
	/**
	 * 未付款
	 */
	Nopay(0),
	/**
	 * 已付款
	 */
	Payed(1),
	/**
	 * 已发货
	 */
	Deliveried(2),
	/**
	 * 已收货
	 */
	Accepted(3),
	/**
	 * 已完成
	 */
	Completed(4),
	/**
	 * 退款中
	 */
	Refunding(5),
	/**
	 * 已退款
	 */
	Refunded(6),
	/**
	 * 已取消
	 */
	Canceled(7),
	/**
	 * 等待确定
	 */
	WaitSure(8),
	/**
	 * 申请取消
	 */
	ApplyCancel(9),
	/**
	 * 已确定
	 */
	Sured(10),
	/**
	 * 已拒绝
	 */
	Rejected(11),
	/**
	 * 已支付定金
	 */
	PayedDeposit(12),
	/**
	 * 已过期
	 */
	Tovoided(13),
	/**
	 * 已删除
	 */
	Delete(14);
	private final int step;

	private OrderStatusEnum(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}
