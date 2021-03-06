package com.bfb.util;

import java.util.ResourceBundle;

public class BfbPaySetting {

	/**
	 *百付宝  商户号
	 */
	public static String sp_no=ResourceBundle.getBundle("paySettings").getString("bfbpay.sp_no");
	
	/**
	 * 百付宝 key
	 */
	public static String SP_KEY=ResourceBundle.getBundle("paySettings").getString("bfbpay.SP_KEY");
		
	/****
	 * 百付宝 不登陆支付 地址
	 */
	public static String DIRECTURL= ResourceBundle.getBundle("paySettings").getString("bfbpay.DIRECTURL");
	
	/**
	 * 百度订单查询接口地址
	 */
	public static String QUERYORDERURL= ResourceBundle.getBundle("paySettings").getString("bfbpay.QUERYORDERURL");
	
	/**
	 * 异步通知 地址
	 */
	public static String return_url= ResourceBundle.getBundle("paySettings").getString("bfbpay.return_url");
	
	/***
	 * 同步通知地址
	 */
	public static String page_url= ResourceBundle.getBundle("paySettings").getString("bfbpay.page_url");
}
