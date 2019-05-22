<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<%@ page import="java.util.List"%>
<%@ page import="com.kitri.dto.Product"%>
<%@ page import="com.kitri.dto.ProductCategory"%>

<script>
	$(function(){
		$("div>button").click(function(){
			$.ajax({
				url: 'addCart',
				method: 'get',
				data: 'no=' + $("div>input[name='no']").val() + '&quantity=' + $("div>input[name='quantity']").val(),
				success: function (result) {
					$("div.addCartResult").remove();
					$("section").append(result);
				}
			});
			return false;
		});
	});
</script>

<c:set var="product" value="${requestScope.productInfo}"/>

<img src="/ELJSTLTest/images/${product.productNo}.jpg" style="display: inline-block; float: left">
<div style="float: left;">
	${product.productName}<br><br>
	${product.productDetail}<br><br>
	<input type="hidden" name="no" value="${product.productNo}">
	상품가격 : <fmt:formatNumber value="${product.productPrice}" type="currency" currencySymbol="&#65510" pattern="#,#00"/><br>
	상품번호 : ${product.productNo}<br>
	수량 : <input type="number" name="quantity" min="1" max="999" value="1">
	<br>
	<button>장바구니 담기</button>
</div>