package com.okwei.appinterface.service.shopingcart;

import java.util.List;

import com.okwei.bean.vo.shoppingcart.ShoppingCar;
import com.okwei.service.IBaseService;

public interface IShoppingCartMgtService extends IBaseService {
	/**
	 * ShoppingCar转换成对象
	 * @param list
	 * @return
	 */
	String getShoppingCarListToJson(List<ShoppingCar> list);
}
