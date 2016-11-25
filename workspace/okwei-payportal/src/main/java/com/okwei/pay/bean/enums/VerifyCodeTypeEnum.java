package com.okwei.pay.bean.enums;

public enum VerifyCodeTypeEnum {
    /**
     * 注册
     */
    register(1),
    /**
     * 绑定手机号码
     */
    bind(2),
    /**
     * 充值
     */
    recharge(3),
    /**
     * 提现
     */
    deposit(4),
    /**
     * 修改密码
     */
    updatepassword(5),
    /**
     * 钱包支付
     */
    pay(6),
    /**
     * 实名认证
     */
    realnameVerify(7),
    /**
     * 解绑
     */
    unbundl(8),
    /**
     * 其他
     */
    Other(0),
    /**
     * 通知供应商发货
     */
    SupplyDeliver(20),
    /**
     * 订单成交通知成交微店
     */
    SendSellerWeiid(21),
    /**
     * 订单成交通知成交微店上级
     */
    SendSellerUpWeiid(22),
    /**
     * 通知微店主发货
     */
    SendWeiDeliver(23),
    /**
     * 修改支付密码
     */
    updatePayPassword(9);

    private final int step;

    private VerifyCodeTypeEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }

}
