package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.TShoppingCar;

public interface IBasicTShoppingCarMgtDAO {
	/**
	 * 查找购物车对象
	 * @param scid
	 * @param weiId
	 * @return
	 */
	TShoppingCar getTShoppingCar(long scid,long weiId,short status);
	/**
	 * 添加购物车
	 * @param tShoppingCar
	 * @return
	 */
long saveTShoppingCar(TShoppingCar tShoppingCar);
	/**
	 * 查找购物车List
	 * @param weiId
	 * @param proNum
	 * @param buyType
	 * @param supplierWeiId
	 * @return
	 */
	List<TShoppingCar> getTShoppingCarList(long weiId,long proNum,short buyType,long supplierWeiId); 
	/**
	 * 修改购物车
	 * @param tShoppingCar
	 * @return
	 */
	void updateTShoppingCar(TShoppingCar tShoppingCar);
	/**
	 * 查询购物车
	 * @param weiId
	 * @param styleId
	 * @param buyType
	 * @param sellerWeiId
	 * @param supplierWeiId
	 * @return
	 */
	TShoppingCar getTShoppingCar(long weiId,long styleId,short buyType,long sellerWeiId,long supplierWeiId,short source);
	
	/**
	 * 获取购物车列表某个款式的
	 * @param weiId
	 * @param styleId
	 * @return
	 */
	public List<TShoppingCar>  find_TShoppingCar(long weiId, long styleId);
	/**
	 * 查找购买车列表
	 * @param weiId
	 * @return
	 */
	List<TShoppingCar> getTShoppingCarList(long weiId,short status);
	/**
	 * 查找购买车列表
	 * @param weiId
	 * @return
	 */
	List<TShoppingCar> getTShoppingCarList(long weiId);
	/**
	 * 修改购物车状态
	 * @param sCID
	 * @return
	 */
	int updateTShoppingCarStatus(long sCID,short status);
	/**
	 * 删除购物车
	 * @param scid
	 * @param weiId
	 * @return
	 */
	int delTShoppingCar(long scid,long weiId);
	/**
	 * 修改购物车的个数
	 */
	int updateTShoppingCar(long scid,int count);
	/**
	 * 修改购物车的价格
	 */
	int updateTShoppingCarPrice(List<Long> scidList,double price);
	/**
	 * 购物车数量
	 * @param weiId
	 * @return
	 */
	List<Object[]> getTShoppingCarCountByState(long weiId,short status);
	/**
	 * 购物车列表
	 * @param weiId
	 * @param scidList
	 * @return
	 */
	List<TShoppingCar> getTShoppingCarList(long weiId,List<Long>scidList);
	
	PProducts getProductByID(Long productID);
}
