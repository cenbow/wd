package com.okwei.appinterface.bean.vo.order;

public class BasePageModle{
	
	private int pageIndex;
	private int pageSize;
	private long totalPage; 
	/**
	 * 当前页
	 * @return
	 */
	public int getPageIndex()
	{
		return pageIndex;
	}
	/**
	 * 每页条数
	 * @return
	 */
	public int getPageSize()
	{
		return pageSize;
	}
	/**
	 * 总共页数
	 * @return
	 */
	public void setPageIndex(int index)
	{
		this.pageIndex=index;
	}
	public void setPageSize(int size)
	{
		this.pageSize=size;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

}
