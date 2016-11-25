package com.okwei.appinterface.bean.vo.wallet;

/**
 * @author fuhao
 *	现金劵实体
 */
public class CashCouponVO {
    
	/**
	 * 现金劵记录Id
	 */
	public Long cashCouponId;
	public String img;//代金券图片
	public Double amount;//代金券金额
	public String name;//代金券名称",
	public String date;//时间
	public String detailUrl;//代金券详情URL"
	/**
	 * 供应商订单号
	 */
	public String supplyOrderId;
	
	public String getSupplyOrderId() {
		return supplyOrderId;
	}
	public void setSupplyOrderId(String supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}
	public Long getCashCouponId() {
		return cashCouponId;
	}
	public void setCashCouponId(Long cashCouponId) {
		this.cashCouponId = cashCouponId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
 
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
  
}
 