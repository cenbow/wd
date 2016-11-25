package com.okwei.pay.bean.enums;

public enum FollowTypeEnum {
    /**
     * 取消了
     */
    Cancel(-1),
    /**
     * 未填写
     */
    NoWrite(0),
    /**
     * 等待确认
     */
    WaitSure(1),
    /**
     * 打回
     */
    Fail(2),
    /**
     * 确认
     */
    Pass(3),
    /**
     * 进驻
     */
    PayIn(4);
    private final int step;

    private FollowTypeEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
