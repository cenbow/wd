package com.okwei.dao.share;

import java.util.List;

import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SShareActive;
import com.okwei.bean.domain.SSingleDesc;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;

public interface IBasicShareDAO extends IBaseDAO {
	/**
	 * 根据分享Id获取分享的所有商品Id 
	 * @param shareId
	 * @return
	 */
	public List<Long> findSProductsByProductIds(long shareId);
	/**
	 * 获取分享中的商品信息
	 * @param shareId 分享Id 
	 * @param limit
	 * @return
	 */
	public PageResult<SProducts> findSProductsList(long shareId,Limit limit);
	
	/**
	 * 获取分享中的商品信息(用了缓存)
	 * @param shareId
	 * @param limit
	 * @return
	 */
	public PageResult<SProducts> findSProductsListRedis(long shareId, Limit limit) ;
	
	/**
	 * 获取分享的 商品详情 图片
	 * @param spid
	 * @return
	 */
	public List<SSingleDesc> findSSingleDescByProducts(long spid);
	
	/**
	 * @param shareId 分享Id 
	 * @param weiId 分享人weiId
	 * @return
	 */
	public SShareActive getSShareActive(long shareId,long weiId);
	
	/**
	 * 修改 分享信息状态为 0 （删除除该记录）
	 * @param shareId
	 * @param shareOne
	 */
	public void updateShareActive(long shareId, long shareOne);
}
