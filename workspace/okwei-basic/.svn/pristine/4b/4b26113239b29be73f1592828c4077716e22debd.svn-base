package com.okwei.common;

import java.io.Serializable;

/**
 * @ClassName:Limit
 * @Description:分布控制类
 * @author xiehz
 * @date 2015年4月22日
 */
@SuppressWarnings("serial")
public class Limit implements Serializable {

	public static final Limit DEFAILT_LIMIT = Limit.buildLimit(1, 12);
	private int size;
	private int pageId;
	private int start;

	/**
	 * @Title:buildLimit
	 * @Description:用于 页面&DB分页
	 * @author xiehz
	 * @date 2015年4月22日
	 */
	public static Limit buildLimit(int pageId, int pageSize) {
		if (pageId <= 0)
			pageId = 1;
		if (pageSize <= 0)
			pageSize = 20;// 默认20
		Limit limit = new Limit();
		limit.pageId = pageId;
		limit.size = pageSize;
		limit.start = (pageId - 1) * pageSize;
		return limit;
	}

	private Limit(int pageId, int size) {
		this.pageId = pageId;
		this.size = size;
	}

	private Limit() {
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the size
	 */
	public int getStart() {
		return start;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the pageId
	 */
	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
}