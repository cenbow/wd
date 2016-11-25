package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.PProductSellKey;

public interface IBasicPProductSellKeyMgtDAO {
	/**
	 * 查找key表
	 * @param productIdList
	 * @return
	 */
	List<PProductSellKey> getPProductSellKeyByProductId(List<Long> productIdList);
	/**
	 * 查询key表
	 * @param productId
	 * @return
	 */
	List<PProductSellKey> getPProductSellKeyByProductId(long productId);
}
