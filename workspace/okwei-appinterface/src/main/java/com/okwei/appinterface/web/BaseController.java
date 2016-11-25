package com.okwei.appinterface.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.okwei.appinterface.bean.dto.BaseParam;
import com.okwei.appinterface.util.DateEditor;
import com.okwei.common.JsonUtil;
import com.sdicons.json.mapper.MapperException;
import com.okwei.appinterface.util.UserValidate;
import com.okwei.bean.domain.UWeiSellerLoginLog;

public class BaseController {

	/** "验证结果"参数名称 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	@Resource(name = "validator")
	private Validator validator;
	
	
	protected Logger LOG = Logger.getLogger(this.getClass());

	protected HttpServletRequest request ; 

	protected HttpServletResponse response ;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	@ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
    }
	/**
	 * 输出json格式对象
	 * @param obj
	 * @throws MapperException 
	 */
	protected void writerObjectToJson(Object obj) {
		PrintWriter writer = null;
		try {
			// 消除缓存
            response.setHeader("pragma", "no-cache");
            response.setHeader("cache-control", "no-cache");
			response.setContentType("application/json; charset=utf-8");
			writer = response.getWriter();
			writer.print(JsonUtil.objectToJsonStr(obj));
		} catch (MapperException e) {
			LOG.error("[writerObject MapperException] obj=" + obj, e);
		} catch (IOException e) {
			LOG.error("[writerObject IOException] obj=" + obj, e);
		} finally{
			if(writer != null){
				writer.flush();
				writer.close();
			}
		}
	}
	

	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * 数据验证
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 数据验证
	 * 
	 * @param type
	 *            类型
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 验证ticket
	 * 
	 * @param param
	 * @return
	 */
	protected UWeiSellerLoginLog validTicket(BaseParam param) {
		UWeiSellerLoginLog seller = null;
		// 是否可以匿名访问，默认不传isAno，不可以匿名访问
		if (param.getIsAno() != 0) {
			seller = new UWeiSellerLoginLog();
		} else {
			seller = UserValidate.IsLoginReturnUser(param.getTiket());
		}
		return seller;
	}
	
	/**
	 * 对象转JSON字符串
	 * @param obj
	 * @return
	 */
	protected String objectToJson(Object obj){
		try {
			return JsonUtil.objectToJsonStr(obj);
		} catch (Exception e) {
			LOG.error("[objectToJson Exception] obj=" + obj, e);
		}
		return "";
	}

}
