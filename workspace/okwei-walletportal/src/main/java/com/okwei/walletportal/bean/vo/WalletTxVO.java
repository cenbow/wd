package com.okwei.walletportal.bean.vo;

import java.util.List;

public class WalletTxVO {
	/**
	 * 最大可提现金额
	 */
	private Double balance;
	/**
	 * 不可用
	 */
	private Double accountNot;
	/**
	 * 提现中
	 */
	private Double accountIng;
	/**
	 * 保证金
	 */
	private Double bond;
	
	/**
	 * 银行卡列表
	 */
	private List<BankCardVO> bankCardVOs;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 是否是第一次
	 */
	private boolean isFirst;

	/**
	 * 是否是退驻过的
	 */
	private boolean isTuizhu;
	
	public boolean isTuizhu() {
		return isTuizhu;
	}

	public void setTuizhu(boolean isTuizhu) {
		this.isTuizhu = isTuizhu;
	}

	public Double getAccountNot() {
		return accountNot;
	}

	public void setAccountNot(Double accountNot) {
		this.accountNot = accountNot;
	}

	public Double getAccountIng() {
		return accountIng;
	}

	public void setAccountIng(Double accountIng) {
		this.accountIng = accountIng;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<BankCardVO> getBankCardVOs() {
		return bankCardVOs;
	}

	public void setBankCardVOs(List<BankCardVO> bankCardVOs) {
		this.bankCardVOs = bankCardVOs;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Double getBond() {
		return bond;
	}

	public void setBond(Double bond) {
		this.bond = bond;
	}
	
	
}
