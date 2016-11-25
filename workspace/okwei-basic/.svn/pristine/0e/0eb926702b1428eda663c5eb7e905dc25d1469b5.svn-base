package com.okwei.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @ClassName: CacheFactory
 * @Description: 自定义缓存处理工厂类
 * @author xiehz
 * @date 2015年5月7日 上午10:23:32
 *
 */
@SuppressWarnings("unchecked")
public class CacheFactory {

	private static final Log logger = LogFactory.getLog(CacheFactory.class);

	public static final String CACHE_CITY = "city";
	public static final String CACHE_MODULE = "module";
	public static final String CACHE_PERMISSION = "permission";
	public static final String CACHE_SITEBASIC_SETTING = "basicSetting";
	public static final String CACHE_COMMON_TYPE = "commonType";
	public static final String CACHE_AD = "ad";

	private static Map map = new HashMap();

	/**
	 * 
	 * @Title: init 
	 * @Description: 缓存初始化 
	 * @param 
	 * @return void
	 * @throws
	 */
	public static void init() {
		// 缓存
		try {
			CacheBaseData.getInstance();
			CacheRoleData.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getStackTrace());
		}
	}

	/**
	 * @Title: getCache
	 * @Description: 获取缓存对象
	 * @param @param cacheKey
	 * @param @return
	 * @return Object
	 */
	public static Object getCache(String cacheKey) {
		Object obj = map.get(cacheKey);
		return map.get(cacheKey);
	}

}
