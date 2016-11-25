package com.okwei.util;

import com.okwei.bean.enums.VerifyCodeType;



public class VerifyCode {
	/**
	 * 插入各个业务的验证码
	 * @param mobile
	 * @param vtype
	 * @return
	 */
	public static String insertVerifyCode(String mobile,VerifyCodeType vtype)
	{
		String verifyCode = String.valueOf(Math.random()).substring(2, 8);
		String tiket=mobile;
		if(vtype != null)
		{
			tiket+="-"+vtype.toString();
		}
		RedisUtil.setObject(tiket, verifyCode, 300);// 五分钟内有效
		String content = "微店网验证码：" + verifyCode + "。请勿将验证码告知他人并确认是本人操作[微店网]";
		String lr = SendSMSByMobile.sendSMS(mobile, content);
		long sendResult = ParseHelper.toLong(lr);
		if (sendResult > 0) {
			return verifyCode;
		} else {
			return "-1";
		}
	}
	/**
	 * 匹配各个业务的验证码，匹配正确后删除
	 * @param mobile
	 * @param vtype
	 * @param verifyCode
	 * @return
	 */
	public static boolean checkVerifyCode(String mobile,VerifyCodeType vtype,String verifyCode)
	{
		String tiket=mobile;
		if(vtype != null)
		{
			tiket+="-"+vtype.toString();
		}
		String comparevf = (String) RedisUtil.getObject(tiket);
		if (comparevf==null || !comparevf.equals(verifyCode)) {
			return false;
		}
		else		
			RedisUtil.delete(tiket);
		return true;
	}
	
	/**
	 * 发送短信
	 * @param mobile
	 * @param vtype  短信的类型（ 枚举VerifyCodeType）
	 * @return
	 */
	public static Boolean insertVCode(String mobile,VerifyCodeType vtype)
	{
		String verifyCode = String.valueOf(Math.random()).substring(2,8);
		String tiket=mobile;
		if(vtype != null)
		{
			tiket+="-"+vtype.toString();
		}
		RedisUtil.setObject(tiket, verifyCode, 300);// 五分钟内有效
		String content = "微店网验证码：" + verifyCode + "。请勿将验证码告知他人并确认是本人操作[微店网]";
		String lr = SendSMSByMobile.sendSMS(mobile, content);
		long sendResult = ParseHelper.toLong(lr);
		if (sendResult > 0) {
			return true;
		} else {
			return false;
		}
	}

}
