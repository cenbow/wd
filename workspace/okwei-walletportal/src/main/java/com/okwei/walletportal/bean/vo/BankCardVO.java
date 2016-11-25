package com.okwei.walletportal.bean.vo;

public class BankCardVO {
	/**
	 * 银行卡ID
	 */
	private Long cardID;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String cardNo;
	/**
	 * 是否对公账号
	 */
	private int isPublic;
	/**
	 * 是否选中
	 */
	private int isSelect;
	
	public Long getCardID() {
		return cardID;
	}

	public void setCardID(Long cardID) {
		this.cardID = cardID;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getIsPublic() {
	    return isPublic;
	}

	public void setIsPublic(int isPublic) {
	    this.isPublic = isPublic;
	}

	public int getIsSelect() {
	    return isSelect;
	}

	public void setIsSelect(int isSelect) {
	    this.isSelect = isSelect;
	}
}
