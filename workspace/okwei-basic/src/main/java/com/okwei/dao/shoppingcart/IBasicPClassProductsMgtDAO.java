package com.okwei.dao.shoppingcart;
import java.util.List;

import com.okwei.bean.domain.PBrandShevle;
import com.okwei.bean.domain.PClassProducts;
/**
 * 上架表DAO
 * @author Administrator
 *
 */
public interface IBasicPClassProductsMgtDAO {
	/**
	 * 判断产品是否上架
	 * @param supplierWeiId
	 * @param productId
	 * @return
	 */
	PClassProducts judageProductIsRacking(long supplierWeiId,long productId,short status);
	/**
	 * 查找上架列表
	 * @param weiIdList
	 * @param productList
	 * @return
	 */
	List<PClassProducts> getPClassProductsList(List<Long> weiIdList,List<Long>productList);
	
	//查询代理区的上架表
	List<PBrandShevle> getPBrandShevleList(List<Long>productList);
}
