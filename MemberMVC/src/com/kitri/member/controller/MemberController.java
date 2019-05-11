package com.kitri.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberServiceImpl;


public class MemberController {
	
	private static MemberController memberController;
	
	static {
		memberController = new MemberController();
	}
	
	public static MemberController getMemberController() {
		return memberController;
	}

	private MemberController() {
		
	}
	
	
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String path = "/Index.jsp";
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
		if (cnt != 0) {
			request.setAttribute("userInfo", memberDetialDto);
			path = "/user/member/registerOK.jsp";
		} else {
			path = "/user/member/registerFail.jsp";
		}
		
		return path;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String path = "/Index.jsp";
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDto memberDto = MemberServiceImpl.getMemberService().loginMember(id, pass);
		
		if (memberDto != null) {
			/**************** cookie ****************/
			String idsv = request.getParameter("idsave");
			if ("idsave".equals(idsv)) {
				Cookie cookie = new Cookie("kid_inf", id);
				cookie.setDomain("localhost");
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60*60*24*365*50);
				
				response.addCookie(cookie);
			} else {
				Cookie cookie[] = request.getCookies();
				if (cookie != null) {
					for(Cookie c : cookie) {
						if ("kid_inf".equals(c.getName())) {
							c.setDomain("localhost");
							c.setPath(request.getContextPath());
							c.setMaxAge(0);
							
							response.addCookie(c);
							break;
						}
					}
				}
			}
			/****************************************/
			
			/**************** session ****************/
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", memberDto);
			/*****************************************/
			path = "/user/login/loginOK.jsp";
		} else {
			path = "/user/login/loginFail.jsp";
		}
		
		return path;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		// method 1)
		// session.setAttribute("userInfo", null);
		
		// method 2) (specified attribute)
		// session.removeAttribute("userInfo");
		
		// method 3) (all attribute)
		session.invalidate();
		
		return "/user/login/login.jsp";
	}

	public String deleteMember(HttpServletRequest request, HttpServletResponse response) {
		String path = "/Index.jsp";
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String id = memberDto.getId();
		
		int cnt = MemberServiceImpl.getMemberService().deleteMember(id);
		
		if (cnt == 2) {
			session.invalidate();
			path = "/user/member/memberOutOK.jsp";
		} else {
			path = "/user/member/memberOutFail.jsp";
		}
		
		return path;
	}
}
