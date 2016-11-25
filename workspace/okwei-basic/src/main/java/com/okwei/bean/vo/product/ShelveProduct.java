package com.okwei.bean.vo.product;

import java.util.List;

public class ShelveProduct {

	private Long productID; 
	private Long supplierWeiid;
	private Long shelveWeiid;
	private Long sendWeiid;
	private Long classID;
	private short IsSendMyself;
	private short sort;
	private String shevleDes;
	private short type;
	private List<BatchPriceM> batchList;
	
	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Long getClassID() {
		return classID;
	}

	public void setClassID(Long classID) {
		this.classID = classID;
	}

	public short getIsSendMyself() {
		return IsSendMyself;
	}

	public void setIsSendMyself(short isSendMyself) {
		IsSendMyself = isSendMyself;
	}

	public List<BatchPriceM> getBatchList() {
		return batchList;
	}

	public void setBatchList(List<BatchPriceM> batchList) {
		this.batchList = batchList;
	}

	public String getShevleDes() {
		return shevleDes;
	}

	public void setShevleDes(String shevleDes) {
		this.shevleDes = shevleDes;
	}

	public short getSort() {
		return sort;
	}

	public void setSort(short sort) {
		this.sort = sort;
	}

	public Long getSupplierWeiid() {
		return supplierWeiid;
	}

	public void setSupplierWeiid(Long supplierWeiid) {
		this.supplierWeiid = supplierWeiid;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public Long getShelveWeiid() {
		return shelveWeiid;
	}

	public void setShelveWeiid(Long shelveWeiid) {
		this.shelveWeiid = shelveWeiid;
	}

	public Long getSendWeiid() {
		return sendWeiid;
	}

	public void setSendWeiid(Long sendWeiid) {
		this.sendWeiid = sendWeiid;
	}
}

