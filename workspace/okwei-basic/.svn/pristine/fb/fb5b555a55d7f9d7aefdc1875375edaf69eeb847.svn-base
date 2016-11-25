package com.okwei.dao.impl.user;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PShelverCount;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IPShelverCountMgtDAO;


@Repository
public class PShelverCountMgtDAO extends BaseDAO implements IPShelverCountMgtDAO {

	@Override
	public PShelverCount getPShelverCount() {
		// TODO Auto-generated method stub
		String hql = " from PShelverCount";
		return super.getNotUniqueResultByHql(hql);
	}
}
