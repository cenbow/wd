package com.okwei.company.bean.vo;

public class YsquerParam {
     private Integer type;
     private Integer c;
     private Integer pageId;
     private Integer psize;
    
	public Integer getPsize() {
		if(psize==null || psize<1)
			psize=60;
		return psize;
	}
	public void setPsize(Integer psize) {
		this.psize = psize;
	}
	public Integer getType() {
		if(type==null || type<0)
			type=0;
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getC() {
		if(c==null || c<0)
			c=0;
		return c;
	}
	public void setC(Integer c) {
		this.c = c;
	}
	public Integer getPid() {
		if(pageId==null || pageId<1)
			pageId=1;
		return pageId;
	}
	public void setPid(Integer pid) {
		this.pageId = pid;
	}
     
}
