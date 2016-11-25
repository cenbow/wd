package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.UDemandProduct;

public interface IBasicUDemandProductMgtDAO {
	/**
	 * 查找产品招商需求关系对象
	 * @param demandId
	 * @param productId
	 * @return
	 */
	UDemandProduct getUDemandProduct(int demandId,long productId);
	/**
	 * 查找产品招商需求关系对象
	 * @param weiIdList
	 * @return
	 */
	List<UDemandProduct>  getUDemandProductListByProductId(List<Long> productList);
	/**
	 * 查找招商需求关系表
	 * @param productList
	 * @param demandId
	 * @return
	 */
	List<UDemandProduct> getUDemandProductListByProductIdAndDemandId(List<Long> productList,List<Integer> demandIdList);
	/**
	 * 产品id查找对象
	 * @param productId
	 * @return
	 */
	UDemandProduct getUDemandProductByProductId(long productId);
}
