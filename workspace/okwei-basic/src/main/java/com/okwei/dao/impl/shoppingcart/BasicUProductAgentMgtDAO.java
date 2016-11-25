package com.okwei.dao.impl.shoppingcart;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UProductAgent;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUProductAgentMgtDAO;

@Repository
public class BasicUProductAgentMgtDAO extends BaseDAO implements IBasicUProductAgentMgtDAO {

	@Override
	public UProductAgent getUProductAgent(long weiId) {
		// TODO Auto-generated method stub
		return super.get(UProductAgent.class, weiId);
	}
}
