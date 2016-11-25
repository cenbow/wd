package com.okwei.company.service;

import java.util.List;

import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TMarketImgs;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.company.bean.vo.BatchMarket;
import com.okwei.company.bean.vo.BatchMarketVO;
import com.okwei.company.bean.vo.ProductInfoVO;
import com.okwei.company.bean.vo.WholesaleCount;
import com.okwei.company.bean.vo.WholesaleList;
import com.okwei.service.IBaseService;

public interface IWholesaleService extends IBaseService{
    /**
     * 获取批发市场的数量
     * @return
     */
    public WholesaleCount getWholesaleCount();
    /**
     * 获取批发市场推荐的信息
     * @return
     */
    public List<WholesaleList> getWholesaleLists();
    /**
     * 获取批发市场推荐的信息(改)
     * @return
     */
    public List<WholesaleList> getWholesaleListss();

    /**
     * 批发市场信息
     * @param m
     * @return
     * @throws Exception
     */
	public BatchMarketVO getBatchMarketVOById(Integer m,int type) throws Exception;

	/**
	 * 根据市场id获得市场图集
	 * @param bmid
	 * @return
	 */
	public List<TMarketImgs> getMarketImgsListByBmid(Integer bmid) throws Exception;

	public PageResult<UBatchSupplyer> getUBatchSupplyerList(Limit buildLimit, Integer bmid) throws Exception;

	public PageResult<ProductInfoVO> getUBatchSupplyerProductList(
			Limit buildLimit, Integer bmi,Long weiNod) throws Exception;
	/**
     * 获取批发市场list
     * 
     * @param code
     * @param level
     * @param id
     * @return
     */
    public WholesaleCount getWholesaleCount(String code);

    /**
     * 获取批发市场列表
     * 
     * @param limit
     * @return
     */
    public PageResult<BatchMarket> getWholesaleList(Limit limit, String code, String id);
    
    /**
     * 普通微店主单个产品上架
     * @param productid
     * @param classid
     * @param weiId
     * @return
     */
  	public boolean updateShevles(long productid,int classid,long weiId) throws Exception;
  	/**
  	 * 商家上架单个产品
  	 * @param productid
  	 * @param classid
  	 * @param weiId
  	 * @param batchPrice
  	 * @return
  	 */
  	public boolean updateShevlesWithBatchPrice(long productid,int classid,long weiId,String batchPrice) throws Exception;
  	
	public List<PShopClass> getShopClassList(long weiID);

}
