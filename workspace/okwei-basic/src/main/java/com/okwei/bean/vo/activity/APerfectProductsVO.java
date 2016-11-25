package com.okwei.bean.vo.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * 精选单品列表中对象的 信息
 * @author zlp
 */
public class APerfectProductsVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long perPid;
	/**
	 * 单品标题
	 */
	private String title;
	
	/**
	 * 产品ID
	 */
	private Long productId;
	
	/**
	 * 链接
	 */
	private String url;
	
	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 状态ID
	 */
	private Short state;
	
	/**
	 * 开启时间
	 */
	private String beginTime;
	
	/**
	 * 开启时间
	 */
	private String endTime;
	
	/**
	 * 推荐次数
	 */
	private Integer recNum;
	/**
	 * 图片
	 */
	private String productImg;

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public Integer getSort() {
		return sort;
	}

	public Short getState() {
		return state;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public Integer getRecNum() {
		return recNum;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setRecNum(Integer recNum) {
		this.recNum = recNum;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public Long getPerPid() {
		return perPid;
	}

	public void setPerPid(Long perPid) {
		this.perPid = perPid;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
	
	
}
