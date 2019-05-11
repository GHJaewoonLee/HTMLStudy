<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.kitri.member.model.MemberDetailDto"%>

<%@ include file="/template/header.jsp" %>

<%		
		MemberDetailDto memberDetailDto = (MemberDetailDto)request.getAttribute("userDetailInfo");
		
		if (memberDetailDto != null) {
%>		
		회원정보수정이 성공적으로 이루어졌습니다.<br><br>
		수정된 정보는 아래와 같습니다.<br>
		이름 : <%=memberDetailDto.getName()%><br>
		이메일 : <%=memberDetailDto.getEmailid()%>@<%=memberDetailDto.getEmaildomain()%><br>
		전화번호 : <%=memberDetailDto.getTel1()%>-<%=memberDetailDto.getTel2()%>-<%=memberDetailDto.getTel3()%><br>
		주소 : <%=memberDetailDto.getZipcode()%> <%=memberDetailDto.getAddress()%> <%=memberDetailDto.getAddressDetail()%><br>
		다시 로그인 해주세요.<br>
		<%request.getSession().invalidate();%>
		<a href='<%=root%>/user?act=main'>메인으로</a>
		<%}%>
<%@ include file="/template/footer.jsp" %>