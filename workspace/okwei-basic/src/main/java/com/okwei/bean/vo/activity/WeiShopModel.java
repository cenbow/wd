package com.okwei.bean.vo.activity;

public class WeiShopModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	    private Long userId;  //微店号
	    private String userName;//店铺名称
        private String shopName;  //活动标题
        private String shopImg; //店铺图片URL
        private Short identityType;  //店铺身份标识
        
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getShopName() {
			return shopName;
		}
		public void setShopName(String shopName) {
			this.shopName = shopName;
		}
		public String getShopImg() {
			return shopImg;
		}
		public void setShopImg(String shopImg) {
			this.shopImg = shopImg;
		}
		public Short getIdentityType() {
			return identityType;
		}
		public void setIdentityType(Short identityType) {
			this.identityType = identityType;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
        
}
