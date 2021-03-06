package com.baidu.ueditor.upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

public class PostToFileSys {
	/**
	 * httpClient  post请求返回结果信息 
	 * @param url
	 * @param paramStr （以a=b&c=213&d=ddd的形式）
	 * @return
	 */
	public static String SendPost(String url,Map<String,Object> map)
	{
		String resultString="";
		HttpClient client = new HttpClient();  
		PostMethod method = new PostMethod(url); 
		try
		{
			List<NameValuePair> paraList= new ArrayList<NameValuePair>();
			if(map!=null)
			{
				for (String key  : map.keySet()) {
					paraList.add(new NameValuePair(key,String.valueOf(map.get(key)) ));
				}
			}
			method.setRequestHeader("Content-Type",  
			           "application/x-www-form-urlencoded;charset=utf-8"); 
//			 method.setRequestHeader("Content-Type",  
//					           "multipart/form-data;charset=utf-8");
			NameValuePair[] param = new NameValuePair[paraList.size()];
			for(int i=0;i<paraList.size();i++)
			{
				param[i]=paraList.get(i);
			}
			method.setRequestBody(param);  
			client.executeMethod(method);
			
			 System.out.println();  
		     InputStream stream = method.getResponseBodyAsStream();  
		       
		     BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8")); 
		     String line;  
		     while (null != (line = br.readLine())) {  
		       resultString+=line;
		     }  	    
		       //释放连接  
		
		}
		catch(Exception ex)
		{
			
		}
		finally
		{
			method.releaseConnection();  
		}
		return resultString;
	}
	
	 public static String sendPost(String url, Map<String, Object> parameters) {  
	        String result = "";// 返回的结果  
	        BufferedReader in = null;// 读取响应输入流  
	        PrintWriter out = null;  
	        StringBuffer sb = new StringBuffer();// 处理请求参数  
	        String params = "";// 编码之后的参数  
	        try {  
	            // 编码请求参数  
	            if (parameters.size() == 1) {  
	                for (String name : parameters.keySet()) {  
	                    sb.append(name).append("=").append(  
	                            java.net.URLEncoder.encode(parameters.get(name).toString(),  
	                                    "UTF-8"));  
	                }  
	                params = sb.toString();  
	            } else {  
	                for (String name : parameters.keySet()) {  
	                    sb.append(name).append("=").append(  
	                            java.net.URLEncoder.encode(parameters.get(name).toString(),  
	                                    "UTF-8")).append("&");  
	                }  
	                String temp_params = sb.toString();  
	                params = temp_params.substring(0, temp_params.length() - 1);  
	            }  
	            // 创建URL对象  
	            java.net.URL connURL = new java.net.URL(url);  
	            // 打开URL连接  
	            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
	                    .openConnection();  
	            // 设置通用属性  
	            httpConn.setRequestProperty("Accept", "*/*");  
	            httpConn.setRequestProperty("Connection", "Keep-Alive");  
	            httpConn.setRequestProperty("User-Agent",  
	                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
	            // 设置POST方式  
	            httpConn.setDoInput(true);  
	            httpConn.setDoOutput(true);  
	            httpConn.setConnectTimeout(30000);
	            httpConn.setReadTimeout(30000);
	            // 获取HttpURLConnection对象对应的输出流  
	            out = new PrintWriter(httpConn.getOutputStream());  
	            // 发送请求参数  
	            out.write(params);  
	            // flush输出流的缓冲  
	            out.flush();  
	            // 定义BufferedReader输入流来读取URL的响应，设置编码方式  
	            in = new BufferedReader(new InputStreamReader(httpConn  
	                    .getInputStream(), "UTF-8"));  
	            String line;  
	            // 读取返回的内容  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                if (out != null) {  
	                    out.close();  
	                }  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (IOException ex) {  
	                ex.printStackTrace();  
	            }  
	        }  
	        return result;  
    }

	 
		private static Gson gson = null;
		static {
			if (gson == null) {
				gson = new Gson();
			}
		}
		
		/**
		 * 将json转换成bean对象
		 * 
		 * @param jsonStr
		 * @return
		 */
		public static Object jsonToBean(String jsonStr, Class<?> cl) {
			Object obj = null;
			if (gson != null) {
				obj = gson.fromJson(jsonStr, cl);
			}
			return obj;
		}
}
