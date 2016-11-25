package com.okwei.service.user;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.service.IBaseService;

public interface IStaticsService extends IBaseService {
	/**
	 * 获取子供应商的订单统计
	 * @param childrenId
	 * @return
	 */
    ReturnModel getOrderStatics(String childrenId);
    /**
     * 获取子供应商产品统计
     * @param childrenId
     * @return
     */
    ReturnModel getProductStatics(String childrenId);
}
