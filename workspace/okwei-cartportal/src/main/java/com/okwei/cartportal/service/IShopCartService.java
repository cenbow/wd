package com.okwei.cartportal.service;

import java.util.List;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.vo.address.AddressVO;
import com.okwei.cartportal.bean.JiesuanInfo;
import com.okwei.cartportal.bean.KuaiDi;
import com.okwei.cartportal.bean.PreSubmitCartList;
import com.okwei.cartportal.bean.ShoppingCarList;
import com.okwei.cartportal.bean.vo.ProdStylesVo;
import com.okwei.cartportal.bean.vo.ReturnOdertInfo;
import com.okwei.cartportal.bean.vo.StylesPriceVo;
import com.okwei.service.IBaseService;

public interface IShopCartService extends IBaseService {


	/**
	 * 根据订单类型获得购物车列表
	 * @param weiID
	 * @param stype
	 * @return
	 */
	List<ShoppingCarList> getShoppingCarList(long weiID, Short stype) throws Exception;

	/**
	 * 修改购物车商品数量
	 * @param prodCount
	 * @param scId
	 * @return
	 */
	void updateCartPordCount(int prodCount, long scId) throws Exception;

	/**
	 * 删除购物车
	 * @param scids
	 * @throws Exception
	 */
	void delCartPord(String scids,long weiid) throws Exception;

	JiesuanInfo getJiesuanInfo(String scids, long weiID,Short stype) throws Exception;
	/**
	 * 结算  chenhaitao
	 * @param scids
	 * @param weiID
	 * @param stype
	 * @return
	 * @throws Exception
	 */
	JiesuanInfo getNewJiesuanInfo(String scids, long weiID,Short stype) throws Exception;
	
	List<AddressVO> getAddressList(long weiID) throws Exception;

	int setDefault(long weiid, int caddrId) throws Exception;

	int saveOrUpdateAdd(UCustomerAddr entity) throws Exception;

	int deleteAddress(long weiid, int caddrID) throws Exception;
	
	/**
	 * 批发价转换
	 * @param dianNo
	 * @param proId
	 * @return
	 */
	public List<PProductBatchPrice> getBatchPricess(Long dianNo,Long proId);

	/**
	 * 获取供应商（正常状态）
	 * @param companyLogid
	 * @return
	 */
	USupplyer getUSupplyer(Long companyLogid);

	/**
	 * 检查商品是否可以购买
	 * @param weiid 微店号
	 * @param prodId 商品id
	 * @param styleId 商品款式id
	 * @return
	 */
	boolean checkExistProduct(Long weiid, Long prodId, Long styleId);

	/**
     * 查询快递
     * 
     * @param plist
     *            购物车中单个供应商产品
     * @param weiNo
     *            微店号
     * @param cartType
     *            购物类型
     * @param uca
     *            地址
     * @return
     */
    public List<KuaiDi> getkdListBySC(PreSubmitCartList plist,long weiNo,Short cartType,AddressVO uca);

    /**
     * 生成订单
     * @param submitList 购买的商品列表（按供应商）
     * @param weiID 买家微店号
     * @param address 选择的收货地址
     * @param stype 订单类型
     * @return 
     * @throws Exception
     */
	ReturnOdertInfo savePlaceOrderByWeiNo(List<PreSubmitCartList> submitList,
			long weiID, AddressVO address, Short stype) throws Exception;

	String GetRroductStyleName(Long proid, Long styleid);


	/**
	 * 查询商品款式价格
	 * @param styleJson
	 * @return
	 * @throws Exception
	 */
	StylesPriceVo getProdStylePrice(String styleJson) throws Exception;
	/**
	 * 修改商品样式
	 * @param scId
	 * @param styleId
	 * @return
	 * @throws Exception
	 */
	StylesPriceVo modifyProdStyleId(long scId, long styleId,long weiId,short source) throws Exception;

	/**
	 * 获得可用的属性值
	 * @param productId
	 * @param attrId
	 * @param keyId
	 * @return
	 */
	List<ProdStylesVo> getAvailableProdStyles(long productId, long attrId,
			long keyId) throws Exception;
	
	
	PProducts getPProducts(Long productId) throws Exception;

	PProductStyles getPProductStylesById(long styleId) throws Exception;

	Double getshoppcartpricebycount(int pricount,
			List<PProductBatchPrice> ppbplist);
	
	/**
     * 获取购物车数量
     * 
     * @param weiid
     * @return
     */
    public long getCartCount(long weiid) throws Exception;
    /**
     * 获取用户的数量
     * 
     * @return
     */
    public long getUserCount() throws Exception;

	PClassProducts getClassProducts(Long supplierWeiId, Long prodId) throws Exception;

	PClassProducts getPClassProductsByWeiId(Long shopWeiId, Long productId) throws Exception;

	long getShopCartCountByType(long weiID, Short stype) throws Exception;

	PClassProducts getPClassProductsByCondition(Object[] obs);

	String getShopNameById(Long weiNo) throws Exception;
	/**
	 * 结算取进货单和铺货单的价格
	 * @param productId
	 * @param source
	 * @return
	 */
	double getBalancePriceBuyPurchasesAndMedic(long styleId,short source,List<PProductStyles> list) ;
}
