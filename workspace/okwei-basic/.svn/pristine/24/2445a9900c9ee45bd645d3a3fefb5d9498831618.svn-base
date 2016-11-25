package com.okwei.dao.user;

import java.util.List;

import com.okwei.dao.IBaseDAO;

public interface IStaticsDAO extends IBaseDAO {
	/**
	 * 获取子供应商的订单统计
	 * @param childrenID
	 * @return
	 */
    List<Object[]> getChildrenSupplierStatics(String childrenID);
    /**
     * 获取订单个数
     * @param childrenID
     * @param state
     * @return
     */
    long getSubSupplierOrderCount(String childrenID,Short state);
    /**
     * 获取子供应商的产品个数
     * @param childrenID
     * @param state
     * @return
     */
    long getSubSupplierProductCount(String childrenID,Short state);
}
