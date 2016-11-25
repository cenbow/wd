package com.okwei.myportal.service;

import java.util.Date;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.SaleOrderVO;

public interface ISaleOrderService
{
    public PageResult<SaleOrderVO> getSaleOrderVOList(long weiid,Limit limit,String title,String supName,Date start,Date end);
}
