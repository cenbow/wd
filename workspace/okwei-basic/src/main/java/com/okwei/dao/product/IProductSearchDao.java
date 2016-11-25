package com.okwei.dao.product;

import java.util.List;

import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.vo.activity.ActProductInfo;

public interface IProductSearchDao {
	
	/**
	 * 根据产品ID获取产品（取缓存）
	 * zy
	 * @param productID
	 * @return
	 */
	public PProducts getPProducts(Long productId);
	/**
	 * 将产品 缓存
	 * @param productId
	 */
	public void setPProduct(Long productId);
	/**
	 * 设置产品缓存
	 * @param product
	 */
	public void setPProduct(PProducts product);
	/**
	 * 获取产品列表
	 * @param productids
	 * @return
	 */
	public List<PProducts> find_PProducts(List<Long>productids);
	

	/**
	 * 判断产品是否参加活动(展示中、未开始的抢购活动产品)
	 * zy(2016-6-30)
	 * @param productId
	 * @return （null没有参加，如果不为Null说明参加了活动，通过beginTime,endTime判断是否在活动中,看type类型：1：表示全返，0或null表示普通限时抢购）
	 */
	public ActProductInfo get_ProductAct(Long productId);
	/**
	 * 查询产品是否在活动中
	 * @param productId
	 * @param isGoing
	 * @return
	 */
	public AActProductsShowTime getAActProductsShowTime(Long productId, boolean isGoing) ;
	
	//----------------------------------------------------------
	/**
	 * 产品下架、不通过、等临时操作，清除缓存区的信息
	 * zy (2016-6-30)
	 * PS：活动产品
	 * （ 主要用户CTS操作）
	 * @param productId
	 */
	public void del_redis_productAct(Long productId);
	/**
	 * 主动设置活动产品 至缓存区
	 * zy (2016-6-30)
	 * PS:活动产品
	 * （建议CTS操作时使用）
	 * @param productId
	 */
	public void set_redis_productAct(Long productId);
}
