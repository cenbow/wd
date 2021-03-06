package com.okwei.walletportal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UPublicBanks;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.walletportal.dao.ICompanyAccountDAO;

@Repository
public class CompanyAccountDAO extends BaseDAO implements ICompanyAccountDAO {

	@Override
	public UPublicBanks getCompanyAccountByWeiId(Long weiId) {
		String hql = "from UPublicBanks where weiId = ? ";
		List<UPublicBanks> list = super.find(hql, weiId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
