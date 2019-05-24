<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.io.File"%>

<%
	String saveDir = "d:\\javadata";
	File dir = new File(saveDir);
	for(String fileName : dir.list()) {
%><a href="download?filename=<%=fileName%>"><%=fileName %></a><br>
<%	  
	}
%>
