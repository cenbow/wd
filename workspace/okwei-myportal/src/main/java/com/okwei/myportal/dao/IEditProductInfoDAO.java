package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PParamKey;
import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PParamValues;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.domain.PPreOrder;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProductImg;
import com.okwei.bean.domain.PProductKeyWords;
import com.okwei.bean.domain.PProductParamKv;
import com.okwei.bean.domain.PProductSellKey;
import com.okwei.bean.domain.PProductSellValue;
import com.okwei.bean.domain.PProductStyleKv;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShelverCount;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.dao.IBaseDAO;


public interface IEditProductInfoDAO extends IBaseDAO {

	
	/**
	 * 获取云商信息
	 * @param supplyID 微店号
	 * @return
	 */
	public UYunSupplier getYunSupplier(long supplyID);
	
	/**
	 * 获取批发号信息
	 * @param supplyID 微店号
	 * @return
	 */
	public UBatchSupplyer getBatchSupplyer(long supplyID);
	
	/**
	 * 获取供应商信息
	 * @param supplyID
	 * @return
	 */
	public USupplyer getSupplyer(long supplyID);
	
	/**
	 * 获取供应商今天发布的商品
	 * @param supplyID 微店号
	 * @return
	 */
	public long getTodayCreateProduct(long supplyID);
	
	/**
	 * 获取供应商发布的商品总数
	 * @param supplyID
	 * @return
	 */
	public long getTotalCreateProduct(long supplyID);
	
	/**
	 * 获取商品信息
	 * @param productID
	 * @return
	 */
	public PProducts getProduct(long productID);
	
	/**
	 * 获取关键词列表
	 * @param productID
	 * @return
	 */
	public List<PProductKeyWords> getKeyWordList(long productID);
	
	/**
	 * 获取商品属性列表
	 * @param productID
	 * @return
	 */
	public List<PProductParamKv> getParamList(long productID);
	
	/**
	 * 获取商品图片集
	 * @param productID
	 * @return
	 */
	public List<PProductImg> getImgList(long productID);
	
	/**
	 * 获取销售属性Key列表
	 * @param productID
	 * @return
	 */
	public List<PProductSellKey> getSellKeyList(long productID);
	
	/**
	 * 获取销售属性Value列表
	 * @param productID
	 * @return
	 */
	public List<PProductSellValue> getSellValueList(long productID);
	
	/**
	 * 获取商品款式列
	 * @param productID
	 * @return
	 */
	public List<PProductStyles> getStyleList(long productID);
	
	/**
	 * 获取商品款式键值对列表
	 * @param productID
	 * @return
	 */
	public List<PProductStyleKv> getStyleKvList(long productID);
	
	/**
	 * 获取商品预定价格
	 * @param productID
	 * @return
	 */
	public PPreOrder getPreOrder(long productID);
	
	/**
	 * 获取商品批发阶梯价格
	 * @param productID
	 * @return
	 */
	public List<PProductBatchPrice> getBatchPriceList(long productID);
	
	/**
	 * 获取供应商邮费模板
	 * @param productID
	 * @return
	 */
	public List<PPostAgeModel> getPostAgeModelList(long supplyID);
	
	/**
	 * 获取供应商商品分类列表
	 * @param supplyID 供应商微店号
	 * @param level 店铺分类等级  
	 * @return
	 */
	public List<PShopClass> getShopClasseList(long supplyID,Short level);
	
	/**
	 * 获取店铺分类模板
	 * @param supplyID
	 * @param cName
	 * @return
	 */
	public long getIsHaveShopName(long supplyID,String sName);
	
	/**
	 * 获取系统商品类型信息
	 * @param classID
	 * @return
	 */
	public PProductClass getProductClass(int classID);
	
	/**
	 * 获取参数模板信息
	 * @param mID
	 * @return
	 */
	public PParamModel getParamModel(int mID);
	
	/**
	 * 判断用户是否已经存在该参数模板名称
	 * @param supplyID
	 * @param pmName
	 * @return
	 */
	public long getIsHavePMName(long supplyID,String pmName);
	
	/**
	 * 获取参数模板的Key列表
	 * @param tempID
	 * @return
	 */
	public List<PParamKey> getParamKeyList(int tempID);
	
	/**
	 * 获取参数模板的Key对应的Value列表
	 * @param keys
	 * @return
	 */
	public List<PParamValues> getParamValueList(Integer[] keys);
	
	/**
	 * 保存用户分类
	 * @param psClass
	 * @return 分类ID
	 */
	public int saveShopClass(PShopClass psClass);
	
	/**
	 * 保存参数模板
	 * @param paramModel
	 * @return
	 */
	public int saveParamModel(PParamModel paramModel);
	
	/**
	 * 保存参数模板Key
	 * @param keyModel
	 * @return
	 */
	public int saveParamKey(PParamKey keyModel);
	
	/**
	 * 获取参数模板key实体
	 * @param keyID
	 * @return
	 */
	public PParamKey getParamKeyModel(Integer keyID);
	
	/**
	 * 保存参数模板值
	 * @param valueModel
	 * @return
	 */
	public int saveParamValue(PParamValues valueModel);
	
	/**
	 * 获取参数模板值实体
	 * @param valueID
	 * @return
	 */
	public PParamValues getParamValueModel(Integer valueID);
	
	/**
	 * 获取商品实体
	 * @param productID
	 * @return
	 */
	public PProducts getProductModel(long productID);
	
	/**
	 * 删除商品的图片列表
	 * @param productID
	 */
	public int deletePImgs(long productID);
	
	/**
	 * 删除商品的关键词
	 * @param productID
	 * @return
	 */
	public int deletePKeyWrods(long productID);
	
	/**
	 * 删除商品的参数
	 * @param productID
	 * @return
	 */
	public int deletePParams(long productID);
	
	/**
	 * 删除商品的款式
	 * @param productID
	 * @return
	 */
	public int deletePStyles(long productID);
	
	/**
	 * 删除商品款式
	 * @param productID
	 * @return
	 */
	public int deletePStyleKvs(long productID);
	
	/**
	 * 删除商品款式key
	 * @param productID
	 * @return
	 */
	public int deletePStyleKeys(long productID);
	
	/**
	 * 删除商品的款式Value
	 * @param productID
	 * @return
	 */
	public int deletePStyleValues(long productID);	
	
	/**
	 * 删除上游信息表
	 * @param supplyID
	 * @param productID
	 * @return
	 */
	public int deleteOwnerMessage(long supplyID,long productID);
	
	/**
	 * 删除商品批发价
	 * @param productID
	 * @return
	 */
	public int deleteBatchPrice(long productID);
	
	/**
	 * 保存规格key
	 * @param model
	 * @return
	 */
	public long saveSellKey(PProductSellKey model);
	
	
	/**
	 * 保存规格Value
	 * @param model
	 * @return
	 */
	public long saveSellValue(PProductSellValue model);
	
	/**
	 * 保存商品规格
	 * @param model
	 * @return
	 */
	public long saveProductStyle(PProductStyles model);
	
	/**
	 * 保存商品规格Key value
	 * @param model
	 * @return
	 */
	public long saveStyleKV(PProductStyleKv model);
	
	/**
	 * 保存商品的批发价格
	 * @param model
	 * @return
	 */
	public long saveBatchPrice(PProductBatchPrice model);
	
	/**
	 * 获取商品上架表
	 * @return
	 */
	public PClassProducts getClassProduct(long weiID,long productID,long supplyID);
		
	/**
	 * 下架所有上架的该商品上架数据
	 * @param productID
	 * @return
	 */
	public int updateClassProduct(long productID);
	
	/**
	 * 下架供应商自己的上架数据
	 * @param productctID
	 * @param weiID
	 * @return
	 */
	public int deleteClassProduct(long productID,long weiID);
	
	/**
	 * 获取目前使用上架功能的总人数
	 * @return
	 */
	public PShelverCount getPShelver();	
	
	/**
	 * 删除上架表的  批发价区间
	 * @param ID
	 * @return
	 */
	public int deleteShevleBatchPrice(long ID);

	public boolean getIsHaveShopName(long weiID, List<String> list);

	public List<PShopClass> saveShopClassList(long weiID, String scName,
			List<String> list);
}