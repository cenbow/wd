package com.okwei.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.okwei.bean.domain.TSmsverification;
import com.okwei.bean.enums.VerifyCodeType;

public class SendSMSByMobile  {
	
	static String uid=AppSettingUtil.getSingleValue("smsuid");
	static String pwd=AppSettingUtil.getSingleValue("smspwd");
	static String url="http://c.kf10000.com/sdk/SMS";
	

	//短信改造
	/*
	 * cmd	命令字	send	发送短信
		uid	帐号	非空	
		psw  	密码	非空	密码需要MD5 32位加密,不区分大小写；
		subid	扩展号（6位以内）		
		mobiles	手机号码		多个号码用逗号隔开最多500个号码,建议100个提交一次;
		msgid	消息编号	整型	客户端生成，唯一性；每提交一次msgid要不同；
		msg	消息内容		GBK 编码格式，需要urlencoder； 
	 */
	public static String sendSMS(String mobile,String content)
	{		
		
		Map<String,String> map= new HashMap<String,String>();
		map.put("cmd", "send"); 
		map.put("uid", uid);
		try {
			map.put("psw", getMD5(pwd));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("mobiles", mobile);
		map.put("msgid", String.valueOf(Math.random()).substring(2,6));
		try {
			map.put("msg", URLEncoder.encode(content.replace("[", "【").replace("]", "】"), "GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String returnMsg=postHttpRequest(map);
		if("100".equals(returnMsg))
			return "1";
		else
			return returnMsg;
	}
	/*
	 * 方法名称：getMD5 
	 * 功    能：字符串MD5加密 
	 * 参    数：待转换字符串 
	 * 返 回 值：加密之后字符串
	 */
	public static String getMD5(String sourceStr) throws UnsupportedEncodingException {
		String resultStr = "";
		try {
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			// resultStr = new String(md5.digest());
			byte[] b = md5.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static  String postHttpRequest(Map<String, String> keyValueMap) {
    	
        HttpClient client = new HttpClient();
        StringBuffer sb = new StringBuffer(url);
        PostMethod postMethod = null;
        try {
            //设置请求参数
            if (keyValueMap != null) {
                Iterator it = keyValueMap.entrySet().iterator();
                if (keyValueMap.size() > 0) {
                    sb.append("?");
                    while (it.hasNext()) {
                        Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                        sb.append(entry.getKey() + "=" + entry.getValue() + "&");
                    }
                    sb.deleteCharAt(sb.length()-1);
                }

            }
            postMethod = new PostMethod(sb.toString());
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            //todo:设置超时时间
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 200000);
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return "-1";
            }
            String responseBody = postMethod.getResponseBodyAsString();
            return responseBody;
        } catch (Exception e) {
        	return e.getMessage();
        } finally {
            if(postMethod!=null){
               postMethod.releaseConnection();
            }
        }
    }
	
	

	

	
	
	/**
	 * 发送验证码
	 * 
	 * @param phone
	 *            手机号码
	 * @param verifier
	 *            发送的类型
	 * @return
	 */
	public boolean sendSMS(String[] param, VerifyCodeType verifier) {
		return sendSMS(param, verifier, 0L);
	}

	/**
	 * 短信发送
	 * @param param
	 * @param verifier
	 * @param weiid
	 * @return
	 */
	public boolean sendSMS(String[] param, VerifyCodeType verifier, long weiid) {
		String currentDomain= AppSettingUtil.getSingleValue("currentDomain");
		if(!(currentDomain!=null&&currentDomain.indexOf(".com")>0)){
			return true;
		}
		String mobile = param[0];// 参数一：手机号码
		String content = "";
		TSmsverification ts = new TSmsverification();
		ts.setWeiId(weiid);
		ts.setCreateTime(new Date());
		ts.setStatus((short) 0);
		ts.setType(Short.parseShort(verifier.toString()));
		ts.setPhone(mobile);
		if (verifier == VerifyCodeType.pay) {// 钱包支付
			String code = param[1];// 参数二：验证码
			content = "您的验证码为：" + code + "[微店网]";
		} else if (verifier == VerifyCodeType.SupplyDeliver) {// 通知供应商发货
			String amount = param[1];// 参数二：价格
			content = "您刚有一个消费者成功购买了产品，总价为：" + amount + "元，请及时登录后台及时发货，以免影响消费者购买体验。下载微店app，手机也能发货了 http://app.okwei.com [微店网]";
		} else if (verifier == VerifyCodeType.SendSellerWeiid) {// 订单成交通知成交微店
			String seller = param[1];// 参数二：成交微店
			String sellcomminss = param[2];// 参数三：成交佣金
			content = "您的微店" + seller + "成交了一个订单，您获得了" + sellcomminss + "元佣金。加强推广，佣金不断。加油！下载微店app，推广更方便 http://app.okwei.com [微店网]";
		} else if (verifier == VerifyCodeType.SendSellerUpWeiid) {// 订单成交通知成交微店上级
			String seller = param[1];// 参数二：成交微店
			String sellcomminss = param[2];// 参数三：成交佣金
			content = "您的分销商（微店号：" + seller + "）成交一个订单，Ta为您赚得佣金" + sellcomminss + "元（占总佣金的30%）。加强推广，分销商越多，佣金越多！下载微店app，推广更方便 http://app.okwei.com [微店网]";
		} else if (verifier == VerifyCodeType.SendWeiDeliver) {// 通知微店主等待发货
			String payOrderNo = param[1];// 参数一：订单号
			content = "亲爱的微粉，您的订单" + payOrderNo + "已经付款成功，供应商马上给您发货！下载微店app。订单信息早知道 http://app.okwei.com，感谢购买,祝您生活愉快！[微店网]";
		}
		// 手机和内容不能为空
		if (ObjectUtil.isEmpty(mobile) || ObjectUtil.isEmpty(content)) {
			return false;
		}
		ts.setContent(content);
		// payOrderService.insertTSmsverification(ts);// 添加到数据库
		String smsId = (ts.getId() == null ? "520123456" : ts.getId().toString());

		
		String result =sendSMS(mobile,content);// 第一次
		if (!"1".equals(result)) {
			result = sendSMS(mobile,content);// 第二次
			if (!"1".equals(result)) {
				result = sendSMS(mobile,content);// 第三次
				if (!"1".equals(result)) {
					return false;
				}
			}
		}
		return true;
	}

}
