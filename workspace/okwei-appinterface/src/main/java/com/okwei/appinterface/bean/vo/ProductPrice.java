package com.okwei.appinterface.bean.vo;

public class ProductPrice {
    private Double price;
    private Double storePrice;
    private Double batchPrice;
    private Double dukePrice;//城主价
	private Double deputyPrice;//副城主价
	private Double agentPrice;//代理价
    private int inventory;
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getStorePrice() {
        return storePrice;
    }
    public void setStorePrice(Double storePrice) {
        this.storePrice = storePrice;
    }
    public Double getAgentPrice() {
        return agentPrice;
    }
    public void setAgentPrice(Double agentPrice) {
        this.agentPrice = agentPrice;
    }
    public Double getBatchPrice() {
        return batchPrice;
    }
    public void setBatchPrice(Double batchPrice) {
        this.batchPrice = batchPrice;
    }
    public int getInventory() {
        return inventory;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public Double getDeputyPrice() {
		return deputyPrice;
	}
    public void setDeputyPrice(Double deputyPrice) {
    	this.deputyPrice = deputyPrice;
    }
    public Double getDukePrice() {
		return dukePrice;
	}
    public void setDukePrice(Double dukePrice) {
		this.dukePrice = dukePrice;
	}
}
