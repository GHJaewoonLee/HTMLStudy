package com.kitri.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.RepBoard;
import com.kitri.service.RepBoardService;


@WebServlet("/boarddetail")
public class RepBoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RepBoardService service;
	
	public RepBoardDetailServlet() {
		service = new RepBoardService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("board_seq"));
		RepBoard board = service.findBySequence(seq);
		request.setAttribute("boarddetail", board);
		
		String path = "/boardDetailResult.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
