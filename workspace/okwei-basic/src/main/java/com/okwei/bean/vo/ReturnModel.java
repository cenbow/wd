package com.okwei.bean.vo;
import java.io.Serializable;


public class ReturnModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReturnStatus Statu=ReturnStatus.LoginError;
	private String StatusReson = "";
	private Object BaseModle =null;
	
	
	
	
	public ReturnModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReturnModel(ReturnStatus statu, String statusReson, Object baseModle) {
		super();
		Statu = statu;
		StatusReson = statusReson;
		BaseModle = baseModle;
	}
	public ReturnStatus getStatu() {
		return Statu;
	}
	public void setStatu(ReturnStatus statu) {
		this.Statu = statu;
	}
	public String getStatusreson() {
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
