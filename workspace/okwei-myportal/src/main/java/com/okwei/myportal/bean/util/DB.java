package com.okwei.myportal.bean.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class DB {
	
	// 创建连接  
    public static Connection createConn() {  
        Connection conn = null;  
        try {  

             ResourceBundle bundle = ResourceBundle.getBundle("sqlserver");
     		 String url= bundle.getString("SqlServerURL");
     		 String username=bundle.getString("SqlServerUserName");
     		 String password=bundle.getString("SqlServerPassWord");
  
            //加载驱动程序
            // Class.forName("com.mysql.jdbc.Driver"); 
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            // 获取连接(这里用户名为root，密码为空)  
            //conn = DriverManager.getConnection("jdbc:sqlserver://10.10.10.197:1433;DatabaseName=WeiShell", "okwei.com", "Okwei.com123");  
            conn = DriverManager.getConnection(url,username,password);
            
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        return conn;  
    }  
  
    // 创建回话,获取预处理语句（可以防止sql语句注入）  
    public static PreparedStatement prepare(Connection conn, String sql) {  
        PreparedStatement ps = null;  
        try {  
            ps = conn.prepareStatement(sql);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return ps;  
    }  
  
    // 关闭连接  
    public static void close(Connection conn) {  
        if (conn != null) {  
            try {  
                conn.close();  
                conn = null;  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    // 关闭回话  
    public static void close(Statement st) {  
        if (st != null) {  
            try {  
                st.close();  
                st = null;  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    // 关闭查询结果集  
    public static void close(ResultSet rs) {  
        if (rs != null) {  
            try {  
                rs.close();  
                rs = null;  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
