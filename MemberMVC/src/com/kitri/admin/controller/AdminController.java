package com.kitri.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.admin.model.service.AdminServiceImpl;
import com.kitri.util.MoveURL;
import com.kitri.util.SiteContance;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "Index.jsp";
		
		if ("memberlist".equals(act)) {
			path = "/admin/member/memberList.jsp";
			MoveURL.redirect(request, response, path);
		} else if ("getmemberlist".equals(act)) {
			String key = request.getParameter("key");
			String word = request.getParameter("word");
			
			String resultXML = AdminServiceImpl.getAdminService().getMemberList(key, word); 
					
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteContance.ENCODE);
		doGet(request, response);
	}
}
