package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.service.user.IStaticsService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RestController
public class StatisticsController extends SSOController{
	
	@Autowired
	private IStaticsService staticsService;
	
	@RequestMapping("/getStatisticalData")
    public String getOrderStatics() throws MapperException{
		ReturnModel rm = new ReturnModel();
		LoginUser user = super.getSubuser();
		if(user==null)
		{
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = staticsService.getOrderStatics(user.getAccount());
    	return JsonUtil.objectToJsonStr(rm);
    }
	@RequestMapping("/getTodo")
	public String getTodo()throws MapperException{
		ReturnModel rm = new ReturnModel();
		LoginUser user = super.getSubuser();
		if(user==null)
		{
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = staticsService.getProductStatics(user.getAccount());
    	return JsonUtil.objectToJsonStr(rm);
	}
}
