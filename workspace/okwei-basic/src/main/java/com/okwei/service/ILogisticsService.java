package com.okwei.service;

import com.okwei.bean.vo.TransVO;


/**
 * 获取物流相关信息
 * @author Administrator
 *
 */

public interface ILogisticsService extends IBaseService {

    /**
     * 获取物流信息
     * @param deliveryOrder 物流单号
     * @return
     */
    public TransVO getLogisticsInfo(String supOrderID);
    
    /**
     * 获取物流信息by物流单号，物流公司代号
     * @param orderNo 运单号
     * @param companyNo 物流公司代号
     * @return
     */
    public String getInfoByNo(String orderNo,String companyNo);
    
    /**
     * 获取物流信息by物流单号，物流公司名称
     * @param orderNo 运单号
     * @param companyName 物流公司名称
     * @return
     */
    public String getInfoByName(String orderNo,String companyName);
}
