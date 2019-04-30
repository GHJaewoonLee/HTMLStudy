package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/ggd")
public class Gugudan extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("		<div align='center'>");
		out.println("			<table id='table' width='720' height='270' border='1' align='center'>");
		out.println("				<caption>");
		out.println("				<h2>*** 구구단 ***</h2>");
		out.println("				</caption>");
		
		for(int j = 1; j <= 9; j++) {
			out.println("				<tr align='center'>");
			
			for(int dan = 2; dan <= 9; dan++) {
				out.println("					<td bgcolor=" + ((dan % 2 == 0) ? "red" : "blue") + "><span style='color: white'>" + dan + " * " + j + " = " + (dan * j) + "</span></td>");
			}
			
			out.println("				</tr>");
		}
		
		out.println("			</table>");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
	}
}
