package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OProductOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_ProductOrder")
public class OProductOrder extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productOrderId;
	private String supplierOrderId;
	private String payOrderId;
	private Long productId;

	@Column(name = "BackOrder")
	private Long backOrder;

	private Long supplyeriId;
	private Long buyerId;
	private Long sellerWeiid;
	private Long sellerUpweiid;
	private Integer classId;
	private String prodcutTitle;
	private String productMinTitle;
	private String productImg;
	private String productDes;
	private Double price;
	private Double sourcePrice;
	private Integer count;
	private Double commision;
	private Double defaultPostage;
	private Short orderType;
	private Short supplyType;
	private Date createTime;
	private Date updateTime;
	private Short state;
	private Short buyerDel;
	private Short sellerDel;

	private Short isChangePrice;
	private String styleDes;
	private Long styleId;
	private Short BackStatu;
	private Long shopWeiID;
	
	private Short fromType;
	private Long makerWeiId;
	private Long sharerUpWeiId;
	private Long shareWeiId;
	private Long shareID;
	private Short isActivity;
	private Long proActId;
	
	
	
	
	@Column(name = "ProActID")
	public Long getProActId() {
		return proActId;
	}


	public void setProActId(Long proActId) {
		this.proActId = proActId;
	}


	@Column(name = "IsActivity")
	public Short getIsActivity() {
		if(isActivity == null)
			return (short)0;
		return isActivity;
	}


	public void setIsActivity(Short isActivity) {
		this.isActivity = isActivity;
	}


	@Column(name="ShareID")
	public Long getShareID() {
		return shareID;
	}


	public void setShareID(Long shareID) {
		this.shareID = shareID;
	}
	
	
	@Column(name="ShopWeiID")
	public Long getShopWeiID() {
		return shopWeiID;
	}

	public void setShopWeiID(Long shopWeiID) {
		this.shopWeiID = shopWeiID;
	}

	@Id
	@Column(name = "ProductOrderID", unique = true, nullable = false, length = 20)
	public String getProductOrderId() {
		return this.productOrderId;
	}

	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
	}

	@Column(name = "SupplierOrderID", length = 32)
	public String getSupplierOrderId() {
		return this.supplierOrderId;
	}

	@Column(name = "SellerWeiID")
	public Long getSellerWeiid() {
		return sellerWeiid;
	}

	public void setSellerWeiid(Long sellerWeiid) {
		this.sellerWeiid = sellerWeiid;
	}

	@Column(name = "SellerUpWeiID")
	public Long getSellerUpweiid() {
		return sellerUpweiid;
	}

	public void setSellerUpweiid(Long sellerUpweiid) {
		this.sellerUpweiid = sellerUpweiid;
	}

	@Column(name = "BuyerDel")
	public Short getBuyerDel() {
		return buyerDel;
	}

	public void setBuyerDel(Short buyerDel) {
		this.buyerDel = buyerDel;
	}

	@Column(name = "SellerDel")
	public Short getSellerDel() {
		return sellerDel;
	}

	public void setSellerDel(Short sellerDel) {
		this.sellerDel = sellerDel;
	}

	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "SupplyeriID")
	public Long getSupplyeriId() {
		return this.supplyeriId;
	}

	public void setSupplyeriId(Long supplyeriId) {
		this.supplyeriId = supplyeriId;
	}

	@Column(name = "BuyerID")
	public Long getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "ClassID")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "ProdcutTitle", length = 128)
	public String getProdcutTitle() {
		return this.prodcutTitle;
	}

	public void setProdcutTitle(String prodcutTitle) {
		this.prodcutTitle = prodcutTitle;
	}

	@Column(name = "ProductMinTitle", length = 128)
	public String getProductMinTitle() {
		return this.productMinTitle;
	}

	public void setProductMinTitle(String productMinTitle) {
		this.productMinTitle = productMinTitle;
	}

	@Column(name = "ProductImg", length = 128)
	public String getProductImg() {
		return this.productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	@Column(name = "ProductDes", length = 65535)
	public String getProductDes() {
		return this.productDes;
	}

	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}

	@Column(name = "Price", precision = 18)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "Count")
	public Integer getCount() {
		if (this.count == null)
			return 0;
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "Commision", precision = 18)
	public Double getCommision() {
		return this.commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	@Column(name = "OrderType")
	public Short getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "PayOrderID", length = 32)
	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	@Column(name = "StyleDes", length = 128)
	public String getStyleDes() {
		return styleDes;
	}

	public void setStyleDes(String styleDes) {
		this.styleDes = styleDes;
	}

	@Column(name = "StyleID")
	public Long getStyleId() {
		return styleId;
	}

	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}

	@Column(name = "IsChangePrice")
	public Short getIsChangePrice() {
		return isChangePrice;
	}

	public void setIsChangePrice(Short isChangePrice) {
		this.isChangePrice = isChangePrice;
	}

	@Column(name = "DefaultPostage")
	public Double getDefaultPostage() {
		if (this.defaultPostage == null)
			return 0d;
		return defaultPostage;
	}

	public void setDefaultPostage(Double defaultPostage) {
		this.defaultPostage = defaultPostage;
	}

	@Column(name = "SupplyType")
	public Short getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Short supplyType) {
		this.supplyType = supplyType;
	}

	@Column(name = "SourcePrice")
	public Double getSourcePrice() {
		return sourcePrice;
	}

	public void setSourcePrice(Double sourcePrice) {
		this.sourcePrice = sourcePrice;
	}

	public Short getBackStatu() {
		return BackStatu;
	}

	public void setBackStatu(Short backStatu) {
		BackStatu = backStatu;
	}

	public Long getBackOrder() {
		return backOrder;
	}

	public void setBackOrder(Long backOrder) {
		this.backOrder = backOrder;
	}

	@Column(name = "MakerWeiID")
	public Long getMakerWeiId() {
		return makerWeiId;
	}

	public void setMakerWeiId(Long makerWeiId) {
		this.makerWeiId = makerWeiId;
	}
	

	@Column(name = "ShareWeiID")
	public Long getShareWeiId() {
		return shareWeiId;
	}

	public void setShareWeiId(Long shareWeiId) {
		this.shareWeiId = shareWeiId;
	}
	
	@Column(name = "ShareUpWeiID")
	public Long getSharerUpWeiId() {
		return sharerUpWeiId;
	}

	public void setSharerUpWeiId(Long sharerUpWeiId) {
		this.sharerUpWeiId = sharerUpWeiId;
	}

	

	@Column(name = "FromType")
	public Short getFromType() {
		return fromType;
	}

	public void setFromType(Short fromType) {
		this.fromType = fromType;
	}

	
}