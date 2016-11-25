package com.okwei.appinterface.bean.dto;

import java.io.Serializable;

public class BaseParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tiket;

	private short isAno;

	public short getIsAno() {
		return isAno;
	}

	public void setIsAno(short isAno) {
		this.isAno = isAno;
	}

	public String getTiket() {
		return tiket;
	}

	public void setTiket(String tiket) {
		this.tiket = tiket;
	}

}
