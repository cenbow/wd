package com.okwei.myportal.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PParamKey;
import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PParamValues;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.domain.PPreOrder;
import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProductImg;
import com.okwei.bean.domain.PProductKeyWords;
import com.okwei.bean.domain.PProductParamKv;
import com.okwei.bean.domain.PProductRecordMsg;
import com.okwei.bean.domain.PProductRelation;
import com.okwei.bean.domain.PProductSellKey;
import com.okwei.bean.domain.PProductSellValue;
import com.okwei.bean.domain.PProductStyleKv;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProductSup;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShelverCount;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TProductIndex;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UOwnerMessage;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.PubProductTypeEnum;
import com.okwei.bean.enums.SupplierStatusEnum;
import com.okwei.bean.enums.SupplierTypeEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.order.ProductBatchPriceVO;
import com.okwei.bean.vo.order.ProductImgVO;
import com.okwei.bean.vo.order.ProductSellValueVO;
import com.okwei.bean.vo.order.ProductStyleKVVO;
import com.okwei.bean.vo.product.ProductParamVO;
import com.okwei.bean.vo.product.ProductSellKeyVO;
import com.okwei.bean.vo.product.ProductStylesVO;
import com.okwei.common.JsonUtil;
import com.okwei.myportal.bean.enums.BaseResultStateEnum;
import com.okwei.myportal.bean.enums.MsgType;
import com.okwei.myportal.bean.enums.ProductBType;
import com.okwei.myportal.bean.enums.ProductTagType;
import com.okwei.myportal.bean.util.ConfigSetting;
import com.okwei.myportal.bean.vo.BaseResultVO;
import com.okwei.myportal.bean.vo.EditProductVO;
import com.okwei.myportal.bean.vo.PostAgeModelVO;
import com.okwei.myportal.bean.vo.ProductClassVO;
import com.okwei.myportal.bean.vo.ProductParamKeyVO;
import com.okwei.myportal.bean.vo.ProductParamModelVO;
import com.okwei.myportal.bean.vo.ProductParamValueVO;
import com.okwei.myportal.bean.vo.ShopClassVO;
import com.okwei.myportal.dao.IEditProductInfoDAO;
import com.okwei.myportal.service.IEditProductInfoService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.ImgDomain;

@Service
public class EditProductInfoService extends BaseService implements IEditProductInfoService {
	
	@Autowired	
	private IEditProductInfoDAO editProductInfoDAO;
	
	@Override
	public BaseResultVO getPower(long weiID) {
		BaseResultVO result = new BaseResultVO();		
		//产品发布限制  
		// 1.正式的云商 与 批发号 才能发布产品
		UYunSupplier yunSupplier = editProductInfoDAO.getYunSupplier(weiID);
		UBatchSupplyer batchSupplyer = editProductInfoDAO.getBatchSupplyer(weiID);
		
		// 2.判断该供应商 是否被限制发布 暂时不处理
		
		// 3.每个供应商每天最多发布2000
		long todayPNum =  editProductInfoDAO.getTodayCreateProduct(weiID);
		if((yunSupplier !=null && yunSupplier.getStatus() == Short.parseShort(SupplierStatusEnum.PayIn.toString()))  || 
				(batchSupplyer !=null &&	batchSupplyer.getStatus() == Short.parseShort(SupplierStatusEnum.PayIn.toString())))
		{
			if(todayPNum < ConfigSetting.SupplyTodayPNum()){
				result.setState(BaseResultStateEnum.Success);
				return result;
			}else{
				result.setState(BaseResultStateEnum.Failure);
				result.setMessage("您今天发布的商品数量已达到限制:"+ConfigSetting.SupplyTodayPNum());
				return result;
			}

		}
		//4.普通微店 只能发10个
		/*long totalPNum= editProductInfoDAO.getTotalCreateProduct(weiID);
		if(totalPNum <ConfigSetting.NoPaySupplyPNum()){
			result.setState(BaseResultStateEnum.Success);
			return result;
		}else{
			result.setState(BaseResultStateEnum.Failure);
			result.setMessage("您发布的商品数量已达到限制:"+ConfigSetting.NoPaySupplyPNum());
			return result;
		}*/
		result.setState(BaseResultStateEnum.Success);
		return result;	
		//5.审核通过  未交钱的 云商 与 批发号 总数只能发10个。

//		if((yunSupplier!=null && yunSupplier.getStatus() == Short.parseShort(SupplierStatusEnum.Pass.toString()))  || 
//				(batchSupplyer !=null &&	batchSupplyer.getStatus() == Short.parseShort(SupplierStatusEnum.Pass.toString())))		
//		{
//			if(totalPNum <ConfigSetting.NoPaySupplyPNum()){
//				result.setState(BaseResultStateEnum.Success);
//				return result;
//			}else{
//				result.setState(BaseResultStateEnum.Failure);
//				result.setMessage("您发布的商品数量已达到限制:"+ConfigSetting.SupplyTodayPNum());
//				return result;
//			}
//		}
		
/*		result.setState(BaseResultStateEnum.Failure);
		result.setMessage("您没有发布商品的权限！");
		return result;*/
	}

	@Override
	public EditProductVO getProductInfo(long productID,Integer pubProductType) {
		EditProductVO baseProduct = new EditProductVO();
		//子账号供应商
		if (Integer.valueOf(PubProductTypeEnum.SubAccount.toString()).equals(pubProductType)) {
			//1.商品主表
			PProductSup productSub = editProductInfoDAO.get(PProductSup.class, productID);
			if(productSub ==null)
			{
				return baseProduct;
			}
			baseProduct.setProductId(productSub.getProductId());
			baseProduct.setDefaultPrice(Double.valueOf(new DecimalFormat("######0.00").format(productSub.getDefaultPrice()==null ? 0:productSub.getDefaultPrice())));
			baseProduct.setAdvicePrice(Double.valueOf(new DecimalFormat("######0.00").format(productSub.getAdvicePrice()==null ? 0:productSub.getAdvicePrice())));
			baseProduct.setProductImg(ImgDomain.GetFullImgUrl(productSub.getDefaultImg()));
			baseProduct.setProductMinTitle(productSub.getProductMinTitle());
			baseProduct.setProductTitle(productSub.getProductTitle());
			baseProduct.setSupplyerWeiid(productSub.getWeiID());
			baseProduct.setCount(productSub.getStock());
			baseProduct.setPcdes(productSub.getPcdes());
			baseProduct.setAppdes(productSub.getAppdes());
			baseProduct.setType(1);
			baseProduct.setBrandId(productSub.getBrandID());
		} else {
			//1.商品主表
			PProducts product = editProductInfoDAO.getProduct(productID);
			if(product ==null)
			{
				if (Integer.valueOf(PubProductTypeEnum.Platform.toString()).equals(pubProductType)) {
					PProductSup productSub = editProductInfoDAO.get(PProductSup.class, productID);
					if (productSub ==  null) {
						return baseProduct;
					}
					baseProduct.setProductId(productSub.getProductId());
					baseProduct.setDefaultPrice(Double.valueOf(new DecimalFormat("######0.00").format(productSub.getDefaultPrice()==null ? 0:productSub.getDefaultPrice())));
					baseProduct.setAdvicePrice(Double.valueOf(new DecimalFormat("######0.00").format(productSub.getAdvicePrice()==null ? 0:productSub.getAdvicePrice())));
					baseProduct.setProductImg(ImgDomain.GetFullImgUrl(productSub.getDefaultImg()));
					baseProduct.setProductMinTitle(productSub.getProductMinTitle());
					baseProduct.setProductTitle(productSub.getProductTitle());
					baseProduct.setSupplyerWeiid(productSub.getWeiID());
					baseProduct.setCount(productSub.getStock());
					baseProduct.setPcdes(productSub.getPcdes());
					baseProduct.setAppdes(productSub.getAppdes());
					baseProduct.setType(1);
					baseProduct.setBrandId(productSub.getBrandID());
				}
			} else {
				baseProduct.setCommission(Double.valueOf(new DecimalFormat("######0.00").format(product.getDefaultConmision()==null ? 0:product.getDefaultConmision() )));
				baseProduct.setFreightId(product.getFreightId());
				baseProduct.setPrice(Double.valueOf(new DecimalFormat("######0.00").format(product.getDefaultPrice()==null ? 0:product.getDefaultPrice())));
				baseProduct.setRetailPrice(Double.valueOf(new DecimalFormat("######0.00").format(product.getDefaultPrice()==null ? 0:(product.getDefaultPrice()-product.getDefaultConmision()) )));
				baseProduct.setProductId(product.getProductId());
				baseProduct.setProductImg(ImgDomain.GetFullImgUrl(product.getDefaultImg()));
				baseProduct.setProductMinTitle(product.getProductMinTitle());
				baseProduct.setProductTitle(product.getProductTitle());
				baseProduct.setSid(product.getSid());
				baseProduct.setSupplyerWeiid(product.getSupplierWeiId());
				baseProduct.setClassId(product.getClassId());
				baseProduct.setmID(product.getMid());
				baseProduct.setCount(product.getCount());
				baseProduct.setPcdes(product.getPcdes());
				baseProduct.setAppdes(product.getAppdes());
				baseProduct.setType(0);
				baseProduct.setBrandId(product.getBrandId());
				baseProduct.setDisplayPrice(Double.valueOf(new DecimalFormat("######0.00").format(product.getOriginalPrice()==null ? 0:product.getOriginalPrice())));
				//招商需求
				UDemandProduct dp =  editProductInfoDAO.getUniqueResultByHql("from UDemandProduct where productId=?", new Object[]{productID});
				if (dp != null) {
					baseProduct.setDemandId(dp.getDemandId());
				}
				//2.商品属性
				List<PProductParamKv> paramList = editProductInfoDAO.getParamList(productID);
				if(paramList != null && paramList.size()>0)
				{
					List<ProductParamVO> paramVOList = new ArrayList<ProductParamVO>();
					for (PProductParamKv item : paramList) {
						ProductParamVO paramVO = new ProductParamVO();
						paramVO.setParamName(item.getParamName());
						paramVO.setParamValue(item.getParamName());
						paramVO.setSysAVID(item.getSysAVID());
						paramVO.setSysAttributeID(item.getSysAttributeID());
						paramVO.setKvid(item.getKvid());
						
						paramVOList.add(paramVO);
					}
					baseProduct.setParamList(paramVOList);
				}
				if (Integer.valueOf(PubProductTypeEnum.Ordinary.toString()).equals(pubProductType) 
						|| Integer.valueOf(PubProductTypeEnum.Supplyer.toString()).equals(pubProductType)) {
					//5.批发阶梯价格
					List<PProductBatchPrice> batchPriceList = editProductInfoDAO.getBatchPriceList(productID);
					if(batchPriceList !=null && batchPriceList.size()>0)
					{
						List<ProductBatchPriceVO> batchPriceVOList = new ArrayList<ProductBatchPriceVO>();
						for (PProductBatchPrice item : batchPriceList) {
							ProductBatchPriceVO bPriceVO = new ProductBatchPriceVO();
							bPriceVO.setBid(item.getBid());
							bPriceVO.setCount(item.getCount());
							bPriceVO.setPirce(Double.valueOf(new DecimalFormat("######0.00").format(item.getPirce()==null ? 0:item.getPirce())));
							bPriceVO.setStylesId(item.getStylesId());
							
							batchPriceVOList.add(bPriceVO);
						}
						baseProduct.setBatchPriceList(batchPriceVOList);			
					}
				}
				if (Integer.valueOf(PubProductTypeEnum.Platform.toString()).equals(pubProductType) 
						|| Integer.valueOf(PubProductTypeEnum.Brand.toString()).equals(pubProductType)) {
					//代理价落地价
					PProductRelation relation = editProductInfoDAO.get(PProductRelation.class, productID);
					if (relation != null) {
						baseProduct.setProxyPrice(Double.valueOf(new DecimalFormat("######0.00").format(relation.getProxyPrice()==null ? 0:relation.getProxyPrice())));
						baseProduct.setFloorPrice(Double.valueOf(new DecimalFormat("######0.00").format(relation.getFloorPrice()==null ? 0:relation.getFloorPrice())));
					}
					if (product.getSid() != null && product.getSid() > 0) {
						PShopClass shopclass = editProductInfoDAO.get(PShopClass.class, product.getSid());
						if (shopclass != null) {
							if (Short.valueOf("2").equals(shopclass.getLevel())) {
								if (shopclass.getParetId() != null && shopclass.getParetId() > 0) {
									PShopClass parentclass = editProductInfoDAO.get(PShopClass.class, shopclass.getParetId());
									if (parentclass != null) {
										baseProduct.setParentSid(parentclass.getSid());
									}
								}
							}
						}
					}
					
				}
				if(Integer.valueOf(PubProductTypeEnum.BrandSupplyer.toString()).equals(pubProductType)){
					baseProduct.setSupplyPrice(product.getSupplyPrice());
				}
			}
		}
		
		//6.产品关键词
		List<PProductKeyWords> keyWordList = editProductInfoDAO.getKeyWordList(productID);
		if(keyWordList !=null && keyWordList.size()>0)
		{
			String keywords = "";
			for (PProductKeyWords item : keyWordList) {
				keywords += item.getKeyWord() +" ";
			}
			baseProduct.setKeyWords(keywords);
		}
		
		//3.商品图片
		List<PProductImg> imgList = editProductInfoDAO.getImgList(productID);
		if(imgList !=null && imgList.size()>0)
		{
			List<ProductImgVO> imgVOList = new ArrayList<ProductImgVO>();
			for (PProductImg item : imgList) {
				if(item.getImgPath() !=null && !"".equals(item.getImgPath()))
				{
					ProductImgVO imgVO = new ProductImgVO();
					imgVO.setImgPath(ImgDomain.GetFullImgUrl(item.getImgPath()));
					imgVO.setProductImgId(item.getProductImgId());
				
					imgVOList.add(imgVO);
				}
			}
			baseProduct.setImgList(imgVOList);
		}
		//4.商品销售属性
		//a.属性Key
		List<PProductSellKey> sellKeyList = editProductInfoDAO.getSellKeyList(productID);
		List<PProductSellValue> sellValueList = editProductInfoDAO.getSellValueList(productID);
		if(sellKeyList != null && sellKeyList.size()>0)
		{
			List<ProductSellKeyVO> sellkeyVOList = new ArrayList<ProductSellKeyVO>();
			for (PProductSellKey keyitem : sellKeyList) {
				ProductSellKeyVO sellKeyVO = new ProductSellKeyVO();
				sellKeyVO.setAttributeId(keyitem.getAttributeId());
				sellKeyVO.setAttributeName(keyitem.getAttributeName());
				sellKeyVO.setSort(keyitem.getSort());
				if(sellValueList !=null && sellValueList.size()>0)
				{
					List<ProductSellValueVO> sellValueVOList = new ArrayList<ProductSellValueVO>();
					for (PProductSellValue valueItem : sellValueList) {						
						if((long)keyitem.getAttributeId() == (long)valueItem.getAttributeId())
						{
							ProductSellValueVO sellValueVO = new ProductSellValueVO();
							sellValueVO.setAttributeId(valueItem.getAttributeId());
							sellValueVO.setClassId(valueItem.getClassId());
							sellValueVO.setDefaultImg(ImgDomain.GetFullImgUrl(valueItem.getDefaultImg()));
							sellValueVO.setKeyId(valueItem.getKeyId());
							sellValueVO.setProductId(valueItem.getProductId());
							sellValueVO.setValue(valueItem.getValue());
							
							sellValueVOList.add(sellValueVO);
						}
					}
					sellKeyVO.setSellValueList(sellValueVOList);
				}				
				sellkeyVOList.add(sellKeyVO);
			}
			baseProduct.setSellKeyList(sellkeyVOList);
		}
		if (!Integer.valueOf(PubProductTypeEnum.SubAccount.toString()).equals(pubProductType)) {
			//c. 款式 属性Kv
			List<PProductStyles> styleList = editProductInfoDAO.getStyleList(productID);
			List<PProductStyleKv> styleKVList = editProductInfoDAO.getStyleKvList(productID);
			if(styleList !=null && styleKVList.size()>0)
			{
				List<ProductStylesVO> stylesVOList = new ArrayList<ProductStylesVO>();
				for (PProductStyles styleItem : styleList) {
					ProductStylesVO stylesVO = new ProductStylesVO();
					stylesVO.setBussinessNo(styleItem.getBussinessNo());
					stylesVO.setConmision(styleItem.getConmision());
					stylesVO.setCount(styleItem.getCount());
					stylesVO.setDefaultImg(styleItem.getDefaultImg());
					stylesVO.setPrice(Double.valueOf(new DecimalFormat("######0.00").format(styleItem.getPrice()==null ? 0:styleItem.getPrice())));
					stylesVO.setRetailPrice(Double.valueOf(new DecimalFormat("######0.00").format(styleItem.getPrice()==null ? 0:(styleItem.getPrice()-styleItem.getConmision()) )));
					stylesVO.setProxyPrice(Double.valueOf(new DecimalFormat("######0.00").format(styleItem.getAgencyPrice()==null ? 0:styleItem.getAgencyPrice())));
					stylesVO.setFloorPrice(Double.valueOf(new DecimalFormat("######0.00").format(styleItem.getLandPrice()==null ? 0:styleItem.getLandPrice())));
					stylesVO.setStylesId(styleItem.getStylesId());
					stylesVO.setSupplyPrice(Double.valueOf(new DecimalFormat("######0.00").format(styleItem.getSupplyPrice()==null ? 0:styleItem.getSupplyPrice())));
					List<ProductStyleKVVO> styleKVVOList = new ArrayList<ProductStyleKVVO>();
					for (PProductStyleKv kvItem : styleKVList) {
						if((long)kvItem.getStylesId() == (long)styleItem.getStylesId())
						{
							ProductStyleKVVO styleKVVO = new ProductStyleKVVO();
							styleKVVO.setAttributeId(kvItem.getAttributeId());
							styleKVVO.setKeyId(kvItem.getKeyId());
							styleKVVO.setKvid(kvItem.getKvid());
							styleKVVO.setStylesId(kvItem.getStylesId());
							for (PProductSellKey keyItem : sellKeyList) {
								if((long)kvItem.getAttributeId() == (long)keyItem.getAttributeId())
								{
									styleKVVO.setAttrbuteName(keyItem.getAttributeName());
									styleKVVO.setSort(keyItem.getSort());
									break;
								}
							}
							for (PProductSellValue valueItem : sellValueList) {
								if((long)kvItem.getKeyId() == (long) valueItem.getKeyId())
								{
									styleKVVO.setValueName(valueItem.getValue());
								}
							}
							//键值对 添加到列表
							styleKVVOList.add(styleKVVO);
						}
					}
					//款式属性键值对 按 key的Sort 排序
					Collections.sort(styleKVVOList,new Comparator<ProductStyleKVVO>()
							{
						@Override
						public int compare(ProductStyleKVVO item1,ProductStyleKVVO item2)
						{					
							return item1.getSort().compareTo(item2.getSort());
						}
							});
					
					//设置款式的属性键值对
					stylesVO.setStyleKVList(styleKVVOList);
					//款式添加到款式列表中
					stylesVOList.add(stylesVO);
				}
				baseProduct.setStyleList(stylesVOList);
			}
		}
		return baseProduct;
	}

	@Override
	public ProductClassVO getClassInfo(int classID) {
		if(classID<1)
		{
			return null;
		}
		PProductClass pClass = editProductInfoDAO.getProductClass(classID);
		if(pClass !=null)
		{
			ProductClassVO result = new ProductClassVO();
			result.setClassID(pClass.getClassId());
			result.setClssName(pClass.getClassName());
			
			PProductClass pClass2 = editProductInfoDAO.getProductClass(pClass.getParentId());
			if(pClass2 !=null)
			{
				result.setTwoClassID(pClass2.getClassId());
				result.setTwoClassName(pClass2.getClassName());
				
				PProductClass pClass1 = editProductInfoDAO.getProductClass(pClass2.getParentId());
				if(pClass1 !=null)
				{
					result.setOneClassID(pClass1.getClassId());
					result.setOneClassName(pClass1.getClassName());
				}
			}
			
			return result;
		}
			
		return null;
	}
	
	@Override
	public List<PostAgeModelVO> getPostAgeList(long weiID) {
		List<PPostAgeModel> modelList = editProductInfoDAO.getPostAgeModelList(weiID);
		
		if(modelList !=null && modelList.size()>0)
		{
			List<PostAgeModelVO> result = new ArrayList<PostAgeModelVO>();
			for (PPostAgeModel item : modelList) {
				PostAgeModelVO modelVO = new PostAgeModelVO();
				modelVO.setBillingType(item.getBillingType());
				modelVO.setDeliveryAddress(item.getDeliveryAddress());
				modelVO.setDeliverytime(item.getDeliverytime());
				modelVO.setFree(item.getFree());
				modelVO.setFreightId(item.getFreightId());
				modelVO.setPostAgeName(item.getPostAgeName());
				modelVO.setStatus(item.getStatus());
				modelVO.setSupplierWeiId(item.getSupplierWeiId());
				
				result.add(modelVO);
			}			
			return result;
		}
		return null;
	}

	@Override
	public List<ShopClassVO> getShopClassList(long weiID,Short level) {
		List<PShopClass> shopClasseList = editProductInfoDAO.getShopClasseList(weiID,level);
		if(shopClasseList !=null && shopClasseList.size()>0)
		{
			List<ShopClassVO> result = new ArrayList<ShopClassVO>();
			for (PShopClass item : shopClasseList) {
				ShopClassVO classVO = new ShopClassVO();
				classVO.setSid(item.getSid());
				classVO.setSname(item.getSname());
				classVO.setSort(item.getSort());
				classVO.setState(item.getState());
				classVO.setType(item.getType());
				classVO.setWeiid(item.getWeiid());
				classVO.setParentId(item.getParetId());
				result.add(classVO);
			}
			return result;
		}
		
		return null;
	}

	@Override
	public BaseResultVO saveProductParam(ProductParamModelVO paramModel) {
		BaseResultVO result = new BaseResultVO();
		result.setMessage("数据提交失败，请稍后重试！");
		result.setState(BaseResultStateEnum.Failure);
		
		long mnum = editProductInfoDAO.getIsHavePMName(paramModel.getSupplierWeiId(), paramModel.getMname());
		if(mnum >0){
			result.setMessage("该模板名称已存在！");
			return result;
		}
		
		PParamModel pModel = new PParamModel();
		pModel.setClassId(paramModel.getClassId());
		pModel.setMname(paramModel.getMname());
		pModel.setState((short)1);
		pModel.setSupplierWeiId(paramModel.getSupplierWeiId());
		pModel.setCreateTime(new Date());
		int mid =editProductInfoDAO.saveParamModel(pModel);
		if(mid<1){
			return result;
		}
		for (ProductParamKeyVO keyVO : paramModel.getKeyList()) {
			PParamKey km = editProductInfoDAO.getParamKeyModel(keyVO.getAttributeId());
			PParamKey keyModel = new PParamKey();
			keyModel.setMid(mid);
			keyModel.setAttributeName(keyVO.getAttributeName());
			keyModel.setClassid(paramModel.getClassId());
			keyModel.setCreateTime(new Date());
			keyModel.setSort((short)1);
			if(km !=null){
				keyModel.setIsMust(km.getIsMust());
				keyModel.setShowtype(km.getShowtype());			
				keyModel.setSysAttributeID(km.getSysAttributeID());
			}else{
				keyModel.setIsMust((short)0);
				keyModel.setShowtype((short)1);			
				keyModel.setSysAttributeID(0);
			}
			
			int keyID = editProductInfoDAO.saveParamKey(keyModel);
			if(keyID<1){
				return result;
			}
			
			for (ProductParamValueVO valueVO : keyVO.getValueList()) {	
				if(valueVO.getValue()==null || valueVO.getValue() ==""){
					continue;
				}
				PParamValues valueModel = new PParamValues();
				valueModel.setAttributeId(keyID);
				valueModel.setCreateTime(new Date());
				valueModel.setIsDefault(valueVO.getIsDefault());
				valueModel.setState((short)1);				
				valueModel.setValue(valueVO.getValue());
				//获取系统属性模板值ID
				PParamValues vm =editProductInfoDAO.getParamValueModel(valueVO.getAvid());
				if(vm !=null ){
					valueModel.setSysAVID(vm.getSysAVID());
				}else{
					valueModel.setSysAVID(0);
				}
				
				editProductInfoDAO.saveParamValue(valueModel);
			}			
		}
		
		result.setMessage(Integer.toString(mid));
		result.setState(BaseResultStateEnum.Success);		
		return result;
	}
	
	@Override
	public BaseResultVO saveShopClass(String scName,long weiID) {
		
		BaseResultVO result = new BaseResultVO();
		long num = editProductInfoDAO.getIsHaveShopName(weiID, scName);
		if(num>0){
			result.setState(BaseResultStateEnum.Failure);
			result.setMessage("该分类名称已存在！");
			return result;
		}
		
		PShopClass psClass = new PShopClass();
		psClass.setCreateTime(new Date());
		psClass.setSname(scName);
		psClass.setWeiid(weiID);
		psClass.setSort((short)0);
		psClass.setState((short)1);
		psClass.setType((short)1);	
		psClass.setLevel((short)1);
		
		int scID = editProductInfoDAO.saveShopClass(psClass);
		if(scID>0){
			result.setState(BaseResultStateEnum.Success);
			result.setMessage(Long.toString(scID));
		}else{
			result.setState(BaseResultStateEnum.Failure);
			result.setMessage("提交失败");
		}
		
		return result;
	}
	
	@Override
	public ProductParamModelVO getParamModel(int tempID) {		
		PParamModel model = editProductInfoDAO.getParamModel(tempID);
		if(model ==null)
		{
			return null;
		}
		ProductParamModelVO result = new ProductParamModelVO(); 	
		result.setMname(model.getMname());
		result.setMid(model.getMid());
		//获取Key列表
		List<PParamKey> keyList = editProductInfoDAO.getParamKeyList(tempID);
		if(keyList !=null && keyList.size()>0)
		{
			Integer[] keys = new Integer[keyList.size()];
			List<ProductParamKeyVO> keyVOList = new ArrayList<ProductParamKeyVO>();
			for(int i=0;i<keyList.size();i++)
			{
				PParamKey itemKey = keyList.get(i);				
				if(itemKey !=null)
				{
					ProductParamKeyVO keyVO = new ProductParamKeyVO();
					keys[i] = itemKey.getAttributeId();	
					keyVO.setAttributeName(itemKey.getAttributeName());
					keyVO.setAttributeId(itemKey.getAttributeId());
					keyVO.setIschooseparam(itemKey.getIschooseparam());
					keyVO.setShowtype(itemKey.getShowtype());
					keyVO.setSort(itemKey.getSort());	
					keyVO.setSysAttributeID(itemKey.getSysAttributeID());
					keyVO.setIsMust(itemKey.getIsMust());
					keyVOList.add(keyVO);
				}				
			}
			
			//获取所有的value  列表
			List<PParamValues> valueList = editProductInfoDAO.getParamValueList(keys);
			if(valueList !=null && valueList.size()>0)
			{
				//设置key的值列表
				for (ProductParamKeyVO keyItem : keyVOList) {
					List<ProductParamValueVO> valueVOList = new ArrayList<ProductParamValueVO>();
					for (PParamValues valueItem : valueList) {					
						if((int)keyItem.getAttributeId() ==(int)valueItem.getAttributeId())
						{
							ProductParamValueVO valueVO = new ProductParamValueVO();
							valueVO.setAttributeId(valueItem.getAttributeId());
							valueVO.setAvid(valueItem.getAvid());
							valueVO.setIsDefault(valueItem.getIsDefault());
							valueVO.setState(valueItem.getState());
							valueVO.setSysAVID(valueItem.getSysAVID());
							valueVO.setValue(valueItem.getValue());
							if(valueItem.getIsDefault() ==1){
								if(keyItem.getValueName() !=null && keyItem.getValueName() !=""){
									keyItem.setValueName(keyItem.getValueName() +" " +valueItem.getValue());
								}else{
									keyItem.setValueName(valueItem.getValue());
								}
							}
							valueVOList.add(valueVO);
						}						
					}
					//如果系统属性是没有值的。 弄一个空值给它 留着 不删 
					/*if(valueVOList.size()==0){
						ProductParamValueVO valueVO = new ProductParamValueVO();
						valueVO.setAttributeId(0);
						valueVO.setAvid(0);
						valueVO.setIsDefault((short)0);
						valueVO.setState((short)1);
						valueVO.setSysAVID(0);
						valueVO.setValue("");
						valueVOList.add(valueVO);
					}*/
					keyItem.setValueList(valueVOList);
				}
			}
			result.setKeyList(keyVOList);
			return result;
		}
		
		return null;
	}

	@Override
	public ProductParamModelVO getProductParamModel(int mid, long productID) {
		//1.先获取该模板信息
		ProductParamModelVO paramModelVO = this.getParamModel(mid);
		//2.获取该商品的商品属性
		List<PProductParamKv> paramList = editProductInfoDAO.getParamList(productID);
		//模板不存在 且该商品参数为空 
		if(paramModelVO ==null &&(paramList ==null || paramList.size()<1))
		{
			return null;
		}
		else if(paramList ==null || paramList.size()<1)
		{
			return paramModelVO;
		}
		else if(paramModelVO == null)
		{
			paramModelVO = new ProductParamModelVO();
		}
		//遍历模板的key列表
		if(paramModelVO.getKeyList() !=null && paramModelVO.getKeyList().size()>0){
			//设置key的值 
			for (ProductParamKeyVO modelKey : paramModelVO.getKeyList()) {
				//遍历商品的参数 如果是系统的参数 使用后删除
				for(int i=0;i<paramList.size();i++){
					PProductParamKv paramKv = paramList.get(i);
					if((int) modelKey.getAttributeId() ==(int) paramKv.getAttributeID()){
						modelKey.setValueName(paramKv.getParamValue());
						//根据不同的展示方式 还原模板
						if(modelKey.getShowtype() !=1){
							if(modelKey.getValueList() !=null && modelKey.getValueList().size()>0){								
								for (ProductParamValueVO pv : modelKey.getValueList()) {
									if((int)pv.getAvid() == (int)paramKv.getaVID()){
										pv.setIsDefault((short)1);
									}
								}
							}
						}else{//如果是普通的文本框
							 // 直接覆盖模板中的值							
							if(modelKey.getValueList() !=null && modelKey.getValueList().size()>0){
								modelKey.getValueList().get(0).setValue(paramKv.getParamValue());
							}else{
								List<ProductParamValueVO> pvList = new ArrayList<ProductParamValueVO>();
								ProductParamValueVO pv = new  ProductParamValueVO();
								pv.setAttributeId(paramKv.getAttributeID());
								pv.setAvid(paramKv.getaVID());
								pv.setSysAVID(paramKv.getSysAVID());
								pv.setValue(paramKv.getParamValue());								
								pvList.add(pv);
								modelKey.setValueList(pvList);
							}							
						}					
						paramList.remove(i);
						i--;
					}
				}		
			}
		}
		
		//设置返回的参数模板的key列表
		List<ProductParamKeyVO> paramVOList = new ArrayList<ProductParamKeyVO>();
		for (PProductParamKv item : paramList) {
			//单个Key实体
			ProductParamKeyVO paramKeyVO = new ProductParamKeyVO();			
			paramKeyVO.setAttributeName(item.getParamName());
			paramKeyVO.setSysAttributeID(item.getSysAttributeID());	
			paramKeyVO.setAttributeId(item.getAttributeID());
			paramKeyVO.setValueName(item.getParamValue());
			paramKeyVO.setShowtype((short)1);
			
			List<ProductParamValueVO> pvList = new ArrayList<ProductParamValueVO>();
			ProductParamValueVO pv = new  ProductParamValueVO();
			pv.setAttributeId(item.getAttributeID());
			pv.setAvid(item.getaVID());
			pv.setSysAVID(item.getSysAVID());
			pv.setValue(item.getParamValue());								
			pvList.add(pv);			
			paramKeyVO.setValueList(pvList);
						
			paramVOList.add(paramKeyVO);
		}
		//合并系统属性 与 自定义属性 列表
		if(paramModelVO.getKeyList() ==null){
			paramModelVO.setKeyList(paramVOList);
		}else{
			paramModelVO.getKeyList().addAll(paramVOList);
		}
		
		return paramModelVO;
	}

	@Override
	public PProducts getProductModel(long productID) {
				
		return editProductInfoDAO.getProduct(productID);
	}
	
	@Override
	public BaseResultVO editProductInfo(EditProductVO product) {
		
		BaseResultVO result = new BaseResultVO();
			
		long productID = product.getProductId();
		short tag=Short.parseShort(ProductTagType.Retail.toString());//商品的售卖方式  默认支持 零售
		//该商品是否支持批发
		if(product.getBatchPriceList() !=null && product.getBatchPriceList().size()>0){
			tag += Short.parseShort(ProductTagType.Wholesale.toString());
		}
		//判断该商品是否支持预定
		if(product.getPreOrder() !=null && product.getPreOrder().getPreOrderPrice() !=null &&
				product.getPreOrder().getPreOrderPrice()>0){
			tag +=Short.parseShort(ProductTagType.Schedule.toString());
		}		
		//获取系统分类的 分类路径
		ProductClassVO classVO = getClassInfo(product.getClassId());
		if(classVO==null){
			result.setState(BaseResultStateEnum.Failure);
			result.setMessage("您提交的类目信息异常！请检查后重试！");
			return result;
		}
		String classPath = classVO.getOneClassID() + "-" +classVO.getTwoClassID()+ "-" +classVO.getClassID();
		//获取供应商信息
		USupplyer supply = editProductInfoDAO.getSupplyer(product.getSupplyerWeiid());
		
		boolean isShelf = false;//标示该商品批发价格而是否 改变
		boolean isSaveDraft = false;//表示该商品是否由正常商品更改为 草稿
		
		//如果存在商品
		PProducts pModel= new PProducts();
		if(product.getProductId()>0){	
			pModel = getProductModel(product.getProductId());
			if(pModel.getProductTitle().equals(product.getProductTitle())&&pModel.getClassId() == product.getClassId() && pModel.getDefaultImg().equals(ImgDomain.ReplitImgDoMain(product.getProductImg())))
			{
				pModel.setVerStatus((short) 1); //是否需要审核
			}
			else
			{
				pModel.setVerStatus((short) 0); //是否需要审核
			}
			//如果批发价发生修改 或 由正常商品 保存为草稿 强制该商品所有下架
			if(pModel.getState() ==Short.parseShort(ProductStatusEnum.Showing.toString()) &&
					product.getState() == Short.parseShort(ProductStatusEnum.OutLine.toString()))
			{
				isSaveDraft = true;
			}
			List<PProductBatchPrice> bpriceList = editProductInfoDAO.getBatchPriceList(productID);	
			if(bpriceList == null && product.getBatchPriceList() !=null){
				isShelf = true;
			}else if(bpriceList !=null && product.getBatchPrice() ==null){
				isShelf = true;
			}else if(bpriceList.size() != product.getBatchPriceList().size()){
				isShelf = true;
			}else if(bpriceList.size() == product.getBatchPriceList().size()){
				for(int i=0;i<bpriceList.size();i++){
					if((double)bpriceList.get(i).getPirce() != (double)product.getBatchPriceList().get(i).getPirce()){
						isShelf = true;
						break;
					}
					if((int)bpriceList.get(i).getCount() != (int)product.getBatchPriceList().get(i).getCount()){
						isShelf = true;
						break;
					}
				}
			}	
			//删除供应商自己的上架 删除上架的批发价
			/*PClassProducts pcp=editProductInfoDAO.getClassProduct(pModel.getSupplierWeiId(), 
					pModel.getProductId(), pModel.getSupplierWeiId());
			if(pcp !=null){
				editProductInfoDAO.deleteShevleBatchPrice(pcp.getId());
				editProductInfoDAO.deleteClassProduct(productID, pModel.getSupplierWeiId());
			}*/
			
			//下架 所有上架过该商品的数据 
			if(isShelf || isSaveDraft){				
				editProductInfoDAO.updateClassProduct(productID);			
			}
			//如果修改过批发价 删除批发价
			if(isShelf){
				editProductInfoDAO.deleteBatchPrice(productID);				
			}
			
			//删除上游信息表
			editProductInfoDAO.deleteOwnerMessage(product.getSupplyerWeiid(), productID);
			//删除商品图片
			editProductInfoDAO.deletePImgs(productID);
			//删除商品关键字
			editProductInfoDAO.deletePKeyWrods(productID);
			//删除商品参数
			editProductInfoDAO.deletePParams(productID);
			//删除商品款式
			editProductInfoDAO.deletePStyles(productID);
			editProductInfoDAO.deletePStyleKvs(productID);
			editProductInfoDAO.deletePStyleKeys(productID);
			editProductInfoDAO.deletePStyleValues(productID);
		}
		else
		{
			pModel.setVerStatus((short) 0); //是否需要审核
		}
				
		pModel.setAppdes(product.getAppdes());
		pModel.setBrandId(0);
		pModel.setbType(Short.parseShort(ProductBType.normal.toString()));
		pModel.setClassId(product.getClassId());
		pModel.setClassPath(classPath);
		pModel.setDefaultImg(ImgDomain.ReplitImgDoMain(product.getProductImg()));
		pModel.setFreightId(product.getFreightId());
		pModel.setMid(product.getmID());
		pModel.setPcdes(product.getPcdes());
		pModel.setProductMinTitle(product.getProductMinTitle());
		pModel.setProductTitle(product.getProductTitle());
		pModel.setSid(product.getSid());
		pModel.setSort((short)0);
		pModel.setState(product.getState());
		pModel.setSupplierWeiId(product.getSupplyerWeiid());
		pModel.setTag(tag);
		pModel.setSupperType(supply==null || supply.getType() ==null  ? 0 :supply.getType() );	
		pModel.setUpdateTime(new Date());
		pModel.setDefaultConmision(product.getCommission());
		pModel.setDefaultPrice(product.getPrice());
		pModel.setBatchPrice(product.getBatchPrice());
		pModel.setBookPrice(product.getPrePrice());
		pModel.setCount(product.getCount());
		
		
		//是否是云商批发号
		UBatchSupplyer ubSupplyer = super.getById(UBatchSupplyer.class, product.getSupplyerWeiid());
		if(ubSupplyer !=null && ubSupplyer.getBmid() !=null && ubSupplyer.getBmid() >0){
			pModel.setBmid(ubSupplyer.getBmid());
		}
		
		//商品表 默认 价格佣金数量
		if(product.getStyleList() !=null && product.getStyleList().size()>0){
			//款式 价格 数量 
			int totalCount =0;
			pModel.setDefaultConmision(product.getStyleList().get(0).getConmision());
			pModel.setDefaultPrice(product.getStyleList().get(0).getPrice());
			for (ProductStylesVO styleItem : product.getStyleList()) {
					//设置商品默认展示的 金额 佣金 数量
					if(styleItem.getConmision()< product.getCommission()){
						pModel.setDefaultConmision(styleItem.getConmision());
					}
					if(styleItem.getPrice()< product.getPrice()){
						pModel.setDefaultPrice(styleItem.getPrice());
					}					
					totalCount += (styleItem.getCount() == null?0: (int)styleItem.getCount());				
			}
			pModel.setCount(totalCount);
		}
		//商品表 默认 批发价格
		if(product.getBatchPriceList() !=null && product.getBatchPriceList().size()>0){
			pModel.setBatchPrice(product.getBatchPriceList().get(0).getPirce());
		}
		//商品表 默认 预定价格
		if(product.getPreOrder() !=null && product.getPreOrder().getPreOrderPrice() !=null){
			pModel.setBookPrice(product.getPreOrder().getPreOrderPrice());
		}
				
		if(product.getProductId()>0){			
			editProductInfoDAO.update(pModel);
		}else{
			pModel.setCreateTime(new Date());
			productID = (long) editProductInfoDAO.save(pModel);
		}
		
		//1.商品图片表
		if(product.getImgList() !=null && product.getImgList().size()>0){
			for (ProductImgVO imgVO : product.getImgList()) {
				if(imgVO.getImgPath() !=null && !"".equals(imgVO.getImgPath()))
				{
					PProductImg imgModel = new PProductImg();
					imgModel.setCreateTime(new Date());
					imgModel.setImgPath(ImgDomain.ReplitImgDoMain(imgVO.getImgPath()));
					imgModel.setProductId(productID);
					imgModel.setSupplierWeiId(product.getSupplyerWeiid());
					
					editProductInfoDAO.save(imgModel);
				}
			}		
		}
		//2.关键词表
		if(product.getKeyWords() !=null && product.getKeyWords() !=""){
			String[] keyWords= product.getKeyWords().split(" ");
			for (String kw : keyWords) {
				PProductKeyWords keyWordModel = new PProductKeyWords();
				keyWordModel.setCreateTime(new Date());
				keyWordModel.setKeyWord(kw);
				keyWordModel.setProductId(productID);
				keyWordModel.setState((short)1);

				editProductInfoDAO.save(keyWordModel);
			}
		}
		//3.产品参数值表
		if(product.getParamList() !=null && product.getParamList().size()>0){
			for (ProductParamVO paramVO : product.getParamList()) {
				//获取Key value 的系统模板中的ID
				PParamKey paramKey = editProductInfoDAO.getParamKeyModel(paramVO.getAttributeID());
				PParamValues paramValue = editProductInfoDAO.getParamValueModel(paramVO.getaVID());
				
				PProductParamKv paramModel = new PProductParamKv();
				if(paramKey !=null){
					paramModel.setAttributeID(paramKey.getAttributeId());
					paramModel.setSysAttributeID(paramKey.getSysAttributeID());
				}else{
					paramModel.setAttributeID(0);
					paramModel.setSysAttributeID(0);
				}
				if(paramValue !=null){
					paramModel.setaVID(paramValue.getAvid());
					paramModel.setSysAVID(paramValue.getSysAVID());
				}else{
					paramModel.setaVID(0);
					paramModel.setSysAVID(0);
				}
				paramModel.setCreateTime(new Date());
				paramModel.setParamName(paramVO.getParamName());
				paramModel.setParamValue(paramVO.getParamValue());
				paramModel.setProductId(productID);
				
				editProductInfoDAO.save(paramModel);
			}
		}
		//4.销售属性key value style kv
		if(product.getStyleList() !=null && product.getStyleList().size()>0){
			List<PProductSellKey> sellKeys = new ArrayList<PProductSellKey>();
			List<PProductSellValue> sellValues= new ArrayList<PProductSellValue>();
			//key
			for (ProductSellKeyVO keyVO : product.getSellKeyList()) {
				PProductSellKey keyModel = new PProductSellKey();
				keyModel.setProductId(productID);
				keyModel.setAttributeName(keyVO.getAttributeName());
				keyModel.setCreateTime(new Date());
				keyModel.setSort(keyVO.getSort());
				
				long attributeId = editProductInfoDAO.saveSellKey(keyModel);
				if(attributeId>0)
				{
					keyModel.setAttributeId(attributeId);
					sellKeys.add(keyModel);
					//value
					for (ProductSellValueVO valueVO : keyVO.getSellValueList()) {
						PProductSellValue valueModel= new PProductSellValue();
						valueModel.setAttributeId(attributeId);
						valueModel.setClassId(product.getClassId());
						valueModel.setCreateTime(new Date());
						valueModel.setDefaultImg(valueVO.getDefaultImg());
						valueModel.setProductId(productID);
						valueModel.setState((short)1);
						valueModel.setValue(valueVO.getValue());
						
						long keyID = editProductInfoDAO.saveSellValue(valueModel);
						if(keyID>0)
						{
							valueModel.setKeyId(keyID);
							sellValues.add(valueModel);
						}
					}
				}
			}
			//style
			for (ProductStylesVO styleVO : product.getStyleList()) {
				PProductStyles styleModel = new PProductStyles();
				styleModel.setBussinessNo(styleVO.getBussinessNo());
				styleModel.setConmision(styleVO.getConmision());
				styleModel.setCount(styleVO.getCount());
				styleModel.setCreateTime(new Date());
				styleModel.setDefaultImg(ImgDomain.ReplitImgDoMain(styleVO.getDefaultImg()));
				styleModel.setPrice(styleVO.getPrice());
				styleModel.setProductId(productID);
				
				long styleID = editProductInfoDAO.saveProductStyle(styleModel);
				if(styleID>0){
					for (ProductStyleKVVO kvvo : styleVO.getStyleKVList()) {
						PProductStyleKv styleKv = new PProductStyleKv();
						styleKv.setStylesId(styleID);
						styleKv.setProductId(productID);
						styleKv.setCreateTime(new Date());
						
						for (PProductSellKey key : sellKeys) {
							if(key.getAttributeName().equals(kvvo.getAttrbuteName())){
								styleKv.setAttributeId(key.getAttributeId());
								break;
							}
						}
						for (PProductSellValue value : sellValues) {
							if(value.getValue().equals(kvvo.getValueName())){
								styleKv.setKeyId(value.getKeyId());
								break;
							}
						}
						
						editProductInfoDAO.saveStyleKV(styleKv);					
					}
				}			
			}		
		}else{//如果没有 款式 默认 -1 -1
			PProductSellKey  keyModel = new PProductSellKey();
			keyModel.setAttributeName("-1");
			keyModel.setCreateTime(new Date());
			keyModel.setProductId(productID);
			keyModel.setSort((short)0);
			long attributeId = (long) editProductInfoDAO.save(keyModel);
			
			PProductSellValue valueModel = new PProductSellValue();
			valueModel.setClassId(product.getClassId());
			valueModel.setCreateTime(new Date());
			valueModel.setProductId(productID);
			valueModel.setState((short)1);
			valueModel.setValue("-1");
			valueModel.setAttributeId(attributeId);
			long keyId=(long) editProductInfoDAO.save(valueModel);
			
			PProductStyles styleModel= new PProductStyles();
			styleModel.setConmision(product.getCommission());
			styleModel.setCount(product.getCount());
			styleModel.setCreateTime(new Date());
			styleModel.setPrice(product.getPrice());
			styleModel.setProductId(productID);			
			long styleID = (long) editProductInfoDAO.save(styleModel);
			
			PProductStyleKv styleKVModel = new PProductStyleKv();
			styleKVModel.setAttributeId(attributeId);
			styleKVModel.setCreateTime(new Date());
			styleKVModel.setKeyId(keyId);
			styleKVModel.setProductId(productID);
			styleKVModel.setStylesId(styleID);
			editProductInfoDAO.save(styleKVModel);					
		}
			
		//商品批发价格表 如果是新增商品 或 批发价格修改后 
		List<PProductBatchPrice> pbpList = new ArrayList<PProductBatchPrice>();
		if(product.getProductId() ==0 || isShelf){
			if(product.getBatchPriceList() !=null && product.getBatchPriceList().size()>0){
				for (ProductBatchPriceVO bpriceVo : product.getBatchPriceList()) {
					PProductBatchPrice bPModel = new PProductBatchPrice();
					bPModel.setCount(bpriceVo.getCount());
					bPModel.setPirce(bpriceVo.getPirce());
					bPModel.setProductId(productID);
					
					editProductInfoDAO.saveBatchPrice(bPModel);
					pbpList.add(bPModel);
				}				
			}
		}
		
		//商品预定价格表
		if(product.getPreOrder() !=null && product.getPreOrder().getPreOrderPrice() !=null
				&& product.getPreOrder().getPreOrderPrice()>0){
			
			PPreOrder ppoModel = editProductInfoDAO.getPreOrder(productID);
			if(ppoModel !=null){
				ppoModel.setContent(product.getPreOrder().getContent());
				ppoModel.setPreOrderPrice(product.getPreOrder().getPreOrderPrice());
				
				editProductInfoDAO.update(ppoModel);
			}else{
				ppoModel = new PPreOrder();
				ppoModel.setContent(product.getPreOrder().getContent());
				ppoModel.setPreOrderPrice(product.getPreOrder().getPreOrderPrice());
				ppoModel.setProductId(productID);
				
				editProductInfoDAO.save(ppoModel);
			}			
		}
		//商品辅助表
		if(product.getProductId() ==0){
			PProductAssist pAssist = new PProductAssist();
			pAssist.setClassId(product.getClassId());
			pAssist.setEvaluateCount(0);
			pAssist.setMonthCount(0);
			pAssist.setProductId(productID);
			pAssist.setShelvesCount(0);
			pAssist.setSupplierId(product.getSupplyerWeiid());
			
			editProductInfoDAO.save(pAssist);
		}
		//操作流水表
		PProductRecordMsg msg = new PProductRecordMsg();
		msg.setCreateTime(new Date());
		msg.setProductId(productID);
		msg.setWeiId(product.getSupplyerWeiid());
		if(product.getProductId()>0){
			msg.setContent("供应商修改商品");
		}else{
			if(product.getState()==Short.parseShort(ProductStatusEnum.Showing.toString())){
				msg.setContent("供应商发布商品");
			}else{
				msg.setContent("供应商保存草稿");
			}			
		}
		editProductInfoDAO.save(msg);
		
		//上游信息表 如果是正常展示 无论是 跟新还是发布 都需更新上游信息表     发布后需要自动上架
		if(product.getState() == Short.parseShort(ProductStatusEnum.Showing.toString())){
			//上游信息表
			UOwnerMessage m = new UOwnerMessage();
			m.setCreateTime(new Date());
			m.setKeyValue(productID);
			m.setMessage("发布新品");
			m.setState((short) 0);
			m.setWeiId(product.getSupplyerWeiid());
			m.setType(Short.parseShort(MsgType.publishproduct.toString()));
			editProductInfoDAO.save(m);
			
			//自动上架
			//this.setShelveMyself(pModel, pbpList);
		}
				
		//luncene 正式的供应商才能被搜索
		if(supply !=null && supply.getType() !=null && IsAnIdentity(supply.getType())){
			TProductIndex pi =new TProductIndex();
			pi.setProductId(productID);
			pi.setStatus((short) 0);
			//如果保存草稿
			if(isSaveDraft){
				pi.setType((short) 2);//0新增1更新2删除
			}else if(product.getProductId()==0){
				pi.setType((short) 0);//0新增1更新2删除
			}else{
				pi.setType((short) 1);//0新增1更新2删除
			}
			editProductInfoDAO.save(pi);	
		}
		
		result.setState(BaseResultStateEnum.Success);
		result.setMessage(Long.toString(productID));
		return result;
	}
	
	
	/**
	 * 判断供应商是否 是否能发布正式
	 * @param weiNo
	 * @return
	 */
	public boolean isSaleProduct(Long weiNo) {
		if(weiNo == null){
			return false;
		}
			
		UYunSupplier ysup = editProductInfoDAO.get(UYunSupplier.class, weiNo);
		if (ysup != null) {
			if(ysup.getStatus()!=null && ysup.getStatus()==4){
				return true;
			}				
		}
		UBatchSupplyer bsup = editProductInfoDAO.get(UBatchSupplyer.class, weiNo);
		if (bsup != null) {
			if(bsup.getStatus()!=null && bsup.getStatus()==4){
				return true;
			}			
		}
		
		return false;
	}
	
	/**
	 * 发布完成后自动上架
	 * @param pro
	 * @param libp
	 */
	@Override
	public void setShelveMyself(long supplyerWeiID,long productID,long sID, List<PProductBatchPrice> libp)
	{
		String hql="from PClassProducts p where p.weiId=? and p.productId=? and p.supplierweiId=?";
		Object[] b= new Object[3];
		b[0]=supplyerWeiID;
		b[1]=productID;
		b[2]=supplyerWeiID;
		PClassProducts pcp=editProductInfoDAO.getUniqueResultByHql(hql, b);
		
		boolean isAdd = false;
		if(pcp==null){
			pcp= new PClassProducts();
			isAdd = true;
		}		
		if(this.isSaleProduct(supplyerWeiID)){
			pcp.setType((short) 1);//正常的供应商
		}			
		else{
			if(this.isNoPayShop(supplyerWeiID)){
				pcp.setType((short) 3);//审核通过 没交钱的供应商 
			}else{
				pcp.setType((short) 2);//普通微店
			}		
		}
		UUserAssist userassist = this.updateUserAssist(supplyerWeiID);
		long pcpid = 0L;	
		if(isAdd){
			pcp.setProductId(productID);
			pcp.setClassId(sID);
			pcp.setIsSendMyself((short) 1);
			pcp.setSort((short)-1);
			pcp.setReason("供应商自动上架");
			pcp.setState((short)1);
			pcp.setCreateTime(new Date());
			pcp.setWeiId(supplyerWeiID); 
			pcp.setSupplierweiId(supplyerWeiID);
			pcp.setShelvweiId(supplyerWeiID);
			pcp.setSendweiId(supplyerWeiID);
			pcp.setWeiIdsort(userassist.getWeiIdSort());
			pcpid = (long) editProductInfoDAO.save(pcp);
		}else{
			pcp.setClassId(sID);
			pcp.setState((short)1);
			editProductInfoDAO.update(pcp);
			//用update语句试一下
/*			String hqlpcp = "update PClassProducts p set p.classId=:classId,state=1,type=:type where "
					+ "p.weiId=:weiId and p.productId=:productId and p.supplierweiId=:supplierweiId";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classId", pcp.getClassId());
			params.put("type", pcp.getType());
			params.put("weiId", pcp.getWeiId());
			params.put("productId", pcp.getProductId());
			params.put("supplierweiId", pcp.getSupplierweiId());
			editProductInfoDAO.executeHqlByMap(hqlpcp, params);*/
			
			pcpid =pcp.getId();			
		}
		String dbhql = " delete from PShevleBatchPrice p where p.id=?";
		Object[] bb= new Object[1];
		bb[0] = pcpid;
		editProductInfoDAO.executeHql(dbhql, bb);// 删除批发表
		if(libp!=null && libp.size()>0)
		{
			for(int i=0;i<libp.size();i++)
			{
				PProductBatchPrice pbp=libp.get(i);			
				PShevleBatchPrice batchprice = new PShevleBatchPrice();
			   batchprice.setWeiIdsort(userassist.getWeiIdSort());
			   batchprice.setId(pcpid);
			   batchprice.setCount(pbp.getCount()==null?0:pbp.getCount());
			   batchprice.setPrice(pbp.getPirce()==null?0.00:pbp.getPirce());
			   editProductInfoDAO.save(batchprice);
			}
		}
		return;
	}
	
	public UUserAssist updateUserAssist(Long weiid)
	{
		//通过微店号查询辅助表
		UUserAssist userassit =editProductInfoDAO.get(UUserAssist.class, weiid);
		if(userassit==null || userassit.getWeiIdSort() == null || userassit.getWeiIdSort() == 0)
		{
			int temp=0;
			if(userassit==null){
				temp=0;
				userassit = new UUserAssist();
			}
			else{
				temp=1;	
			}			
			//如果改微店还没上架，就想辅助表添加改用户使用上架功能时的个人排序
			PShelverCount psc = editProductInfoDAO.getPShelver();
			boolean isAdd = false;
			if(psc == null)
			{
				psc = new PShelverCount();
				psc.setCount(0L);
				isAdd = true;
			}
			
			userassit.setWeiId(weiid);
			userassit.setWeiIdSort(psc.getCount()+1);
			//更新辅助表
			if(temp == 0)
				editProductInfoDAO.save(userassit);
			else
				editProductInfoDAO.update(userassit);
			psc.setCount(psc.getCount()+1);
			//更新记录表，数量加1
			if(isAdd){
				editProductInfoDAO.save(psc);
			}else{
				editProductInfoDAO.update(psc);
			}
		}
		
		return userassit;
	}
	
	/**
	 * 判断供应商 是否是 云商 或 批发号
	 * @param code
	 * @return
	 */
    private boolean IsAnIdentity(short code)
    {
    	boolean yunSupply =(code & Short.parseShort(SupplierTypeEnum.YunSupplier.toString())) > 1;
    	boolean batchSupply=(code & Short.parseShort(SupplierTypeEnum.BatchSupplier.toString())) > 1;
    	if(yunSupply || batchSupply){
    		return true;
    	}
    	
        return false;
    }

	@Override
	public Boolean getIsMyProduct(long productID, long supplyID) {
		if(productID <1 || supplyID <1){
			return true;
		}
		
		PProducts product = editProductInfoDAO.getProduct(productID);
		if((long)product.getSupplierWeiId() != supplyID){
			return false;
		}
		
		return true;
	}
	
	public boolean isNoPayShop(Long weiNo) {
		if(weiNo == null)
			return false;
		boolean b=false;
		UYunSupplier ysup = super.getById(UYunSupplier.class, weiNo);
		if (ysup != null) {
			if(ysup.getStatus()!=null && ysup.getStatus()==3)
			{
				b= true;
			}
		}
		UBatchSupplyer bsup = super.getById(UBatchSupplyer.class, weiNo);
		if (bsup != null) {
			if(bsup.getStatus()!=null && bsup.getStatus()==3)
			{
				b= true;
			}
		}
		
		return b;
	}

	@Override
	public PBrand getBrandById(Integer brandId) {
		return getById(PBrand.class, brandId);
	}
	
	@Override
	public List<USupplyDemand> getUSupplyDemandListByWeiId(Long weiId,Short state) {
		// TODO Auto-generated method stub
		if(weiId == null || weiId < 0)
		{
			return new ArrayList<USupplyDemand>();
		}
		String hql = "from USupplyDemand where weiId = ? and  state = ?";
		return editProductInfoDAO.find(hql, weiId,state);
	}

	@Override
	public ReturnModel saveShopClassList(String scName, String scJson,
			long weiID) {
		ReturnModel rs = new ReturnModel();
		rs.setStatu(ReturnStatus.SystemError);
		rs.setStatusreson("操作失败");
		List<String> list = null;
		if (scJson != null) {
			list = (List<String>) JsonUtil.jsonToList(scJson);
		}
		if(editProductInfoDAO.getIsHaveShopName(weiID,list)){
			rs.setStatu(ReturnStatus.DataError);
			rs.setStatusreson("该分类名称已存在!");
			return rs;
		}
		List<PShopClass> scList = editProductInfoDAO.saveShopClassList(weiID, scName,list);
		if (scList != null && scList.size() > 0) {
			rs.setStatu(ReturnStatus.Success);
			rs.setBasemodle(scList);
			rs.setStatusreson("");
		}
		return rs;
	}

	@Override
	public PShopClass getShopClass(Long weiID, String scName) {
		String hql = "from PShopClass p where p.state=1 and p.weiid =:weiID and p.sname=:sName";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("weiID", weiID);
		params.put("sName", scName);
		List<PShopClass> list = editProductInfoDAO.findByMap(hql, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	
}
