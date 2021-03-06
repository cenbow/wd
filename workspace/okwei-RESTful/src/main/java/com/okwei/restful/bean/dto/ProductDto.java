package com.okwei.restful.bean.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum OrderBy {
		dcreatetime, // 发布时间倒序
		acreatetime, // 发布时间升序
		dcount, // 销售量倒序
		acount, // 销售量升序
		dprice, // 价格倒序
		aprice,// aprice
		dscore//分数倒序
	}

	private Integer bType; // 一级分类ID
	private Integer mType;// 二级分类ID
	private Integer sType;// 三级分类ID
	private Integer marketId;// 市场id
	private Integer brandId;// 品牌id
	private Double sprice;// 开始价格区间
	private Double eprice;// 结束价格区间

	private String content;// 搜索关键字
	private String where;// 限制条件：yu 支持预定 pi 支持批发 ling 支持零售
	private OrderBy orderBy;// 排序
	private Integer pageIndex;// 当前页
	private Integer pageSize;// 页行数
	private Long supWeiId;// 源供应商
	
	private Short pType;//产品类型0为没有落地价和代理价 1为有落地价和代理价
	
	private Short supType;//供应商类型
	
	private Short activity;//是否活动区
	

	
	
	

	public Short getActivity() {
		return activity;
	}

	public void setActivity(Short activity) {
		this.activity = activity;
	}

	public Short getSupType() {
		return supType;
	}

	public void setSupType(Short supType) {
		this.supType = supType;
	}

	public Integer getbType() {
		return bType;
	}

	public void setbType(Integer bType) {
		this.bType = bType;
	}

	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

	public Integer getsType() {
		return sType;
	}

	public void setsType(Integer sType) {
		this.sType = sType;
	}

	public Integer getMarketId() {
		return marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Double getSprice() {
		return sprice;
	}

	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}

	public Double getEprice() {
		return eprice;
	}

	public void setEprice(Double eprice) {
		this.eprice = eprice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getSupWeiId() {
		return supWeiId;
	}

	public void setSupWeiId(Long supWeiId) {
		this.supWeiId = supWeiId;
	}

	public Short getpType() {
		return pType;
	}

	public void setpType(Short pType) {
		this.pType = pType;
	}
	
}
