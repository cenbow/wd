package com.okwei.appinterface.bean.vo.order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductOrderModel  
{   
	//supplierOrderId
	private String supplierOrderId;
	//支付订单号
	private String payOrderID;
	//总价
	private Double totalPrice;
	//订单时间 
	private Date orderTime ;
	//订单状态
	private int orderState;
	//预订单状态
	private List<ProductOrderChild> productOrderChildList = new ArrayList<ProductOrderChild>(); 
	//预订单信息
	private OLBookAssist bookAssist;
	/**
	 * 退款订单号
	 */
	private long backOrder;
	
	/**
	 * 供应商微店号
	 */
	private Long supplierWeiId;   
	/**
	 *供应商微店名
	 */
	private String supplierWeiName;  
	/**
	 *(跟登录人（买卖家）相反的身份)微店名
	 */
	private String weiName;
	/**
	 *订单类型
	 */
	private int orderType;
	/**
	 *购买者微店号
	 */
	private Long buyerWeiId;  
	/**
	 *购买者微店名
	 */
	private String buyerWeiName;  
	//新增
	
	/**
	 * OrderServiceImpl  185行判断身份
	 *购买者身份，1-微店主2-落地店3-代理商 4-供应商
	 */
	private String buyerIdentity="1";   
	
	private int agencyOrder;
	private int agencyType;
	private int forbidModify;
	private int forbidSendOut;
	private int forbidTrack;
	 
	/**
	 * 现金劵金额
	 */
	private double cashCoupon;
	
	public double getCashCoupon() {
		return cashCoupon;
	}
	public void setCashCoupon(double cashCoupon) {
		this.cashCoupon = cashCoupon;
	}
	public String getWeiName() {
		return weiName;
	}
	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}
	public String getSupplierOrderId() {
		return supplierOrderId;
	}
	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}
	public String getPayOrderID() {
		return payOrderID;
	}
	public void setPayOrderID(String payOrderID) {
		this.payOrderID = payOrderID;
	}
 
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	 
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public List<ProductOrderChild> getProductOrderChildList() {
		return productOrderChildList;
	}
	public void setProductOrderChildList(
			List<ProductOrderChild> productOrderChildList) {
		this.productOrderChildList = productOrderChildList;
	}
	public OLBookAssist getBookAssist() {
		return bookAssist;
	}
	public void setBookAssist(OLBookAssist bookAssist) {
		this.bookAssist = bookAssist;
	}
	public long getBackOrder() {
		return backOrder;
	}
	public void setBackOrder(long backOrder) {
		this.backOrder = backOrder;
	}
	public Long getSupplierWeiId() {
		return supplierWeiId;
	}
	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}
	public String getSupplierWeiName() {
		return supplierWeiName;
	}
	public void setSupplierWeiName(String supplierWeiName) {
		this.supplierWeiName = supplierWeiName;
	}

	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
 
	public Long getBuyerWeiId() {
		return buyerWeiId;
	}
	public void setBuyerWeiId(Long buyerWeiId) {
		this.buyerWeiId = buyerWeiId;
	}
	public String getBuyerWeiName() {
		return buyerWeiName;
	}
	public void setBuyerWeiName(String buyerWeiName) {
		this.buyerWeiName = buyerWeiName;
	}
	public String getBuyerIdentity() {
		return buyerIdentity;
	}
	public void setBuyerIdentity(String buyerIdentity) {
		this.buyerIdentity = buyerIdentity;
	}
	public int getAgencyOrder() {
		return agencyOrder;
	}
	public void setAgencyOrder(int agencyOrder) {
		this.agencyOrder = agencyOrder;
	}
	public int getAgencyType() {
		return agencyType;
	}
	public void setAgencyType(int agencyType) {
		this.agencyType = agencyType;
	}
	public int getForbidModify() {
		return forbidModify;
	}
	public void setForbidModify(int forbidModify) {
		this.forbidModify = forbidModify;
	}
	public int getForbidSendOut() {
		return forbidSendOut;
	}
	public void setForbidSendOut(int forbidSendOut) {
		this.forbidSendOut = forbidSendOut;
	}
	public int getForbidTrack() {
		return forbidTrack;
	}
	public void setForbidTrack(int forbidTrack) {
		this.forbidTrack = forbidTrack;
	}
	
}
