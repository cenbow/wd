package com.okwei.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.okwei.common.AjaxUtil;
import com.okwei.common.PropertyFactory;

/**
 * 
 * @ClassName: CustomExceptionResolver
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xiehz
 * @date 2015年6月6日 上午10:56:53
 *
 */
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

	private Log logger = LogFactory.getLog(CustomExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		/* String msg = BaseConfig.get(ex.getClass().getName()); */

		String msg = PropertyFactory.getPropertyValue("exception", ex.getClass().getName());
		if (ex instanceof UserInputException) {
			msg += ex.getMessage();
			logger.warn("UserInputException ：" + ex.getMessage());
		} else if (ex instanceof AuthenticationException || ex instanceof UnauthenticatedException || ex instanceof AuthenticationException) {
			logger.warn(ex.getClass().getName() + " : " + ex.getMessage());
			if (null != ex.getCause() && ex.getCause() instanceof UserInputException) {
				msg += ex.getCause().getMessage();
			}
		} else {
			logger.error("系统异常：", ex);
		}
		if (isAjaxRequest(request) || isUpload(request)) {// ajax 请求
			AjaxUtil.ajaxFail("系统繁忙！请联系管理员！");
			return new ModelAndView();
		} else {// 表单请求、jsp请求、普通跳转
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("msg", msg);
			String viewName = this.determineViewName(ex, request);// 获取配置的错误页面
			if (null == viewName) {
				viewName = "error";
			} else {
				viewName += "?url=" + request.getServletPath(); // 用于登陆后跳转
			}
			return new ModelAndView("redirect:/" + viewName, param);
		}
	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
		return isAjax;
	}

	public boolean isUpload(HttpServletRequest request) {
		String header = request.getHeader("Content-Type");
		if (null == header) {
			return false;
		}
		return header.indexOf("multipart/form-data;") >= 0;
	}
}
