package com.okwei.dao.address;

import java.util.List;

import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.dao.IBaseDAO;

public interface IBasicAdressDAO extends IBaseDAO{
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
    public int deleteAddress(long weiid, int caddrID);

    /**
     * 设为默认收货地址
     * 
     * @param caddrID
     * @return
     */
    public int setDefault(int caddrID);

    
    /**
     * 设为默认落地店地址
     * @param caddrID
     * @return
     */
    public int setShopAddress(int caddrID);
    /**
     * 取消默认收货地址
     * @param weiid
     * @return
     */
    public int cancelDefault(Long weiid);
    
    /**
     *	取消默认落地店
     * @param weiid
     * @return
     */
    public int cancelShopAddress(Long weiid);
    /**
     *	取消默认落地店
     * @param weiid
     * @return
     */
    public int cancelShopAddress(Long weiid,Integer cid);
    /**
     * 取消默认
     * 
     * @param weiid
     * @return
     */
    public int cancelDefault(Long weiid,Integer cid);

	UCustomerAddr getCustomerAddressById(int addressId, long weiId);
}
