package com.okwei.service.friendcircle;

import java.util.List;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.service.IBaseService;

public interface IFriendCircleListService extends IBaseService {

	/**
	 * 获取联系人
	 * @param weiid
	 * @param keyword
	 * @param scope（1下游分销商，2粉丝，4好友 ）
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel find_ContactList(Long weiid,String keyword,int scope,int pageIndex,int pageSize);
	/**
	 * 获取朋友圈信息
	 * @param weiid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel  find_sharelist(Long weiid,int pageIndex,int pageSize);
	/**
	 * 发微店圈
	 * @param shareIds
	 * @param scope
	 * @param weiid
	 * @return
	 */
	public ReturnModel add_FriendShare(List<Long> shareIds,int scope,Long weiid);
}
