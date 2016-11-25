package com.okwei.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: BasePathFactory
 * @Description: 路径工厂类
 * @author xiehz
 * @date 2015年5月7日 上午10:09:42
 *
 */
public class BasePathFactory {

	/**
	 * 
	 * @Title: getBasePath
	 * @Description: 获取根路径
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getBasePath() {
		String path = getClassPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF/classes"));
		} else {
			return null;
		}
		return path;
	}

	/**
	 * 
	 * @Title: getClassPath
	 * @Description: 获得classpath(....../WebRoot/WEB-INF/classes/)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getClassPath() {
		return BasePathFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}

	/**
	 * 
	 * @Title: getWebRootPath
	 * @Description: 获取URL请求路径
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getWebRootPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	}

	/**
	 * 
	 * @Title: getResourcePath
	 * @Description: 获取basic工程的资源文件路径
	 * @param @param resourceName
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getResourcePath(String resourceName) {
		return BasePathFactory.class.getResource("//" + resourceName).getPath();
	}
	
}
