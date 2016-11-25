package com.okwei.bean.dto.shoppingcart;
import com.okwei.bean.domain.TShoppingCar;
/**
 * 加入购物车输入参数
 * @author chenhaitao
 *
 */
public class ShopCarDTO extends TShoppingCar{
	
	private static final long serialVersionUID = 1L;
	
	private Integer shopCount;
	
	public Integer getShopCount() {
		return shopCount;
	}
	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}
}
