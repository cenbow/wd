package com.okwei.bean.vo.shoppingcart;

import java.util.List;

import com.okwei.bean.vo.ReturnModel;

public class BalanceReturnModel extends ReturnModel{
	//购物车没有数据错误 1 成功 2没有记录3铺货单不可以和别的单一起结算4.只有该需求的代理商才可以购买代理价 5您的进货订单不满足落地店的采购标准，请补货后再提交订单 99别的错误
	private short shopCartBalanceStatus;
	//满足成为落地店scid List
//	private List<Long> allowlist;
	//不成为落地店scid List
//	private List<Long> noAllowlist;
	private List<Long> distributionScidList;
	public short getShopCartBalanceStatus() {
		return shopCartBalanceStatus;
	}
	public void setShopCartBalanceStatus(short shopCartBalanceStatus) {
		this.shopCartBalanceStatus = shopCartBalanceStatus;
	}
	public List<Long> getDistributionScidList() {
		return distributionScidList;
	}
	public void setDistributionScidList(List<Long> distributionScidList) {
		this.distributionScidList = distributionScidList;
	}
}
