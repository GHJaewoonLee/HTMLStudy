<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = "abcd";
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div align="center">
			<table width="1000" border="1">
				<tr>
					<td colspan="2" height="120">
						Header
						
						<%if (true) {%>
							로그인
						<%} else {%>
							로그아웃
						<%}%>
					</td>
				</tr>
				<tr height="700">
					<td width="150">
						<a href="/IncludeTest/board/Board.jsp">Board1</a><br>
						<a href="/IncludeTest/board/Album.jsp">Album board2</a>
					</td>
					<td>