package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.MoveURL;
import com.kitri.util.SiteContance;


@WebServlet("/user")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/Index.jsp";
		
		// prevent NullPointerException
		if ("mvjoin".equals(act)) {
			MoveURL.redirect(request, response, "/user/member/member.jsp");
		} else if ("mvlogin".equals(act)) {
			MoveURL.redirect(request, response, "/user/login/login.jsp");
		} else if ("idcheck".equals(act)) {
			String sid = request.getParameter("sid");
			String resultXML = MemberServiceImpl.getMemberService().idcheck(sid);
			
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		} else if ("zipSearch".equals(act)) {
			String doro = request.getParameter("doro");
			String resultXML = MemberServiceImpl.getMemberService().zipSearch(doro);

			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		} else if ("register".equals(act)) {
			path = MemberController.getMemberController().register(request, response);
			MoveURL.forward(request, response, path);
		} else if ("login".equals(act)) {
			path = MemberController.getMemberController().login(request, response);
			MoveURL.forward(request, response, path);
		} else if ("logout".equals(act)) {
			path = MemberController.getMemberController().logout(request, response);
			MoveURL.redirect(request, response, path);
		} else if ("deletemember".equals(act)) {
			
		} else if ("mvmodify".equals(act)) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteContance.ENCODE);
		doGet(request, response);
	}
}