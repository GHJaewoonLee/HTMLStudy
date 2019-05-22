<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(function() {
		var buttons = $("div.addCartResult>button");
		
		$(buttons[0]).click(function() {
			$("nav>ul>li>a[href=productlist]").trigger("click");
			return false;
		});
		
		$(buttons[1]).click(function() {
			$("nav>ul>li>a[href=viewCart]").trigger("click");
			return false;
		});
	});
</script>

<c:set var="no" value="${requestScope.no}"/>
<c:set var="quantity" value="${requestScope.quantity}"/>

<div class="addCartResult" style="position: absolute; top: 350px; left: 150px; width: 400px; border: 1px solid;">
	상품번호가 ${no}인 상품 ${quantity}개를 장바구니에 넣기 성공<br>
	<button>상품목록으로 가기</button>
	<button>장바구니 보기</button>
</div>