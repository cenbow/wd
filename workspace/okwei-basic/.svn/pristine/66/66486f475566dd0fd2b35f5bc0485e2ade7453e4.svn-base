package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.USupplyChannel;

public interface IBasicUSupplyChannelMgtDAO {
	/**
	 * 
	 * @param weiID
	 * @param ChannelType
	 * @return
	 */
	USupplyChannel getUSupplyChannel(long weiID,short ChannelType,short status);
	/**
	 * 代理商或或落地店list
	 * @param list
	 * @param ChannelType
	 * @param status
	 * @return
	 */
	List<USupplyChannel> getUSupplyChannelList(List<Long> list,short ChannelType,short status);
	/**
	 * 代理商或落地店list
	 * @param weiId
	 * @param channelType
	 * @param status
	 * @return
	 */
	List<USupplyChannel> getUSupplyChannelLsitByWeiIdAndChannelTypeAndStatus(long weiId,short channelType,short status);
}
