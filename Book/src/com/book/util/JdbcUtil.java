package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// JDBC 工具类
public class JdbcUtil {
	private static final String DRIVERNAME;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	// 用来保存一个 Connection 对象，保证同一线程保存的是同一对象，不同线程保存的是不同对象
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	static {
		Properties ps = ConfigReader.read("dbconfig.properties");
		DRIVERNAME = ps.getProperty("driverName");
		URL = ps.getProperty("url");
		USERNAME = ps.getProperty("username");
		PASSWORD = ps.getProperty("password");
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 创建一个连接并返回
	public static Connection getConnection() throws SQLException {
		// 先尝试从 ThreadLocal 中获得 Connection 对象
		Connection conn = threadLocal.get();
		if (conn == null) {
			// 如果没有，说明没有创建连接，则创建
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 禁用自动提交事务
			conn.setAutoCommit(false);
			// 将 Connection 对象放入 ThreadLocal 中，以便同一线程其他对象使用
			threadLocal.set(conn);
		}
		return conn;
	}

	// 释放资源，不需要释放的可以传值 null
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		release(rs);
		release(stmt);
		release(conn);
	}

	private static void release(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void release(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void release(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 关闭连接后，Connection 对象已不可用，删除
			threadLocal.remove();
		}
	}
}