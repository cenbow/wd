package com.okwei.bean.vo.order;

import java.util.ArrayList;
import java.util.List;


public class ModifyPrice {
	private Double Totalprice;
	private Double Postpage;
    private	List <UnitPrice> PList = new ArrayList<UnitPrice>();
	private String SupplierOrderID;
	private String PState;
	public Double getTotalprice() 
	{
		return Totalprice;
	}
	public void setTotalprice(Double totalprice)
	{
		Totalprice = totalprice;
	}
	public Double getPostpage() {
		return Postpage;
	}
	public void setPostpage(Double postpage) {
		Postpage = postpage;
	}
	public String getSupplierOrderID() {
		return SupplierOrderID;
	}
	public void setSupplierOrderID(String supplierOrderID) {
		SupplierOrderID = supplierOrderID;
	}
	public List <UnitPrice> getPList() {
		return PList;
	}
	public void setPList(List <UnitPrice> pList) {
		PList = pList;
	}
	public String getPState() {
		return PState;
	}
	public void setPState(String pState) {
		PState = pState;
	}
}
