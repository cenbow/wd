package com.okwei.supplyportal.bean.vo;

import java.util.List;

public class ProductManageVO {
private int ptype;
private int issearch;
private String content;
private SupplyerMsg supmsg;
private SupplyOrderCount ordercount;
private SupplyerProductCount productcount;
private SupplyerProductCollect productcollect;
private Short yun;
private Short batch;
private Short verifer;
public Short getYun() {
	return yun;
}
public void setYun(Short yun) {
	this.yun = yun;
}
public Short getBatch() {
	return batch;
}
public void setBatch(Short batch) {
	this.batch = batch;
}
public Short getVerifer() {
	return verifer;
}
public void setVerifer(Short verifer) {
	this.verifer = verifer;
}
public SupplyerMsg getSupmsg() {
	return supmsg;
}
public void setSupmsg(SupplyerMsg supmsg) {
	this.supmsg = supmsg;
}
public SupplyOrderCount getOrdercount() {
	return ordercount;
}
public void setOrdercount(SupplyOrderCount ordercount) {
	this.ordercount = ordercount;
}
public SupplyerProductCount getProductcount() {
	return productcount;
}
public void setProductcount(SupplyerProductCount productcount) {
	this.productcount = productcount;
}
public SupplyerProductCollect getProductcollect() {
	return productcollect;
}
public void setProductcollect(SupplyerProductCollect productcollect) {
	this.productcollect = productcollect;
}
public int getPtype() {
	return ptype;
}
public void setPtype(int ptype) {
	this.ptype = ptype;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getIssearch() {
	return issearch;
}
public void setIssearch(int issearch) {
	this.issearch = issearch;
}

}
