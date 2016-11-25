package com.okwei.detail.bean.vo;

public class Product {
    public long id;
    public long proID;
    public String proTitle;
    public String proImg;
    public double price;
    
    public Double agentPrice;
    public Double landPrice;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getProID() {
        return proID;
    }
    public void setProID(long proID) {
        this.proID = proID;
    }
    public String getProTitle() {
        return proTitle;
    }
    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }
    public String getProImg() {
        return proImg;
    }
    public void setProImg(String proImg) {
        this.proImg = proImg;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Double getAgentPrice() {
        return agentPrice;
    }
    public void setAgentPrice(Double agentPrice) {
        this.agentPrice = agentPrice;
    }
    public Double getLandPrice() {
        return landPrice;
    }
    public void setLandPrice(Double landPrice) {
        this.landPrice = landPrice;
    }
    
}
