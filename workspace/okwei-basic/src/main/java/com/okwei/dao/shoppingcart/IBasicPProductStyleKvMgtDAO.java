package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.PProductStyleKv;

public interface IBasicPProductStyleKvMgtDAO {
	/**
	 * 查找款式kv表
	 * @param styleIdList
	 * @param productIdList
	 * @return
	 */
	List<PProductStyleKv> getPProductStyleKvByProductIdAndStyleId(List<Long> styleIdList,List<Long> productIdList);
	/**
	 * 查找款式kv表
	 * @param styleId
	 * @param productId
	 * @return
	 */
	List<PProductStyleKv> getPProductStyleKvByProductIdAndStyleId(long styleId,long productId);
}
