package com.okwei.service.friendcircle;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.service.IBaseService;
import com.sdicons.json.mapper.MapperException;

public interface IFriendCircleService extends IBaseService {

	/**
	 *	请求加好友
	 * @param weiNo 用户微店号 	
	 * @param requestExtra	请求附加信息，留做备用，可为空
	 * @return
	 */
	public ReturnModel friendRequest(Long weiNo,Long friendId,String requestExtra);
	
	
	/**
     * 回复加好友请求
     * @param friendId 用户微店号 (请求好友微店号)
     * @param reply=1 请求回复，1为同意，2为拒绝
     * @param replyExtra 回复的附加信息，如拒绝理由，留做备用，可为空
     * @param requestCode 加好友请求代码
     * @return
     * @throws MapperException
     */
	public ReturnModel replyFriendRequest(Long weiNo,Long friendId,Short reply,String replyExtra,String requestCode);
	
	/**
	 *	删除好友
	 * @param weiNo 用户微店号 	
	 * @param friendId 好友微店号	
	 * @return
	 */
	public ReturnModel deleteFriend(Long weiNo,Long friendId);
	
	/**
	 * 关注/取消关注微店
	 * @param weiNo=1111 //用户微店号 
	 * @param attention=1 //关注微店，1为关注，0为取消关注
	 */
	public ReturnModel patAttentionTo(Long weiNo,Long friendId,Short attention);
	
	/**
     * 屏蔽/取消屏蔽微店圈
     * @param weiNo 用户微店号 
     * @param deny 屏蔽微店圈，1为屏蔽，0为允许
     */
	public ReturnModel circleDeny(Long weiNo,Long friendId,Short deny);
	
	
	/**
     * 免打扰/取消免打扰
     * @param weiNo 用户微店号 
     * @param deny  设置为免打扰，1为免打扰，0为取消免打扰
     */
	public ReturnModel disturbDeny(Long myweiNO, Long weiNo, Short deny);

	/**
	 * 点赞
	 * @param weiID
	 * @param pageId
	 * @param shareOne
	 * @param deny
	 * @param id
	 * @return
	 */
	public ReturnModel updateLikeSharePage(Long weiID, Long friendShareId, Short deny);
	
	/**
     * 获取加好友请求列表
     * @param weiNo 微店号 
     */
	public ReturnModel  findfriendRequestlist(Long weiid,int pageIndex,int pageSize);
	
	
	/**
     * 清除加好友请求列表
     * @param weiNo 需清除好友请求消息的对方微店号，-1为清除所有请求
     */
	public ReturnModel  doclearFriendRequest(Long friendId,Long weiId);
	
}
