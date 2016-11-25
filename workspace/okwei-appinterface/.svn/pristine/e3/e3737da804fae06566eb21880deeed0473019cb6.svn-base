package com.okwei.appinterface.bean.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Limit implements Serializable {

	public static final Limit DEFAILT_LIMIT = Limit.buildLimit(1, 12);
	private int pageSize;
	private int pageIndex;
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
		limit.pageIndex = pageId;
		limit.pageSize = pageSize;
		limit.start = (pageId - 1) * pageSize;
		return limit;
	}

	private Limit(int pageId, int size) {
		this.pageIndex = pageId;
		this.pageSize = size;
	}

	private Limit() {
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
