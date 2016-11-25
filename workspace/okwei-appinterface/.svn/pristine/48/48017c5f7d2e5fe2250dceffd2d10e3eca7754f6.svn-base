package com.okwei.appinterface.bean.vo.brandAgent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tang on 16/7/15.
 */
public class BrandShop extends BaseShop implements Serializable {

    private static final long serialVersionUID = -628030408757301858L;

    /**
     * userId : 1111
     * userName : XXX
     * shopName : XXX品牌特价活动
     * shopImg : http://xxx/xxx
     * identityType : 1
     * brandId : 123
     * brandName : "XXX品牌"
     * brandLogo : "http://xxx/xxx"
     * saleProductsNum : 13
     * agentPeopleNum : 11
     * productList : []
     */

    private Integer brandId;
    private String brandName;
    private String brandLogo;

    public BrandShop() {
    }

    public BrandShop(Long userId, String userName, String shopName, String shopImg, Integer identityType, Integer brandId, String brandName, String brandLogo) {
        super(userId, userName, shopName, shopImg, identityType);
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandLogo = brandLogo;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

}
