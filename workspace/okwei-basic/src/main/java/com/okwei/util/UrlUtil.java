package com.okwei.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
/**
 * Url操作相关类
 * @author Administrator
 *
 */
public class UrlUtil {

	/**
	 * 返回完整请求地址
	 * @param request
	 * @return
	 */
	public static String getFullUrl(HttpServletRequest request){
		int port  = request.getServerPort();
		String strBackUrl = "http://" + request.getServerName() //服务器地址  
                + (port==80?"":":" + request.getServerPort())    
                + request.getContextPath()      //项目名称  
                + request.getServletPath()  ;    //请求页面或其他地址  
        if(!StringHelp.IsNullOrEmpty(request.getQueryString())){
        	strBackUrl += "?" + (request.getQueryString()); //参数  
        }
		return strBackUrl;
	}
	
	/**
	 * 返回部分完整请求地址
	 * @param request
	 * @return
	 */
	public static String getPartUrl(HttpServletRequest request,long weino){
		int port  = request.getServerPort();
		String strBackUrl = "http://" + (weino==0?"":  weino + "." ) + request.getServerName() //服务器地址  
                + (port==80?"":":" + request.getServerPort())   
                + request.getContextPath()   ;   //项目名称  
		return strBackUrl;
	}
	
	/**
	 * 返回部分完整请求地址
	 * @param request
	 * @return
	 */
	public static String getPartUrl(HttpServletRequest request){
		int port  = request.getServerPort();
		String strBackUrl = "http://" +   request.getServerName() //服务器地址  
                + (port==80?"":":" + request.getServerPort())   
                + request.getContextPath()   ;   //项目名称  
		return strBackUrl;
	}
	
	/**
	 * 判断是否ajax请求
	 * 
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader(
				"x-requested-with");
		if (header != null && "XMLHttpRequest".equals(header)) {
			return true;
		} else {
			return false;
		}
	}

}
