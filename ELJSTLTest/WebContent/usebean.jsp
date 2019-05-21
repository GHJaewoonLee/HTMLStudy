<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.kitri.dto.Customer"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%--
			// 1. 	request의 속성 (이름 : "c", 타입 : "com.kitri.dto.Customer"
			Customer c = (Customer) request.getAttribute("c");
		
			// 2.1   속성이 null인 경우
			//			Customer 객체를 생성하여 c 참조변수에 대입
			//			c 참조변수를 request의 속성으로 (이름 : "c") 추가
			if (c == null) {
				c = new Customer();
				request.setAttribute("c", c);
			}
		--%>
		<!-- class의 값은 가급적이면 package 이름을 전부 사용할 것 (이미 import 되었더라도) -->
		<jsp:useBean id="c" class="com.kitri.dto.Customer" scope="request"/>
		
		<%--c.setId("ID1");--%>
		<jsp:setProperty name="c" property="id" value="ID1"/>
		
		<%--c.setName(request.getParameter("n");--%>
		<jsp:setProperty name="c" property="name" param="n"/>
		
		<%-- <%=c.getId()%> --%>
		<jsp:getProperty name="c" property="id"/>
		
		<!-- 문제점 : Has-A 관계를 표현하기가 매우 어려움 -->
		<!-- 해결책 : EL/JSTL 사용 -->
	</body>
</html>