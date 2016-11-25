package com.okwei.dao.impl.address;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.dao.address.IBasicAdressDAO;
import com.okwei.dao.impl.BaseDAO;

@Repository
public class BasicAdressDAO extends BaseDAO implements IBasicAdressDAO {

    @Override
    public List<UCustomerAddr> getAddressList(Long weiid) {
	String hql = "from UCustomerAddr where weiId=?";
	Object[] params = new Object[1];
	params[0] = weiid;
	return super.find(hql, params);
    }
    
    @Override
	public UCustomerAddr  getCustomerAddressById(int addressId, long weiId) {
		String hql = " from UCustomerAddr t where t.caddrId=? and t.weiId=? "; 
		Object[] b = new Object[2];// 参数列表
		b[0] = addressId;
		b[1] = weiId;
		return super.getUniqueResultByHql(hql, b);
	}

    @Override
    public int deleteAddress(long weiid, int caddrID) {
	String hql = "delete UCustomerAddr where caddrId=? and weiId=?";
	Object[] params = new Object[2];
	params[0] = caddrID;
	params[1] = weiid;
	return super.executeHql(hql, params);
    }

    @Override
    public int addCustomerAddr(UCustomerAddr model) {
	super.save(model);
	return model.getCaddrId();
    }

    @Override
    public int updateCustomerAddr(UCustomerAddr model) {
	if (model.getCaddrId() == null || model.getCaddrId().longValue() <= 0)
	    return 0;
	super.update(model);
	return 1;
    }

    @Override
    public int setDefault(int caddrID) {
	String hql = "update UCustomerAddr set isDefault=1 where caddrId=?";
	Object[] params = new Object[1];
	params[0] = caddrID;
	return super.executeHql(hql, params);
    }
    
    
    @Override
    public int cancelShopAddress(Long weiid) {
	String hql = "update UCustomerAddr set isShopAddress=0 where weiId=?";
	Object[] params = new Object[1];
	params[0] = weiid;
	return super.executeHql(hql, params);
    }
    @Override
    public int setShopAddress(int caddrID) { 
	String hql = "update UCustomerAddr set isShopAddress=1 where caddrId=?";
	Object[] params = new Object[1];
	params[0] = caddrID;
	return super.executeHql(hql, params);
    } 
    @Override
    public int cancelDefault(Long weiid) {
	String hql = "update UCustomerAddr set isDefault=0 where weiId=?";
	Object[] params = new Object[1];
	params[0] = weiid;
	return super.executeHql(hql, params);
    }

    @Override
    public UCustomerAddr getUCustomerAddr(int caddrID) {
	return super.get(UCustomerAddr.class, caddrID);
    }

    @Override
    public int cancelDefault(Long weiid,Integer cid)
    {
        String hql = "update UCustomerAddr set isDefault=0 where weiId=? and caddrId!=?";
        Object[] params = new Object[2];
        params[0] = weiid;
        params[1] = cid;
        return super.executeHql(hql, params);
    }
    
    public int cancelShopAddress(Long weiid,Integer cid) {
        String hql = "update UCustomerAddr set isShopAddress=0 where weiId=? and caddrId!=?";
        Object[] params = new Object[2];
        params[0] = weiid;
        params[1] = cid;
        return super.executeHql(hql, params);
    }
}
