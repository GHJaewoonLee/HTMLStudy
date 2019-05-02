package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 구버전의 Tomcat을 쓰는 경우 client에서 보낸 한글이 Server에서 깨지는 경우가 발생할 수 있음. (Get 방식)

// 한글이 깨짐
// 1) 기존 한글이 깨지는 경우 - setcontentType 사용
// 2) GET 방식에서 한글이 깨지는 경우 - 깨진 한글을 byte array로 쪼갠 후 (UTF-8) 다시 문자열로 변환 (원래 인코딩 방식)
// 3) POST 방식에서 한글이 깨지는 경우 - setCharacterEncoding 사용


@SuppressWarnings("serial")
@WebServlet("/multiParam")
public class MultiParameterTest extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. get Data
		String name = request.getParameter("name"); // element의 name 속성
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] fruits = request.getParameterValues("fruit");
		
		// 2. Logic
		String color = (age == 10) ? "red" : "blue";
		
		// 3. Create response page : 이름(ID)님 안녕하세요. (조건 : 연령이 10대이하면 ID를 빨간색, 아니면 파란색)
		//							 당신이 좋아하는 과일은
		//							 ... 입니다. (1개 선택)
		//							 ..., ..., ... 입니다 (여러개 선택)
		//							 없습니다. (선택하지 않았을 때)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println(name + "(" + "<font color='" + color + "'>" + id + "</font>" + ")님 안녕하세요.<br>");
		out.print("당신이 좋아하는 과일은 ");
		
		if (fruits != null) {
			int index = 0;
			int len = fruits.length;
			
			out.print(fruits[index++]);
			while (index < len) {
				out.print(", " + fruits[index++]);
			}
			out.println("입니다.");
		} else {
			out.println("없습니다.");
		}
		
		out.println("	</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 선언하지 않으면 POST 방식에서 한글이 깨짐
		doGet(request, response);
	}
}
