<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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


<%
	Product product = (Product) request.getAttribute("productInfo");
%>

<img src="/JQueryTest/images/<%=product.getProductNo()%>.jpg" style="display: inline-block; float: left">
<div style="float: left;">
	<%=product.getProductName()%><br><br>
	<%=product.getProductDetail()%><br><br>
	상품가격 : <%=product.getProductPrice()%><br>
<!-- 	<form action="addCart" method="get"> -->
		<input type="hidden" name="no" value="<%=product.getProductNo()%>">
		상품번호 : <%=product.getProductNo()%><br>
		수량 : <input type="number" name="quantity" min="1" max="999" value="1">
		<br>
		<button>장바구니 담기</button>
<!-- 	</form> -->
</div>