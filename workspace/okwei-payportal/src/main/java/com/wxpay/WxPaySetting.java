package com.wxpay;

import java.util.ResourceBundle;

public class WxPaySetting {
	/**
	 * 微信支付  appid
	 */
	public static String appid=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.appid");
	
	
	/**
	 * 微信支付  appid
	 */
	public static String key=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.key");
	
	/***
	 * 微信支付  商户号
	 */
	public static String partner=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.partner");
	
	/**
	 * 微信支付 后台通知Url
	 */
	public static String notify_url=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.notify_url");
	
	/**
	 * 统一下单 api url
	 */
	public static String placeOrder_url =ResourceBundle.getBundle("paySettings")
			.getString("wxpay.placeOrder_url");
	
	/**
	 * 微信订单查询 api url
	 */
	public static String queryorder_url =ResourceBundle.getBundle("paySettings")
			.getString("wxpay.queryorder_url");
	
	/**
	 * 微信公众号 商户号
	 */
	public static String publicpartner=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.publicpartner");
	
	/**
	 * 微信公众号 商户密钥
	 */
	public static String publickey=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.publickey");
	
	/**
	 * 微信公众号 appid
	 */
	public static String publicappid=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.publicappid");
	
	/**
	 * 微信公众号 app密钥  //paysignkey(非appkey) 
	 */
	public static String publicappkey=ResourceBundle.getBundle("paySettings")
			.getString("wxpay.publicappkey");
}


