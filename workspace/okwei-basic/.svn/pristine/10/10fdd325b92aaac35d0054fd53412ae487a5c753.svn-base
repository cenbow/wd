package com.okwei.dao.friendcircle;


import com.okwei.bean.domain.TWeiFans;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UFriends;
import com.okwei.dao.IBaseDAO;

public interface IFriendCircleDAO  extends IBaseDAO  {
	
	/**
	 * 根据微店号和朋友号得到朋友表记录
	 * @param weiId 微店号
	 * @param friendId 朋友号
	 * @return
	 */
	public UFriends getUFriends(long weiId, long friendId);
	
	
	/**
	 * 根据微店号和朋友号得到微粉表记录
	 * @param weiId 微店号
	 * @param friendId 朋友号
	 * @return
	 */
	public TWeiFans getTWeiFans(long weiId, long friendId);
	
	
	/**
	 * 取消免打扰
	 * @param myweiNO 微店号
	 * @param weiNo 用户微店号
	 * @return
	 */
	public boolean deleteDisturb(Long myweiNO, Long weiNo);
	
	/**
	 * 加入免打扰
	 * @param myweiNO 微店号
	 * @param weiNo 用户微店号
	 * @return
	 */
	public boolean saveDisturb(Long myweiNO, Long weiNo); 
	
	
	/**
	 * 删除好友请求
	 * @param weiId 微店号
	 * @param friendId 用户微店号
	 * @return
	 */
	public int deleteUfrindAppay(Long weiId, Long friendId);
	
	/**
	 * 删除用户所有好友请求
	 * @param weiId 微店号
	 * @return
	 */
	public int deleteUfrindAppayByWeiId(Long weiId);
	
	
	/**
	 * 判断是否为微粉关系
	 * @param weiId 微店号
	 * @param friendId 用户微店号
	 * @return
	 */
	public boolean IsExistsTfansAttention(Long weiId, Long friendId);
	
	/**
	 * 得到店铺关注对像
	 * @param weiId 微店号
	 * @param friendId 用户微店号
	 * @return
	 */
	public UAttention getUAttentionByparam(Long weiId, Long friendId);

	/**
	 * 得到店铺关注对像
	 * @param weiId 微店号
	 * @param friendId 用户微店号
	 * @return
	 */
	public UAttentioned getUAttentionedByparam(Long weiId, Long friendId);
}
