<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>First.jsp</title>
	</head>
	<body>
		첫번째 JSP.
		<%int i; // scriptlet : _jspService() 내부에 작성될 구문
		i=99;%>
		<%// expression : _jspService() 내부에 작성될 구문
		// out.print() 함수가 자동 호출%>
		<%=i%>
		
		<% // declaration : _jspService() 외부에 작성될 구문%>
		<%!int i;%>
		<hr>
		i=<%=i%>, this.i=<%=this.i%>
		<hr>
		<h3>지시자</h3>
		<ul>
			<li>page directive : 속성 - contentType, import, errorPage, isErrorPage, buffer<br>
				<%Date date = new Date();
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				%>
				<%=simpleDateFormat.format(date)%>
			</li>
			<li>include directive : 정적포함(.java 파일에 포함), 속성 - file</li>
			<li>taglib directive</li>
		</ul>
		<hr>
		<h3>ACTION TAG</h3>
		<ul>
			<li>STANDARD Action Tag</li>
				<ol>
					<li>jsp:include : 동적포함(실행결과에 포함), 속성 - page</li>
					<li>jsp:forward</li>
					<li>jsp:param</li>
					<li>jsp:useBean</li>
					<li>jsp:setProperty</li>
					<li>jsp:getProperty</li>
				</ol>
			<li>CUSTOM Action Tag</li>
		</ul>
	</body>
</html>