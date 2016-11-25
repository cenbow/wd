package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.USupplyer;

public interface IBasicUSupplyerMgtDAO {
	/**
	 * 查询供应商信息表
	 * @param weiId
	 * @return
	 */
	USupplyer getUSupplyer(long weiId);
	/**
	 * 查找用户信息列表
	 * @param weiIdList
	 * @return
	 */
	List<USupplyer> getUSupplyerList(List<Long> weiIdList);
}
