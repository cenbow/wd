package com.okwei.cartportal.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.bean.vo.order.BShoppingCartVO;

public class ShoppingCartOrderParam implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private BAddressVO receiveInfo;
	private List<BShoppingCartVO> supplierOrderList;

	public BAddressVO getReceiveInfo() {
		return receiveInfo;
	}

	public void setReceiveInfo(BAddressVO receiveInfo) {
		this.receiveInfo = receiveInfo;
	}

	public List<BShoppingCartVO> getSupplierOrderList() {
		return supplierOrderList;
	}

	public void setSupplierOrderList(List<BShoppingCartVO> supplierOrderList) {
		this.supplierOrderList = supplierOrderList;
	}

}
