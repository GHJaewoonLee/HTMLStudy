<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Include.jsp</title>
	</head>
	<body>
		<!-- include 후 새로운 변수를 선언 하거나 기존에 선언된 변수를 참조하는 경우를 둘 다 고려해야 함 -->
		<h3>포함지시자</h3>
		<%@include file="loginResult.jsp"%>
		<hr>
		<h3>포함태그</h3>
		<jsp:include page="loginResult.jsp"/>
	</body>
</html>