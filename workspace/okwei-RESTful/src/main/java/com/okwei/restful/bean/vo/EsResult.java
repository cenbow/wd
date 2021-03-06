package com.okwei.restful.bean.vo;

import java.util.List;

public class EsResult<T> {

	public static final int DEFAULT_PAGE_SIZE = 10;

	private int pageIndex;// 当前页
	private int pageSize;// 页行数
	private int totalPage;// 总页数
	private int totalCount; // 总命中数
	private List<T> list;// 结果数据

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		totalPage = totalCount / pageSize
				+ (totalCount % pageSize == 0 ? 0 : 1);
		if (0 == totalCount) {
			if (pageIndex != 1) {
				pageIndex = 1;
			}
		} else {
			if (pageIndex > totalPage) {
				pageIndex = totalPage;
			}
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
