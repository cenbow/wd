package com.okwei.service;

import java.util.List;

import com.okwei.bean.domain.UPushMessage;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.UWeiSeller;

public interface IBaseCommonService {
	/**
	 * 消息推送
	 * @param msg
	 * @return
	 */
	public boolean insertSendPushMsg(UPushMessage msg);
	
	/**
	 * 获取店铺名称
	 * @param weiNo
	 * @return
	 */
	public String getShopNameByWeiId(Long weiNo);
	/**
	 * 获取用户联系方式
	 * @param weiNo
	 * @return
	 */
	public String getContactPhone(Long weiNo);
	/**
	 * 获取联系QQ
	 * @param weiNo
	 * @return
	 */
	public String getQQ(Long weiNo);
	/**
	 * 获取店铺图片
	 * @param weiNo
	 * @return
	 */
	public String getShopImageByWeiId(Long weiNo);
	/**
	 * 获取店铺主营
	 * @param weiNo
	 * @return
	 */
	public String getBusContentByWeiId(Long weiNo);
	/**
	 * 获取用户信息
	 * @param weiids
	 * @return
	 */
	public List<UShopInfo> find_shopinfolist(List<Long> weiids);
	/**
	 * 获取店铺名称
	 * @param shoplist
	 * @param weiid
	 * @return
	 */
	public String getShopName(List<UShopInfo> shoplist,Long weiid);
	/**
	 * 获取店铺头像
	 * @param shoplist
	 * @param weiid
	 * @return
	 */
	public String getShopImg(List<UShopInfo> shoplist,Long weiid);
	/**
	 * zy
	 * 获取微店信息
	 * @param weiids
	 * @return
	 */
	public List<UWeiSeller> find_Userlist(List<Long> weiids);
	
}
