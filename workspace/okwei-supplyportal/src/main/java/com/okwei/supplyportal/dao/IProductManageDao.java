package com.okwei.supplyportal.dao;

import java.util.List;

import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UYunSupplier;


public interface IProductManageDao {
	/**
	 * 根据微店号获取供应商信息
	 * @param weiid
	 * @return
	 */
	UBatchSupplyer getSupplyerMsg(Long weiid);
	/**
	 * 获取云商通供应商信息
	 * @param weiid
	 * @return
	 */
	UYunSupplier getYunSupplyerMsg(Long weiid);
	/**
	 * 获取供应商基本信息
	 * @param weiid
	 * @return
	 */
	USupplyer getBaseSupplyerMsg(Long weiid);
	/**
	 * 获取认证员基本资料
	 * @param weiid
	 * @return
	 */
	UVerifier getBaseVerifierMsg(Long weiid);
	/**
	 * 获取不同状态的订单个数
	 * @param weiid
	 * @param state
	 * @return
	 */
	Long getOrderCountByState(Long weiid,Short state);
	/**
	 * 
	 * @param weiid
	 * @return
	 */
	List<Object[]> getOrderCount(Long weiid);
	/**
	 * 获取不同状态的产品个数
	 * @param weiid
	 * @param state
	 * @return
	 */
	Long getProductCountyByState(Long weiid,Short state,String content);
	/**
	 * 
	 * @param weiid
	 * @param state
	 * @return
	 */
	List<Object[]> getProductsCount(Long weiid);
	/**
	 * 获取产品列表
	 * @param supID
	 * @param state
	 * @param pagesize
	 * @param pageindex
	 * @return
	 */
	List<Object[]> getProductsBySupID(Long supID,Short state,Integer pagesize,Integer pageindex,String content);
	/**
	 * 更改产品的排序,取消置顶
	 * @param productid
	 * @param sort
	 */
	void updateProductSort(Long productid,Short sort,Long supweiid);
	
	/**
	 * 更改产品的排序，置顶
	 * @param productid
	 * @param sort
	 */
	void updateProductSort(Long productid,Long supweiid);
	/**
	 * 移动推荐位置
	 * @param productid
	 * @param updown
	 */
	int moveposition(Long productid,Short updown,Long supweiid);
	/**
	 * 批量置顶
	 * @param products
	 */
	void batchontop(String[] products,Long supweiid);
	/**
	 * 批量下架
	 * @param products
	 * @param supweiid
	 */
	void batchoffshow(String[] products,Long supweiid);
	
	/**
	 * 批量操作产品。上架、删除
	 * @param products
	 * @param supweiid
	 * @param optype 1 上架 -1 删除
	 * @return
	 */
	void batchoperate(String[] products,Long supweiid,Short optype);
	/**
	 * 获取供应商的最小排序
	 * @param supweiid
	 * @return
	 */
	int getminSort(Long supweiid);
	/**
	 * 获取供应商的最大排序
	 * @param supweiid
	 * @return
	 */
	int getmaxSort(Long supweiid);
	
}
