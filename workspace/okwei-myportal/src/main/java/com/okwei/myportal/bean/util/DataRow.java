package com.okwei.myportal.bean.util;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

/**
 * @功能描述 数据行，即是表格中的每一行数据的集合，为List类型，其中List中每一个对象是一个DataColumn类(键值对)
 * @可能的错误 当需要取得行数据中某一列时，若是类型转换不匹配会出错（例：1 若是数据项为字符串，我们取时候想取整形；）；
 * @修改说明 由于取为空字符串时候会报类型转换的错误，便使用了异常处理
 */
public class DataRow {
	List<DataColumn> col;

	/**
	 * @功能描述 返回指定DataColumn类型数据列对象

	 */
	public DataColumn getColumnObject(String colName) {

		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return c;
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return null;
	}

	/**
	 * @功能描述 返回指定Object类型数据列对象
	 */
	public Object getColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return c.getValue();
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return null;
	}

	/**
	 * @功能描述 返回指定int类型数据列对象
	 */
	public int getIntColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return Integer.parseInt(c.getValue().toString());
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return 0;
	}

	/**
	 * @功能描述 返回指定int类型数据列对象
	 */
	public Long getLongColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return Long.parseLong(c.getValue().toString());
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return 0L;
	}

	/**
	 * @功能描述 返回指定String类型数据列对象
	 */
	public String getStringColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return c.getValue().toString();
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return null;
	}

	/**
	 * @功能描述 返回指定String类型数据列对象
	 */
	public boolean getBooleanColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return (boolean)c.getValue();
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return false;
	}
	/**
	 * @功能描述 返回指定String类型数据列对象
	 */
	public String eval(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return c.getValue() + "";// 此方法将屏蔽错误！！！
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return null;
	}

	/**
	 * @功能描述 返回指定Date类型数据列对象
	 */
	public Date getDateColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return Date.valueOf(c.getValue().toString());
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return null;
	}

	/**
	 * @功能描述 返回指定Blob类型数据列对象
	 */
	public Blob getBlobColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return (Blob) c.getValue();
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return null;
	}

	/**
	 * @功能描述 返回指定Blob类型数据列对象
	 */
	public float getFloatColumn(String colName) {
		for (DataColumn c : col) {
			if (c.getKey().toUpperCase().equals(colName.toUpperCase())) {
				try {
					return Float.parseFloat(c.getValue().toString());
				} catch (Exception e) {
					System.out.println("错误描述：" + e.toString());
				}
			}
		}
		return 0;
	}

	public DataRow(List<DataColumn> c) {
		col = c;
	}

	public List<DataColumn> getCol() {
		return col;
	}

	public void setCol(List<DataColumn> col) {
		this.col = col;
	}
}
