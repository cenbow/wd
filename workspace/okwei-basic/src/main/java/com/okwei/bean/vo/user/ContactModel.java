package com.okwei.bean.vo.user;

import java.io.Serializable;

public class ContactModel implements Serializable
{
    private static final long serialVersionUID = 7036020192373421077L;

	/**
	 *  昵称
	 */
	private String namePrefix;
	/**
	 * 显示数据拼音的首字母
	 */
	private String nameSort;
	private Long weiId;
	/**
	 * 店铺名称
	 */
	private String shopName;
	private String logo;
	/**
	 *  是否天然关系，参考G05
	 */
	private Integer isNature;
	/**
	 *  是否分销商关系
	 */
	private Integer isReseller;
	/**
	 *  是否好友关系
	 */
	private Integer isFriend;
	/**
	 *  是否粉丝关系
	 */
	private Integer isFan;
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public String getNameSort() {
		return nameSort;
	}
	public void setNameSort(String nameSort) {
		this.nameSort = nameSort;
	}
	public Long getWeiId() {
		return weiId;
	}
	public void setWeiId(Long weiId) {
		this.weiId = weiId;
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
	public Integer getIsNature() {
		return isNature;
	}
	public void setIsNature(Integer isNature) {
		this.isNature = isNature;
	}
	public Integer getIsReseller() {
		return isReseller;
	}
	public void setIsReseller(Integer isReseller) {
		this.isReseller = isReseller;
	}
	public Integer getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(Integer isFriend) {
		this.isFriend = isFriend;
	}
	public Integer getIsFan() {
		return isFan;
	}
	public void setIsFan(Integer isFan) {
		this.isFan = isFan;
	}
	
	
}
