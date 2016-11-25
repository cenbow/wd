package com.okwei.appinterface.service.share;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.common.Limit;
import com.okwei.service.IBaseService;

public interface IShareSvervice extends IBaseService {

	/**
	 *	添加分享页回调
	 * @param shareId 分享ID	
	 * @param shareOne	 分享人weiID
	 * @param user
	 * @return
	 */
	public ReturnModel getShareUrl1(Long shareId,Long shareOne,LoginUser user);
	/**
	 * 获取收藏的分享列表
	 * @param weiId
	 * @param limit
	 * @return
	 */
	public ReturnModel findUCollectionStoreList(long weiId,Limit limit);
	/**
	 * 对分享信息 点赞
	 * @param weiId 登录人
	 * @param pageId 分享Id 
	 * @param shareOne	分享人weiId
	 * @return
	 */
	public ReturnModel updateLikeSharePage(long weiId,Long pageId,Long shareOne);
	
	/**
	 * 针对wap的点赞 添加 微金币
	 * @param weiId
	 * @param pageId 分享Id
	 * @param shareOne	分享人
	 * @return
	 */
	public ReturnModel updateLikeSharePages(long weiId,Long pageId,Long shareOne);
	/**
	 * 获取我的分享列表
	 * @param weiId
	 * @param limit
	 * @return
	 */
	public  ReturnModel findMyShareList(Long weiId, Limit limit); 
	/**
	 * 获取Wap我的分享列表
	 * @param weiId
	 * @param limit
	 * @return
	 */
	public  ReturnModel findWapMyShareList(Long weiId, Limit limit); 
	/**
	 * 获取热门分享列表
	 * @param weiId
	 * @param limit
	 * @return
	 */
	public  ReturnModel findHotShareList(long weiId,Limit limit);
	
	/**
	 * 获取wap热门分享列表
	 * @param weiId 没有登录人传0
	 * @param limit
	 * @return
	 */
	public  ReturnModel findWapHotShareList(long weiId,Limit limit);
	/**
	 * 获取分享详情app
	 * @param weiId  登录人weiID
	 * @param shareOne 分享人weiID
	 * @param shareId 分享ID
	 * @return
	 */
	public  ReturnModel GetShareDetails(long weiId,long shareOne,long shareId,Limit limit);
	
	/**
	 * 获取分享详情wap
	 * @param weiId  登录人weiID
	 * @param shareOne 分享人weiID
	 * @param shareId 分享ID
	 * @param limit
	 * @return
	 */
	public  ReturnModel GetWapShareDetails(long weiId,long shareOne,long shareId,Limit limit);
	
	/**
	 * 获取分享详情  中的商品列表
	 * @param weiId 
	 * @param shareId 分享Id
	 * @param limit
	 * @return
	 */
	public  ReturnModel findProductList(long weiId,long shareId,Limit limit);
	
	
	/**
	 * 删除分享信息
	 * @param shareId分享Id 
	 * @param weiId 
	 * @param shareOne 分享人WeiId
	 * @return
	 */
	public ReturnModel updateSharePage(long weiId,long shareOne,Integer shareId);
  

    /**
     * 收藏分享页 取消分享页
     * @param weiid
     * @param pageid
     * @param key
     * @return
     */
    public ReturnModel addcollectSharePage(Long weiid, Long pageid, String key);
 
    /**
     * 判断app中的简易分享
     * @param 
     * @param sharId
     * @return
     */
    public ReturnModel addOrEdit(Long pageId);
}
