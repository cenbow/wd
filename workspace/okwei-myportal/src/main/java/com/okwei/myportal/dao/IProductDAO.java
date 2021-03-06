package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.PShopClass;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.myportal.bean.dto.ProductDTO;
import com.okwei.myportal.bean.vo.ProductVO;

public interface IProductDAO extends IBaseDAO {

	PageResult<ProductVO> getProducts(ProductDTO dto, Limit limit);

	// 获取第一级店铺分类
	List<PShopClass> getMyShopClass(Long weiId);

	List<PBrand> getPBrand(Long weiId);

	Long getShowingCount(Long weiId);

	Long getDropCount(Long weiId);

	Long getOutLineCount(Long weiId);

	// 子供应商
	Long getShowingCount_subSupplier(Long weiId, String subWeiId);

	Long getDropCount_subSupplier(Long weiId, String subWeiId);

	// 子供应商获取草稿箱产品数量
	Long getOutLineCount_subSupplier(Long weiId, String subWeiId);

	// 平台号获取待审核的产品数量
	Long getAuditCount(Long weiId);

	// 子供应商获取待处理的产品数量
	Long getHandleCount(String subWeiId);

	List<PClassProducts> getClassProducts(List<Long> ids, Long weiId);

	List<PProducts> getPProducts(List<Long> ids, Long weiId);

	/**
	 * 
	 * @Title: getClassProducts_other
	 * @Description: 获取别人分销自己的产品的上架记录
	 * @param @param productId
	 * @param @param weiId
	 * @param @return
	 * @return List<PClassProducts>
	 * @throws
	 */
	List<PClassProducts> getClassProducts_other(Long productId, Long weiId);

	List<PProductBatchPrice> getBatchPrice(Long productId);

	List<PShevleBatchPrice> getMyBatchPrice(Long productId);

	PProductBatchPrice getBatchPrice(Long productId, Long bid);

	PShevleBatchPrice getMyBatchPrice(Long sjId, Long sbid);

	Short getMaxSort(Integer shopClassId, Long weiId);
}
