package com.okwei.pay.bean.enums;

public enum PayTypeEnum {

    /**
     * 其他支付（如线下，等）
     */
    OtherPay(1),
    /**
     * 智付支付
     */
    DinPay(2),
    /**
     * 财付通
     */
    TenPay(3),
    /**
     * 银联
     */
    ChinaPay(4),
    /**
     * . 微信支付
     */
    WxPay(5),
    /**
     * 新浪支付
     */
    SinaPay(6),
    /**
     * 连连支付
     */
    LLPay(7),
    /**
     * 微店钱包
     */
    WeiWallet(8),
    /**
     * 微信App云商微店支付
     */
    WxAppPay(9),
    /**
     * 百付宝支付
     */
    BFBPay(10),
    /**
     * 微信APP微店网支付
     */
    WxAppWDPay(11),
    /**
     * 测试支付
     */
    TestPay(999);
    private final int step;

    private PayTypeEnum(int step) {

	this.step = step;
    }

    @Override
    public String toString() {
	return String.valueOf(this.step);
    }
}
