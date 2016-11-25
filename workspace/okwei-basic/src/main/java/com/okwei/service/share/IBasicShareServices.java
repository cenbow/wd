package com.okwei.service.share;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.share.SMainDataDetailsVO;
import com.okwei.common.Limit;
import com.okwei.service.IBaseService;

public interface IBasicShareServices extends IBaseService{
 
	/**
	 * 获取分享详情
	 * @param shareId 分享Id
	 * @param weiId 
	 * @param limit
	 * @param type 1web 2wap 3app
	 * @return
	 */
	public SMainDataDetailsVO  getSMainDataDetails(long shareId,long weiId,Limit limit,int type);
	
	/**
	 * 添加分享次数
	 * @param weiId 分享人ID
	 * @param shareid 分享Id
	 * @param makeWeiID 制作人Id
	 * @param type 1web 2wap 3app
	 */
	public void addShareCount(long weiId,long shareId,int type);
	
	/**
	 * 删除分享信息
	 * @param weiId
	 * @param shareOne 分享人
	 * @param shareId 分享ID
	 * @return
	 */
	public ReturnModel updateSMainData(long weiId,long shareOne,long shareId);


}
