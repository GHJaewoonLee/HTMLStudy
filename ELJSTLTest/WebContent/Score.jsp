<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%! int numOfPerson = 0;
	int totalStar = 0;
%>
    
<%	numOfPerson++;
	totalStar += Integer.parseInt(request.getParameter("star"));
%>

<br>
참여자 수 : <%=numOfPerson%>명<br>
총 별점 : <%=totalStar%>점<br>
<%-- 평균 별점 : <%=(totalStar / (numOfPerson * 1.0))%>점 --%>