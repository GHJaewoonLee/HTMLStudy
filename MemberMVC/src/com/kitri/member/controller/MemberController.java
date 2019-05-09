package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.SiteContance;


@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		
		// prevent NullPointerException
		if ("mvjoin".equals(act)) {
			response.sendRedirect("/MemberMVC/user/member/member.jsp");
		} else if ("mvlogin".equals(act)) {
			response.sendRedirect("/MemberMVC/user/login/login.jsp");
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
			MemberDetailDto memberDetialDto = new MemberDetailDto();
			
			memberDetialDto.setName(request.getParameter("name"));
			memberDetialDto.setId(request.getParameter("id"));
			memberDetialDto.setPass(request.getParameter("pass"));
			memberDetialDto.setEmailid(request.getParameter("emailid"));
			memberDetialDto.setEmaildomain(request.getParameter("emaildomain"));
			memberDetialDto.setTel1(request.getParameter("tel1"));
			memberDetialDto.setTel2(request.getParameter("tel2"));
			memberDetialDto.setTel3(request.getParameter("tel3"));
			memberDetialDto.setZipcode(request.getParameter("zipcode"));
			memberDetialDto.setAddress(request.getParameter("address"));
			memberDetialDto.setAddressDetail(request.getParameter("address_detail"));
			
			int cnt = MemberServiceImpl.getMemberService().registerMember(memberDetialDto);
		} else if ("".equals(act)) {
			
		} else if ("".equals(act)) {
			
		} else if ("".equals(act)) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteContance.ENCODE);
		doGet(request, response);
	}
}
