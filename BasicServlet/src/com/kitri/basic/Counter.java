package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.text.NumberFormat;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/counter")
public class Counter extends HttpServlet {

	int cnt;
	int totalLen;
	
	
	// Load driver
	public Counter() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading Failed.");
		}
	}
	
	
	// Connect to DB
	public Connection makeConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		return conn;
	}
	
	// Close SQL objects.
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
	
	// Search specified table.
	// If 1, table find. Else , table not find.
	private int findTable(String tableName) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
	
	// 1. Search 'counter' table
	// 2. if find, get no.
	// 		2-1) if get, cnt = no;
	//		2-2) else, cnt = 0;
	// 3. else, cnt = 0;
	@Override
	public void init() throws ServletException {
		totalLen = 8;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
		cnt++;
		
//		방법 1)
//		String counter = String.valueOf(cnt);
//		while(counter.length() < 8) {
//			counter = "0" + counter;
//		}
		
//		방법 2)
		String counter = String.format("%08d", cnt);
		
//		방법 3)
//		NumberFormat nf = NumberFormat.getIntegerInstance();
//		nf.setMinimumIntegerDigits(totalLen);
//		String counter = nf.format(cnt).replace(",", "");
		
//		방법 4)
//		cnt의 자릿수, 비어있는 자리수를 각각 구하여 for 문을 적용
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은 ");
		
		for(int i = 0; i < totalLen; i++) {
			out.println("<img src='/BasicServlet/img/" + counter.substring(i, i + 1) + ".png' width='20' height='15'>");
		}
		
		out.println("번째 방문자입니다.");
		out.println("	</body>");
		out.println("</html>");
	}

	// 1. Search 'counter' table
	// 2. if find, update cnt;
	// 3. else, create table and insert cnt
	@Override
	public void destroy() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			int result = findTable("counter");

			conn = makeConnection();
			
			if (result == 1) {
				String sql = "";
				sql += "update counter ";
				sql += "set no = " + cnt;
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.executeUpdate();
			} else {
				String sql = "";
				sql += "create table counter ( ";
				sql += "no number ";
				sql += ")";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.executeUpdate();
				
				pstmt.close();
				
				sql = "";
				sql += "insert into COUNTER (no) ";
				sql += "values (" + cnt + ")";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Fail to store value");
			e.printStackTrace();
		} finally {
			closeDB(conn, pstmt, null);
		}
	}
}