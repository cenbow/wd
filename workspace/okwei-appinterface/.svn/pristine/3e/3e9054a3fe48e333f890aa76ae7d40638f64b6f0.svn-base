package com.okwei.appinterface.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;








import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.order.PayParamVO;
import com.okwei.appinterface.bean.vo.order.SettlementParam;
import com.okwei.appinterface.bean.vo.order.ShoppingCartOrderParam;
import com.okwei.appinterface.enums.PPHDataType;
import com.okwei.appinterface.service.IBrandSvervice;
import com.okwei.appinterface.service.order.IPayProcess;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.enums.FromTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.bean.vo.order.BReturnOdertInfo;
import com.okwei.bean.vo.order.BShoppingCartVO;
import com.okwei.common.JsonUtil;
import com.okwei.service.IBasicShoppingCartService;
import com.okwei.service.order.IBasicPayService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ParseHelper;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * 首页统计数据
 * 
 * @author fuhao
 *
 */
@RequestMapping("/Count")
@RestController
public class CountController extends SSOController {

	private static final Log logger = LogFactory.getLog(CountController.class);
	
	@Autowired
	public IBrandSvervice brandSvervice;
	

	/**
	 * 获取统计数据 子供应商
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/queryStatistics")
	public String queryStatistics(String type) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm=brandSvervice.queryStatistics(user.getWeiID(), type);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 获取待处理数据  子供应商
	 * @param list
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/queryWaitDeal")
	public String queryWaitDeal(String type) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm=brandSvervice.queryWaitDeal(user.getWeiID(),type);
		return JsonUtil.objectToJsonStr(rm);
	}
	/**
	 * 
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/queryUpManagerStat")
	public String queryUpManagerStatic() throws MapperException
	{
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm=brandSvervice.queryUpManagerStatic(user.getWeiID());
		return JsonUtil.objectToJsonStr(rm);
	}
}
