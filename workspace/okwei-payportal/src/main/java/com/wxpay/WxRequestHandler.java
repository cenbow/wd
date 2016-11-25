package com.wxpay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.okwei.util.MD5Encrypt;
import com.tenpay.MD5Util;
import com.tenpay.TenpayUtil;

/*
 '΢��֧��������ǩ��֧������������
 '============================================================================
 'api˵����
 'init(app_id, app_secret, partner_key, app_key);
 '��ʼ��������Ĭ�ϸ�һЩ������ֵ����cmdno,date�ȡ�
 'setKey(key_)'�����̻���Կ
 'getLasterrCode(),��ȡ�������
 'GetToken();��ȡToken
 'getTokenReal();Token���ں�ʵʱ��ȡToken
 'createMd5Sign(signParams);����Md5ǩ��
 'genPackage(packageParams);��ȡpackage��
 'createSHA1Sign(signParams);����ǩ��SHA1
 'sendPrepay(packageParams);�ύԤ֧��
 'getDebugInfo(),��ȡdebug��Ϣ
 '============================================================================
 '*/
public class WxRequestHandler {
	/** Token��ȡ���ص�ַ��ַ */
	private String tokenUrl;
	/** Ԥ֧������url��ַ */
	private String gateUrl;
	/** ��ѯ֧��֪ͨ����URL */
	private String notifyUrl;
	/** �̻����� */
	private String appid;
	private String appkey;
	private String partnerkey;
	private String appsecret;
	private String key;

	/** ����Ĳ��� */
	private SortedMap parameters;
	/** Token */
	private String Token;
	private String charset;
	/** debug��Ϣ */
	private String debugInfo;
	private String last_errcode;

	private HttpServletRequest request;

	private HttpServletResponse response;

	/**
	 * ��ʼ���캯����
	 * 
	 * @return
	 */
	public WxRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		this.last_errcode = "0";
		this.request = request;
		this.response = response;
		this.charset = "GBK";
		this.parameters = new TreeMap();
		// ��֤notify֧����������
		notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
		
	}

	/**
	 * ��ʼ��������
	 */
	public void init(String app_id, String app_secret, String app_key,
			String partner_key) {
		this.last_errcode = "0";
		this.Token = "token_";
		this.debugInfo = "";
		this.appkey = app_key;
		this.appid = app_id;
		this.partnerkey = partner_key;
		this.appsecret = app_secret;
	}

	public void init() {
	}

	/**
	 * ��ȡ�������
	 */
	public String getLasterrCode() {
		return last_errcode;
	}

	/**
	 *��ȡ��ڵ�ַ,����������ֵ
	 */
	public String getGateUrl() {
		return gateUrl;
	}
	
	/**
	 * ��ȡ����ֵ
	 * 
	 * @param parameter
	 *            ��������
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	
	 //������Կ
	
	public void setKey(String key) {
		this.partnerkey = key;
	}
	//����΢����Կ
	public void  setAppKey(String key){
		this.appkey = key;
	}
	
	// �����ַ�����
	public String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, this.charset).replace("+", "%20");
	}

	// ��ȡpackage��ǩ����
	public String genPackage(SortedMap<String, String> packageParams)
			throws UnsupportedEncodingException {
		String sign = createSign(packageParams);

		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + UrlEncode(v) + "&");
		}

		// ȥ�����һ��&
		String packageValue = sb.append("sign=" + sign).toString();
		System.out.println("packageValue=" + packageValue);
		return packageValue;
	}

	/**
	 * ����md5ժҪ,������:����������a-z����,������ֵ�Ĳ������μ�ǩ����
	 */
	public String createSign(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.partnerkey);
		System.out.println("md5 sb:" + sb);
		String sign ="";// MD5Util.MD5Encode(sb.toString(), this.charset).toUpperCase();				
		sign = MD5Encrypt.encrypt(sb.toString()).toUpperCase();
		return sign;

	}
	/**
	 * ����packageǩ��
	 */
	public boolean createMd5Sign(String signParams) {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		// ���ժҪ
		String enc = TenpayUtil.getCharacterEncoding(this.request,
				this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();

		// debug��Ϣ
		this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:"
				+ tenpaySign);

		return tenpaySign.equals(sign);
	}

	

    //���XML
	   public String parseXML() {
		   StringBuffer sb = new StringBuffer();
	       sb.append("<xml>");
	       Set es = this.parameters.entrySet();
	       Iterator it = es.iterator();
	       while(it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				String k = (String)entry.getKey();
				String v = (String)entry.getValue();
				if(null != v && !"".equals(v) && !"appkey".equals(k)) {
					
					sb.append("<" + k +">" + getParameter(k) + "</" + k + ">\n");
				}
			}
	       sb.append("</xml>");
			return sb.toString();

		}
	   
   public String parseToXML() {
	   StringBuffer sb = new StringBuffer();
	   //sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
       sb.append("<xml>");
       Set es = this.parameters.entrySet();
       Iterator it = es.iterator();
       String sign ="";
       while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(null != v && !"".equals(v) && !"appkey".equals(k)) {				
				sb.append("<" + k +">" + v + "</" + k + ">\n");
			}			
		}
       sb.append("</xml>");
       
		try {
			return new String(sb.toString().getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	   
   public static String httpPost(String urlStr,String param) {  
      String result="";
		try {  
            URL url = new URL(urlStr);  
            URLConnection con = url.openConnection();  
            con.setDoOutput(true);  
            con.setRequestProperty("Pragma:", "no-cache");  
            con.setRequestProperty("Cache-Control", "no-cache");  
            con.setRequestProperty("Content-Type", "text/xml");  
  
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());    
            out.write(new String(param.getBytes("ISO-8859-1")));  //param.getBytes(),"ISO-8859-1"
            out.flush();  
            out.close();  
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));  
            String line = "";  
            for (line = br.readLine(); line != null; line = br.readLine()) {  
               result+=line; 
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
            result+=e;
        } catch (IOException e) {  
            e.printStackTrace();  
            result+=";"+e;
        }  
		return result;
    } 
	   
	/**
	 * ����debug��Ϣ
	 */
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	public String getDebugInfo() {
		return debugInfo;
	}
	public String getKey() {
		return key;
	}
	public void setParameters(SortedMap parameters) {
		this.parameters = parameters;
	}
}
