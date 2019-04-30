package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/basic")
public class BasicServletTest extends HttpServlet {
	
	String name;
	int age;
	
	
	@Override
	public void init() throws ServletException {
		name = "jwlee";
		age = 25;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String color = (age <= 18) ? "red" : "blue";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("		<font color=\"steelblue\">" + name + "</font>(" + "<font color=" + ((age <= 18) ? "red" : "blue") + ">" + age + "</font><font>)Hello</font>");
		out.println("	</body>");
		out.println("</html>");
	}
}
