package com.okwei.bean.vo;

import java.util.List;

import com.okwei.bean.vo.product.AdPicture;


public class ShopInfoMsg {
	private String shopName; //店铺名称
	private String logo; //头像
	private Long weiNo; //店铺号
	private List<EnsureType> brandList;  //品牌担保包退
	private String marketKey;
	private String marketName;// 市场名称
	private String marketUrl;//市场wap地址
	private String shopBusKey;//
	private Integer marketId;//市场id
	private String shopBusContent; //经营范围
	private String marketAddress;
	private String region;
	private Integer weiIdentity;//店铺身份
	private List<AdPicture> shopAdPicture;//店铺轮播图
	
	private List<BrandShopModel> brandShopList;//代理商品牌列表
	
	
	public List<BrandShopModel> getBrandShopList() {
		return brandShopList;
	}
	public void setBrandShopList(List<BrandShopModel> brandShopList) {
		this.brandShopList = brandShopList;
	}
	public String getMarketUrl() {
		return marketUrl;
	}
	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}
	/**
	 * 是否关注 对方
	 */
	private int attented;
	public String getMarketAddress() {
		return marketAddress;
	}
	public void setMarketAddress(String marketAddress) {
		this.marketAddress = marketAddress;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getAttented() {
		return attented;
	}
	public void setAttented(int attented) {
		this.attented = attented;
	}
	public String getMarketKey() {
		return marketKey;
	}
	public void setMarketKey(String marketKey) {
		this.marketKey = marketKey;
	}
	public String getShopBusKey() {
		return shopBusKey;
	}
	public void setShopBusKey(String shopBusKey) {
		this.shopBusKey = shopBusKey;
	}
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Long getWeiNo() {
		return weiNo;
	}
	public void setWeiNo(Long weiNo) {
		this.weiNo = weiNo;
	}
	public String getShopBusContent() {
		return shopBusContent;
	}
	public void setShopBusContent(String shopBusContent) {
		this.shopBusContent = shopBusContent;
	}
	public List<EnsureType> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<EnsureType> brandList) {
		this.brandList = brandList;
	}
	
	public Integer getMarketId() {
		return marketId;
	}
	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public Integer getWeiIdentity() {
		return weiIdentity;
	}
	public void setWeiIdentity(Integer weiIdentity) {
		this.weiIdentity = weiIdentity;
	}
	public List<AdPicture> getShopAdPicture() {
		return shopAdPicture;
	}
	public void setShopAdPicture(List<AdPicture> shopAdPicture) {
		this.shopAdPicture = shopAdPicture;
	}
}
