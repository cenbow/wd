package com.okwei.sso.bean.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SendSMSByMobile  {
	static String sn="SDK-SKY-010-02291";
	static String pwd="593887";
	static SMSClient client =null;
	

	
	public static void init()
	{
		try {
			 client=new SMSClient(sn,pwd);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public static String sendSMS(String mobile,String content)
	{
		init();
		String content2="";
		try {
			 content2=URLEncoder.encode(content,"utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return client.mdsmssend(mobile, content2, "", "", "", "");
	}
}
