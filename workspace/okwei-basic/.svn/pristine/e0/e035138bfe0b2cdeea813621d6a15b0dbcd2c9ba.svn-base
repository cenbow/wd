package com.okwei.service.product;

import java.util.List;

import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.UserInfo;
import com.okwei.bean.vo.product.P_PrductsClassList;
import com.okwei.bean.vo.product.PostAgeModelVO;
import com.okwei.bean.vo.product.ProductAttributeVO;
import com.okwei.bean.vo.product.ProductEditInfo;
import com.okwei.bean.vo.product.ProductParam;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;


public interface IBasicProductService extends IBaseService {

	/**
	 * 保存、发布产品
	 * @param product
	 * @param userinfo
	 * @param reqSource 请求来源 app/pc
	 * @return
	 * @throws Exception
	 */
	public ReturnModel editProduct(ProductParam product,UserInfo userinfo,String reqSource) throws Exception;
	
	/**
	 * 保存销售属性模板
	 * @param weino
	 * @param classid
	 * @param keyParams
	 * @return
	 */
	public ReturnModel saveProductSellParamModel(long weino,int classid,String modelName, List<ProductAttributeVO> keyParams);
	
	public int getDownCount(Long weiNo);
	
	/*
	 *  是否能发布能销售的产品
	 */
	public boolean isSaleProduct(Long weiNo);
	/**
	 * 判断是否允许在手机端来编辑
	 * @param productid
	 * @return
	 */
	public  boolean isEditByMobile(Long productid);
	/**
	 * 根据产品Id获取编辑
	 * @param productId
	 * @param idtype 登陆身份
	 * @return
	 */
	ProductEditInfo getEditProductInfoByID(Long productId,int idtype)  throws Exception;

	List<PPostAgeModel> getPostAgeListByWeiid(long weino) throws Exception;

	public ProductEditInfo getEditProductSubInfoByID(Long productId);

	int getAgentCount(Long supplyId, Short state);

	int getStoreCount(Long supplyId);

	public PageResult<PostAgeModelVO> getPostAgeModelPageResult(Long weiID,
			Integer pageIndex, Integer pageSize);

	/**
	 * 获取云端产品库大分类
	 * @return
	 */
	public List<P_PrductsClassList> findYunProductClass() throws Exception;

	void shelveProductToAgeStore(Integer demandId, Long platformWid,
			Long weiId, Short channelType, Short shelveStatu) throws Exception;
	
}
