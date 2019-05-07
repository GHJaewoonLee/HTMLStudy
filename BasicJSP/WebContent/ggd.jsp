<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
	int dan;
	int i;
	String color;
	
	String gugudan() {
		StringBuffer str = new StringBuffer("");
		
		for(i = 1; i <= 9; i++) { 
			str.append("<tr align='center'>");
			
			for(dan = 2; dan <= 9; dan++) {
				str.append("<td bgcolor=" + ((dan % 2 == 0) ? "red" : "blue") + "><span style='color: white'>" + dan + " * " + i + " = " + (dan * i) + "</span></td>");
			}
			
			str.append("</tr>");
		}
		
		return str.toString();
	}
%>
    
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>구구단</title>
	</head>
	<body>
		<div align="center">
			<h3>*** 구구단 ***</h3>
			<table id='table' style='width: 720; height: 270; border: 1; align: center'>
				<%= gugudan() %>
			</table>
			
			<hr>
			<h3>*** 구구단 ***</h3>
			<table id='table' style='width: 720; height: 270; border: 1; align: center'>
				<%
					for(int j = 1; j <= 9; j++) {
						out.println("	<tr align='center'>");
						
						for(dan = 2; dan <= 9; dan++) {
							out.println("		<td bgcolor=" + ((dan % 2 == 0) ? "red" : "blue") + "><span style='color: white'>" + dan + " * " + j + " = " + (dan * j) + "</span></td>");
						}
						
						out.println("	</tr>");
					}
				%>
			</table>
			
			<hr>
			<h3>*** 구구단 ***</h3>
			<table id='table' style='width: 720; height: 270; border: 1; align: center'>
				<% for(i = 1; i <= 9; i++) { %>
					<tr align="center">

					<% for(dan = 2; dan <= 9; dan++) { %>
						<% color = ((dan % 2 == 0) ? "red" : "blue"); %>
						<td bgcolor="<%= color %>"><span style="<%="color: white"%>"><%= dan %> * <%= i %> = <%= (dan * i) %></span></td>
					<% } %>
					
					</tr>
				<% } %>
			</table>
		</div>
	</body>
</html>