package com.okwei.bean.vo.share;

import java.io.Serializable;
import java.util.List;


public class SharePageDetailModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	private long  pageId; //分享页Id，新增时为空
	private Long friendShareId;//微店圈分享ID；
	private String  pageTitle;  //分享页标题，限20字
	private String  pageDescription;  //分享推广语，限150字
	private long  pageProducer;  //分享页制作人微店号
	private int  isForward;  //是否转发，0否，1是
	private int  isLike;  //是否点赞，0否，1是
	private Integer  isCollected=0;//是否点收藏，0否，1是
	/**
	 * 推广产品列表，参考SharePageListModel
	 */
	private List<ProductListModel> productList;  
	private int  productCount; //分享页产品数量
	private int  likeCount;  //点赞数
	private int  shareCount;  //分享数
	private long  shareOne;  //分享人微店号
	private String  shareOneImgUrl;  //分享人头像完整地址
	private String  shareOneShopName;  //分享人店铺名
	private String  shareDate; //分享页分享时间
	private String  lastEditDate;  //分享页最后修改时间
	
	 //枚举 ShareTypeEnum
	private Short pageTemplate; //：1为简易型、2为单个精品、3为多个精品 
	 

	public Long getFriendShareId() {
		return friendShareId;
	}
	public void setFriendShareId(Long friendShareId) {
		this.friendShareId = friendShareId;
	}
	public Short getPageTemplate() {
		return pageTemplate;
	}
	public void setPageTemplate(Short pageTemplate) {
		this.pageTemplate = pageTemplate;
	}
	public Integer getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Integer isCollected) {
		this.isCollected = isCollected;
	}
	public long getPageId() {
		return pageId;
	}
	public void setPageId(long pageId) {
		this.pageId = pageId;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageDescription() {
		return pageDescription;
	}
	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}
	public long getPageProducer() {
		return pageProducer;
	}
	public void setPageProducer(long pageProducer) {
		this.pageProducer = pageProducer;
	}
	public int getIsForward() {
		return isForward;
	}
	public void setIsForward(int isForward) {
		this.isForward = isForward;
	}
	public int getIsLike() {
		return isLike;
	}
	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}
	public String getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate;
	}
	public List<ProductListModel> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductListModel> productList) {
		this.productList = productList;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public long getShareOne() {
		return shareOne;
	}
	public void setShareOne(long shareOne) {
		this.shareOne = shareOne;
	}
	public String getShareOneImgUrl() {
		return shareOneImgUrl;
	}
	public void setShareOneImgUrl(String shareOneImgUrl) {
		this.shareOneImgUrl = shareOneImgUrl;
	}
	public String getShareOneShopName() {
		return shareOneShopName;
	}
	public void setShareOneShopName(String shareOneShopName) {
		this.shareOneShopName = shareOneShopName;
	}
	public String getShareDate() {
		return shareDate;
	}
	public void setShareDate(String shareDate) {
		this.shareDate = shareDate;
	}
	
}
