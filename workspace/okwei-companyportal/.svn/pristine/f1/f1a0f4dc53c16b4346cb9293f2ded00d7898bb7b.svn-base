package com.okwei.company.dao;

import java.util.List;

import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TBussinessClass;
import com.okwei.bean.domain.TMarketImgs;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;

public interface IbatchMarketDAO extends IBaseDAO {

	/**
	 * 根据市场id获得市场的行业类型
	 * @param bmid
	 * @return
	 */
	List<TBussinessClass> getTBussinessClass(Integer bmid);

	/**
	 * 根据市场Id、认证状态获取服务点数量
	 * @param bmid
	 * @param status
	 */
	long getUBatchPortCount(Integer bmid, Short status);

	List<TMarketImgs> getMarketImgsListByBmid(Integer bmid);

	PageResult<UBatchSupplyer> getUBatchSupplyerPage(Limit buildLimit,Short status, Integer bmid);

	List<PShopClass> getShopClassList(long weiID);


}
