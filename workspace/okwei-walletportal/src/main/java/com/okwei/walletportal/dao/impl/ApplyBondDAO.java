package com.okwei.walletportal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UBankCard;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UPublicBanks;
import com.okwei.bean.domain.UTuizhu;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.BondType;
import com.okwei.bean.enums.OperationEnum;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.walletportal.dao.IApplyBondDAO;

@Repository
public class ApplyBondDAO extends BaseDAO implements IApplyBondDAO {

    @Override
    public UBatchSupplyer getBatchSupplyer(long weiid) {
	return super.get(UBatchSupplyer.class, weiid);
    }

    @Override
    public UYunSupplier getYunSupplier(long weiid) {
	return super.get(UYunSupplier.class, weiid);
    }

    @Override
    public long getShelveCount(long weiid) {
	String hql = "select count(1) from PClassProducts t where t.weiId=:weiid and t.sendweiId=:weiid and t.state=:state";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiid", weiid);
	params.put("state", (short) 1);
	return super.countByMap(hql, params);
    }

    @Override
    public long getOrderCount(long weiid) {
	String hql = "select count(1) from OSupplyerOrder t where t.supplyerId=:weiid and t.state in(:state)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiid", weiid);
	Short[] state = new Short[] { Short.parseShort(OrderStatusEnum.Payed.toString()), Short.parseShort(OrderStatusEnum.Deliveried.toString()), Short.parseShort(OrderStatusEnum.Accepted.toString()), Short.parseShort(OrderStatusEnum.Refunding.toString()), Short.parseShort(OrderStatusEnum.Sured.toString()), Short.parseShort(OrderStatusEnum.PayedDeposit.toString()) };
	params.put("state", state);
	return super.countByMap(hql, params);
    }

    @Override
    public long getIsApply(BondType type, long weiid) {
	String hql = "select count(1) from  UTuizhu t where t.weiId=:weiid and t.type in (:type) and t.state in(:state)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiid", weiid);
	if (type.equals(BondType.BatchSupplier)) {
	    params.put("type", new Short[] { 2, 4 });
	} else {
	    params.put("type", new Short[] { Short.parseShort(type.toString()) });
	}
	Short[] state = new Short[] { Short.parseShort(OperationEnum.Submit.toString()), Short.parseShort(OperationEnum.Ok.toString()), Short.parseShort(OperationEnum.No.toString()) };
	params.put("state", state);
	return super.countByMap(hql, params);
    }

    @Override
    public UTuizhu getTuizhu(int type, long weiid) {
	String hql = "from UTuizhu t where t.weiId=:weiid and t.type in (:type) order by t.tid desc";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiid", weiid);
	if (type == 2) {
	    params.put("type", new Short[] { 2, 4 });
	} else {
	    params.put("type", new Short[] { (short) type });
	}
	List<UTuizhu> list = super.findByMap(hql, params);
	if (list != null && list.size() > 0)
	    return list.get(0);
	return null;
    }

    @Override
    public List<UPublicBanks> getPublicBanks(long weiid) {
	String hql = "from UPublicBanks t where t.weiId=? and t.state=?";
	return super.find(hql, new Object[] { weiid, Short.parseShort(OperationEnum.Ok.toString()) });
    }

    @Override
    public List<UBankCard> getBankCards(long weiid) {
	String hql = "from UBankCard where weiId = ? ";
	return super.find(hql, weiid);
    }

}
