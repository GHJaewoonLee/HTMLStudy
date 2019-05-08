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
	
	request.setCharacterEncoding("UTF-8");
	
	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String emailid = request.getParameter("emailid");
	String emaildomain = request.getParameter("emaildomain");
	String tel1 = request.getParameter("tel1");
	String tel2 = request.getParameter("tel2");
	String tel3 = request.getParameter("tel3");
	String zipcode = request.getParameter("zipcode");
	String address = request.getParameter("address");
	String addressdetail = request.getParameter("address_detail");
	
	int cnt = 0;
	try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");

		StringBuffer sql = new StringBuffer();
		sql.append("insert all ");
		sql.append("into member (id, name, pass, emailid, emaildomain, joindate) ");
		sql.append("values (?, ?, ?, ?, ?, sysdate) ");
		sql.append("into member_detail (id, zipcode, address, address_detail, tel1, tel2, tel3) ");
		sql.append("values (?, ?, ?, ?, ?, ?, ?) ");
		sql.append("select * ");
		sql.append("from dual");
		
		pstmt = conn.prepareStatement(sql.toString());
		
		int idx = 1;
		pstmt.setString(idx++, id);
		pstmt.setString(idx++, name);
		pstmt.setString(idx++, pass);
		pstmt.setString(idx++, emailid);
		pstmt.setString(idx++, emaildomain);
		
		pstmt.setString(idx++, id);
		pstmt.setString(idx++, zipcode);
		pstmt.setString(idx++, address);
		pstmt.setString(idx++, addressdetail);
		pstmt.setString(idx++, tel1);
		pstmt.setString(idx++, tel2);
		pstmt.setString(idx++, tel3);
		
		cnt = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
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
	
	if (cnt != 0) {
		response.sendRedirect(root + "/user/registerOK.jsp?name=" + URLEncoder.encode(name, "UTF-8"));
	} else {
		response.sendRedirect(root + "/user/registerFail.jsp");
	}
%>