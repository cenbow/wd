package com.okwei.cartportal.bean;
import java.io.Serializable;

import com.okwei.bean.vo.ReturnStatus;

public class ReturnModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReturnStatus Statu=ReturnStatus.LoginError;
	private String StatusReson = "";
	private Object BaseModle =null;
	public ReturnStatus getStatu() {
		return Statu;
	}
	public void setStatu(ReturnStatus statu) {
		this.Statu = statu;
	}
	public String getStatusreson() {
		if(StatusReson==null)
			return "";
		return StatusReson;
	}
	public void setStatusreson(String statusreson) {
		this.StatusReson = statusreson;
	}
	public Object getBasemodle() {
		
		return BaseModle;
	}
	public void setBasemodle(Object basemodle) {
		this.BaseModle = basemodle;
	}
	
	

}
