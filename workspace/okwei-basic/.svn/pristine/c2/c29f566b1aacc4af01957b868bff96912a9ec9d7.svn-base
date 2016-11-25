package com.okwei.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientUtil {

	public static String doGetMethod(String url, Map<String, String> keyValueMap) {
		// Protocol myhttps = new Protocol("https", new
		// MySecureProtocolSocketFactory (), 443);
		// Protocol.registerProtocol("https", myhttps);
		HttpClient client = new HttpClient();

		StringBuffer sb = new StringBuffer(url);
		PostMethod postMethod = null;
		try {
			// 设置请求参数
			if (keyValueMap != null) {
				Iterator it = keyValueMap.entrySet().iterator();
				if (keyValueMap.size() > 0) {
					sb.append("?");
					while (it.hasNext()) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
						if (entry != null && entry.getValue() != null) {
							sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&");
						}
					}
					sb.deleteCharAt(sb.length() - 1);
				}

			}
			postMethod = new PostMethod(sb.toString());
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			// todo:设置超时时间
			postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 200000);
			int statusCode = client.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return "调用外部链接异常！";
			}
			String responseBody = postMethod.getResponseBodyAsString();
			return responseBody;
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
	}
	
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，转换为 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
	 * @throws Exception 
     */
    public static String doPost(String url,  Map<String, String> keyValueMap) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            
            StringBuilder sb = new StringBuilder();
            Iterator<Entry<String, String>> it = keyValueMap.entrySet().iterator();
			if (keyValueMap.size() > 0) {
				while (it.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					if (entry != null && entry.getValue() != null) {
						sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&");
					}
				}
				sb.deleteCharAt(sb.length() - 1);
			}
            out.print(sb);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                throw ex;
            }
        }
        return result;
    }    
}
