package com.okwei.myportal.dao;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.DistributionOrderDTO;
import com.okwei.myportal.bean.vo.ProductOrderVO;
import com.okwei.myportal.bean.vo.SaleOrderVO;

import net.sf.ehcache.search.impl.BaseResult;

public interface IDistributionOrderDAO
{

    /**
     * 分页获取我的分销商品
     * 
     * @param weiID
     * @param param
     * @param limit
     * @return
     */
    public PageResult<SaleOrderVO> getDistributionOrders(long weiID,DistributionOrderDTO dto,Limit limit);
}
