package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/counter")
public class Counter extends HttpServlet {

	int cnt;
	int totalLen;
	
	@Override
	public void init() throws ServletException {
		cnt = 0;
		totalLen = 8;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cnt++;
		
//		방법 1)
//		String counter = String.valueOf(cnt);
//		while(counter.length() < 8) {
//			counter = "0" + counter;
//		}
		
//		방법 2)
		String counter = String.format("%08d", cnt);
		
//		방법 3)
//		NumberFormat nf = NumberFormat.getIntegerInstance();
//		nf.setMinimumIntegerDigits(totalLen);
//		String counter = nf.format(cnt).replace(",", "");
		
//		방법 4)
//		cnt의 자릿수, 비어있는 자리수를 각각 구하여 for 문을 적용
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은 ");
		
		for(int i = 0; i < totalLen; i++) {
			out.println("<img src='/BasicServlet/img/" + counter.substring(i, i + 1) + ".png' width='20' height='15'>");
		}
		
		out.println("번째 방문자입니다.");
		out.println("	</body>");
		out.println("</html>");
	}
}

// init() 에서
// counter 값 얻어오기
// 1) table이 있다면 cnt 읽어오기
// 2) table이 없다면 cnt = 0

// destroy() 에서
// 1) table이 없다면 table 생성 후 저장 (insert into)
// 2) table이 있다면 저장 (update into)

//create table counter
//(
//	no number
//);
//
//insert into counter
//values (counter);