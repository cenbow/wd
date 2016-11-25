package com.okwei.bean.vo.product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 付豪
 *
 */
public class P_PrductsClassList {
	/**
	 * 分类对象
	 */
	private P_PrductsClass prductsClass;
	/**
	 * 二级分类集合
	 */
	private List<P_PrductsClass> list=new ArrayList<P_PrductsClass>();
	
	public P_PrductsClass getPrductsClass() {
		return prductsClass;
	}
	public void setPrductsClass(P_PrductsClass prductsClass) {
		this.prductsClass = prductsClass;
	}
	public List<P_PrductsClass> getList() {
		return list;
	}
	public void setList(List<P_PrductsClass> list) {
		this.list = list;
	}
	
}
