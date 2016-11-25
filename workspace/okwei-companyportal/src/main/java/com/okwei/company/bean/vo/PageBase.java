package com.okwei.company.bean.vo;

public class PageBase {
    private Integer itemcount;
    private Integer pagecount;
    private Integer pageindex;
    private Integer pagesize;
	public Integer getPagesize() {
		if(pagesize==null || pagesize<0)
			pagesize=10;
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getItemcount() {
		if(itemcount==null || itemcount<0)
			itemcount=0;
		return itemcount;
	}
	public void setItemcount(Integer itemcount) {
		this.itemcount = itemcount;
	}
	public Integer getPagecount() {
		if(pagecount==null|| pagecount<0)
			pagecount=0;
		return pagecount;
	}
	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}
	public Integer getPageindex() {
		if(pageindex==null || pageindex<0)
			pageindex=1;
		return pageindex;
	}
	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}
    
}
