package com.okwei.bean.vo;

public class KeyValue {

	private Long key;
	private Long value;
	
	

	public KeyValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KeyValue(long i, long j) {
		super();
		this.key = i;
		this.value = j;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
