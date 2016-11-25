package com.okwei.appinterface.bean.vo.order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ProductOrderDetailsModel
{   
	
	/**
	 * 现金劵金额
	 */
	private double cashCoupon=0;
	/**
	 * 微金币
	 */
	private double okweiCoin=0;
	//供应商订单
	private String supplierOrderId ;
	//微店名称 
	private String WeiName;
	//订单时间
	private String OrderTime;
	//收货人姓名
	private String ReceiverName;
	//手机
	private String MobilePhone;
	//收货地址
	private String Address;
	//总价
	private Double TotalPrice; 
	//产品列表
	private List<ProductOrderChild> ProductList  = new ArrayList<ProductOrderChild>();
	//订单类型 
	private int OrderType;
    //买家留言   
	private String Message; 
	//邮费
	private Double Postage;
	//订单状态
	private short State;
	//支付订单
	private String PayOrderID;
	
    private BookAssistModel  BookAssistContent = new BookAssistModel();
    //联系名称
	private String LinkName;
	//联系人微店号
	private long LinkWeiID;
	//联系人头像 
	private String LinkImg;
	//店铺ID
	private Long sellerID;
	/**
	 * 退款订单Id
	 */
	private String backOrder;
	/**
	 * isShipments 是否发货（1显示发货按钮 0不显示）
	 */
	private int isShipments;
	/**
	 * isShow 是否显示取消申请按钮(1 显示 0 不显示)
	 */
	private int isShow;
	/**
	 * isShowSupply是否显示申请退款退货(1 显示 0 不显示)
	 */
	private int isShowSupply;

	/**
	 * 显示对方weiId
	 */
	private Long shopWeiId;
	
	

	public double getOkweiCoin() {
		return okweiCoin;
	}
	public void setOkweiCoin(double okweiCoin) {
		this.okweiCoin = okweiCoin;
	}
	public Long getShopWeiId() {
		return shopWeiId;
	}
	public void setShopWeiId(Long shopWeiId) {
		this.shopWeiId = shopWeiId;
	}
	public double getCashCoupon() {
		return cashCoupon;
	}
	public void setCashCoupon(double cashCoupon) {
		this.cashCoupon = cashCoupon;
	}
	public String getBackOrder() {
		return backOrder;
	}
	public void setBackOrder(String backOrder) {
		this.backOrder = backOrder;
	}
	public int getIsShowSupply() {
		return isShowSupply;
	}
	public void setIsShowSupply(int isShowSupply) {
		this.isShowSupply = isShowSupply;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public String getSupplierOrderId() {
		return supplierOrderId;
	}
	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}
	public int getIsShipments() {
		return isShipments;
	}
	public void setIsShipments(int isShipments) {
		this.isShipments = isShipments;
	}
	public Long getSellerID() {
		return sellerID;
	}
	public void setSellerID(Long sellerID) {
		this.sellerID = sellerID;
	}
	public String getsupplierOrderId()
	{
		return supplierOrderId;
	}
	public void setsupplierOrderId(String supplierOrderId)
	{
		this.supplierOrderId = supplierOrderId;
	}
	public String getWeiName()
	{
		return WeiName;
	}
	public void setWeiName(String WeiName)
	{
		this.WeiName = WeiName;
	}
	public String getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}
	public String getReceiverName()
	{
		return ReceiverName;
	}
	public void setReceiverName(String ReceiverName)
	{
		this.ReceiverName = ReceiverName;
	}
	public String getMobilePhone()
	{
		return MobilePhone;
	}
	public void setMobilePhone(String MobilePhone)
	{
		this.MobilePhone = MobilePhone;
	}
	public String getAddress()
	{
		return Address;
	}
	public void setAddress(String Address)
	{
		this.Address = Address;
	}
	public Double getTotalPrice()
	{
		return TotalPrice;
	}
	public void setTotalPrice(Double TotalPrice)
	{
		this.TotalPrice = TotalPrice;
	}
	public List<ProductOrderChild> getProductList()
	{
		return ProductList;
	}
	public void setProductList(List<ProductOrderChild> ProductList)
	{
		this.ProductList = ProductList;
	}
	public int getOrderType() {
		return OrderType;
	}
	public void setOrderType(int orderType) {
		OrderType = orderType;
	}
	public String getMessage()
	{
		return Message;
	}
	public void setMessage(String Message)
	{
		this.Message = Message;
	}
	public Double getPostage() {
		return Postage;
	}
	public void setPostage(Double postage) {
		Postage = postage;
	}
	public short getState() {
		return State;
	}
	public void setState(short state) {
		State = state;
	}
	public BookAssistModel getBookAssistContent() {
		return BookAssistContent;
	}
	public void setBookAssistContent(BookAssistModel bookAssistContent) {
		BookAssistContent = bookAssistContent;
	}
	public String getPayOrderID() {
		return PayOrderID;
	}
	public void setPayOrderID(String payOrderID) {
		PayOrderID = payOrderID;
	}
	public String getLinkName() {
		return LinkName;
	}
	public void setLinkName(String linkName) {
		LinkName = linkName;
	}
	public long getLinkWeiID() {
		return LinkWeiID;
	}
	public void setLinkWeiID(long linkWeiID) {
		LinkWeiID = linkWeiID;
	}
	public String getLinkImg() {
		return LinkImg;
	}
	public void setLinkImg(String linkImg) {
		LinkImg = linkImg;
	}
}

