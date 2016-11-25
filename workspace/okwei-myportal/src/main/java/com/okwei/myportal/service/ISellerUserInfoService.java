package com.okwei.myportal.service;

import java.util.List;

import com.okwei.bean.domain.UCustomerAddr; 
import com.okwei.myportal.bean.vo.AddressVO;

public interface ISellerUserInfoService
{

    /**
     * 获取个人收货地址
     * 
     * @param weiid
     * @return
     */
    public List<AddressVO> getAddressList(Long weiid);

    /**
     * 添加或者修改收货地址
     * 
     * @param model
     * @return
     */
    public int saveOrUpdateAdd(UCustomerAddr model);

    /**
     * 删除收货地址
     * 
     * @param caddrID
     * @return
     */
    public int deleteAddress(long weiid,int caddrID);

    /**
     * 设为默认
     * 
     * @param weiid
     * @param caddrID
     * @return
     */
    public int setDefault(long weiid, int caddrID);

}
