<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    
<%
	List<Product> product = (List<Product>) request.getAttribute("allProduct");
%>

<%
for(Product p : product) {%>
	<div style="display: inline-block; border: solid;" id="<%=p.getProductNo()%>">
		<img src="/JQueryTest/images/<%=p.getProductNo()%>.jpg"><br>
		<div>카테고리 : <%=p.getProductCategory().getCategoryName()%></div>
		<div>상품번호 : <%=p.getProductNo()%></div>
		<div>상품명 : <%=p.getProductName()%></div>
		<div>상품가격 : <%=p.getProductPrice()%></div>
	</div>
	&nbsp;&nbsp;
<%}%>