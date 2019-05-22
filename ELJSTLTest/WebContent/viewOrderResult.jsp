<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		
		<c:set var="list" value="${requestScope.orderlist}"/>
		<c:forEach var="info" items="${list}">
			<c:set var="detailList" value="${info.details}"/>
			<tr>
				<td>${info.order_no}</td>
				<td>${info.order_time}</td>
				
				<c:set var="nos" value=""/>
				<c:set var="names" value=""/>
				<c:set var="prices" value=""/>
				<c:set var="quantities" value=""/>
				
				<c:forEach var="infoDetail" items="${detailList}">
					<c:set var="nos" value="${nos += infoDetail.product.productNo += '<br>'}"/>
					<c:set var="names" value="${names += infoDetail.product.productName += '<br>'}"/>
					<c:set var="prices" value="${prices += infoDetail.product.productPrice += '<br>'}"/>
					<c:set var="quantities" value="${quantities += infoDetail.order_quantity += '<br>'}"/>
				</c:forEach>
				
				<td><pre>${nos.trim()}</pre></td>
				<td><pre>${names.trim()}</pre></td>
				<td><pre>${prices.trim()}</pre></td>
				<td><pre>${quantities.trim()}</pre></td>
			</tr>
		</c:forEach>
	</table>
</div>