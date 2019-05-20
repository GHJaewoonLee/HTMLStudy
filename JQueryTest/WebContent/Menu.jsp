<%@ page contentType="text/html; charset=UTF-8"%>

<ul>
	<%String id = (String)session.getAttribute("loginInfo");
	if (id == null) {%>
	<li><a href="user/login.html">로그인</a></li>
	<li><a href="user/member.html">회원가입</a></li>
	<%} else {%>
	<li><a href="logout">로그아웃</a></li>
	<%}%>
	<li><a href="productlist">상품조회</a></li>
	<li><a href="viewCart">장바구니 보기</a></li>
	<%if (id != null) {%>
	<li><a href="viewOrder">주문내역 보기</a></li>
	<%}%>
</ul>