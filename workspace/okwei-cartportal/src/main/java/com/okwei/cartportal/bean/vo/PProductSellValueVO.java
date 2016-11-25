package com.okwei.cartportal.bean.vo;

import com.okwei.bean.domain.PProductSellValue;

public class PProductSellValueVO extends PProductSellValue{
	/**选择的款式*/
	private Long sellKey;

	public Long getSellKey() {
		return sellKey;
	}

	public void setSellKey(Long sellKey) {
		this.sellKey = sellKey;
	}
}
