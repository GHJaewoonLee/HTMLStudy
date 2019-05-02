package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 모든 자바 : JDK / lib
// Web 상에서만 : Tomcat / lib
// 현재 web만 : WEB-INF / lib

@SuppressWarnings("serial")
@WebServlet("/DBCounter")
public class DBCounter extends HttpServlet {
       
	int cnt;
	int totalLen;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
    public DBCounter() {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading Failed.");
		}
    }

    
 	public Connection makeConnection() throws SQLException {
 		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
 		return conn;
 	}
    
 	private void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs) {
 		try {
 			if (rs != null) {
 				rs.close();
 			}
 			
 			if (pstmt != null) {
 				pstmt.close();
 			}
 			
 			if (conn != null) {
 				conn.close();
 			}
 		} catch (SQLException e) {
 			System.out.println("Exception occurs while closing.");
 		}
 	}

	private int findTable(String tableName) {
		int result = 0;
		
		try {
			conn = makeConnection();
			
			String sql = "";
			sql += "select count(*) exist ";
			sql += "from all_tables ";
			sql += "where table_name = '" + tableName.toUpperCase() + "'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt("exist");
			}
		} catch (SQLException e) {
			System.out.println("Exception occurs while find table.");
		} finally {
			closeDB(conn, pstmt, rs);
		}
		
		return result;
	}
 	
 	@Override
	public void init() throws ServletException {
 		totalLen = 8;
 		
 		try {
			int result = findTable("counter");

			conn = makeConnection();
			
			if (result == 1) {
				String sql = "";
				sql += "select no ";
				sql += "from counter";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					cnt = rs.getInt("no");
				} else {
					cnt = 0;
				}
			} else {
				cnt = 0;
			}
		} catch (SQLException e) {
			cnt = 0;
		} finally {
			closeDB(conn, pstmt, rs);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			conn = makeConnection();
			
			String sql = "";
			sql += "select no ";
			sql += "from counter";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			
			cnt = rs.getInt("no");
			
			sql = "update counter ";
			sql += "set no = no + 1";
			pstmt.execute(sql);
		} catch (SQLException e) {
			System.out.println("Fail to store value");
			e.printStackTrace();
		} finally {
			closeDB(conn, pstmt, rs);
		}
		
		String counter = String.format("%08d", cnt);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은");
		for (int i = 0; i < totalLen; i++) {
			out.println("<img src='/BasicServlet/img/" + counter.charAt(i) + ".png' width='20' height='15'>");
		}
		out.println("번재 방문자입니다.");
		out.println("	</body>");
		out.println("</html>");
	}
	
	/*
	@Override
	public void destroy() {
		try {
			int result = findTable("counter");

			conn = makeConnection();
			
			String sql = "";
			if (result == 1) {
				sql += "select no ";
				sql += "from counter";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					if (cnt > rs.getInt("no")) {
						sql = "";
						sql += "update counter ";
						sql += "set no = " + cnt;
						
						pstmt = conn.prepareStatement(sql);
						pstmt.executeUpdate();
					}
				}
			} else {
				sql += "create table counter ( ";
				sql += "no number ";
				sql += ")";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				
				sql = "";
				sql += "insert into counter (no) ";
				sql += "values (" + cnt + ")";

				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Fail to store value");
			e.printStackTrace();
		} finally {
			closeDB(conn, pstmt, rs);
		}
	}
	*/
}