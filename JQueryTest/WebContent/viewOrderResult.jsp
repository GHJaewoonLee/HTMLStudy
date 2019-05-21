<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>

<%@page import="com.kitri.dto.Product"%>
<%@page import="com.kitri.dto.OrderInfo"%>
<%@page import="com.kitri.dto.OrderInfoDetail"%>

<style>
	pre {
		margin: 0;
	}
	
	table {
		margin: 0 auto;
		width: 40%;
	}
</style>

<div class="viewOrder">
	<h4 style="text-align: center;">주문 내역</h4>
	<table border="1">
		<tr><th>주문번호</th><th>주문일자</th><th>상품번호</th><th>상품명</th><th>가격</th><th>주문수량</th></tr>
<%
	List<OrderInfo> list = (List<OrderInfo>) request.getAttribute("orderlist");

	for (OrderInfo info : list) {
%> <tr> <%
		int oldOrder = -1;
		int orderNo = info.getOrder_no();
		Date orderTime = info.getOrder_time();
%> <td><%=orderNo%></td><td><%=orderTime%></td>
<%
		List<OrderInfoDetail> detailList = info.getDetails();

		String nos = "";
		String names = "";
		String prices = "";
		String quantities = "";
		
		for (OrderInfoDetail infoDetail : detailList) {
			Product product = infoDetail.getProduct();
			
			nos += (product.getProductNo() + "\n");
			names += (product.getProductName() + "\n");
			prices += (product.getProductPrice() + "\n");
			quantities += (infoDetail.getOrder_quantity() + "\n");
		}
%> <td><pre><%=nos.trim()%></pre></td><td><pre><%=names.trim()%></pre></td><td><pre><%=prices.trim()%></pre></td><td><pre><%=quantities.trim()%></pre></td></tr> <%
	}
%>
	</table>
</div>