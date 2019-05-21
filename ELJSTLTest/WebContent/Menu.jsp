<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
	<c:set var="id" value="${sessionScope.loginInfo}"/>
	<c:choose>
		<c:when test="${empty id}">
			<li><a href="user/login.html">로그인</a></li>
			<li><a href="user/member.html">회원가입</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="logout">로그아웃</a></li>
		</c:otherwise>
	</c:choose>
	<li><a href="productlist">상품조회</a></li>
	<li><a href="viewCart">장바구니 보기</a></li>
	<c:if test="${!(empty id)}">
		<li><a href="viewOrder">주문내역 보기</a></li>
	</c:if>
</ul>