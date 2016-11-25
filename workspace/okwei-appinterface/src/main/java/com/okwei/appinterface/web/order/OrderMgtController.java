package com.okwei.appinterface.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.common.JsonUtil;
import com.okwei.service.order.IBaseOrderMgtService;
import com.okwei.service.shoppingcart.IBaseCartNewService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RestController
@RequestMapping("/order")
public class OrderMgtController extends SSOController {

	@Autowired
	public IBaseCartNewService cartService;
	@Autowired
	private IBaseOrderMgtService OrderService;

	/**
	 * 获取兑换
	 * @param shopCar
	 * @param receiveInfo
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/exchangeProduct")
	public String getExchangeProduct(String shopCar, String receiveInfo) throws MapperException {
		ReturnModel returnModel = new ReturnModel();
		LoginUser user = this.getUser();//this.getLoginUser();//
		if (user == null||user.getWeiID()<=0) {
			returnModel.setStatu(ReturnStatus.LoginError);
			returnModel.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(returnModel);
		}
		BAddressVO address = (BAddressVO) JsonUtil.jsonStrToObject(receiveInfo, BAddressVO.class);
		return JsonUtil.objectToJsonStr(cartService.exchangeProduct(user.getWeiID(), shopCar, address));
	}

	@ResponseBody
	@RequestMapping("/modifyPrice")
	public String modifyPrice(String usestate, String param) throws MapperException {
		LoginUser user =  this.getUserOrSub();
		ReturnModel returnModel = new ReturnModel();
		if (user!=null && user.getWeiID() != null) {
			if (user.getIsSeller() == 1) {//如果是供应商（修改单价、总价）
				switch (usestate) {
				case "1":// 修改单价
					returnModel = OrderService.updatePostUnitPrice(user.getWeiID(), param);
					break;
				case "2":// 修改总价
					returnModel = OrderService.updatePostUnitPrice(user.getWeiID(), param);
					break;
				default:
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("参数有误");
					break;
				}
			} else if (user.getBrandagent() == 1) {//代理商修改产品单价
				returnModel = OrderService.updateOrderProductPrice(user.getWeiID(), param);
			}
		}

		return JsonUtil.objectToJsonStr(returnModel);
	}
}
