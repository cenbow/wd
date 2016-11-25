package com.okwei.appinterface.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.vo.activity.ActivityModel;


public class MsgProductInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4469881564140087436L;
	private Long productId;
	private String title;
	/**
	 * 商品小标题
	 */
	private String minTitle;
	private Double price;
	private boolean isBrand;
	private Double startPrice;
	private Double endPrice;
	private Double startCommision;
	private Double endCommision;
	/**
	 * 预定说明
	 */
	private String explain;
	/**
	 * 用户类型图片（工厂号，批发号）
	 */
	private String IdentityCategoryImg;
	/**
	 * 店铺主营
	 */
	private String busContent;
	/**
	 * web点赞和评论的链接地址
	 */
	private String webUrl;
	/**
	 * 邮费ID
	 */
	private Integer postFeeId;
	/**
	 *  是否有批发价 true为有，false为没有
	 */
	private boolean isBatch;
	private Double startBatchPrice;
	private Double endBatchPrice;
	/**
	 * 是否有预订价 true为有 false没有
	 */
	private boolean isBook;	
	/**
	 * 销售量
	 */
	private int sellNum=0;
	private Double bookPrice;
	private String bookRemark;
	private String wapUrl;
	private List<String> imgList;
	private List<BatchPrice> batchPrice;
	private String weiName;
	private String weiImg;
	private Long weiNo;
	private String sellAttr;
	private Double customPostFee;
	private String supType;
	private List<EnsureType> ensure;
	private String productDesc;
	private int productNum;
	private Long currentWeiId;
	private List<String> imgListLarge;
	private int isOnShevles;
	private int isOnSale;
	private String noSaleReason;
	private Short saleType;
	
	private int isOk;
	private int isCanShevles;
	private String isCanShevlesMsg;
	
	// 5.0 新增字段
	private boolean isHasStorePrice;
	private Double storePrice;
	private boolean isHasAgentPrice;
	private Double agentPrice;
	private int inventory;
	private Double storeBuyAmount;
	private boolean isAgent;
	private boolean isStore;
	private Long supplierWeiId;
	private int demandId;
	private String demandProductUrl;
	private String couponsPrice;
	//分享新增字段
	private Double displayPrice;
	private Long sharePageProducer;
	private Long shareOne;
	private Long sharePageId;
	//修改 5.0添加属性
	/**
	 * 原价
	 */
	private double originalPrice;
	 
	private ActivityModel activityModel;
	private Short publishType;//类型 普通区还是代理产品
	
	private int isCanExchange;
	private boolean isBrandAgent;//是否是品牌代理商
	private Double brandAgentPrice;//品牌代理商价格
	
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	public String getDemandProductUrl() {
	    return demandProductUrl;
	}
	public void setDemandProductUrl(String demandProductUrl) {
	    this.demandProductUrl = demandProductUrl;
	}
	public boolean getIsHasStorePrice() {
	    return isHasStorePrice;
	}
	public void setIsHasStorePrice(boolean isHasStorePrice) {
	    this.isHasStorePrice = isHasStorePrice;
	}
	public Double getStorePrice() {
	    return storePrice;
	}
	public void setStorePrice(Double storePrice) {
	    this.storePrice = storePrice;
	}
	public boolean getIsHasAgentPrice() {
	    return isHasAgentPrice;
	}
	public void setIsHasAgentPrice(boolean isHasAgentPrice) {
	    this.isHasAgentPrice = isHasAgentPrice;
	}
	public Double getAgentPrice() {
	    return agentPrice;
	}
	public void setAgentPrice(Double agentPrice) {
	    this.agentPrice = agentPrice;
	}
	public int getInventory() {
	    return inventory;
	}
	public void setInventory(int inventory) {
	    this.inventory = inventory;
	}
	public Double getStoreBuyAmount() {
	    return storeBuyAmount;
	}
	public void setStoreBuyAmount(Double storeBuyAmount) {
	    this.storeBuyAmount = storeBuyAmount;
	}
	public boolean getIsAgent() {
	    return isAgent;
	}
	public void setIsAgent(boolean isAgent) {
	    this.isAgent = isAgent;
	}
	public boolean getIsStore() {
	    return isStore;
	}
	public void setIsStore(boolean isStore) {
	    this.isStore = isStore;
	}
	public Long getSupplierWeiId() {
	    return supplierWeiId;
	}
	public void setSupplierWeiId(Long supplierWeiId) {
	    this.supplierWeiId = supplierWeiId;
	}
	public int getDemandId() {
	    return demandId;
	}
	public void setDemandId(int demandId) {
	    this.demandId = demandId;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public int getSellNum() {
		return sellNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public String getIdentityCategoryImg() {
		return IdentityCategoryImg;
	}
	public void setIdentityCategoryImg(String identityCategoryImg) {
		IdentityCategoryImg = identityCategoryImg;
	}
	public String getBusContent() {
		return busContent;
	}
	public void setBusContent(String busContent) {
		this.busContent = busContent;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public int getIsCanShevles() {
		return isCanShevles;
	}
	public void setIsCanShevles(int isCanShevles) {
		this.isCanShevles = isCanShevles;
	}
	public String getIsCanShevlesMsg() {
		return isCanShevlesMsg;
	}
	public void setIsCanShevlesMsg(String isCanShevlesMsg) {
		this.isCanShevlesMsg = isCanShevlesMsg;
	}
	public int getIsOk() {
		return isOk;
	}
	public void setIsOk(int isOk) {
		this.isOk = isOk;
	}
	public Short getSaleType() {
		return saleType;
	}
	public void setSaleType(Short saleType) {
		this.saleType = saleType;
	}
	public String getNoSaleReason() {
		return noSaleReason;
	}
	public void setNoSaleReason(String noSaleReason) {
		this.noSaleReason = noSaleReason;
	}
	
	public int getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(int isOnSale) {
		this.isOnSale = isOnSale;
	}
	
	
	public int getIsOnShevles() {
		return isOnShevles;
	}
	public void setIsOnShevles(int isOnShevles) {
		this.isOnShevles = isOnShevles;
	}
	public List<String> getImgListLarge() {
		return imgListLarge;
	}
	public void setImgListLarge(List<String> imgListLarge) {
		this.imgListLarge = imgListLarge;
	}
	public Long getCurrentWeiId() {
		return currentWeiId;
	}
	public void setCurrentWeiId(Long currentWeiId) {
		this.currentWeiId = currentWeiId;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getSupType() {
		return supType;
	}
	public void setSupType(String supType) {
		this.supType = supType;
	}
	public List<EnsureType> getEnsure() {
		return ensure;
	}
	public void setEnsure(List<EnsureType> ensure) {
		this.ensure = ensure;
	}
	public String getWeiName() {
		return weiName;
	}
	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}
	public String getWeiImg() {
		return weiImg;
	}
	public void setWeiImg(String weiImg) {
		this.weiImg = weiImg;
	}
	public Long getWeiNo() {
		return weiNo;
	}
	public void setWeiNo(Long weiNo) {
		this.weiNo = weiNo;
	}
	public boolean isBrand() {
		return isBrand;
	}
	public void setBrand(boolean isBrand) {
		this.isBrand = isBrand;
	}
	public Double getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}
	public Double getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}
	public Double getStartCommision() {
		return startCommision;
	}
	public void setStartCommision(Double startCommision) {
		this.startCommision = startCommision;
	}
	public Double getEndCommision() {
		return endCommision;
	}
	public void setEndCommision(Double endCommision) {
		this.endCommision = endCommision;
	}
	public Integer getPostFeeId() {
		return postFeeId;
	}
	public void setPostFeeId(Integer postFee) {
		this.postFeeId = postFee;
	}
	public boolean isBatch() {
		return isBatch;
	}
	public void setBatch(boolean isBatch) {
		this.isBatch = isBatch;
	}
	public Double getStartBatchPrice() {
		return startBatchPrice;
	}
	public void setStartBatchPrice(Double startBatchPrice) {
		this.startBatchPrice = startBatchPrice;
	}
	public Double getEndBatchPrice() {
		return endBatchPrice;
	}
	public void setEndBatchPrice(Double endBatchPrice) {
		this.endBatchPrice = endBatchPrice;
	}
	public boolean isBook() {
		return isBook;
	}
	public void setBook(boolean isBook) {
		this.isBook = isBook;
	}
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookRemark() {
		return bookRemark;
	}
	public void setBookRemark(String bookRemark) {
		this.bookRemark = bookRemark;
	}
	public String getWapUrl() {
		return wapUrl;
	}
	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMinTitle() {
		return minTitle;
	}
	public void setMinTitle(String minTitle) {
		this.minTitle = minTitle;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public List<BatchPrice> getBatchPrice() {
		return batchPrice;
	}
	public void setBatchPrice(List<BatchPrice> batchPrice) {
		this.batchPrice = batchPrice;
	}
	public String getSellAttr() {
		return sellAttr;
	}
	public void setSellAttr(String sellAttr) {
		this.sellAttr = sellAttr;
	}
	public Double getCustomPostFee() {
		return customPostFee;
	}
	public void setCustomPostFee(Double customPostFee) {
		this.customPostFee = customPostFee;
	}
	public String getCouponsPrice() {
	    return couponsPrice;
	}
	public void setCouponsPrice(String couponsPrice) {
	    this.couponsPrice = couponsPrice;
	}
	public Long getSharePageId() {
		return sharePageId;
	}
	public void setSharePageId(Long sharePageId) {
		this.sharePageId = sharePageId;
	}
	public ActivityModel getActivityModel() {
		return activityModel;
	}
	public void setActivityModel(ActivityModel activityModel) {
		this.activityModel = activityModel;
	}
	public Double getDisplayPrice() {
		return displayPrice;
	}
	public void setDisplayPrice(Double displayPrice) {
		this.displayPrice = displayPrice;
	}
	public Long getSharePageProducer() {
		return sharePageProducer;
	}
	public void setSharePageProducer(Long sharePageProducer) {
		this.sharePageProducer = sharePageProducer;
	}
	public Long getShareOne() {
		return shareOne;
	}
	public void setShareOne(Long shareOne) {
		this.shareOne = shareOne;
	}
	public int getIsCanExchange() {
		return isCanExchange;
	}
	public void setIsCanExchange(int isCanExchange) {
		this.isCanExchange = isCanExchange;
	}
	public Short getPublishType() {
		return publishType;
	}
	public void setPublishType(Short publishType) {
		this.publishType = publishType;
	}
	public boolean getIsBrandAgent() {
		return isBrandAgent;
	}
	public Double getBrandAgentPrice() {
		return brandAgentPrice;
	}
	public void setIsBrandAgent(boolean isBrandAgent) {
		this.isBrandAgent = isBrandAgent;
	}
	public void setBrandAgentPrice(Double brandAgentPrice) {
		this.brandAgentPrice = brandAgentPrice;
	}
	
	
}
