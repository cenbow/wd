package com.okwei.walletportal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.UWeiCoinLog;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.dao.wallet.IBasicWalletDao;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;
import com.okwei.walletportal.bean.enums.BaseResultState;
import com.okwei.walletportal.bean.vo.BaseResultVO;
import com.okwei.walletportal.bean.vo.coupon.CashCouponVO;
import com.okwei.walletportal.service.ICashCouponService;

/**
 * @author fuhao 
 *	现金劵
 */
@Service 
public class CashCouponService extends BaseService implements ICashCouponService {

	@Autowired
	private IBasicWalletDao basicWalletDao;
	
	@Autowired
	private IBasicOrdersDao basicOrdersDao;
	
	@Override
	public BaseResultVO update_CashCoupon(Long weiId,String[] cashCouponId) { 
		Long[] cashCouponId1 =new  Long[cashCouponId.length];
		int i=0;
		for (String string : cashCouponId) {
			cashCouponId1[i]=ParseHelper.toLong(string);
			i++;
		} 
		String hql="update UWeiCoinLog set deleted=1 where weiId=:weiId and coinLogId in (:coinLogId)";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("weiId", weiId);
		map.put("coinLogId", cashCouponId1);
		int executeHql = basicOrdersDao.executeHqlByMap(hql,map); 
		BaseResultVO resultVO = new BaseResultVO();
		resultVO.setState(BaseResultState.Success);
		resultVO.setMessage("删除成功！");
		if (executeHql <= 0) { 
			resultVO.setMessage("删除失败！");
			resultVO.setState(BaseResultState.Failure);
		}
		return resultVO;
	}
	
	 
	/**
	 * @param weiId
	 * @param limit
	 * @param dt 现金劵类型  1购物返现金券 2购物抵现 3过期现金卷
	 * @return
	 */
	public PageResult<CashCouponVO> find_CashCouponList(Long weiId,Limit limit,int dt){ 
		PageResult<CashCouponVO> pageResult1=new PageResult<CashCouponVO>();
		
		List<CashCouponVO> list1=new ArrayList<CashCouponVO>();
		
		//数据源
		PageResult<UWeiCoinLog> pageResult = basicWalletDao.find_CashCoupon(weiId, limit, dt);
		List<UWeiCoinLog> list = pageResult.getList();
		if (list!=null&&list.size()>0) {
			//先获取 商品订单号 
			String[] supplyOrderids=new String[list.size()];
			int i=0;
			for (UWeiCoinLog uWeiCoinLog : list) {
				supplyOrderids[i]=uWeiCoinLog.getSupplyOrderId();
				i++;
			}
			
			//查询出商品的名称和图片 获取商品订单集合
			List<OProductOrder> find_ProductOrderBySupOrderIds = basicOrdersDao.find_ProductOrderBySupOrderIds(supplyOrderids);
			//拼接展示的数据
			for (UWeiCoinLog uWeiCoinLog : list) {
				CashCouponVO ccv=new CashCouponVO();
				ccv.setCashCouponId(uWeiCoinLog.getCoinLogId());
				ccv.setCoinAmount(uWeiCoinLog.getCoinAmount());
				ccv.setCreateTime(DateUtils.format(uWeiCoinLog.getCreateTime(), "yyyy-MM-dd HH:mm"));
				ccv.setProductOrderId(uWeiCoinLog.getProductOrderId());
				ccv.setState(dt);
				ccv.setWeiid(weiId);
				if (uWeiCoinLog.getProductOrderId()!=null) { 
					for (OProductOrder oProductOrder : find_ProductOrderBySupOrderIds) {
						if (uWeiCoinLog.getSupplyOrderId().equals(oProductOrder.getSupplierOrderId())) {
							//给商品 图片  描述 赋值
							ccv.setProductId(oProductOrder.getProductId());
							ccv.setProductImg(ImgDomain.GetFullImgUrl(oProductOrder.getProductImg(), 24));
							ccv.setProdcutTitle(oProductOrder.getProdcutTitle());
							ccv.setSupOrderID(oProductOrder.getSupplierOrderId());
						}
					}	
				}
				list1.add(ccv);
			} 
			pageResult1.setList(list1);
			pageResult1.setPageCount(pageResult.getPageCount());
			pageResult1.setPageIndex(pageResult.getPageIndex());
			pageResult1.setPageSize(pageResult.getPageSize());
			pageResult1.setTotalCount(pageResult.getTotalCount());
			pageResult1.setTotalPage(pageResult.getTotalPage());
		} 
		return pageResult1;
	}

	
	
	
}
