package com.kitri.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.RepBoard;
import com.kitri.service.RepBoardService;


@WebServlet("/writeboard")
public class RepBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RepBoardService service;
	
	public RepBoardServlet() {
		service = new RepBoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String password = request.getParameter("password");
		String contents = request.getParameter("contents");
		
		RepBoard repBoard = new RepBoard(subject, writer, contents, password);
		
		String result = "";
		try {
			service.write(repBoard);
			result = "Write board success";
		} catch (ClassNotFoundException e) {
			result = "Write board fail";
		} catch (SQLException e) {
			result = "Write board fail";
		}
		request.setAttribute("boardResult", result);
		
		String path = "/writeBoardResult.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
