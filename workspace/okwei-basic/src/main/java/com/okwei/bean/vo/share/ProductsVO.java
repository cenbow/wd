package com.okwei.bean.vo.share;

/**
 * @author fuhao
 *
 */
public class ProductsVO {
	
	/**
	 * 图片详情Id
	 */
	private Long sid;
	/**
	 * 商品详情图片
	 */
	private String productPicture;
	
	/**
	 * 描述
	 */
	private String shareDescription;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(String productPicture) {
		this.productPicture = productPicture;
	}

	public String getShareDescription() {
		return shareDescription;
	}

	public void setShareDescription(String shareDescription) {
		this.shareDescription = shareDescription;
	}
 
	
}
