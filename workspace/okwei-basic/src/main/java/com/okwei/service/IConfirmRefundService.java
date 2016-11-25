package com.okwei.service;


import com.okwei.bean.domain.TOrderBackTotal;

public interface IConfirmRefundService {

    /**
     * 确认退款
     * 
     * @param sellerID
     *            卖家微店号
     * @param refundID
     *            退款ID
     * @return
     */
    public void refundPenny(long sellerID,TOrderBackTotal refOrder);
    
    /**
     * 取消落地店的身份
     */
    public void cancelLandShop(String supOrderID, Long proID, Long buyID);
}
