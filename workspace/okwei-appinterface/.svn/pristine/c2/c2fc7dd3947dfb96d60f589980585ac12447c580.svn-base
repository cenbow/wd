package com.okwei.appinterface.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.appinterface.dao.IFootPhoneDao;
import com.okwei.bean.domain.BFootPhone;
import com.okwei.dao.impl.BaseDAO;
@Repository
public class FootPhoneDao extends BaseDAO  implements IFootPhoneDao {

	@Override
	public List<BFootPhone> getFootPhoneList() {
		return super.loadAll(BFootPhone.class);
	}

}
