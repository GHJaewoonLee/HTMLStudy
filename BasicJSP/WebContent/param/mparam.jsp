<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String id = request.getParameter("id");
	int age = Integer.parseInt(request.getParameter("age"));
	String[] fruits = request.getParameterValues("fruit");
	
	String color = (age == 10) ? "red" : "blue";
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%=name%>(<font color="<%=color%>"><%=id%></font>)님 안녕하세요.<br>
		당신이 좋아하는 과일은<br>
		<%if (fruits != null) {
			int index = 0;
			int len = fruits.length;
		%><%=fruits[index++]%><%while (index < len) {%>, <%=fruits[index++]%><%}%>입니다.
		<%} else {%>
			없습니다.
		<%}%>
	</body>
</html>