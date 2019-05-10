<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.kitri.member.model.MemberDto"%>
<%@page import="com.kitri.util.MoveURL"%>

<%@ include file="/template/header.jsp" %>

<%
	MemberDto memberDto = (MemberDto)session.getAttribute("userInfo");

	if (memberDto != null) {
%>
		<script type="text/javascript">
			function deleteMember() {
				if (confirm("탈퇴하시겠습니까?")) {
					document.location.href = "<%=root%>/user?act=deletemember";
				}
			}
		</script>

		<strong><%=memberDto.getName()%>(<%=memberDto.getId()%>)</strong>님 안녕하세요.
		<a href="<%=root%>/user?act=logout">로그아웃</a>
		<a href="<%=root%>/user?act=mvmodify">정보수정</a>
		<a href="#" onclick="javascript:deleteMember();">회원탈퇴</a>
		<%if ("java2".equals(memberDto.getId())) {%>
		<a href="<%=root%>/admin?act=memberlist">관리자</a>
		<%} else {
			MoveURL.redirect(request, response, "/user?act=mvlogin");
		}
	}%>
		
<%@ include file="/template/footer.jsp" %>