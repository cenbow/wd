package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.DBrands;
import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TProductIndex;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.enums.ProductShelveStatu;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.ShelveType;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.myportal.bean.dto.ProductDTO;
import com.okwei.myportal.bean.vo.ProductSearchVO;
import com.okwei.myportal.bean.vo.ProductVO;
import com.okwei.myportal.bean.vo.product.MyProductInfo;
import com.okwei.myportal.dao.IEditProductInfoDAO;
import com.okwei.myportal.dao.IProductDAO;
import com.okwei.myportal.service.IMyProductService;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.agent.IDAgentService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;

@Service
public class MyProductService extends BaseService implements IMyProductService {

	@Autowired
	private IProductDAO productDAO;
	@Autowired
	private IEditProductInfoDAO editProductInfoDAO;
	@Autowired
	private IDAgentService idAgentService;
	@Autowired
	private IBaseProductDao productDao;
	@Autowired
	private IBaseCommonService commonService;
	

	public PageResult<MyProductInfo> find_productlist(Long weiid,Integer systemClassId,Integer shopClassID,int  pageIndex,int pageSie){
		PageResult<PProducts> pageResult=idAgentService.find_Productlist(weiid, shopClassID, systemClassId, pageIndex, pageSie);
		List<PProductClass> classlist=productDao.find_PProductClassAll();
		if(pageResult!=null&&pageResult.getList()!=null&&pageResult.getList().size()>0){
			List<MyProductInfo> list=new ArrayList<MyProductInfo>();
			for (PProducts pp : pageResult.getList()) {
				MyProductInfo info=new MyProductInfo();
				info.setProductId(pp.getProductId());
				info.setClassId(pp.getClassId());
				info.setProductTitle(pp.getProductTitle());
				info.setSid(pp.getSid());
				info.setDefaultImg(ImgDomain.GetFullImgUrl(pp.getDefaultImg()));
				info.setDefaultPrice(pp.getDefaultPrice());
				info.setDeputyPrice(pp.getDeputyPrice());
				info.setAgentPrice(pp.getAgentPrice());
				info.setDukePrice(pp.getDukePrice());
				for (PProductClass cc : classlist) {
					if(cc.getClassId().intValue()==pp.getClassId()){
						info.setClassName(cc.getClassName()); 
					}
				}
				info.setSupplierName(commonService.getShopNameByWeiId(pp.getSupplierWeiId())); 
				DBrandSupplier ss=productDao.get(DBrandSupplier.class, pp.getSupplierWeiId());
				if(ss!=null){
					DBrands brand=productDao.get(DBrands.class, ss.getBrandId());
					info.setBrandId(ss.getBrandId());
					info.setBrandName(brand==null?"":brand.getBrandName());
				}
				list.add(info);
			}
			return new PageResult<MyProductInfo>(pageResult.getTotalCount(),Limit.buildLimit(pageIndex, pageSie),list); 
		}
		return  new PageResult<MyProductInfo>();
	}
	
	@Override
	public PageResult<ProductVO> getProducts(ProductDTO dto, Limit limit) {
		PageResult<ProductVO> result = productDAO.getProducts(dto, limit);
		if (null != result && null != result.getList()) {
			for (ProductVO vo : result.getList()) {
				if (ObjectUtil.isNotEmpty(vo.getBrandId())) {
					PBrand pb = super.getById(PBrand.class, vo.getBrandId());
					if (null != pb) {
						vo.setBrandName(pb.getBrandName()); // 品牌名称
					}
				}
				if (ObjectUtil.isNotEmpty(vo.getSupplierWeiId())) {
					USupplyer supplyer = super.getById(USupplyer.class, Long.parseLong(vo.getSupplierWeiId().toString()));
					if (null != supplyer) {
						vo.setSupplierName(supplyer.getCompanyName()); // 供应商名字
					}
				}
				if (dto.getStatus() == ProductStatusEnum.OutLine) {// 草稿箱时，返回的产品均为"自营"类型
					vo.setType((short) 1);
				}
			}
		}
		return result;
	}

	@Override
	public ProductSearchVO getSearchVO(Long weiId) {
		List<PShopClass> list = productDAO.getMyShopClass(weiId);
		List<PBrand> brandList = productDAO.getPBrand(weiId);
		Long showingCount = productDAO.getShowingCount(weiId);
		Long dropCount = productDAO.getDropCount(weiId);
		Long outCount = productDAO.getOutLineCount(weiId);
		return new ProductSearchVO(showingCount, dropCount, outCount, list, brandList);
	}

	@Override
	public ProductSearchVO getSearchVO_subSupplier(Long weiId, String subSupplier) {
		Long showingCount = productDAO.getShowingCount_subSupplier(weiId, subSupplier);
		Long dropCount = productDAO.getDropCount_subSupplier(weiId, subSupplier);
		Long outCount = productDAO.getOutLineCount_subSupplier(weiId, subSupplier);
		return new ProductSearchVO(showingCount, dropCount, outCount, null, null);
	}

	@Override
	public Long getToHandleCount(Long weiId) {
		return productDAO.getAuditCount(weiId);
	}

	@Override
	public Long getHandleCount(String subWeiId) {
		return productDAO.getHandleCount(subWeiId);
	}

	@Override
	public boolean dropProduct(String[] productIds, Long weiId) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < productIds.length; i++) {
			ids.add(Long.valueOf(productIds[i]));
		}
		List<PClassProducts> list = productDAO.getClassProducts(ids, weiId);
		if (null != list) {
			for (PClassProducts p : list) {
				// 设置P_ClassProducts的State为下架状态
				p.setState(Short.parseShort(ProductShelveStatu.OffShelf.toString()));

				// 自营的产品
				if (null != p.getType() && (p.getType() == ShelveType.Self.getNo()) || p.getType() == ShelveType.NoSale.getNo()
						|| p.getType() == ShelveType.NoPay.getNo()) {
					// 更新 pproducts表的state为下架状态
					PProducts product = super.getById(PProducts.class, p.getProductId());
					if (null != product) {
						product.setState(Short.parseShort(ProductStatusEnum.Drop.toString()));
						product.setUpdateTime(new Date());
					}

					if (p.getType() == ShelveType.Self.getNo()) {
						// P_ClassProducts中下架所有别人分销该产品的上架记录
						List<PClassProducts> pps = productDAO.getClassProducts_other(p.getProductId(), weiId);
						if (null != pps) {
							for (PClassProducts pp : pps) {
								pp.setState(Short.parseShort(ProductShelveStatu.OffShelf.toString()));
							}
						}
						// 更新产品索引
						TProductIndex pi = new TProductIndex();
						pi.setProductId(p.getProductId());
						pi.setStatus((short) 0);
						pi.setType((short) 2);// 0新增1更新2删除
						editProductInfoDAO.save(pi);
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean raiseProduct(String[] productIds, Long weiId) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < productIds.length; i++) {
			ids.add(Long.valueOf(productIds[i]));
		}
		List<PClassProducts> list = productDAO.getClassProducts(ids, weiId);
		if (null != list) {
			for (PClassProducts p : list) {
				PProducts product = super.getById(PProducts.class, p.getProductId());
				// 设置P_ClassProducts的State为上架状态
				// 如果是自营 直接上架 其他则判断源头是否为上架状态
				if (null != p.getType() && (p.getType() == ShelveType.Other.getNo())) {
					if (product.getState() != Short.parseShort(ProductStatusEnum.Showing.toString())) {
						continue;
					}
				}
				p.setState(Short.parseShort(ProductShelveStatu.OnShelf.toString()));

				// 自营的产品
				if (null != p.getType() && (p.getType() == ShelveType.Self.getNo()) || p.getType() == ShelveType.NoSale.getNo()
						|| p.getType() == ShelveType.NoPay.getNo()) {
					// 更新 pproducts表的state为上架状态
					if (null != product) {
						product.setState(Short.parseShort(ProductStatusEnum.Showing.toString()));
						product.setUpdateTime(new Date());
					}

					if (p.getType() == ShelveType.Self.getNo()) {
						// 更新产品索引
						TProductIndex pi = new TProductIndex();
						pi.setProductId(p.getProductId());
						pi.setStatus((short) 0);
						pi.setType((short) 0);// 0新增1更新2删除
						editProductInfoDAO.save(pi);
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean deleteProduct(String[] productIds, Long weiId) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < productIds.length; i++) {
			ids.add(Long.valueOf(productIds[i]));
		}
		List<PClassProducts> list = productDAO.getClassProducts(ids, weiId);
		List<Long> tempList = new ArrayList<Long>();
		if (null != list) {
			for (PClassProducts p : list) {
				tempList.add(p.getProductId());
				// 设置P_ClassProducts的State为下架状态
				p.setState(Short.parseShort(ProductShelveStatu.Deleted.toString()));

				// 自营的产品
				if (null != p.getType() && (p.getType() == ShelveType.Self.getNo()) || p.getType() == ShelveType.NoSale.getNo()
						|| p.getType() == ShelveType.NoPay.getNo()) {
					// 更新 pproducts表的state为删除状态
					PProducts product = super.getById(PProducts.class, p.getProductId());
					if (null != product) {
						product.setState(Short.parseShort(ProductStatusEnum.Deleted.toString()));
						product.setUpdateTime(new Date());
					}

					if (p.getType() == ShelveType.Self.getNo()) {
						// P_ClassProducts中下架所有别人分销该产品的上架记录
						List<PClassProducts> pps = productDAO.getClassProducts_other(p.getProductId(), weiId);
						if (null != pps) {
							for (PClassProducts pp : pps) {
								pp.setState(Short.parseShort(ProductShelveStatu.Deleted.toString()));
							}
						}
						// 更新产品索引
						TProductIndex pi = new TProductIndex();
						pi.setProductId(p.getProductId());
						pi.setStatus((short) 0);
						pi.setType((short) 2);// 0新增1更新2删除
						editProductInfoDAO.save(pi);
					}
				}
			}
		}

		// 草稿箱的产品,传入的产品列表减去上架表存在的，即是草稿箱的
		ids.removeAll(tempList);
		if (ids.size() > 0) {
			List<PProducts> pps = productDAO.getPProducts(ids, weiId);
			if (null != pps && pps.size() > 0) {
				for (PProducts pp : pps) {
					pp.setState(Short.parseShort(ProductStatusEnum.Deleted.toString()));
					pp.setUpdateTime(new Date());
				}
			}
		}
		return true;
	}

	@Override
	public boolean moveProduct(String[] productIds, Integer shopClassId, Long weiId) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < productIds.length; i++) {
			ids.add(Long.valueOf(productIds[i]));
		}
		List<PClassProducts> list = productDAO.getClassProducts(ids, weiId);
		if (null != list) {
			for (PClassProducts p : list) {
				// 设置P_ClassProducts的ClassID=shopClassId
				p.setClassId(Long.parseLong(shopClassId.toString()));
				// 自营的产品
				if (null != p.getType() && (p.getType() == ShelveType.Self.getNo()) || p.getType() == ShelveType.NoSale.getNo()) {
					// 更新 pproducts表的sid=shopClassId
					PProducts product = super.getById(PProducts.class, p.getProductId());
					if (null != product) {
						product.setSid(shopClassId);
						product.setUpdateTime(new Date());
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean connectProduct(String[] productIds, Integer brandId, Long weiId) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < productIds.length; i++) {
			ids.add(Long.valueOf(productIds[i]));
		}
		List<PClassProducts> list = productDAO.getClassProducts(ids, weiId);
		if (null != list) {
			for (PClassProducts p : list) {
				// 自营的产品,才允许关联品牌操作
				if (null != p.getType() && p.getType() == ShelveType.Self.getNo()) {
					// 更新 pproducts表的sid=shopClassId
					PProducts product = super.getById(PProducts.class, p.getProductId());
					if (null != product) {
						product.setBrandId(brandId);
						product.setUpdateTime(new Date());
					}
				}
			}
		}
		return true;
	}

	@Override
	public List<PProductBatchPrice> getBatchPrices(Long productId) {
		return productDAO.getBatchPrice(productId);
	}

	@Override
	public List<PShevleBatchPrice> getMyBatchPrices(Long sjId) {
		return productDAO.getMyBatchPrice(sjId);
	}

	@Override
	public boolean updateProduct(Long productId, Long sjId, Integer sid, String prices, Integer type, Long supplierId, Long weiId) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(productId);
		List<PClassProducts> list = productDAO.getClassProducts(ids, weiId);
		if (null != list) {
			for (PClassProducts p : list) {
				p.setState(Short.parseShort(ProductShelveStatu.OnShelf.toString()));// 设置上架状态
				p.setClassId(Long.parseLong(sid.toString()));// 修改店铺分类
				if (type == 1) {// 由自己发货
					p.setIsSendMyself((short) 1);
				}
			}
		}

		if (StringUtils.isNotEmpty(prices)) {
			String[] array = prices.split(",");
			for (int i = 0; i < array.length; i++) {
				String str = array[i];
				String[] item = str.split("_");
				if (null != item && item.length > 0) {
					Long bid = Long.parseLong(item[0].toString());
					Double pirce = Double.parseDouble(item[1].toString());
					PShevleBatchPrice batchPrice = productDAO.getMyBatchPrice(sjId, bid);
					if (null != batchPrice) {
						batchPrice.setPrice(pirce);
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean topProduct(Long productId, Integer shopClassId, Long weiId) {
		Short max = productDAO.getMaxSort(shopClassId, weiId);
		if (null != max) {
			List<Long> ids = new ArrayList<Long>();
			ids.add(productId);
			List<PClassProducts> list = productDAO.getClassProducts(ids, weiId);
			if (null != list && list.size() > 0) {
				max++;
				PClassProducts pp = list.get(0);
				pp.setSort(max);
			}
		}
		return true;
	}
}
