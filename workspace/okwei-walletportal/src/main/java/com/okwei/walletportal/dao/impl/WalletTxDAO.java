package com.okwei.walletportal.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UBankCard;
import com.okwei.bean.domain.UCancleOrderAmoutDetail;
import com.okwei.bean.domain.UPublicBanks;
import com.okwei.bean.domain.UWalletDetails;
import com.okwei.bean.enums.UserAmountType;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.util.DateUtils;
import com.okwei.walletportal.dao.IWalletTxDAO;

@Repository
public class WalletTxDAO extends BaseDAO implements IWalletTxDAO {

	@Override
	public List<UBankCard> getBankCards(long weiID) {
		if (weiID < 1) {
			return null;
		}

		String hql = "from UBankCard p where p.weiId =? and p.cardType =2";

		return super.find(hql, weiID);
	}

	@Override
	public long getMothTxNum(long weiID) {
		if (weiID < 1) {
			return 0;
		}
		String countHql ="select count(*) from UCancleOrderAmoutDetail p where p.weiId=? and "
				+ "p.createTime>=? and p.type=1 and p.state=1";
		
		return super.count(countHql, weiID,DateUtils.getMonFirstDay());
/*		String hql = "select count(*) from UWalletDetails p where p.weiId =? and p.createTime>= ? and p.type=?";
		return super.count(hql, weiID, DateUtils.getMonFirstDay(),Short.valueOf(UserAmountType.tixian.toString()));*/
	}

	@Override
	public UBankCard getBankCard(long cardID, long weiNo) {
		if (cardID < 1) {
			return null;
		}
		String hql = "from UBankCard p where p.id =? and weiId=?";
		Object[] bObjects = new Object[2];
		bObjects[0] = cardID;
		bObjects[1] = weiNo;

		List<UBankCard> lists = super.find(hql, bObjects);
		if (lists != null && lists.size() > 0) {
			return lists.get(0);
		}
		return null;
	}

	@Override
	public double getTxingAmout(long weiID) {
		if (weiID < 1) {
			return 0;
		}

		String hql = "select sum(amount) from UCancleOrderAmoutDetail p where p.weiId =? and type=1 and state=0";
		List<Object> details = super.find(hql, weiID);
		if (details != null && details.size() > 0) {
			return (double) details.get(0);
		}

		return 0;
	}

        
	@Override
        public UPublicBanks getPublicBanks(long weiid)
        { 
	    String hql = "from UPublicBanks u where u.weiId=? and u.state=1";
	    List<UPublicBanks> pb = find(hql,weiid);
	    if(pb != null && pb.size() > 0){
	        return pb.get(0);
	    }
            return null;
        }

}
