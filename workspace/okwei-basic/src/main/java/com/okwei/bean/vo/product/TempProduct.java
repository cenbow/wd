package com.okwei.bean.vo.product;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TempProduct {
    private BigInteger ProductID;
    private String ProductTitle;
    private String DefaultImg;
    private BigDecimal DefaultPrice;
    private BigInteger SupplierWeiID;
    private BigInteger stockCount;
    private BigInteger selledCount;
    public BigInteger getProductID() {
        return ProductID;
    }
    public void setProductID(BigInteger productID) {
        ProductID = productID;
    }
    public String getProductTitle() {
        return ProductTitle;
    }
    public void setProductTitle(String productTitle) {
        ProductTitle = productTitle;
    }
    public String getDefaultImg() {
        return DefaultImg;
    }
    public void setDefaultImg(String defaultImg) {
        DefaultImg = defaultImg;
    }
    public BigDecimal getDefaultPrice() {
        return DefaultPrice;
    }
    public void setDefaultPrice(BigDecimal defaultPrice) {
        DefaultPrice = defaultPrice;
    }
    public BigInteger getSupplierWeiID() {
        return SupplierWeiID;
    }
    public void setSupplierWeiID(BigInteger supplierWeiID) {
        SupplierWeiID = supplierWeiID;
    }
    public BigInteger getStockCount() {
        return stockCount;
    }
    public void setStockCount(BigInteger stockCount) {
        this.stockCount = stockCount;
    }
    public BigInteger getSelledCount() {
        return selledCount;
    }
    public void setSelledCount(BigInteger selledCount) {
        this.selledCount = selledCount;
    }
    
}
