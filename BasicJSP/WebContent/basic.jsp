<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! 
	private String name;
	int age;
	
	public void init() {
		name = "이재운";
		age = 33;
	}
%>

<%
	String color = (age > 18) ? "blue" : "red";
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Basic JSP</title>
	</head>
	<body>
		<div align="center">
			<% out.print(name); %>(<% out.print(age); %>)님 안녕하세요.
			<%= name %>(<font color="<%= color %>"><%= age %></font>)님 안녕하세요.
		</div>
	</body>
</html>