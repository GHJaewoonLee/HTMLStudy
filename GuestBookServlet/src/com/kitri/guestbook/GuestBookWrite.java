package com.kitri.guestbook;

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
@WebServlet("/gbwrite")
public class GuestBookWrite extends HttpServlet {
       
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
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		int cnt = 0;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into guestbook (seq, name, subject, content, logtime) ");
			sql.append("values (guestbook_seq.nextVal, ?, ?, ?, sysdate)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 1;
			pstmt.setString(idx++, name);
			pstmt.setString(idx++, subject);
			pstmt.setString(idx++, content);
			
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
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		
		if (cnt != 0) {
			out.println("글이 성공적으로 등록되었습니다.<br>");
			out.println("<a href='/GuestBookServlet/Index.html'>메인으로</a>");
		} else {
			out.println("<font color='red'>");
			out.println("서버 문제로 글쓰기가 실패하였습니다.<br>");
			out.println("재시도해주세요.");
			out.println("</font>");
			out.println("<a href='/GuestBookServlet/guestbook/write.html'>글쓰기</a>");
		}
		
		out.println("	</body>");
		out.println("</html>");
	}
}
