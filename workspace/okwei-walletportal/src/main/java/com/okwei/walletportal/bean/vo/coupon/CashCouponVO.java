package com.okwei.walletportal.bean.vo.coupon;

/**
 * @author fuhao
 *	现金劵实体
 */
public class CashCouponVO {
    
	/**
	 * 现金劵记录Id
	 */
	public Long cashCouponId;
	/**
	 * 用户Id
	 */
	public Long  weiid;
	/**
	 * 现金劵状态  1购物返现金券 2购物抵现 3过期现金卷
	 */
	public int state;
	public String createTime;
	
	/**
	 * 商品标题
	 */
	public String prodcutTitle;
	/**
	 * 商品Id
	 */
	public Long productId;
	/**
	 * 商品图片
	 */
	public String productImg;
	/**
	 * 商品订单
	 */
	public String productOrderId;
	/**
	 * 供供应商订单号
	 */
	public String supOrderID;
	/**
	 * 现金劵变化金额
	 */
	public Double coinAmount;
	
	 
	public Long getWeiid() {
		return weiid;
	}
	public void setWeiid(Long weiid) {
		this.weiid = weiid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getProdcutTitle() {
		return prodcutTitle;
	}
	public void setProdcutTitle(String prodcutTitle) {
		this.prodcutTitle = prodcutTitle;
	}
	 
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
	}
	public Double getCoinAmount() {
		return coinAmount;
	}
	public void setCoinAmount(Double coinAmount) {
		this.coinAmount = coinAmount;
	}
	public Long getCashCouponId() {
		return cashCouponId;
	}
	public void setCashCouponId(Long cashCouponId) {
		this.cashCouponId = cashCouponId;
	}
	public String getSupOrderID() {
		return supOrderID;
	}
	public void setSupOrderID(String supOrderID) {
		this.supOrderID = supOrderID;
	}
 
	
	
}
 