package com.okwei.supplyportal.bean.dto;

import java.io.Serializable;
import java.util.List;


public class ProductParam implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Short ptype;
private Long productid;
private Short updown;
private String products;
private String c;
public Short getPtype() {
	if(this.ptype==null)
		ptype=1;
	return ptype;
}

public void setPtype(Short ptype) {
	this.ptype = ptype;
}

public Long getProductid() {
	return productid;
}

public void setProductid(Long productid) {
	this.productid = productid;
}

public Short getUpdown() {
	return updown;
}

public void setUpdown(Short updown) {
	this.updown = updown;
}

public String getProducts() {
	return products;
}

public void setProducts(String products) {
	this.products = products;
}

public String getC() {
	return c==null?"":c;
}

public void setC(String c) {
	this.c = c;
}

}
