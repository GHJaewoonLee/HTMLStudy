package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/gblist")
public class GuestBookList extends HttpServlet {
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to load Oracle driver");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.println("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css'>");
		out.println("		<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>");
		out.println("		<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js'></script>");
		out.println("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js'></script>");
		out.println("	</head>");
		
		out.println("	<body>");
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * ");
			sql.append("from guestbook ");
			sql.append("order by seq desc");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			out.println("<div class='container' align='center'>");
			out.println("	<div class='col-lg-8' align='center'>");
			out.println("		<h2>글목록</h2>");
			out.println("		<table class='table table-borderless'>");
			out.println("			<tr>");
			out.println("				<td align='right'><a href='/GuestBookServlet/guestbook/write.html'>글쓰기</a></td>");
			out.println("			</tr>");
			out.println("		</table>");
			
			while(rs.next()) {
				out.println("		<table class='table table-active'>");
				out.println("			<tbody>");
				out.println("				<tr>");
				out.println("					<td>작성자 : " + rs.getString("name") + "</td>");
				out.println("					<td style='text-align: right;'>작성일 : " + rs.getDate("logtime") + "</td>");
				out.println("				</tr>");
				out.println("				<tr>");
				out.println("					<td colspan='2'><strong>" + rs.getInt("seq") + ". " + rs.getString("subject") + "</strong></td>");
				out.println("				</tr>");
				out.println("				<tr>");
				out.println("					<td colspan='2'><pre>" + rs.getString("content") + "</pre></td>");
				out.println("				</tr>");
				out.println("			</tbody>");
				out.println("		</table>");
			}
			
			out.println("	</div>");
			out.println("</div>");
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
		
		out.println("	</body>");
		out.println("</html>");
	}
}
