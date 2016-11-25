package com.okwei.bean.enums;

public enum PayTypeEnum {


	/**
	 * 其他支付
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
	 * 微信支付
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
	 /// <summary>
    /// 微信App云商微店支付
    /// </summary>
	 WxAppPay(9),
    /// <summary>
    /// 百付宝支付
    /// </summary>
    BFBPay(10),
    /// <summary>
    /// 微信APP微店网支付
    /// </summary>
    WxAppWDPay(11),
    /// <summary>
    /// 微金币支付
    /// </summary>
    WeiCoinPay(12),
  /// <summary>
    /// 微钱包和微钱包混合支付
    /// </summary>
    WalletCoinPay(13),
    /// <summary>
    /// 测试支付
    /// </summary>
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
