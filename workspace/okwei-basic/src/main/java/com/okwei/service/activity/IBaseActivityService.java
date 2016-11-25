package com.okwei.service.activity;

import java.util.Date;
import java.util.List;

import com.okwei.bean.domain.AActProducts;
import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActSupplier;
import com.okwei.bean.domain.AActivity;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.AActivityTimespans;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.SaleAreaModel;
import com.okwei.bean.vo.activity.AActivityProExtend;
import com.okwei.bean.vo.activity.AActivityProductsResult;
import com.okwei.bean.vo.activity.ActProductShowResult;
import com.okwei.bean.vo.activity.FlashSaleActivityVO;
import com.okwei.bean.vo.activity.SaleActivityProductModel;
import com.okwei.common.PageResult;

public interface IBaseActivityService   {

	/**
	 * 限时抢购 -》 所有抢购活动列表
	 * zy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResult<AActivity> find_ActivityList(int pageIndex,int pageSize);
	
	/**
	 * 限时抢购 =》限时抢购活动列表
	 * zy
	 * WEB、app（M04）、wap 前端展示用
	 * @return
	 */
	public List<FlashSaleActivityVO> flashSaleList();
	/**
	 * 购物首页 =>获取显示抢购模块
	 * zy
	 * @return
	 */
	public SaleAreaModel getSaleAreaModel(Long weiid);
	/**
	 * 限时抢购 =》限时抢购产品列表（前端展示用）
	 * zy
	 * Web、APP、wap前端展示用
	 * @param itemId 活动时间段
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResult<SaleActivityProductModel> flashSaleProductList( int itemId, int pageIndex, int pageSize) ;
	/**
	 * 前端展示 限时抢购 产品列表
	 * zy
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param pageIndex 
	 * @param pageSize
	 * @return
	 */
	public PageResult<SaleActivityProductModel> find_ActProductlist(Date startTime,Date endTime, int pageIndex, int pageSize) ;
	/**
	 * 限时抢购 =》 活动产品列表（IBS供应商查看产品报名情况用）
	 * zy
	 * IBS用
	 * @param actId 活动ID
	 * @param sellerId 供应商ID(不传，则查询多个供应商的报名产品)
	 * @param pageIndex 
	 * @param pageSize
	 */
	public PageResult<AActivityProducts> find_ApplyProductListBySellerID(Long actId, Long sellerId,int pageIndex,int pageSize);
	/**
	 * 限时抢购 =》活动产品列表（IBS供应商查看产品报名情况用）
	 * @param actId 活动id
	 * @param sellerId 供应商id
	 * @param state 产品审核状态（获取全部 输入Null）
	 * @param pageIndex 
	 * @param pageSize
	 * @return
	 */
	public PageResult<AActivityProducts> find_ApplyProductListBySellerID(Long actId, Long sellerId,Short state,int pageIndex,int pageSize);
	/**
	 * 限时抢购 =》卖家报名产品
	 * zy
	 * @param actId
	 * @param sellerId
	 * @param state
	 * @return
	 */
	public List<AActivityProducts> find_AActivityProductsBySellerID(Long actId, Long sellerId,Short state);
	
	/**
	 * 限时抢购 =》供应商 抢购活动的报名产品数量
	 * zy
	 * @param actId
	 * @param sellerId
	 * @return
	 */
	public long count_AActivityProducts(Long actId, Long sellerId,Short state);
	/**
	 * 限时抢购 =》抢购活动信息
	 * zy
	 * @param actID
	 * @return
	 */
	public AActivity getAActivity(Long actID);
	/**
	 * 获取活动报名的商家信息
	 * zy
	 * @param actID
	 * @param weiid
	 * @return
	 */
	public AActSupplier getAActSupplier(Long actID,Long weiid);
	/**
	 * 限时抢购 =》获取报名产品详情
	 * zy
	 * @param proActID
	 * @return
	 */
	public AActivityProducts getAActivityProducts(Long proActID);
	/**
	 * 限时抢购 =》检测 产品是否在限时抢购活动中
	 * zy(2016-5-08)
	 * @param productId 
	 * @param isGoing （true:是否正在抢购，false：拿到正在抢购 或 即将开始的限时抢购）
	 * @return
	 */
	public AActProducts getAActProducts(Long productId, boolean isGoing);
	/**
	 * 限时抢购 =》检测 产品是否在限时抢购活动中
	 * zy (2016-5-12)
	 * 备注：检索数据源较少（建议使用）
	 * @param productId
	 * @param isGoing
	 * @return
	 */
	public AActProductsShowTime getAActProductsShowTime(Long productId, boolean isGoing);
	/**
	 * 
	 * 限时抢购 =》获取报名产品详情
	 * @param proActID
	 * @return
	 */
	public AActivityProductsResult getAActivityProductsResult(Long proActID);
	/**
	 * 
	 * 限时抢购  =》 产品报名（产品修改）
	 * zy
	 * @param list
	 * @return
	 */
	public ReturnModel editAActivityProducts(Long weiid, List<AActivityProducts> list);
	/**
	 * 限时抢购(升级版，保存 参与活动的供应商)  =》 产品报名（产品修改）
	 * @param weiid
	 * @param amount
	 * @param list
	 * @return
	 */
	public ReturnModel editAActivityProducts(Long weiid,Double amount, List<AActivityProducts> list);

	/**
	 *  限时抢购  =》获取活动 每日展示时间段（一般有三个时间段）
	 * @return
	 */
	public List<AActivityTimespans> find_ActTimeSpanslist();
	/*------------------------------*/
	/**
	 * 限时抢购  =》设置产品 展示时间
	 * zy
	 * CTS 
	 * @param ids
	 * @param timelist
	 * @return
	 */
	public ReturnModel set_ActProductShowTime(Long weiid,List<Long> ids,List<AActivityProExtend> timelist);
	/**
	 * 限时抢购 =》 产品审核
	 * zy
	 * CTS
	 * @param weiid 审核人
	 * @param actProID 产品申请ID
	 * @param state  枚举 ActProductVerState
	 * @param reason 不通过理由
	 * @return
	 */
	public ReturnModel edit_AActivityProduct(Long weiid,Long actProID,Short state,String reason);
	/**
	 * 获取活动产品列表
	 * @param actId 活动id
	 * @param sellerId 供应商ID
	 * @param state 产品审核状态(全部则为null)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public 	PageResult<AActivityProductsResult> find_ActProductListBySellerID(Long actId, Long sellerId,Short state, int pageIndex,int pageSize);
	/**
	 * 获取活动 产品展示列表
	 * @param sellerid 商家微店号
	 * @param state（0未开始，1进行中，2已结束 ,NULL表示全部）
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResult<AActivityProductsResult> find_ActDetail_prolist(String dateTime,int id,Long sellerid,Long actid, Short state,int pageIndex,int pageSize);
	/**
	 * 修改活动产品数量
	 * @param proActID
	 * @param count
	 * @return
	 */
	public ReturnModel updateCount(Long proActID, Integer count);
	/**
	 * 
	 * @param proActID
	 * @return
	 */
	public AActivityProducts getProducts(Long proActID);
	/**
	 * 删除活动产品
	 * @param proActID
	 * @param actID
	 * @return
	 */
	public ReturnModel deleteActProducts(Long proActID, Long actID);
	/**
	 * 设置排序
	 * @param proActID
	 * @return
	 */
	public ReturnModel updateSort(Long proActID,Short state);
	/**
	 * 删除
	 * @param proActID
	 * @param actID
	 * @return
	 */
	public ReturnModel deleteActivityProducts(Long proActID, Long actID);
	
	
	/*---------------------------------828活动-----------------------------------------------------*/
	/**
	 * 抢购活动列表
	 * @param activiyId 活动id
	 * @param classId 分类id
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResult<ActProductShowResult> find_AActivityProducts(Integer classId,int pageIndex,int pageSize);
} 

