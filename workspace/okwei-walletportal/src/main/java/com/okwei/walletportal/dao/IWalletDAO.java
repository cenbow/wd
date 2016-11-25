package com.okwei.walletportal.dao;

import java.util.List;

import com.okwei.bean.domain.UBankCard;
import com.okwei.dao.IBaseDAO;

public interface IWalletDAO extends IBaseDAO {

	List<UBankCard> getBandCardByWeiId(Long weiId);

	UBankCard getByCardId(Long weiId, String bankCard);

}
