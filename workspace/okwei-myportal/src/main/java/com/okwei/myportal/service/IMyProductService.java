package com.okwei.myportal.service;

import java.util.List;

import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.ProductDTO;
import com.okwei.myportal.bean.vo.ProductSearchVO;
import com.okwei.myportal.bean.vo.ProductVO;
import com.okwei.myportal.bean.vo.product.MyProductInfo;
import com.okwei.service.IBaseService;

public interface IMyProductService extends IBaseService {

	PageResult<ProductVO> getProducts(ProductDTO dto, Limit limit);
	/**
	 * 获取产品列表（代理商的产品管理用）
	 * zy(2016-7-30)
	 * @param weiid 
	 * @param systemClassId 系统分类id
	 * @param shopClassID 店铺分类ID
	 * @param pageIndex 
	 * @param pageSie
	 * @return
	 */
	public PageResult<MyProductInfo> find_productlist(Long weiid,Integer systemClassId,Integer shopClassID,int  pageIndex,int pageSie);

	ProductSearchVO getSearchVO(Long weiId);
	
	ProductSearchVO getSearchVO_subSupplier(Long weiId, String subSupplier);
	
	Long getToHandleCount(Long weiId);
	
	Long getHandleCount(String subWeiId);

	boolean dropProduct(String[] productIds, Long weiId);

	boolean raiseProduct(String[] productIds, Long weiId);

	boolean deleteProduct(String[] productIds, Long weiId);

	boolean moveProduct(String[] productIds, Integer shopClassId, Long weiId);

	boolean connectProduct(String[] productIds, Integer brandId, Long weiId);

	// boolean updateProduct(Long productId, Integer brandId, Long weiId);

	List<PProductBatchPrice> getBatchPrices(Long productId);

	List<PShevleBatchPrice> getMyBatchPrices(Long sjId);

	boolean updateProduct(Long productId, Long sjId, Integer sid, String prices, Integer type, Long supplierId, Long weiId);

	boolean topProduct(Long productId, Integer shopClassId, Long weiId);

}
