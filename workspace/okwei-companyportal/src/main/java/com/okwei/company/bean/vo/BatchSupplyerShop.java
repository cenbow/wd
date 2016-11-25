package com.okwei.company.bean.vo;

/**
 * 批发市场商铺
 * @author yangjunjun
 *
 */
public class BatchSupplyerShop {
	private Long weiId;// 微店号
	private Integer bmid;// 批发市场id
	private String shopName;// 商铺名
	private String shopPic;// 商铺图片
	private String ShopBusContent;// 主营

	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	public Integer getBmid() {
		return bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPic() {
		return shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}

	public String getShopBusContent() {
		return ShopBusContent;
	}

	public void setShopBusContent(String shopBusContent) {
		ShopBusContent = shopBusContent;
	}
}
