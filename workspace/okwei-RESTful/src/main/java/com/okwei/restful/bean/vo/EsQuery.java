package com.okwei.restful.bean.vo;

import io.searchbox.core.search.sort.Sort;

import java.io.Serializable;

public class EsQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int size;
	private int pageId;
	private int start;
	private Sort sort;

	private String queryString;

	public static EsQuery buildQuery(String queryString, int pageId, int pageSize) {
		if (pageId <= 0)
			pageId = 1;
		if (pageSize <= 0)
			pageSize = 20;// 默认20
		EsQuery query = new EsQuery();
		query.pageId = pageId;
		query.size = pageSize;
		query.start = (pageId - 1) * pageSize;
		query.queryString = queryString;
		return query;
	}

	public static EsQuery buildQuery(String queryString, int pageId, int pageSize, Sort sort) {
		if (pageId <= 0)
			pageId = 1;
		if (pageSize <= 0)
			pageSize = 20;// 默认20
		EsQuery query = new EsQuery();
		query.pageId = pageId;
		query.size = pageSize;
		query.start = (pageId - 1) * pageSize;
		query.queryString = queryString;
		query.sort = sort;
		return query;
	}

	private EsQuery(int pageId, int size) {
		this.pageId = pageId;
		this.size = size;
	}

	private EsQuery() {
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
