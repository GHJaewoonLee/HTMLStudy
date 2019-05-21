<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>

<%@ page buffer="100kb"%>
<%@ page errorPage="Err.jsp"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>PageDirective.jsp</title>
	</head>
	<body>
		<%for(int i = 1; i <= 10000; i++) {%>
			i = <%=i%><br>
		<%}%>
		<%// a.txt 파일을 byte 단위로 읽기 위한 스트림 객체 생성
		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream("a.txt");
		%>
	</body>
</html>