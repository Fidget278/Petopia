package utill;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 집에서 할 때
		
		 String url = "jdbc:mysql://localhost:3306/cafedb";
		 String user = "root";
		 String password = "mymysql";
		 
		
		
//		String url = "jdbc:mysql://10.3.14.55:3306/cafedb";
//		String user = "MZC3";
//		String password = "`123";

		return DriverManager.getConnection(url, user, password);
	}

	public static Connection getConnection(String dbURL, String dbId, String dbPassword) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbId, dbPassword);
			return conn;
		} catch (Exception e) {
			throw new RuntimeException("Connection Error");
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//DatabaseUtil.java
	public class DatabaseUtil { public Connection getConnection() { //데이터베이스와 연결상태 관리 
		try { 
			String dbURL = "jdbc:mysql://localhost:3306/TUTORIAL?useSSL=false&serverTimezone=UTC"; 
			String dbID = "root"; 
			String dbPassword = "mymysql"; 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			return DriverManager.getConnection(dbURL, dbID, dbPassword); 
			
		} catch (Exception e) { 
			e.printStackTrace(); 
			} 
		return null; 
	}


	}}
