package com.okwei.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: SpringContextUtil
 * @Description: 获取spring容器，以访问容器中定义的其他bean
 * @author xiehz
 * @date 2015年5月7日 上午11:03:52
 *
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private SpringContextUtil() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}	
	
	/**
	 * 
	 * @Title: getBean 
	 * @Description: 获取容器中的bean对象 
	 * @param @param beanId
	 * @param @return
	 * @param @throws BeansException
	 * @return Object
	 * @throws
	 */
	public static Object getBean(String beanId) throws BeansException {
		return applicationContext.getBean(beanId);
	}

	

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 改方法仅提供做单元测试用
	public static void setActForTest(ApplicationContext act) {
		applicationContext = act;
	}
}
