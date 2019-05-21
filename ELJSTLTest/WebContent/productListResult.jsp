<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>
<%@ page import="com.kitri.dto.Product"%>
<%@ page import="com.kitri.dto.ProductCategory"%>

<script>
$(function(){
	var aDiv = $("div");
	
	$(aDiv).click(function(){
		$.ajax({
			url: 'productinfo',
			method: 'get',
			data: 'no=' + $(this).attr("id"),
			//$(this).find("attributes");
			success: function (result) {
				//location.href='productInfoResult.jsp';
				$("section").html(result.trim());
			}
		});
		return false;
	});
});
</script>
    
<c:forEach var="p" items="${requestScope.allProduct}">
	<div style="display: inline-block; border: solid;" id="${p.productNo}">
		<img src="/ELJSTLTest/images/${p.productNo}.jpg"><br>
		<div>카테고리 : ${p.productCategory.categoryName}</div>
		<div>상품번호 : ${p.productNo}</div>
		<div>상품명 : ${p.productName}</div>
		<div>상품가격 : ${p.productPrice}</div>
	</div>
	&nbsp;&nbsp;
</c:forEach>