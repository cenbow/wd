package com.okwei.bean.vo.order;

public class UnitPrice {
	private String ProductOrderID;
	private Double Price;

	public String getProductOrderID()
	{
		return ProductOrderID;
	}
	public void setProductOrderID(String productOrderID) {
		ProductOrderID = productOrderID;
	}
	public Double getPrice() 
	{
		return Price;
	}
	public void setPrice(Double price) 
	{
		Price = price;
	}
}
