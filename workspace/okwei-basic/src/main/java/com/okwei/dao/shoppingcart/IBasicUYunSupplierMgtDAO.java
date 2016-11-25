package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.UYunSupplier;

public interface IBasicUYunSupplierMgtDAO {
	/**
	 * 查找云商通供应商列表
	 * @param weiIdList
	 * @return
	 */
	List<UYunSupplier> getUYunSupplierList(List<Long> weiIdList,short status);
}
