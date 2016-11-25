package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.order.DComanpyVO;
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

/**
 * 订单列表
 * 
 * @author fuhao
 *
 */
@RequestMapping("/OrderList")
@RestController
public class OrderListController extends SSOController {

	// private static final Log logger =
	// LogFactory.getLog(OrderListController.class);

	@Autowired
	public IOrderService orderService;

	@Autowired
	public IBasicOrdersService basicOrdersService;

	@Autowired
	public IBasicAdressService basicAdressService;

	/**
	 * 收货地址列表
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/find_CustomerAddressesList")
	public String find_CustomerAddressesList(Long weiId) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = this.getUserOrSub();
		if (user != null) {
			if (weiId == null || weiId < 1) {
				weiId = user.getWeiID();
			}
			rm = orderService.getCustomerAddressesList(weiId);
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 新增/ 修改 地址列表
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/update_CustomerAddress")
	public String update_OrderList(@RequestParam(required = false, defaultValue = "") String type, @RequestParam(required = false, defaultValue = "") String model) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			switch (type) {
			case "new":
				// 新增收货地址
				rm = orderService.insertAddress(model, user.getWeiID());
				break;
			case "update":
				// 修改收货地址
				rm = orderService.updateAddress(model, user.getWeiID());
				break;
			case "delete":
				// 修改收货地址
				rm = orderService.deleteAddress(model, user.getWeiID());
				break;
			default:
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("参数错误");
				break;
			}
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 订单列表
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/find_OrderList")
	public String find_OrderList(OrderServiceListVO order) throws MapperException {

		ReturnModel rm = new ReturnModel();
		LoginUser user = this.getUserOrSub();
		if (user != null && user.getWeiID() != null && user.getWeiID() > 0) {
			if (user.judgePower("Order_Query")) {
				order.setWeiId(user.getWeiID());
				if ("2".equals(order.getUserState()) && user.getIsSeller() != 1) {// 不是供应商，但是查询的卖家订单列表
					rm = orderService.GetSellerProductOrderList(user.getWeiID(), order.getState(), order.getPageindex(), order.getPagesize());
				} else {
					rm = orderService.findProductOrderModelList(order);
				}
			} else {
				rm.setStatu(ReturnStatus.LoginError);
				rm.setStatusreson("用户权限不足！");
			}
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 订单详情
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/get_OrderDetails")
	public String get_OrderDetails(OrderServiceListVO order) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = this.getUserOrSub();
		if (user != null) {
			order.setWeiId(user.getWeiID());
			rm = orderService.getOrderDetails(order.getSupplyOrderid(), order.getUserType(), user.getWeiID());

		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 确认或取消铺货订单
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/intsert_advancedOrder")
	public String intsert_advancedOrder(@RequestParam(required = false, defaultValue = "") String param) throws MapperException {

		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null && user.getWeiID() > 0) {
			rm = orderService.insertBookOrder(param, user.getWeiID());
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}

		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 买家确认收货
	 * 
	 * @param weiid
	 * @param supOrderID
	 * @return
	 */
	@RequestMapping("/update_ConfirmationReceipt")
	public String update_ConfirmationReceipt(@RequestParam(required = false, defaultValue = "") String supOrderID) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null && user.getWeiID() > 0) {
			rm = basicOrdersService.updateConfirmationReceipt(user.getWeiID(), supOrderID);
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 卖家确认收货
	 * 
	 * @param weiid
	 * @param supOrderID
	 * @return
	 */
	@RequestMapping("/update_Confirmcargo")
	public String update_Confirmcargo(@RequestParam(required = false, defaultValue = "") String backOrder) throws MapperException {

		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null && user.getWeiID() > 0) {
			rm = basicOrdersService.updateConfirmcargo(user.getWeiID(), backOrder);
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 供应商发货
	 * 
	 * @param dc
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/update_sendGoods")
	public String update_sendGoods(DComanpyVO dc) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			rm = basicOrdersService.sendGoods(dc.getSupplierOrderId(), user.getWeiID(), dc.getDComanpyNo(), dc.getDeliveryCompany(), dc.getDeliveryOrder());
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 买家发货
	 * 
	 * @param dc
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/buyersDelivery")
	public String buyersDelivery(DComanpyVO dc) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			rm = basicOrdersService.buyersDelivery(user.getWeiID(), dc.getBackOrder(), dc.getDeliveryOrder(), dc.getDeliveryCompany());
		} else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(rm);
	}

}
