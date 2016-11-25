package com.okwei.cartportal.bean;

import java.io.Serializable;
/**
 * 选择的商品数量及总价
 * @author yangjunjun
 *
 */
public class JiesuanInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int prodCount;// 选择的商品总数
	private Double prodTotalPrice;// 选择的商品总价格

	public int getProdCount() {
		return prodCount;
	}

	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}

	public Double getProdTotalPrice() {
		return prodTotalPrice;
	}

	public void setProdTotalPrice(Double prodTotalPrice) {
		this.prodTotalPrice = prodTotalPrice;
	}
}
