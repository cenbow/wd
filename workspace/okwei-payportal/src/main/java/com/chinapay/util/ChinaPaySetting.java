package com.chinapay.util;

import java.util.ResourceBundle;

public class ChinaPaySetting {

	/**
	 *银联支付 商户号
	 */
	public static String MerId=ResourceBundle.getBundle("paySettings").getString("chinapay.MerId");
	/**
	 *银联支付 版本号
	 */
	public static String Version=ResourceBundle.getBundle("paySettings").getString("chinapay.Version");
	
	/**
	 *银联支付 货币代码
	 */
	public static String CuryId=ResourceBundle.getBundle("paySettings").getString("chinapay.CuryId");
	
	/**
	 *银联支付 后台通知URL
	 */
	public static String BgRetUrl=ResourceBundle.getBundle("paySettings").getString("chinapay.BgRetUrl");
		
	/**
	 *银联支付 前台通知URL
	 */
	public static String PageRetUrl=ResourceBundle.getBundle("paySettings").getString("chinapay.PageRetUrl");
	
	/**
	 *银联支付 网关号
	 */
	public static String GateId=ResourceBundle.getBundle("paySettings").getString("chinapay.GateId");
	
	/**
	 *银联支付 交易类型
	 */
	public static String TransType=ResourceBundle.getBundle("paySettings").getString("chinapay.TransType");
	
	
	/**
	 *银联支付 私钥文件路径
	 */
	public static String merkeyfilepath=ResourceBundle.getBundle("paySettings").getString("chinapay.merkeyfilepath");
	
	/**
	 *银联支付 公钥文件路径
	 */
	public static String pubkeyfilepath=ResourceBundle.getBundle("paySettings").getString("chinapay.pubkeyfilepath");

	/**
	 *银联支付 调起路径
	 */
	public static String paymenturl=ResourceBundle.getBundle("paySettings").getString("chinapay.paymenturl");
	
	/**
	 *银联支付 退款路径
	 */
	public static String refundurl=ResourceBundle.getBundle("paySettings").getString("chinapay.refundurl");
	
	/**
	 *银联支付 订单查询路径
	 */
	public static String queryurl=ResourceBundle.getBundle("paySettings").getString("chinapay.queryurl");
	

}
