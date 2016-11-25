package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.PProducts;

/**
 * 产品表
 * @author Administrator
 *
 */
public interface IBasicPProductsMgtDAO {
	/**
	 * 查询单个产品
	 */
	PProducts getPProducts(Long productId);
	/**
	 * 产品列表
	 * @param productList
	 * @return
	 */
	List<PProducts>getPProductsList(List<Long>productList);
}
