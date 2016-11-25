package com.tenpay;

import java.util.ResourceBundle;

/**
 * 财付通 参数配置类
 * @author Administrator
 *
 */
public class TenPaySetting {
	
	/**
	 * 财付通 收款方名称（商户名称）
	 */
	public static String spname=ResourceBundle.getBundle("paySettings")
			.getString("spname");
	
	/**
	 * 财付通 商户号
	 */
	public static String partner=ResourceBundle.getBundle("paySettings")
			.getString("tenPay.partner");
	
	/**
	 * 财付通 商户密钥
	 */
	public static String key=ResourceBundle.getBundle("paySettings")
			.getString("tenPay.key");
	
	/**
	 * 财付通支付成功 同步通知Url
	 */
	public static String return_url=ResourceBundle.getBundle("paySettings")
			.getString("tenPay.return_url");
	
	/**
	 * 财付通支付成功 异步通知Url
	 */
	public static String notify_url=ResourceBundle.getBundle("paySettings")
			.getString("tenPay.notify_url");
	
}
