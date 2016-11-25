package com.okwei.walletportal.dao;

import com.okwei.bean.domain.UPublicBanks;
import com.okwei.dao.IBaseDAO;

public interface ICompanyAccountDAO extends IBaseDAO {

	UPublicBanks getCompanyAccountByWeiId(Long weiId);

}
