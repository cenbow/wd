package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.USupplyChannel;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUSupplyChannelMgtDAO;


@Repository
public class BasicUSupplyChannelMgtDAO extends BaseDAO implements IBasicUSupplyChannelMgtDAO {

	@Override
	public USupplyChannel getUSupplyChannel(long weiID, short ChannelType,
			short status) {
		// TODO Auto-generated method stub
		String hql = "from USupplyChannel where weiId = ? and channelType = ? and state = ?";
		return super.getNotUniqueResultByHql(hql,weiID,ChannelType,status);
	}

	@Override
	public List<USupplyChannel> getUSupplyChannelList(List<Long> list,
			short ChannelType, short status) {
		// TODO Auto-generated method stub
		if(list == null || list.size() == 0)
		{
			return new ArrayList<USupplyChannel>();
		}
		String hql = "from USupplyChannel where weiId in(:list) and channelType =:ChannelType and state = :status";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("ChannelType", ChannelType);
		map.put("status", status);
		return super.findByMap(hql,map);
	}

	@Override
	public List<USupplyChannel> getUSupplyChannelLsitByWeiIdAndChannelTypeAndStatus(
			long weiId, short channelType, short status) {
		// TODO Auto-generated method stub
		String hql = "from USupplyChannel where weiId = ? and channelType = ? and state = ?";
		return super.find(hql,weiId,channelType,status);
	}
}
