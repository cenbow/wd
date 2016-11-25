package com.okwei.bean.vo.share;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.okwei.bean.domain.SSingleDesc;

public class ShareProduct implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BigInteger id;
    private BigInteger proID;
    private String proImage;
    private String proTitle;
    private BigDecimal price;
    private BigDecimal yprice;
    private BigDecimal commission;
    private Short type;
    private BigInteger shopid;
    private String shopClass;
    private BigInteger supweiid;
    private String supName;
    private List<SSingleDesc> singlist;
    private Integer saleCount;
    public BigInteger getID() {
        return id;
    }
    public void setID(BigInteger id) {
        this.id = id;
    }
    public BigInteger getProID() {
        return proID;
    }
    public void setProID(BigInteger proID) {
        this.proID = proID;
    }
    public String getProImage() {
        return proImage;
    }
    public void setProImage(String proImage) {
        this.proImage = proImage;
    }
    public String getProTitle() {
        return proTitle;
    }
    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Short getType() {
        return type;
    }
    public void setType(Short type) {
        this.type = type;
    }
    public String getShopClass() {
        return shopClass;
    }
    public void setShopClass(String shopClass) {
        this.shopClass = shopClass;
    }
    public String getSupName() {
        return supName;
    }
    public void setSupName(String supName) {
        this.supName = supName;
    }
    public BigInteger getShopid() {
        return shopid;
    }
    public void setShopid(BigInteger shopid) {
        this.shopid = shopid;
    }
    public BigInteger getSupweiid() {
        return supweiid;
    }
    public void setSupweiid(BigInteger supweiid) {
        this.supweiid = supweiid;
    }
    public BigDecimal getYprice() {
        return yprice;
    }
    public void setYprice(BigDecimal yprice) {
        this.yprice = yprice;
    }
    public BigDecimal getCommission() {
        return commission;
    }
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
    public List<SSingleDesc> getSinglist() {
        return singlist;
    }
    public void setSinglist(List<SSingleDesc> singlist) {
        this.singlist = singlist;
    }
    public Integer getSaleCount() {
        return saleCount;
    }
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }
}
