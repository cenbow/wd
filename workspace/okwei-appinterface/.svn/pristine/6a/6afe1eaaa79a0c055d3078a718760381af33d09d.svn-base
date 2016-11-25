package com.okwei.appinterface.service;

import java.util.List;
import java.util.Map;

public interface ISimpleTotalService {
	
	/**
	 * 1 查询平台号或品牌号下代理商的订单排名
	 * @param weiId 平台号或品牌号卖家微店号
	 * @param 身份类型（待定）
	 * @param dateType 日期类型（非必需,1为七天、2为30天、3为累计）
	 * @param pageIndex 当前请求页
	 * @param pageSize 每页个数
	 * @return map
	 * @author zhangmaolin 
	 **/
	public Map<String,Object> queryAgentOrderRank(Long weiId, short dateType, int pageIndex, int pageSize) ;
	
	/**
	 * 2 查询下属落地店销量排名
	 * @param weiId 上级微店号
	 * @param weiType 上级类型（1平台号，2代理商）
	 * @param dateType 日期类型（非必需,1为七天、2为30天、3为累计）
	 * @param pageIndex 当前请求页
	 * @param pageSize 每页个数
	 * @return list 
	 * @author zhangmaolin
	 **/
	public Map<String,Object> queryGroundOrderRank(Long weiId, short weiType, short dateType, int pageIndex, int pageSize) ;
	
	/**
	 * 产品热销前三名 
	 * @param weiId 上级微店号
	 * @param weiType 上级类型
	 * @param dateType 日期类型（非必需,1为七天、2为30天、3为累计）
	 * @param pageIndex 当前请求页
	 * @param pageSize 每页个数
	 * @return list
	 * @author zhangmaolin
	 **/
	public Map<String,Object> queryHotRank(Long weiId, short weiType, short dateType, int pageIndex, int pageSize);
	
	/**
	 * 查询库存预警
	 * @param weiId 上级ID 
	 * @param pageIndex 当前请求页
	 * @param pageSize 每页个数
	 * @return list
	 * @author zhangmaolin  
	 **/
	public Map<String,Object> queryStockAlert(Long weiId, short weiType, int pageIndex, int pageSize);
	
	
	/**
	 * 查询代理商的供货商（即代理商的上级 9） 
	 * @param weiId 登录人微店号
	 * @param weiType 登录人类型
	 * @param pageIndex 请求页
	 * @param pageSize 每页记录数
	 **/
	public Map<String,Object> querySupplyList(Long weiId, short weiType, int pageIndex, int pageSize) ;


}
