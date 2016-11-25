package com.okwei.util;

/**
 * 
 * @ClassName: HtmlUtil 
 * @Description: html工具类  
 * @author xiehz 
 * @date 2015年6月3日 下午2:59:44 
 *
 */
public class HtmlUtil {

	public static String replaceAllTag(String html) {
		if (ObjectUtil.isEmpty(html)) {
			return "";
		}
		// 去掉所有html元素,
		String str = html.trim().replaceAll("<[^>]+>", "");
		return str;
	}

	public static String replaceCommontTag(String html) {
		html = html.replace("<formula><![CDATA[", "");
		html = html.replace("]]></formula>", "");
		return html;
	}

	/**
	 * 
	 * @Title: replaceResource 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param questionTitle
	 * @param @param path
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String replaceResource(String questionTitle, String path) {
		questionTitle = questionTitle.replaceAll("source=\"", "src=\"" + path);
		return questionTitle;
	}
}
