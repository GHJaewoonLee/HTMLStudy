<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL : JSP Standard Tag Library --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%-- <% int num = Integer.parseInt(request.getParameter("num")); %> --%>
		<c:set var="num" value="${param.num}"/>
		<hr>
		<%-- <% if ((num % 2) == 0) {짝수} %> --%>
		<c:if test="${(num % 2) == 0}">
			짝수
		</c:if>
		<hr>
		<c:choose>
			<c:when test="${(num % 3) == 1}">3으로 나눈 나머지 : 1</c:when>
			<c:when test="${(num % 3) == 2}">3으로 나눈 나머지 : 2</c:when>
			<c:when test="${(num % 3) == -1}">3으로 나눈 나머지 : -1</c:when>
			<c:when test="${(num % 3) == -2}">3으로 나눈 나머지 : -2</c:when>
			<c:otherwise>3의 배수</c:otherwise>
		</c:choose>
		<hr>
		<%-- <% for (int i = 1; i <=10; i++) {} %> --%>
		<%-- <% for (int i = 1, j = 1; (i <= 10) && (j <= 10); i++, j++) {i+j} %> --%>
		<c:forEach begin="1" end="10" step="1" var="i">
			${i}
		</c:forEach>
		<hr>
		<c:set var="total" value="0"/>
		<c:forEach begin="1" end="10" step="1" var="i">
			<c:set var="total" value="${total + i}"/>
		</c:forEach>
		${total}
		<hr>
		<%
			List<String> list = new ArrayList<String>();
			list.add("one");
			list.add("two");
			list.add("three");
			
			request.setAttribute("list", list);
		%>
		<%-- <%for (String e : (List<String>)request.getAttribute("list")) {} %> --%>
		<c:forEach var="e" items="${requestScope.list}">
			${e}<br>
		</c:forEach>
		<hr>
		<%-- index : 현재 인덱스 --%>
		<%-- count : 반복 수행된 횟수 --%>
		<c:forEach var="e" items="${requestScope.list}" varStatus="obj">
			${obj.index} - ${e} : ${obj.count}회<br>
		</c:forEach>
	</body>
</html>