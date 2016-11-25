package com.okwei.walletportal.service;

import com.okwei.bean.domain.UPublicBanks;
import com.okwei.bean.vo.LoginUser;
import com.okwei.service.IBaseService;

public interface ICompanyAccountService extends IBaseService{

	UPublicBanks getCompanyAccount(long weiId) throws Exception;

	String addOrUpdateCompanyAccount(UPublicBanks accountInfo,LoginUser user,boolean flag) throws Exception;
	
	boolean getPublicBanksPassCount(long weiId) throws Exception;

}
	