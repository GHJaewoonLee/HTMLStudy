<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
%>

<%
	Thread.sleep(1 * 1000);
%>
<%=id%>(<%=name%>) Hello.