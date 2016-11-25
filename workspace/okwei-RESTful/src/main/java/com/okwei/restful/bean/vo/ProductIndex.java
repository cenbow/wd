package com.okwei.restful.bean.vo;

import io.searchbox.annotations.JestId;

import java.io.Serializable;
import java.util.Date;

public class ProductIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JestId
	private String id;//自增ID
	private Long productId; //产品ID
	private String productTitle;//产品标题
	private Long supplierWeiId;//供应商ID
	private Integer thridClass;//三级分类
	private Integer secondClass;//二级分类
	private Integer firstClass;//一级分类
	private Integer brandId=0;//品牌ID
	private Integer marketId=0;//市场id
	private Double bookPrice;//预定价格
	private Double batchPrice;//批发价格
	private Double defaultPrice;//默认价格
	private Double defaultConmision;//默认佣金
	private Integer totalCount;//总销量
	private Integer evaluateCount;//总评论数
	private Integer shelvesCount;//总上架数
	private String createTime;//创建时间
	private Integer score=0;//七天得分
	private Integer isBatch=0;//是否批发号供应商  默认0不是 1是 
	private Integer isYun=0;//是否云商通
	private Integer isPlatform=0;//是否平台号
	private Integer isBrandform=0;//是否品牌号
	private Integer ling=0; //支持零售
	private Integer pi=0;   //支持批发
	private Integer yu=0;	  //支持预定
	private Integer isActivity=0;//是否活动区
	
	
	
	
	public Integer getIsActivity() {
		return isActivity;
	}

	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}

	public Integer getIsPlatform() {
		return isPlatform;
	}

	public void setIsPlatform(Integer isPlatform) {
		this.isPlatform = isPlatform;
	}

	public Integer getIsBrandform() {
		return isBrandform;
	}

	public void setIsBrandform(Integer isBrandform) {
		this.isBrandform = isBrandform;
	}

	public Integer getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(Integer isBatch) {
		this.isBatch = isBatch;
	}

	public Integer getIsYun() {
		return isYun;
	}

	public void setIsYun(Integer isYun) {
		this.isYun = isYun;
	}

	public Integer getLing() {
		return ling;
	}

	public void setLing(Integer ling) {
		this.ling = ling;
	}

	public Integer getPi() {
		return pi;
	}

	public void setPi(Integer pi) {
		this.pi = pi;
	}

	public Integer getYu() {
		return yu;
	}

	public void setYu(Integer yu) {
		this.yu = yu;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Long getSupplierWeiId() {
		return supplierWeiId;
	}

	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

	

	public Integer getThridClass() {
		return thridClass;
	}

	public void setThridClass(Integer thridClass) {
		this.thridClass = thridClass;
	}

	public Integer getSecondClass() {
		return secondClass;
	}

	public void setSecondClass(Integer secondClass) {
		this.secondClass = secondClass;
	}

	public Integer getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(Integer firstClass) {
		this.firstClass = firstClass;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getMarketId() {
		return marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}

	

	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Double getBatchPrice() {
		return batchPrice;
	}

	public void setBatchPrice(Double batchPrice) {
		this.batchPrice = batchPrice;
	}

	public Double getDefaultConmision() {
		return defaultConmision;
	}

	public void setDefaultConmision(Double defaultConmision) {
		this.defaultConmision = defaultConmision;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(Integer evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public Integer getShelvesCount() {
		return shelvesCount;
	}

	public void setShelvesCount(Integer shelvesCount) {
		this.shelvesCount = shelvesCount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
