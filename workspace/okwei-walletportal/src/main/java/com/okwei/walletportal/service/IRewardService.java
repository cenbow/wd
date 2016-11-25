package com.okwei.walletportal.service;

import java.util.Map;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;
import com.okwei.walletportal.bean.vo.reward.RewardVO;

public interface IRewardService extends IBaseService {
	/**
	 * 统计已支付和未支付的个数  
	 * @param weiId
	 * @return
	 */
	public Map<String, Object> get_countReward(Long weiId);
	/**
	 * 悬赏列表
	 * @param weiId 用户Id
	 * @param time	 查询时间
	 * @param isPayReward	0未支付1已支付
	 * @param limit
	 * @return
	 */
	public PageResult<RewardVO> find_UPlatformServiceOrder(Long weiId,String yearTimeStr,String monthTimeStr,short isPayReward,Limit limit );
	
 
	/**
	 * 未支付悬赏
	 * 当月未支付的金额
	 * @param weiId
	 * @param yearTimeStr
	 * @param monthTimeStr
	 * @return
	 */
	public double get_monthTotalMoney(Long weiId,String yearTimeStr,String monthTimeStr);
	
	/**
	 * 未支付悬赏 的金额
	 * @param weiId
	 * @param dt 支付类型
	 * @return
	 */
	public double get_TotalMoney(Long weiId,short dt);
}
