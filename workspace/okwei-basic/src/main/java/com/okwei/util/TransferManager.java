package com.okwei.util;

import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class TransferManager {
	
	 
	 /**
	     * get方式提交
	     *
	     * @param url
	     * @param keyValueMap
	     * @return
	     */
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public static  String SearchProduct(Map<String, String> keyValueMap) {
	    	ResourceBundle bundle = ResourceBundle.getBundle("url");
			if (bundle == null) {
				throw new IllegalArgumentException("[url.properties] is not found!");
			}
			String url= bundle.getString("SearchUrl");
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
	                return "";
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
	}

