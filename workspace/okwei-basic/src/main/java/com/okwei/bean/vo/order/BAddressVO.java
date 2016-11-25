package com.okwei.bean.vo.order;

import java.io.Serializable;

/**
 * 收货地址
 * @author Administrator
 *
 */
public class BAddressVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    //--------------5.0参数----------------
    private String addressId;
	private String receiveName;
    private String phone;
    private String qq;
    private String detailAddr;
    private String address;//全地址
   //---------扩展字段--
    private Integer province;
    private Integer city;
    private Integer district;
    private int isDefault;//是否默认
    private int isShopAddress;
    
    //海外购 订单用
    private String idCard;
    
	public int getIsShopAddress() {
		return isShopAddress;
	}
	public void setIsShopAddress(int isShopAddress) {
		this.isShopAddress = isShopAddress;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
    
    
    
}
