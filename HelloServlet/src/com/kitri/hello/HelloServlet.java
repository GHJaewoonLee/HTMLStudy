package com.kitri.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Servlet 3.0은 @WebServlet annotation을 사용하여 저절로 mapping이 이루어짐

@SuppressWarnings("serial")
@WebServlet("/hs")
public class HelloServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("		Hello Servlet <br>");
		out.println("		안녕 Servlet 3.0");
		out.println("	</body>");
		out.println("</html>");
	}
}
