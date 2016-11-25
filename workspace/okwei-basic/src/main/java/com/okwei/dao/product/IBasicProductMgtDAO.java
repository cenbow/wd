package com.okwei.dao.product;

import java.util.List;

import com.okwei.bean.domain.PAgentStock;
import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.dto.ProductDTO;
import com.okwei.bean.vo.ProductMgtVO;
import com.okwei.bean.vo.product.ShelveProduct;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;

public interface IBasicProductMgtDAO extends IBaseDAO {

	PageResult<ProductMgtVO> getProducts(ProductDTO dto, Limit limit);

	PAgentStock getPAgentStock(Long productId, Long weiId);

	// 获取同一店铺分类下最大的序号
	Short getMaxSort(Integer shopClassId, Long weiId);

	// 获取当前用户发布的所有产品
	List<PClassProducts> getClassProducts(List<Long> ids, Long weiId);

	// 获取别人分销自己的产品的上架记录
	List<PClassProducts> getClassProducts_other(Long productId, Long weiId);

	List<PProducts> getPProducts(List<Long> ids, Long weiId);

	PClassProducts GetShelveProductByWeiid(Long weiid, Long productid, Short statu);

	UAttention getattentionModle(Long attener, Long attento);

	PShopClass getShopClassByid(Integer sid);

	List<PShopClass> getSubShopClassByid(Integer parentId);

	List<PShevleBatchPrice> GetShevleBatchPriceByID(Long shelveID);

	List<PProductBatchPrice> GetBatchPriceByID(Long productid);

	PClassProducts getShevleProductByWeiidAndProductID(Long weiid, Long productid);

	void deleteShevleProductByWeiidAndProductID(Long weiid, Long productid, Long batchid);

	UUserAssist updateUserAssist(Long weiid);

	Long InsertShevleProduct(PClassProducts classproduct, ShelveProduct sproduct, Long WeiID, Long WeiIDsort);

	List<PClassProducts> getPClassProducts(Long weiid, Long shopClassId, Short state);

	void UP_PClassProductsByProIds(Long weiid, Long[] productIdS, Short state, boolean isDelete);

	void UP_ProductByProductIDS(Long weiid, Long[] productIdS, Short state);

	boolean UP_UOwnerMessagesDelByProIds(Long[] proIds, Long weiid);

	void UP_PClassProductsByProId(Long weiid, Long productId, Short state, boolean isDelete);

	void UP_ProductByProductID(Long weiid, Long productId, Short state);

	List<PProductBatchPrice> getBatchPrice(Long productId);

	List<PShevleBatchPrice> getMyBatchPrice(Long productId);

	// 获取子供应商列表
	List<UChildrenUser> getChildSupplyer(Long parentId);

	void UP_SubProductByProductIDS(String weiid, Long[] productIdS, Short state);

	void UP_ProductSubByProductIDS(String weiid, Long[] productIdS, Short state);

	/*
	 * 获取图片
	 */
	List<String> getPictureImg(Long productId);
}
