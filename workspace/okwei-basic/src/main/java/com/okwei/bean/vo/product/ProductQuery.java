package com.okwei.bean.vo.product;

/**
 * 店铺产品查询参数
 * @author yangjunjun
 *
 */
public class ProductQuery {

	private Long shopWeiId;//店铺微店号
	private String keyword;//关键词
	private Integer pageIndex;//当前页码
	private Integer	pageSize;//页码大小
	private String orderBy;//排序方式（传排序id）
	private Integer	type;//搜索类型0零售区1落地进货区2代理区
	private Long classId;//店铺分类ID 
	private Long classLevel;//店铺分类等级 1、一级；2、二级
	
	public Long getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(Long classLevel) {
		this.classLevel = classLevel;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public Long getShopWeiId() {
		return shopWeiId;
	}
	public void setShopWeiId(Long shopWeiId) {
		this.shopWeiId = shopWeiId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
