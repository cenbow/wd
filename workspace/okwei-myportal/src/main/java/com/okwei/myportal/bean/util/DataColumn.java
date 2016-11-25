package com.okwei.myportal.bean.util;

/**
 * @功能描述 数据列，也是最简单的数据项，相当于表格中的一个单元项目。 采用健值对思想，key为列名，value对应key值的单元格元素，为Object类型
 * @可能的错误
 * @修改说明
 * @修改人
 */
public class DataColumn {
	private String key;
	private Object value;

	public DataColumn(String k, Object v) {
		key = k;
		value = v;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}