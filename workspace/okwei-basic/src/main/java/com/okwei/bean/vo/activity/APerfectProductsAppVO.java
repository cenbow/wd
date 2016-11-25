package com.okwei.bean.vo.activity;

import java.io.Serializable;
import java.util.Date;

import com.okwei.bean.vo.product.ProductModel;

/**
 * 精选单品列表中对象的 信息
 * @author zlp
 */
public class APerfectProductsAppVO extends ProductModel  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long perPid;
	/**
	 * 单品标题
	 */
	private String title;
	/**
	 * 链接
	 */
	private String url;
	
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 精品图片
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
	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getPerPid() {
		return perPid;
	}

	public void setPerPid(Long perPid) {
		this.perPid = perPid;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	
}
