package com.okwei.pay.service;

import com.okwei.pay.bean.vo.ResultMsg;

public interface IRepaymentService {
    /**
     * 重新生成支付订单
     * 
     * @param orderNo
     * @return
     */
    public ResultMsg getNewPayOrder(String orderNo);
}
