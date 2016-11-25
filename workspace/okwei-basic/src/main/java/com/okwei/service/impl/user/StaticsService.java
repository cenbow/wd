package com.okwei.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.ChildrenOrderStaticsVO;
import com.okwei.bean.vo.user.ChildrenProductStaticsVO;
import com.okwei.dao.user.IStaticsDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.user.IStaticsService;
import com.okwei.util.RedisUtil;
@Service
public class StaticsService extends BaseService implements IStaticsService {

	@Autowired
	private IStaticsDAO staticsDao;
	
	@Override
	public ReturnModel getOrderStatics(String childrenId) {
		// TODO 自动生成的方法存根
		ReturnModel rm = new ReturnModel();
		ChildrenOrderStaticsVO vo = (ChildrenOrderStaticsVO) RedisUtil.getObject("StaticsService_OrderStatics");
		if(vo==null){
			vo = new ChildrenOrderStaticsVO();
			List<Object[]> obList = staticsDao.getChildrenSupplierStatics(childrenId);
			if(obList!=null&&obList.size()>0)
			{
				vo.setOrderCount(obList.get(0)[1]==null?0:Long.parseLong(obList.get(0)[1].toString()));
				vo.setAmount(obList.get(0)[0]==null?0.0:(Double)obList.get(0)[0]);
			}
			RedisUtil.setObject("StaticsService_OrderStatics", vo, 300);
		}
		rm.setBasemodle(vo);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		return rm;
	}

	@Override
	public ReturnModel getProductStatics(String childrenId) {
		// TODO 自动生成的方法存根
		ReturnModel rm = new ReturnModel();
		ChildrenProductStaticsVO vo=(ChildrenProductStaticsVO) RedisUtil.getObject("StaticsService_ProductStatics");
		if(vo==null){
			vo = new ChildrenProductStaticsVO();
			long payedCount = staticsDao.getSubSupplierOrderCount(childrenId, (short) 2);
			long notAuditCount = staticsDao.getSubSupplierProductCount(childrenId, (short) 1);
			vo.setNotAuditProductCount(notAuditCount);
			vo.setPayedOrderCount(payedCount);
			RedisUtil.setObject("StaticsService_ProductStatics", vo, 300);
		}
		rm.setBasemodle(vo);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		return rm;
	}

}
