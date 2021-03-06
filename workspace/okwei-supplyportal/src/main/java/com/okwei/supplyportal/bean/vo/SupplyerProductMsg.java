package com.okwei.supplyportal.bean.vo;

public class SupplyerProductMsg {
 private Long productid;
 private String productimage;
 private String producttitle;
 private Double price;
 private String realsetime;
 private int shevlecount;
 private int sharedcount;
 private int istop;
 private int isshow;
//不为置顶的为0；第一个置为1；中间的为2；最后一个为3
 private int position;
public String getProductimage() {
	return productimage;
}
public void setProductimage(String productimage) {
	this.productimage = productimage;
}
public String getProducttitle() {
	return producttitle;
}
public void setProducttitle(String producttitle) {
	this.producttitle = producttitle;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public String getRealsetime() {
	return realsetime;
}
public void setRealsetime(String realsetime) {
	this.realsetime = realsetime;
}
public int getShevlecount() {
	return shevlecount;
}
public void setShevlecount(int shevlecount) {
	this.shevlecount = shevlecount;
}
public int getSharedcount() {
	return sharedcount;
}
public void setSharedcount(int sharedcount) {
	this.sharedcount = sharedcount;
}
public int getIstop() {
	return istop;
}
public void setIstop(int istop) {
	this.istop = istop;
}
public Long getProductid() {
	return productid;
}
public void setProductid(Long productid) {
	this.productid = productid;
}
public int getPosition() {
	return position;
}
public void setPosition(int position) {
	this.position = position;
}
public int getIsshow() {
	return isshow;
}
public void setIsshow(int isshow) {
	this.isshow = isshow;
}

}
