<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List"%>
<%@ page import="com.kitri.dto.Product"%>
<%@ page import="com.kitri.dto.ProductCategory"%>

<%
	Product product = (Product) request.getAttribute("productInfo");
%>

<img src="/JQueryTest/images/<%=product.getProductNo()%>.jpg" style="display: inline-block; float: left">
<div style="float: left;">
	<%=product.getProductName()%><br><br>
	<%=product.getProductDetail()%><br><br>
	상품번호 : <%=product.getProductNo()%><br>
	상품가격 : <%=product.getProductPrice()%><br>
	수량 : <select>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
		 </select>
</div>