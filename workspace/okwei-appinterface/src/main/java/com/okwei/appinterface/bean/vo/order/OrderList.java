package com.okwei.appinterface.bean.vo.order;

import java.util.List;

public class OrderList extends BasePageModle 
{
	 private Long totalcount;
     private List<ProductOrderModel> list;
	public Long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Long totalcount) {
		this.totalcount = totalcount;
	}
	public List<ProductOrderModel> getList() {
		return list;
	}
	public void setList(List<ProductOrderModel> list) {
		this.list = list;
	}
 
}
