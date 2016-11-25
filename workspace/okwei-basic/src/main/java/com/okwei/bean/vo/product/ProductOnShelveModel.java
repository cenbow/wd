package com.okwei.bean.vo.product;

import java.util.List;

import com.okwei.bean.domain.PShopClass;

public class ProductOnShelveModel {
   private Long productid;
   private String productimg;
   private String producttitle;
   private Double price;
   private Double commision;
   private Double maxbatchprice;
   private Double minbatchprice;
   private List<BatchPriceM> batchList;
   private Double maxbatchprice_s;
   private Double minbatchprice_s;
   private Long supplierid;
   private Long productOwnerId;
   private Short IsSendMyself;
   private Short IsAttentioned;
   private Short IsShelved;
   private PShopClass myStoreCateModel;
   private Short isHaveWholesaleInfo;
   
   private Double orignalPrice;
   
   
   //private Long upWeiid;
   
   public Double getOrignalPrice() {
	return orignalPrice;
}

public void setOrignalPrice(Double orignalPrice) {
	this.orignalPrice = orignalPrice;
}

public Double getMaxbatchprice_s() {
	return maxbatchprice_s;
}

public void setMaxbatchprice_s(Double maxbatchprice_s) {
	this.maxbatchprice_s = maxbatchprice_s;
}

public Double getMinbatchprice_s() {
	return minbatchprice_s;
}

public void setMinbatchprice_s(Double minbatchprice_s) {
	this.minbatchprice_s = minbatchprice_s;
}

public PShopClass getMyStoreCateModel() {
	return myStoreCateModel;
}

public void setMyStoreCateModel(PShopClass myStoreCateModel) {
	this.myStoreCateModel = myStoreCateModel;
}

public Short getIsSendMyself() {
	return IsSendMyself;
}

public void setIsSendMyself(Short isSendMyself) {
	IsSendMyself = isSendMyself;
}

public Short getIsAttentioned() {
	return IsAttentioned;
}

public void setIsAttentioned(Short isAttentioned) {
	IsAttentioned = isAttentioned;
}

public Long getSupplierid() {
	return supplierid;
}

public void setSupplierid(Long supplierid) {
	this.supplierid = supplierid;
}
   public Long getProductid() {
	return productid;
}

public void setProductid(Long productid) {
	this.productid = productid;
}

public String getProductimg() {
	return productimg;
}

public void setProductimg(String productimg) {
	this.productimg = productimg;
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

public Double getCommision() {
	return commision;
}

public void setCommision(Double commision) {
	this.commision = commision;
}

public Double getMaxbatchprice() {
	return maxbatchprice;
}

public void setMaxbatchprice(Double maxbatchprice) {
	this.maxbatchprice = maxbatchprice;
}

public Double getMinbatchprice() {
	return minbatchprice;
}

public void setMinbatchprice(Double minbatchprice) {
	this.minbatchprice = minbatchprice;
}

public List<BatchPriceM> getBatchList() {
	return batchList;
}

public void setBatchList(List<BatchPriceM> batchList) {
	this.batchList = batchList;
}

public Short getIsShelved() {
	return IsShelved;
}

public void setIsShelved(Short isShelved) {
	IsShelved = isShelved;
}

public Long getProductOwnerId() {
	return productOwnerId;
}

public void setProductOwnerId(Long productOwnerId) {
	this.productOwnerId = productOwnerId;
}

public Short getIsHaveWholesaleInfo() {
	return isHaveWholesaleInfo;
}

public void setIsHaveWholesaleInfo(Short isHaveWholesaleInfo) {
	this.isHaveWholesaleInfo = isHaveWholesaleInfo;
}

}

