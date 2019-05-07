<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
	int cnt;
	int totalLen;	

	public void init() {
		cnt = 0;
		totalLen = 8;
	}
%>
    
<%
	cnt++;
	String cntStr = cnt + "";
	int cntLen = cntStr.length();
	int zeroLen = totalLen - cntLen;
%>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
	</head>
	<body>
		<div align="center">
		당신은
		<%for (int i = 0; i<zeroLen; i++) {%> 
			<img src="/BasicJSP/img/0.png" width="<%=25%>">
      	<%}
      	for (int i = 0; i<cntLen; i++) {%> 
			<img src = "/BasicJSP/img/<%=cntStr.charAt(i)%>.png" width="<%=25%>">
		<%}%>
		번째 방문자입니다.
		</div>
	</body>
</html>