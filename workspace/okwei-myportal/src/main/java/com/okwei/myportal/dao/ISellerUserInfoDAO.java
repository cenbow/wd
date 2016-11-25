package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.dao.IBaseDAO;

public interface ISellerUserInfoDAO extends IBaseDAO
{
    /**
     * 获取个人收货地址
     * 
     * @param weiid
     * @return
     */
    public List<UCustomerAddr> getAddressList(Long weiid);

    /**
     * 获取收货地址model
     * 
     * @param caddrID
     * @return
     */
    public UCustomerAddr getUCustomerAddr(int caddrID);

    /**
     * 添加收货地址
     * 
     * @param model
     * @return
     */
    public int addCustomerAddr(UCustomerAddr model);

    /**
     * 修改收货地址
     * 
     * @param model
     * @return
     */
    public int updateCustomerAddr(UCustomerAddr model);

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
     * @param caddrID
     * @return
     */
    public int setDefault(int caddrID);

    /**
     * 取消默认
     * 
     * @param weiid
     * @return
     */
    public int cancelDefault(Long weiid,Integer cid);

}
