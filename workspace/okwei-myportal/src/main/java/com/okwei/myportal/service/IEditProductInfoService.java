package com.okwei.myportal.service;

import java.util.List;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.myportal.bean.vo.BaseResultVO;
import com.okwei.myportal.bean.vo.EditProductVO;
import com.okwei.myportal.bean.vo.PostAgeModelVO;
import com.okwei.myportal.bean.vo.ProductClassVO;
import com.okwei.myportal.bean.vo.ProductParamModelVO;
import com.okwei.myportal.bean.vo.ShopClassVO;



public interface IEditProductInfoService {
	
	/**
	 * 获取用户发布产品的权限
	 * @param weiID
	 * @return
	 */
	public BaseResultVO getPower(long weiID);
	
	/**
	 * 编辑产品信息
	 * @return
	 */
	public BaseResultVO editProductInfo(EditProductVO product);
	
	/**
	 * 获取产品详细信息
	 * @param productID
	 * @return
	 */
	public EditProductVO getProductInfo(long productID,Integer pubProductType);
	
	/**
	 * 获取商品实体
	 * @param productID
	 * @return
	 */
	public PProducts getProductModel(long productID);
	
	/**
	 * 获取商品分类信息
	 * @return
	 */
	public ProductClassVO getClassInfo(int classID);
		
	/**
	 * 获取参数模板
	 * @param tempID 参数模板ID
	 * @return
	 */
	public ProductParamModelVO getParamModel(int tempID);
	
	/**
	 * 获取商品的参数模板与参数数据
	 * @param mid
	 * @param productID
	 * @return
	 */
	public ProductParamModelVO getProductParamModel(int mid,long productID);
	
	/**
	 * 判断是否是自己的商品
	 * @param productID
	 * @param supplyID
	 * @return
	 */
	public Boolean getIsMyProduct(long productID,long supplyID);
	
	/**
	 * 保存店铺分类
	 * @param sc
	 * @return
	 */
	public BaseResultVO saveShopClass(String scName,long weiID);
	
	/**
	 * 获取邮费模板列表
	 * @param weiID
	 * @return
	 */
	public List<PostAgeModelVO> getPostAgeList(long weiID);
	
	/**
	 * 获取店铺分类列表
	 * @param weiID
	 * @param level
	 * @return
	 */
	public List<ShopClassVO> getShopClassList(long weiID,Short level);
	
	/**
	 * 保存参数模板
	 * @param weiID
	 * @param classID
	 * @return
	 */
	public BaseResultVO saveProductParam(ProductParamModelVO paramModel);
	
	/**
	 * 正常展示的商品 供应商自动上架
	 * @param pro
	 * @param libp
	 */
	public void setShelveMyself(long supplyerWeiID,long productID,long sID, List<PProductBatchPrice> libp);

	public PBrand getBrandById(Integer brandId);

	/**
	 * 根据微店号查找招商需求列表
	 * @param weiId
	 * @param state
	 * @return
	 */
	List<USupplyDemand> getUSupplyDemandListByWeiId(Long weiId,Short state);

	/**
	 * 保存店铺分类 
	 * @param scName 一级分类
	 * @param scJson 二级分类集合
	 * @param weiID 店铺id
	 * @return
	 */
	public ReturnModel saveShopClassList(String scName, String scJson,
			long weiID);

	public PShopClass getShopClass(Long weiID, String scName);
}
