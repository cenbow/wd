package com.okwei.supplyportal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.supplyportal.dao.IUserInfoDAO;

@Repository
public class UserInfoDAO extends BaseDAO implements IUserInfoDAO {

    @Override
    public List<UCustomerAddr> getAddressList(Long weiid) {
	String hql = "from UCustomerAddr where weiId=?";
	Object[] params = new Object[1];
	params[0] = weiid;
	return super.find(hql, params);
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

}
