package com.kitri.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/login")
public class MemberLogin extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to load Oracle driver");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		request.setCharacterEncoding("UTF-8");
		
		// 1. data get : (아이디, 비밀번호)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// 2. logic : member table에서 id 검색
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
		
		// 3. Response page : 2의 결과
		// 3.1 Exist : xxx님 안녕하세요.
		// 3.2 Not exist : 아이디 또는 비밀번호를 다시 확인하세요.
		//                 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
		//                 (로그인 페이지)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		if (name != null) {
			out.println("<stong>" + name + "</strong>님 안녕하세요.");
		} else {
			out.println("<font color='red'>");
			out.println("아이디 또는 비밀번호를 다시 확인하세요.<br>");
			out.println("등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			out.println("</font><br>");
			out.println("<a href='/MemberServlet/user/login.html'>로그인</a>");
		}
		out.println("	</body>");
		out.println("</html>");
	}
}
