package com.okwei.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.okwei.bean.domain.UPushMessage;
import com.okwei.bean.enums.PushMsgEnum;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ObjectUtil;

public class SendPushMessage {
	private Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * 消息推送
	 * @param receptWeiid 消息接收的人
	 * @param type  枚举 PushMsgEnum
	 * @param content 消息内容
	 * @param remarck 标记（订单消息，需传orderNo）
	 * @param json （需要推送的json参数）
	 * @return
	 */
	public boolean insertPushMsg(long receptWeiid, Short type, String content,String remarck,String json) {
		try {
			UPushMessage msg = new UPushMessage();
			msg.setReciptWeiId(receptWeiid);
			msg.setCreateTime(new Date());
			msg.setMsgType(type);
			if(!ObjectUtil.isEmpty(remarck)){
				msg.setObjectId(remarck);
			}
			msg.setPushWeiId((long) 1);
			msg.setExtra(json);
			msg.setPushContent(content);
			SendMessage(msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	
	/**
	 * type(
	 * 10聊天离线消息，11团队消息
	 * 20系统公告，21活动推送
	 * 31购买订单，32销售订单，33物流通知，
	 * 40供应商申请，41供应商代申请,42认证点申请
	 * 53采购需求    )
	 * @param msg
	 * @return
	 */
	public boolean SendMessage(UPushMessage msg) {
		// 加载规则

		// 调用接口发送
		if (msg.getMsgType() == null || msg.getReciptWeiId() == null || msg.getPushContent() == null || "".equals(msg.getPushContent())) {
			return false;
		}
		ResourceBundle bundle = ResourceBundle.getBundle("url");
		if (bundle == null) {
			throw new IllegalArgumentException("[url.properties] is not found!");
		}
		String type = "微店网";
		if (msg.getTitle() == null || "".equals(msg.getTitle())) {
			switch (msg.getMsgType().toString()) {
			case "10":
				type = "聊天离线消息";
				break;
			case "11":
				type = "团队消息";
				break;
			case "20":
				type = "系统公告";
				break;
			case "21":
				type = "活动推送";
				break;
			case "30":
				type = "微店钱包";
				break;
			case "31":
				type = "购买订单";
				break;
			case "32":
				type = "销售订单";
				break;
			case "33":
				type = "物流通知";
				break;
			case "40":
				type = "供应商申请";
				break;
			case "41":
				type = "供应商代申请";
				break;
			case "42":
				type = "认证点申请";
				break;
			case "51":
				type="互动消息";
				break;
			case "53":
				type="采购需求";
				break;
			case "12":
				type="请求加好友";
				break;
			case "13":
				type="好友消息";
				break;
					

			}
		} else {
			type = msg.getTitle();
		}
		// String
		// message="{\"title\":\""+type+"\",\"content\":\""+msg.getPushContent()+"\",\"type\":"+msg.getMsgType().toString()+",\"objectUrl\":\"http://m.okwei.com\"}";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", type);
		map.put("content", msg.getPushContent());
		map.put("type", msg.getMsgType());
		map.put("senderId", msg.getPushWeiId());
		map.put("objectId", msg.getObjectId());
		if (msg.getObjectUrl() == null || "".equals(msg.getObjectUrl())) {
			map.put("objectUrl", "http://m.okwei.com");
		} else {
			map.put("objectUrl", msg.getObjectUrl());
		}
		if (msg.getExtra() != null && !"".equals(msg.getExtra())) {
			map.put("extra", msg.getExtra());
		}
		if (msg.getSenderAvatar() != null && !"".equals(msg.getSenderAvatar()) && !"null".equals(msg.getSenderAvatar())) {
			map.put("senderAvatar", msg.getSenderAvatar());
		}
		String domainString=AppSettingUtil.getSingleValue("currentDomain");
		if(!domainString.contains("api.okwei.com")){
			String usersList= AppSettingUtil.getSingleValue("testUsers");
			boolean istest=false;
			if(usersList!=null&&!"".equals(usersList)){
				String[] arrStrings=usersList.split(",");
				if(arrStrings!=null&&arrStrings.length>0){
					for (String str : arrStrings) {
						if(str!=null&&str.equals(msg.getReciptWeiId().toString())){
							istest=true;
						}
					}
				}
			}
			if(!istest){
				return true;
			}
		}
		
		String url = bundle.getString("PushUrl");
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			String message = JsonUtil.objectToJson(map);
			System.out.println(message);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			method.setRequestHeader("token", "123456");
			NameValuePair[] param = { new NameValuePair("alias", msg.getReciptWeiId().toString()), new NameValuePair("message", message), new NameValuePair("channel", "1") };
			method.setRequestBody(param);
			int statusCode = 0;
			try {
				statusCode = client.executeMethod(method);
			} catch (HttpException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			System.out.println(statusCode);
			return true;
		} catch (Exception ex) {
			return false;
		} finally {
			method.releaseConnection();
		}

	}

	/**
	 * httpClient post请求返回结果信息
	 * 
	 * @param url
	 * @param paramStr
	 *            （以a=b&c=213&d=ddd的形式）
	 * @return
	 */
	public static String SendPost(String url, String paramStr) {
		String resultString = "";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			List<NameValuePair> paraList = new ArrayList<NameValuePair>();
			String[] arrStrings = paramStr.split("&");
			if (arrStrings != null && arrStrings.length > 0) {
				for (String string : arrStrings) {
					if (!"".equals(string)) {
						String[] iteArr = string.split("=");
						if (iteArr != null && iteArr.length > 1) {
							NameValuePair pair = new NameValuePair(iteArr[0], iteArr[1]);
							paraList.add(pair);
						}
					}
				}
			}
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			method.setRequestHeader("token", "123456");
			NameValuePair[] param = new NameValuePair[paraList.size()];
			for (int i = 0; i < paraList.size(); i++) {
				param[i] = paraList.get(i);
			}
			method.setRequestBody(param);
			client.executeMethod(method);

			System.out.println();
			InputStream stream = method.getResponseBodyAsStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			String line;
			while (null != (line = br.readLine())) {
				resultString += line;
			}

			// 释放连接

		} catch (Exception ex) {

		} finally {
			method.releaseConnection();
		}
		return resultString;
	}

	/**
	 * httpClient post请求返回结果信息
	 * 
	 * @param url
	 * @param paramStr
	 *            （以a=b&c=213&d=ddd的形式）
	 * @return
	 */
	public static String SendPost(String url, Map<String, Object> map) {
		String resultString = "";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			List<NameValuePair> paraList = new ArrayList<NameValuePair>();
			if (map != null) {
				for (String key : map.keySet()) {
					paraList.add(new NameValuePair(key, String.valueOf(map.get(key))));
				}
			}
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			// method.setRequestHeader("Content-Type",
			// "multipart/form-data;charset=utf-8");
			NameValuePair[] param = new NameValuePair[paraList.size()];
			for (int i = 0; i < paraList.size(); i++) {
				param[i] = paraList.get(i);
			}
			method.setRequestBody(param);
			client.executeMethod(method);
			InputStream stream = method.getResponseBodyAsStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			String line;
			while (null != (line = br.readLine())) {
				resultString += line;
			}

			// 释放连接

		} catch (Exception ex) {

		} finally {
			method.releaseConnection();
		}
		return resultString;
	}

}
