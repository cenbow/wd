package com.okwei.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: AjaxUtil
 * @Description: 用于封装Ajax访问结果
 * @author xiehz
 * @date 2015年5月15日 下午6:53:37
 *
 */
public class AjaxUtil {

	/**
	 * 
	 * @Title: ajaxError 
	 * @Description: 直接打印异常到页面 统一用json格式 {error:true,status:'500',msg:'授权失败',entity:{}} 
	 * @param @param tx
	 * @param @param msg
	 * @param @param response
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public static void ajaxError(Throwable tx, String msg, HttpServletResponse response) throws IOException {
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(true);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setDetailMsg(tx.getMessage());
		ajaxResponse.setStatus("500");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(JsonUtil.objectToJson(ajaxResponse));
	}

	/**
	 * 
	 * @Title: ajaxError 
	 * @Description: 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @param @param tx
	 * @param @param msg
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String ajaxError(Throwable tx, String msg) {
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(true);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setDetailMsg(tx.getMessage());
		ajaxResponse.setStatus("500");
		return JsonUtil.objectToJson(ajaxResponse);
	}

	/**
	 * 
	 * @Title: ajaxFail 
	 * @Description: 返回ajax需要格式的json字符串 用于@ResponseBody 
	 * @param @param msg
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String ajaxFail(String msg) {
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(true);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setStatus("500");
		return JsonUtil.objectToJson(ajaxResponse);
	}

	/**
	 * 
	 * @Title: ajaxSuccess 
	 * @Description: 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @param @param msg
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String ajaxSuccess(String msg) {
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(false);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setStatus("200");
		return JsonUtil.objectToJson(ajaxResponse);
	}

	/**
	 * 
	 * @Title: ajaxSuccess 
	 * @Description: 返回ajax需要格式的json字符串 用于@ResponseBody 
	 * @param @param ajaxResponse
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String ajaxSuccess(AjaxResponse<?> ajaxResponse) {
		ajaxResponse.setError(false);
		ajaxResponse.setStatus("200");
		return JsonUtil.objectToJson(ajaxResponse);
	}
}
