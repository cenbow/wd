package com.okwei.bean.vo.product;

import java.io.Serializable;

public class BrandAgentPrice implements Serializable{
	private static final long serialVersionUID = 1L;
	private Double supplyPrice;
	private Double dukePrice;
	private Double deputyPrice;
	private Double agentPrice;
	public Double getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(Double supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public Double getDukePrice() {
		return dukePrice;
	}
	public void setDukePrice(Double dukePrice) {
		this.dukePrice = dukePrice;
	}
	public Double getDeputyPrice() {
		return deputyPrice;
	}
	public void setDeputyPrice(Double deputyPrice) {
		this.deputyPrice = deputyPrice;
	}
	public Double getAgentPrice() {
		return agentPrice;
	}
	public void setAgentPrice(Double agentPrice) {
		this.agentPrice = agentPrice;
	}
	
}
