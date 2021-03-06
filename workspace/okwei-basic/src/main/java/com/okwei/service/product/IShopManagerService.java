package com.okwei.service.product;

import java.util.List;

import com.okwei.bean.domain.AActSupplier;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.PcUserAdnotice;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.bean.vo.product.ProductQuery;
import com.okwei.bean.vo.product.Products;
import com.okwei.bean.vo.product.ShopClassVO;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;

public interface IShopManagerService extends IBaseService{

	List<PShopClass> getShopClassList(Long weiId, Integer sid) throws Exception;

	List<PcUserAdnotice> findPcUserAdnoticeByWeiid(long weiID);

	PageResult<Products> getProductsList(ProductQuery param, LoginUser loginUser) throws Exception;

	ReturnModel editShopClass(ShopClassVO scVo, LoginUser loginUser)  throws Exception;
	/**
	 * 获取用户 店铺分类列表
	 * @param weiid
	 * @param isSelf
	 * @param state
	 * @return
	 */
	public List<PShopClass> find_shopClasslist(Long weiid,boolean isSelf,Short state);
	/**
	 * 获取用户自营产品列表（展示中的）
	 * @param sellerID
	 * @param shopClassID
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResult<PProducts> find_productlist(Long sellerID,Integer shopClassID,Long actid,String title, int pageIndex,int pageSize);
	
	/**
	 * 得到店铺所有已审核过参与828活动
	 * @author zlp
	 * @param weiId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageResult<ProductInfo> find_Shop828ProductId(Long weiId, int pageIndex, int pageSize);
	/**
	 * 得到报名的供应商信息
	 * @author zlp
	 * @param supplyWeiid
	 * @return
	 */
	AActSupplier getActSupplier(long supplyWeiid);

	/**
	 * 得到所有已审核过参与828活动的产品
	 * @author zlp
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageResult<ProductInfo> find_Product828ProductId(int pageIndex, int pageSize);

	
}
