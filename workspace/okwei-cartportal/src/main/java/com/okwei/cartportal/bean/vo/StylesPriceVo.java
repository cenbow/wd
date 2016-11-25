package com.okwei.cartportal.bean.vo;

public class StylesPriceVo implements java.io.Serializable
{
	private static final long serialVersionUID = 2707125117325866073L;
     
    private long styleId;
    private Double price;
    private String property;
    private String img;
    
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public long getStyleId() {
		return styleId;
	}
	public void setStyleId(long styleId) {
		this.styleId = styleId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
