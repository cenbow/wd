package com.okwei.service;

import java.util.List;

import com.okwei.bean.domain.PProductBatchPrice;
import com.okwei.bean.enums.FromTypeEnum;
import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.bean.vo.order.BKuaiDi;
import com.okwei.bean.vo.order.BReturnOdertInfo;
import com.okwei.bean.vo.order.BShoppingCartVO;

public interface IBasicShoppingCartService {
	
	/**
	 * 用户下单
	 * @param sellerlist
	 * @param weiid
	 * @param address
	 * @param stype
	 * @param orderFrom 订单来源
	 * @return
	 */
	BReturnOdertInfo savePlaceOrder(List<BShoppingCartVO> sellerlist,long weiid,BAddressVO address, FromTypeEnum orderFrom);

	/**
	 * 用户下单兑换订单
	 * @param sellerlist
	 * @param weiid
	 * @param address
	 * @param stype
	 * @param orderFrom 订单来源
	 * @return
	 */
	BReturnOdertInfo saveExchangeOrder(List<BShoppingCartVO> sellerlist,long weiid,BAddressVO address, FromTypeEnum orderFrom);

	
	
	/**
	 * 获取发货人
	 * @param weiid 买家微店号
	 * @param productid 产品编号
	 * @param provice 收货人地址（省）
	 * @param city 市
	 * @param areaid 区
	 * @return 发货人weiid
	 */
	public String getSellerWeiid(long weiid, long productid, int provice, int city, int areaid);
	/**
	 * 后去快递
	 * @param type（1快递, 2EMS, 3平邮）
	 * @param amount
	 * @return
	 */
	BKuaiDi getBKuaiDi(int type,double amount);
	/**
	 * 查询并返回运费方式
	 * @param pur
	 * @param address
	 * @return
	 */
	public BShoppingCartVO getCartModel(BShoppingCartVO pur,BAddressVO address);
	/**
	 * 获取批发价列表
	 * @param dianNo
	 * @param proId
	 * @return
	 */
	public List<PProductBatchPrice> getBatchPricess(Long dianNo, Long proId);
	/**
	 * 拿到批发价
	 * @param count
	 * @param pplist
	 * @return
	 */
	public Double getshoppcartpricebycount(int count, List<PProductBatchPrice> pplist);
	/**
	 * 获取产品属性
	 * @param proid
	 * @param styleid
	 * @return
	 */
	public String getProductStyleName(Long proid, Long styleid);
	
	
}
