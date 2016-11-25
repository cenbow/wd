package com.okwei.appinterface.web;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.okwei.appinterface.bean.vo.ShoppingCartCount;
import com.okwei.appinterface.service.shopingcart.IShoppingCartMgtService;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.shoppingcart.ShoppingCar;
import com.okwei.common.JsonUtil;
import com.okwei.service.shoppingcart.IBasicShoppingCartMgtService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * 添加购物车
 * 
 * @author
 *
 */
@RestController
public class ShoppingCartMgtController extends SSOController {
	private static final Log logger = LogFactory.getLog(OrderListController.class);
	@Autowired
	public IBasicShoppingCartMgtService iBasicShoppingCartMgtService;
	@Autowired
	private IShoppingCartMgtService iShoppingCartMgtService;

	/**
	 * 添加购物车
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/addShoppingCart")
	public String shoppingCartAdd(String shopCar, Long sharePageProducer, Long shareOne, Long sharePageId) throws MapperException {
		ReturnModel returnModel = new ReturnModel();
		LoginUser user = this.getUserOrSub();
		if (user == null) {
			returnModel.setStatu(ReturnStatus.LoginError);
			returnModel.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(returnModel);
		}
		returnModel = iBasicShoppingCartMgtService.addShoppingCart(shopCar, user.getWeiID(), user, sharePageProducer == null ? 0L : sharePageProducer, shareOne == null ? 0L : shareOne, sharePageId == null ? 0L : sharePageId);
		return JsonUtil.objectToJsonStr(returnModel);
	}

	/**
	 * 购物车列表
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/getShopCartList")
	public String shoppingCartList() throws MapperException {
		ReturnModel returnModel = new ReturnModel();
		LoginUser user = this.getUserOrSub();
		if (user == null) {
			returnModel.setStatu(ReturnStatus.LoginError);
			returnModel.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(returnModel);
		}
		// 更新购物车状态
		iBasicShoppingCartMgtService.updateTShoppingCarState(user.getWeiID());
		returnModel = iBasicShoppingCartMgtService.getShoppingCartList(user.getWeiID());
		return iShoppingCartMgtService.getShoppingCarListToJson((List<ShoppingCar>) returnModel.getBasemodle());
	}

	/**
	 * 修改购物车
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/updateShoppingCart")
	public String updateShoppingCart(long scid, long sellerWeiId, int count) throws MapperException {
		ReturnModel returnModel = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			returnModel.setStatu(ReturnStatus.LoginError);
			returnModel.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(returnModel);
		}
		returnModel = iBasicShoppingCartMgtService.updateTShoppingCar(user.getWeiID(), scid, sellerWeiId, count);
		return JsonUtil.objectToJsonStr(returnModel);
	}

	/**
	 * 删除购物车
	 */
	@RequestMapping("/deleteShoppingCart")
	public String updateShoppingCart(long scid) throws MapperException {
		ReturnModel returnModel = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			returnModel.setStatu(ReturnStatus.LoginError);
			returnModel.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(returnModel);
		}
		returnModel = iBasicShoppingCartMgtService.delTShoppingCar(user.getWeiID(), scid);
		return JsonUtil.objectToJsonStr(returnModel);
	}

	/**
	 * 购物车个数
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/getShoppingCartCount")
	public String getShoppingCartCount() throws MapperException {
		ReturnModel returnModel = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			returnModel.setStatu(ReturnStatus.LoginError);
			returnModel.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(returnModel);
		}
		iBasicShoppingCartMgtService.updateTShoppingCarState(user.getWeiID());
		ShoppingCartCount shoppingCartCount = new ShoppingCartCount();
		shoppingCartCount.setProductCount(iBasicShoppingCartMgtService.getShoppingCartCount(user.getWeiID(), (short) 1));
		returnModel.setBasemodle(shoppingCartCount);
		returnModel.setStatu(ReturnStatus.Success);
		returnModel.setStatusreson("成功!");
		// 更新购物车状态;
		return JsonUtil.objectToJsonStr(returnModel);
	}

	/**
	 * 添加购物车
	 * 
	 * @throws MapperException
	 */
	@RequestMapping("/shoppingCartAddStyleList")
	public String shoppingCartAddStyleList(String shopCar) throws MapperException {
		ReturnModel returnModel = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			returnModel.setStatu(ReturnStatus.LoginError);
			returnModel.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(returnModel);
		}
		returnModel = iBasicShoppingCartMgtService.addShoppingCartByStyleList(shopCar, user);
		return JsonUtil.objectToJsonStr(returnModel);
	}
}
