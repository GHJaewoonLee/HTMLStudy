package com.kitri.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.PageBean;
import com.kitri.dto.RepBoard;
import com.kitri.service.RepBoardService;


@WebServlet("/boardlist")
public class RepboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RepBoardService service;
	
	public RepboardListServlet() {
		service = new RepBoardService();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		int countPerPage = 2;
		int totalCount = service.getTotalCount();
		int countPerPageGroup = 2;
		String url = "boardlist";

		String sr = request.getParameter("currentPage");
		if (sr != null) {
			currentPage = Integer.parseInt(sr);
		}
		
		PageBean bean = new PageBean(countPerPage, totalCount, countPerPageGroup, url, currentPage);
		List<RepBoard> list = service.findByRows(bean.getStartRow(), bean.getEndRow());
		bean.setList(list);
		
		request.setAttribute("pagebean", bean);
		
		//int startRow = (cp - 1) * countPerPage + 1;
		//int endRow = cp * countPerPage;
		
		//request.setAttribute("boardlist", list);
		
		/* Total page number */
		//int totalPage = 1;
		
		//totalPage = ((totalCount - 1) / countPerPage) + 1;
		//request.setAttribute("totalPage", totalPage);
		
		/* Page group */
		
		//int startPage = ((cp - 1) / countPerPageGroup) * countPerPageGroup + 1;
		//int endPage = (((cp - 1) / countPerPageGroup) + 1) * countPerPageGroup;
		//if (endPage > totalPage) {
		//	endPage = totalPage;
		//}
		//request.setAttribute("startPage", startPage);
		//request.setAttribute("endPage", endPage);
		
		String path = "/boardListResult.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
