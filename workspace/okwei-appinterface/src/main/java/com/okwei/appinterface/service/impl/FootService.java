package com.okwei.appinterface.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.dao.IFootPhoneDao;
import com.okwei.appinterface.service.IFootService;
import com.okwei.bean.domain.BFootPhone;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;

@Service("footService")
public class FootService implements IFootService {

	@Autowired
	private IFootPhoneDao phoneDao;
	@Override
	public ReturnModel getPhones() {
		ReturnModel rm= new ReturnModel();
		
		
		List<BFootPhone> list =phoneDao.getFootPhoneList();
		if(list !=null && list.size()>0)
		{
			rm.setStatu(ReturnStatus.Success);
			rm.setBasemodle(list);
			rm.setStatusreson("调用成功");
			return rm;
		}
		rm.setStatu(ReturnStatus.DataError);
		rm.setStatusreson("没有数据");
		return rm;
	}

}
