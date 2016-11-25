package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.okwei.util.ImgDomain;

public class ProductVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger productId;
	private String defaultImg;
	private String productTitle;

	private String productMinTitle;

	// 零售价
	private BigDecimal defaultPrice;
	// 预订价
	private BigDecimal bookPrice;
	// 批发价
	private BigDecimal batchPrice;
	// 佣金
	private BigDecimal defaultConmision;
	// 店铺分类
	private Integer sid;
	private BigInteger classId;// 因为两个表的shopClassId字段类型不一样
	private String sName;
	// 品牌
	private Integer brandId;
	private String brandName;

	/**
	 * 类型: 0表示分销；1、2、3表示自营(对应于ShelveType枚举值); -1表示页面选择全部
	 */
	private Short type;
	// 供应商
	private BigInteger supplierWeiId;
	private String supplierName;

	// 批发、预定、零售
	private Short tag;

	// 上架表id
	private BigInteger sjId;

	private Short sort;

	private Date createTime;

	public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = ImgDomain.GetFullImgUrl(defaultImg);
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductMinTitle() {
		return productMinTitle;
	}

	public void setProductMinTitle(String productMinTitle) {
		this.productMinTitle = productMinTitle;
	}

	public BigDecimal getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(BigDecimal defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	public BigDecimal getBatchPrice() {
		return batchPrice;
	}

	public void setBatchPrice(BigDecimal batchPrice) {
		this.batchPrice = batchPrice;
	}

	public BigDecimal getDefaultConmision() {
		return defaultConmision;
	}

	public void setDefaultConmision(BigDecimal defaultConmision) {
		this.defaultConmision = defaultConmision;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		if (null != type && type > 0) {
			this.type = 1;
		} else {
			this.type = type;
		}
	}

	public BigInteger getSupplierWeiId() {
		return supplierWeiId;
	}

	public void setSupplierWeiId(BigInteger supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Short getTag() {
		return tag;
	}

	public void setTag(Short tag) {
		this.tag = tag;
	}

	public BigInteger getSjId() {
		return sjId;
	}

	public void setSjId(BigInteger sjId) {
		this.sjId = sjId;
	}

	public BigInteger getClassId() {
		return classId;
	}

	public void setClassId(BigInteger classId) {
		this.classId = classId;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
