package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/singleParam")
public class SingleParameterTest extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. get Data
		String name = request.getParameter("name"); // element의 name 속성
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// 2. Logic
		String color = (age == 10) ? "red" : "blue";
		
		// 3. Create response page : 이름(ID)님 안녕하세요. (조건 : 연령이 10대이하면 ID를 빨간색, 아니면 파란색)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println(name + "(" + "<font color='" + color + "'>" + id + "</font>" + ")님 안녕하세요.");
		out.println("	</body>");
		out.println("</html>");
	}

	// 내부적으로 socket IO가 발생
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. get Data
		request.setCharacterEncoding("UTF-8"); // POST 방식에서만 가능
		String name = request.getParameter("name"); // element의 name 속성
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// 2. Logic
		String color = (age == 10) ? "red" : "blue";
		
		// 3. Create response page : 이름(ID)님 안녕하세요. (조건 : 연령이 10대이하면 ID를 빨간색, 아니면 파란색)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println(name + "(" + "<font color='" + color + "'>" + id + "</font>" + ")님 안녕하세요.");
		out.println("	</body>");
		out.println("</html>");
	}
}
