package com.okwei.appinterface.bean.vo.brandAgent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tang on 16/7/15.
 */
public class BrandShopEx extends BrandShop implements Serializable {

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

    private Integer saleProductsNum;
    private Integer agentPeopleNum;
    private List<?> productList;

    public BrandShopEx() {
    }

    public BrandShopEx(long userId, String userName, String shopName, String shopImg, Integer identityType, Integer brandId, String brandName, String brandLogo, int saleProductsNum, int agentPeopleNum) {
        super(userId, userName, shopName, shopImg, identityType, brandId, brandName, brandLogo);
        this.saleProductsNum = saleProductsNum;
        this.agentPeopleNum = agentPeopleNum;
    }

    public Integer getSaleProductsNum() {
        return saleProductsNum;
    }

    public void setSaleProductsNum(Integer saleProductsNum) {
        this.saleProductsNum = saleProductsNum;
    }

    public Integer getAgentPeopleNum() {
        return agentPeopleNum;
    }

    public void setAgentPeopleNum(Integer agentPeopleNum) {
        this.agentPeopleNum = agentPeopleNum;
    }

    public List<?> getProductList() {
        return productList;
    }

    public void setProductList(List<?> productList) {
        this.productList = productList;
    }
}
