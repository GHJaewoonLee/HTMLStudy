<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.*" %>
    
<%!
	int cnt;
	int totalLen;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public void init() {
		totalLen = 8;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading Failed.");
		}
		
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
	
	public Connection makeConnection() throws SQLException {
 		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
 		return conn;
 	}
	
	public void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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
 			e.printStackTrace();
 		}
 	}
	
	public int findTable(String tableName) {
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
%>

<%
	try {
		conn = makeConnection();
	
		StringBuffer sql = new StringBuffer("");
		sql.append("select no ");
		sql.append("from counter");
		
		pstmt = conn.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();
		rs.next();
		
		cnt = rs.getInt("no");
		
		sql = new StringBuffer("update counter ");
		sql.append("set no = no + 1");
		pstmt.execute(sql.toString());
	} catch (SQLException e) {
		System.out.println("Fail to store value");
		e.printStackTrace();
	} finally {
		closeDB(conn, pstmt, rs);
	}

	String counter = String.format("%08d", cnt);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>DB Counter</title>
	</head>
	<body>
		<div align="center">
			당신은 
			<%for (int i = 0; i < totalLen; i++) {%>
			<img src='/BasicJSP/img/<%=counter.charAt(i)%>.png' width='20' height='15'>
			<%}%>번째 방문자입니다.
		</div>
	</body>
</html>