package com.okwei.appinterface.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.appinterface.dao.IKeywordsDao;
import com.okwei.bean.domain.BFilterWords;
import com.okwei.dao.impl.BaseDAO;

@Repository
public class KeywordsDao  extends BaseDAO  implements IKeywordsDao {

	@Override
	public BFilterWords getFilterWords() {
		String strHql=" from BFilterWords";		
		List<BFilterWords> list=super.find(strHql, null);
		if(list!=null && list.size()>0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public void saveFilterWords(BFilterWords fw) {
		super.saveOrUpdate(fw);
		return;
		
	}

}
