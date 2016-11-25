package com.okwei.service;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.agent.BaseResultVO;
import com.okwei.bean.vo.agent.HalfProductVO;
import com.okwei.bean.vo.agent.HeadInfo;



public interface IAgentCommonService {
	  /**
     * 获取购物车数量
     * 
     * @param weiid
     * @return
     */
    public long getCartCount(long weiid);

    /**
     * 获取头部公共信息
     * @return
     */
    public HeadInfo getHeadInfo();
    
    /**
     * 获取半价领取商品信息
     * @return
     */
    public HalfProductVO getProductInfo();
    
    /**
     * 半价购买
     * @param weiid
     * @param addrid
     * @param num
     * @return
     */
    public ReturnModel halfBuy(long weiid,int addrid,int num);
}
