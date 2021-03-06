package com.okwei.myportal.service;

import com.okwei.myportal.bean.vo.ResultMsg;

public interface IRepaymentService {
    /**
     * 重新生成支付订单
     * 
     * @param orderNo
     * @return
     */
    public ResultMsg getNewPayOrder(String orderNo);
}
