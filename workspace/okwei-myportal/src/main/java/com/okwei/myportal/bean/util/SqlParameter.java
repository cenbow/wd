package com.okwei.myportal.bean.util;

import java.sql.Blob;
import java.sql.Date;

/**
 * @功能描述 sql参数，sql执行时传递的参数用此类封装
 * @可能的错误
 * @修改说明
 * @修改人
 */
public class SqlParameter {
	
	public SqlParameter(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public SqlParameter(String type, int intValue) {
		this.type = type;
		this.intValue = intValue;
	}

	public SqlParameter(String type, boolean boolValue) {
		this.type = type;
		this.boolValue = boolValue;
	}

	public SqlParameter(String type, Date dateValue) {
		this.type = type;
		this.dateValue = dateValue;
	}

	public SqlParameter(String type, Blob blobValue) {
		this.type = type;
		this.blobValue = blobValue;
	}
	
	public SqlParameter(String type, Long longValue) {
		this.type = type;
		this.longValue = longValue;
	}

	String type;
	String value;
	int intValue;
	boolean boolValue;
	Date dateValue;
	Blob blobValue;
	Long longValue;
	
	

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public int getIntValue() {
		return intValue;
	}

	public boolean getBoolValue() {
		return boolValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public Blob getBlobValue() {
		return blobValue;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public void setBoolValue(boolean boolValue) {
		this.boolValue = boolValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public void setBlobValue(Blob blobValue) {
		this.blobValue = blobValue;
	}
}