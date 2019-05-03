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

// Statement
// 1. 문법 검사, 타입 검사 수행
// 2. 수행을 위해서는 메모리에 올림 (1의 과정에서 문제가 없을 시)
// 3. 그 후 query 수행

// PreparedStatement
// 1. 문법 검사, 타입 검사 수행
// 2. 메모리에 올리기 전에 같은 query문이 실행되었는지 cache에서 확인
// 3. 수행을 위해서는 메모리에 올림 (1의 과정에서 문제가 없을 시)
// 4. 그 후 query 수행 (이미 메모리에 올라갔다면 타입 검사만 수행)


@SuppressWarnings("serial")
@WebServlet("/register")
public class MemberRegister extends HttpServlet {
	
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
		
		// 1. data get : (이름, 아이디, 비밀번호, 이메일1, 이메일2, 전화번호1, 전화번호2, 전화번호3, 우편번호, 주소, 상세주소)
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
		
		// 2. logic : 1의 data를 insert
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

		// 3. Response page : 2의 결과
		// 3.1 !0 : xxx님의 회원가입이 처리되었습니다.
		// 3.2 0 : 서버 문제로 회원가입이 실패하였습니다.
		//         재시도해주세요.
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		if (cnt != 0) {
			out.println(name + "님의 회원가입이 처리되었습니다.<br>");
			out.println("로그인 후 모든 서비스를 이용할 수 있습니다.<br>");
			out.println("<a href='/MemberServlet/user/login.html'>로그인</a>");
		} else {
			out.println("<font color='red'>");
			out.println("서버 문제로 회원가입이 실패하였습니다.<br>");
			out.println("재시도해주세요.");
			out.println("</font>");
		}
		out.println("	</body>");
		out.println("</html>");
	}

}
