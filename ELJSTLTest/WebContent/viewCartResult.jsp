<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="com.kitri.dto.Product"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	var btnAddOrder = $("div.viewCartResult>table tr>td>button.addOrder");
	btnAddOrder.click(function() {
		$.ajax({
			url : "addOrder",
			method : "get",
			success : function (result) {
				if (result.trim() == '1') {
					alert("Order success");
				} else {
					alert("Order fail");
				}
			}
		});
		return false;
	});
</script>
    
<div class="viewCartResult">
	<table>
	   	<tr>
	     	<th>상품번호</th><th>상품명</th><th>상품가격</th><th>수량</th>     
	   	</tr>
			<c:forEach var="m" items="${requestScope.requestCart}">
				<tr>
					<td>${m.key.productNo}</td>
					<td>${m.key.productName}</td>
					<td>${m.key.productPrice}</td>
					<td>${m.value}</td>
				</tr>
				<br>
			</c:forEach>
		<tr>
     		<td colspan="4" style="text-align: center;">
     			<button style="margin: 10px;" class="removeCart">장바구니 비우기</button>
     			<c:if test="${!empty sessionScope.loginInfo}">
	     			<button style="margin: 10px;" class="addOrder">주문하기</button>
     			</c:if>
   			</td>
 		</tr>
	</table>
</div>