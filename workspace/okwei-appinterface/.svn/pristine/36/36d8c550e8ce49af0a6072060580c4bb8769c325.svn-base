package com.okwei.appinterface.bean.vo.brandAgent;

import java.io.Serializable;

/**
 * Created by tang on 16/7/15.
 */
public class MyBrandShop extends BrandShop implements Serializable {

    private static final long serialVersionUID = -9179556168858077519L;

    /**
     * userId : 1111
     * userName : XXX
     * shopName : XXX品牌特价活动
     * shopImg : http://xxx/xxx
     * identityType : 1
     * address : 广东省 深圳市
     * relationshipType : 4
     * relationshipName : 城主
     */

    private String address;
    private Integer relationshipType;
    private String relationshipName;

    public MyBrandShop() {
    }

    public MyBrandShop(Long userId, String userName, String shopName, String shopImg, Integer identityType, Integer brandId, String brandName, String brandLogo, String address, Integer relationshipType, String relationshipName) {
        super(userId, userName, shopName, shopImg, identityType, brandId, brandName, brandLogo);
        this.address = address;
        this.relationshipType = relationshipType;
        this.relationshipName = relationshipName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(Integer relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }
}
