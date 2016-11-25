package com.okwei.appinterface.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.order.OrderServiceListVO;
import com.okwei.appinterface.service.order.IOrderService;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.service.address.IBasicAdressService;
import com.okwei.service.order.IBasicOrdersService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RequestMapping("/OrderList")
@RestController
public class OrderListMgtController extends SSOController {
	@Autowired
	public IOrderService orderService;
	@Autowired
	public IBasicOrdersService basicOrdersService;
	@Autowired
	public IBasicAdressService basicAdressService;

	/**
	 * 订单列表
	 * 2016-8-4
	 * @throws MapperException
	 */
	@RequestMapping("/getOrderList")
	public String find_OrderList(@RequestParam(required = false, defaultValue = "-1")short state,String UserState,@RequestParam(required = false, defaultValue = "-2")short type,int pageindex,int pagesize) throws MapperException {

		ReturnModel rm = new ReturnModel();
		LoginUser user = this.getUserOrSub();
		if (user != null && user.getWeiID() != null && user.getWeiID() > 0) {
			if ("2".equals(UserState) && user.getIsSeller() != 1) {// 不是供应商，但是查询的卖家订单列表
				rm = orderService.GetSellerProductOrderList(user.getWeiID(),state, pageindex, pagesize);
			} else {
				OrderServiceListVO order=new OrderServiceListVO();
				order.setPageindex(pageindex);
				order.setPagesize(pagesize);
				order.setUserState(UserState);
				if(type==-1){
					type=-2;
				}
				order.setType(type);
				order.setWeiId(user.getWeiID()); 
				order.setState(state); 
				rm = orderService.findProductOrderModelList(order);
			}
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	
}
