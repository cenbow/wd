package com.okwei.myportal.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.myportal.bean.util.DB;
import com.okwei.myportal.bean.util.DataColumn;
import com.okwei.myportal.bean.util.DataRow;
import com.okwei.myportal.bean.util.DataTable;
import com.okwei.web.base.BaseController;
@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController {
	@ResponseBody
	@RequestMapping(value="/test")
	private DataTable index(){
		Connection conn = DB.createConn();
		String sql = "select * from W_InfoNotice";
		PreparedStatement ps = DB.prepare(conn, sql);
		DataTable t = null;
		try {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			List<DataRow> row = new ArrayList<DataRow>();// 表所有行集合
			List<DataColumn> col = null;// 行所有列集合
			DataRow r = null; // 单独一行
			DataColumn c = null;// 单独一列
			// 此处开始循环读数据，每次往表格中插入一行记录
			while (rs.next()) {
				// 初始化行集合，
				// 初始化列集合
				col = new ArrayList<DataColumn>();
				// 此处开始列循环，每次向一行对象插入一列
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					Object value = rs.getObject(columnName);
					// 初始化单元列
					c = new DataColumn(columnName, value);
					// 将列信息加入列集合
					col.add(c);
				}
				// 初始化单元行
				r = new DataRow(col);
				// 将行信息降入行结合
				row.add(r);
			}
			// 得到数据表
			t = new DataTable(row);
			rs.close();
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(ps);
			DB.close(conn);
		}
		return t;

	}
}
