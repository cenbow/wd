package com.okwei.bean.vo.share;

import com.okwei.common.PageResult;

/**
 * 分享详情页面VO
 * @author fh
 */
public class SMainDataDetailsVO {
	
	/**
	 * 分享Id
	 */
	private long shareId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 推广语
	 */
	private String describle;
	/**
	 * 制作人weiId
	 */
	private long makeWeiID;
 
	/**
	 * 商品信息
	 */
	private PageResult<SProductsVO> productsList;
	
	/**
	 * 获取二维码路径
	 */
	private String QRCodeUrl;
	
	/**
	 * 分享类型  ShareTypeEnum 枚举
	 */
	private Short shareType;
    
	
	public Short getShareType() {
		return shareType;
	}

	public void setShareType(Short shareType) {
		this.shareType = shareType;
	}

	public long getMakeWeiID() {
		return makeWeiID;
	}

	public void setMakeWeiID(long makeWeiID) {
		this.makeWeiID = makeWeiID;
	}

	public long getShareId() {
		return shareId;
	}

	public void setShareId(long shareId) {
		this.shareId = shareId;
	}

	public String getQRCodeUrl() {
		return QRCodeUrl;
	}

	public void setQRCodeUrl(String qRCodeUrl) {
		QRCodeUrl = qRCodeUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescrible() {
		return describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public PageResult<SProductsVO> getProductsList() {
		return productsList;
	}

	public void setProductsList(PageResult<SProductsVO> productsList) {
		this.productsList = productsList;
	}

	 
	
}
