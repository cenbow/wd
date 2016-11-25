package com.okwei.appinterface.dao;

import java.util.List;
import java.util.Map;

import com.okwei.appinterface.bean.vo.share.DianZanCountVO;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SShareActive;
import com.okwei.bean.domain.SStatics;
import com.okwei.bean.domain.SZambiaActive;
import com.okwei.bean.domain.UCollectionStore;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;

public interface IShareDAO  extends IBaseDAO  {
	
	/**
	 * 获取收藏的分享列表
	 * @param weiId 登录用户ID 
	 * @param limit
	 * @return
	 */
	public PageResult<UCollectionStore> findUCollectionStoreList(long weiId,Limit limit);
	/**
	 * 获取收藏对象
	 * @param thingId 分享Id
	 * @param weiId	 分享人
	 * @param thingType	 =3分享类型
	 * @return
	 */
	public UCollectionStore getUCollectionStore(Long thingId,Long weiId,Short thingType);
	
	/**
	 * 获取 我的分享  列表
	 * @param weiId
	 * @param limit
	 * @return
	 */
	public PageResult<SShareActive> findSShareActiveList(Long weiId,Limit limit);
	
	/**
	 * 获取热门列表
	 * @param limit
	 * @return
	 */
	public PageResult<SMainData> findSMainDataList(Limit limit);
	/**
	 * 获取标题信息
	 * @param shareId 分享Id集合
	 * @return
	 */
	public List<SMainData> findSMainDataList(List<Long> shareIds);
	
	 
	/**
	 * 获取关联表信息（从中取得商品Id）
	 * @param shareId 分享Id集合
	 * @return
	 */
	public List<SProducts> findSProductsList(Long shareIds);
	
	/**
	 * 根据统计表获取 分享数量
	 * @param shareIds
	 * @return
	 */
	public List<SStatics> findSStaticsList(List<Long> shareIds);
	
	/**
	 * 获取 自己点赞的分享记录
	 * @param shareIds 分享Id 
	 * @param weiId	 点赞人的微店号
	 * @return
	 */
	public List<SZambiaActive> findSZambiaActiveList(List<Long> shareIds,long weiId);
	
	/**
	 * 获取点赞次数
	 * @param shareIds
	 * @return
	 */
	public List<DianZanCountVO> getDianZanCount(List<Long> shareIds);
	
	/**
	 * 获取关联表信息（从中取得商品Id）
	 * @param shareId 分享Id集合
	 * @return
	 */
	public List<PProducts> findPProductsList(List<Long> productIds);
	/**
	 * 获取分享类型
	 * @param pageId
	 * @return
	 */
	public List<SMainData> findSMainData(Long pageId);
	
}
