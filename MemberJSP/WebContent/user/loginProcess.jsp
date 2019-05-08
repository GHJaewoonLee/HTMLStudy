<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %>
<%@ page import="java.net.URLEncoder" %>

<%!
	public void init() throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
%>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	String name = null;
	try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select name ");
		sql.append("from member ");
		sql.append("where id = (?) and pass = (?)");
		
		pstmt = conn.prepareStatement(sql.toString());
		
		int idx = 1;
		pstmt.setString(idx++, id);
		pstmt.setString(idx++, pass);
		
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			name = rs.getString("name");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
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

	String root = request.getContextPath();
	
	if (name != null) {
		response.sendRedirect(root + "/user/loginOK.jsp?name=" + URLEncoder.encode(name, "UTF-8"));
	} else {
		response.sendRedirect(root + "/user/loginFail.jsp");
	}
%>  