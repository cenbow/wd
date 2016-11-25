package com.okwei.util;

import com.mashape.unirest.http.HttpResponse; 
import com.mashape.unirest.http.JsonNode; 
import com.mashape.unirest.http.Unirest; 
import com.mashape.unirest.request.GetRequest; 
import com.mashape.unirest.request.HttpRequestWithBody;   

import java.io.IOException;
import java.util.Map;  
import java.util.concurrent.Future; 

import org.json.JSONObject;  
/**  *   *        *       
 * * @author       
 *  * @version     
 *  * @created
 *  * @function:使用异步库 async HttpClient调用     */ 
public class AsynHttpUtil 
{                     
	/**      *       
	 * * @param url      
	 * * @param params      
	 * * @return 这是异步的post方法 记住 最后要把Unirest.shutdown(); 队列 关闭掉      */     
	public static JSONObject post(String url,Map<String,Object> params)
	{     	
		HttpRequestWithBody request=Unirest.post(url).header("accept", "application/json");     	
		if(params!=null)
		{     		      	            
			request.fields(params);     	
		}
		Future<HttpResponse<JsonNode>> future = request.asJsonAsync();     	 		
		try 
		{                 			 			
			JSONObject ojson=future.get().getBody().getObject(); 			
			return ojson; 		
		} 
		catch (Exception e) 
		{ 		 	 
			//flag=false; 		 	 
			//size++; 	
			return null;
		} 		 		
		finally
		{ 			 			
			try 
			{ 				
				Unirest.shutdown(); 			
			} 
			catch (IOException e) 
			{ 				 				
				e.printStackTrace(); 			
				
			} 		
		} 		
		
	}	
	/**      *       
		 * * @param url      
		 * * @param params     
		 *  * @return 这是异步的post方法 记住 最后要把Unirest.shutdown(); 队列 关闭掉      */     
	public static JSONObject get(String url, Map<String, Object> params) {
		GetRequest request = null;
		if (params == null)
			request = Unirest.get(url);
		request = Unirest.get(url + "?" + stringify(params));
		Future<HttpResponse<JsonNode>> future = request.asJsonAsync();
		HttpResponse<JsonNode> resp = null;
		try {
			resp = future.get();
			JSONObject ojson = resp.getBody().getObject();
			return ojson;
		} catch (Exception e) {
			return null;
		}
		finally
		{ 			 			
			try 
			{ 				
				Unirest.shutdown(); 			
			} 
			catch (IOException e) 
			{ 				 				
				e.printStackTrace(); 			
				
			} 		
		} 	
		
	}   

	public static String stringify(Map<String, Object> map) {
		if (map == null)
			return null;
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			builder.append(entry.getKey()).append("=").append(entry.getValue())
					.append("&");
		}
		return builder.deleteCharAt(builder.length() - 1).toString();
	}
	
}
