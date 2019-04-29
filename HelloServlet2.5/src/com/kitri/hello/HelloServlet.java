package com.kitri.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//페이지 이동
//1. 주소 입력
//2. link
//3. form
// - GET
//------------
// - POST

//반드시 public 사용
//반드시 HttpServlet 상속
//반드시 doGet/doPost 메소드를 구현

// 생성 : Tomcat 서버
// 실행 : Client

// web.xml에서 자바 파일을 실행하기 위한 설정 필요
//<servlet>
//	<servlet-name></servlet-name>	- <servlet-mapping>의 name과 일치해야 함
//	<servlet-class></servlet-class> - Servlet이 있는 위치 (package 경로 포함)
//</servlet>
//
//<servlet-mapping>
//	<servlet-name></servlet-name>	- <servlet>의 name과 일치해야 함
//	<url-pattern></url-pattern>  	- URL 값을 지정
//</servlet-mapping>

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 코드 상에서 한글이 깨지는 경우
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("		Hello Servlet <br>");
		out.println("		안녕 Servlet");
		out.println("	</body>");
		out.println("</html>");
	}
}
