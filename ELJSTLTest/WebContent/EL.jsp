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
		작업구분
		<hr>
		<%=request.getParameter("opt")%> 작업을 선택<br>
		${param.opt} 작업을 선택
		<hr>
		<%=Integer.parseInt(request.getParameter("a")) + 10%><br>
		${param.a+10}
		<hr>
		<%--
			Customer customer = new Customer("id1", "p1", "n1");
			request.setAttribute("customer", customer);
		--%>
		고객이름 : <%-- <%=((Customer)request.getAttribute("customer")).getName()%> --%><br>
		고객이름 : ${requestScope.customer.name}
		
		<%-- EL의 문제점 : 반복문을 처리할 수 없음 --%>
		<%-- 해결방법 : JSTL 사용 --%>
		<hr>
		웹컨텍스트 이름 : ${pageContext.request.contextPath}
	</body>
</html>