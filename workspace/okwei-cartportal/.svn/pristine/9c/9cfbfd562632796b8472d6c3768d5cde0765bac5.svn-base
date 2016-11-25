package com.okwei.cartportal.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 选择的商品 用于页面展示、订单提交
 * @author yangjunjun
 *
 */
public class PreSubmitCartList implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long scid;//购物车id
	private Long companyLogid;//店铺标识  供应商id
	private String companyName;// 店铺名称
	private Double totalShopPrice;//店铺的商品总价
	private Double totalShopYoufei;//店铺的商品总邮费
	private List<ProductModel> productList;//店铺下商品列表
	
	private List<KuaiDi> logistics; // 邮费列表
    private Double courier;// 快递
    private Double ems;// EMS
    private Double ordinary;// 平邮
    private String message;// 消息
    
    private String logisticsId;//选择的物流类型 
	
	public String getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	public Long getScid() {
		return scid;
	}
	public void setScid(Long scid) {
		this.scid = scid;
	}
	public Long getCompanyLogid() {
		return companyLogid;
	}
	public void setCompanyLogid(Long companyLogid) {
		this.companyLogid = companyLogid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<ProductModel> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductModel> productList) {
		this.productList = productList;
	}
	public Double getTotalShopPrice() {
		return totalShopPrice;
	}
	public void setTotalShopPrice(Double totalShopPrice) {
		this.totalShopPrice = totalShopPrice;
	}
	public List<KuaiDi> getLogistics() {
		return logistics;
	}
	public void setLogistics(List<KuaiDi> logistics) {
		this.logistics = logistics;
	}
	public Double getCourier() {
		return courier;
	}
	public void setCourier(Double courier) {
		this.courier = courier;
	}
	public Double getEms() {
		return ems;
	}
	public void setEms(Double ems) {
		this.ems = ems;
	}
	public Double getOrdinary() {
		return ordinary;
	}
	public void setOrdinary(Double ordinary) {
		this.ordinary = ordinary;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Double getTotalShopYoufei() {
		return totalShopYoufei;
	}
	public void setTotalShopYoufei(Double totalShopYoufei) {
		this.totalShopYoufei = totalShopYoufei;
	}
	
}
