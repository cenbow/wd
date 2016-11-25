package com.okwei.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.okwei.cache.CacheFactory;
import com.okwei.util.SpringContextUtil;

/**
 * 
 * @ClassName: Initializer 
 * @Description: 服务器启动初始化类 
 * @author xiehz 
 * @date 2015年5月7日 上午11:43:12 
 *
 */
public class Initializer extends HttpServlet {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final Log logger = LogFactory.getLog(Initializer.class);

	@Override
	public void init() throws ServletException {
		try {
			// 初始化缓存
			CacheFactory.init();
			// 系统初始化数据载入cache
			com.okwei.service.ISysInitService service = (com.okwei.service.ISysInitService) SpringContextUtil.getBean("sysInitService");
			service.addCacheSysInit();
			logger.info("系统初始化数据完成");

		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
