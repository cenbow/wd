package com.okwei.appinterface.bean.vo.brandAgent;

import java.io.Serializable;

/**
 * Created by tang on 16/7/15.
 */
public class BaseShop implements Serializable {

    private static final long serialVersionUID = 599490485896511601L;

    /**
     * userId : 1111
     * userName : XXX
     * shopName : XXX品牌特价活动
     * shopImg : http://xxx/xxx
     * identityType : 1
     */

    private Long userId;
    private String userName;
    private String shopName;
    private String shopImg;
    private Integer identityType;

    public BaseShop() {
    }

    public BaseShop(Long userId, String userName, String shopName, String shopImg, Integer identityType) {
        this.userId = userId;
        this.userName = userName;
        this.shopName = shopName;
        this.shopImg = shopImg;
        this.identityType = identityType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

}
