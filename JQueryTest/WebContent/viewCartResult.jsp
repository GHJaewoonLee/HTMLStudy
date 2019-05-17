<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="com.kitri.dto.Product"%>
    
<%
	Map<Product, Integer> map = (Map<Product, Integer>) request.getAttribute("requestCart");
	Set<Product> set = map.keySet();
%>

<div class="viewCartResult">
	<table>
	   	<tr>
	     	<th>상품번호</th><th>상품명</th><th>상품가격</th><th>수량</th>     
	   	</tr>
		<%for (Product product : set) {%>
		<tr>
			<td><%=product.getProductNo()%></td>
			<td><%=product.getProductName()%></td>
			<td><%=product.getProductPrice()%></td>
			<td><%=map.get(product)%></td>
		</tr>
		<%}%>
		<tr>
     		<td colspan="4" style="text-align: center;">
     			<button style="margin: 10px;" class="removeCart">장바구니 비우기</button>
     			<%if(session.getAttribute("loginInfo") != null) {%>
     			<button style="margin: 10px;" class="addOrder">주문하기</button>
     			<%}%>
   			</td>
 		</tr>
	</table>
</div>