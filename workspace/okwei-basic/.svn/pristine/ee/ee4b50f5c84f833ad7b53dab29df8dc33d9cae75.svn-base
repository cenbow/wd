package com.okwei.service.shop;

import java.util.List;

import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.vo.CompanyInfo;
import com.okwei.bean.vo.ShopClassVO;
import com.okwei.bean.vo.ShopInfoMsg;
import com.okwei.service.IBaseService;

public interface IBasicShopMgtService extends IBaseService {

	List<ShopClassVO> getShopClass(Integer parentId, Long weiId);

	CompanyInfo getShopInfo(Long weiId);
	
	/**
	 * 判断是否是828活动店铺
	 * @param weiId
	 * @return
	 */
	boolean isActShop(Long weiId);
	/**
	 * 得到产品的一级分类
	 * @author zlp at 2016-07-08
	 * @param step
	 * @return
	 */
	List<PProductClass> getPProductClass(short step);

	/**
	 * 
	 * @param weiNo
	 * @param weiID
	 * @param tiket
	 * @return
	 */
	public ShopInfoMsg UP_getShopInfo(Long weiNo,
			Long weiID, String tiket);

}
