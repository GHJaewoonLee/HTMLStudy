<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.kitri.member.model.MemberDetailDto"%>
    
<%@ include file="/template/header.jsp" %>  
		<script type="text/javascript" src="<%=root%>/js/httpRequest.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#zipcode').focusin(function() {
					$('#zipModal').modal();
				});
			});
			
			var reExpName = /^[가-힣]{2,5}$/
			var rgExpIDPW = /^[a-zA-Z0-9]{4,16}$/
			
			function modifyRegister() {
				if (!reExpName.test(document.getElementById("name").value)) {
					alert("2 ~ 5글자의 한글을 입력하세요.");
					return;
				}  else if (!rgExpIDPW.test(document.getElementById("pass").value)) {
					alert("숫자와 영문을 조합한 4 ~ 16자리 사이의 글자를 입력하세요.");
					return;
				} else if (document.getElementById("pass").value != document.getElementById("passcheck").value) {
					alert("비밀번호와 비밀번호 확인 값은 일치하여야 합니다.");
					return;
				} else {
					document.getElementById("memberform").action = "<%=root%>/user";
					document.getElementById("memberform").submit();
				}
			}
		</script>

<%
	MemberDetailDto memberDetailDto = (MemberDetailDto)session.getAttribute("userDetailInfo");
%>
	
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<h2>회원가입</h2>
			<form id="memberform" method="post" action="">
				<input type="hidden" name="act" value="modify">
				<div class="form-group" align="left">
					<label for="name">이름</label>
					<input type="text" class="form-control" id="name" name="name" value="<%=memberDetailDto.getName()%>">
				</div>
				<div class="form-group" align="left">
					<label for="">아이디</label>
					<input type="text" class="form-control" id="id" name="id" value="<%=memberDetailDto.getId()%>" readonly="readonly">
					<div id="idresult"><font color="gray">아이디는 변경이 불가능합니다.</font></div>
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호</label>
					<input type="password" class="form-control" id="pass" name="pass" placeholder="">
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호재입력</label>
					<input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="">
				</div>
				<div class="form-group" align="left">
					<label for="email">이메일</label><br>
					<div id="email" class="custom-control-inline">
					<input type="text" class="form-control" id="emailid" name="emailid" value="<%=memberDetailDto.getEmailid()%>" size="25"> @
					<select class="form-control" id="emaildomain" name="emaildomain">
						<option value="naver.com">naver.com</option>
						<option value="google.com">google.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="hanmail.net">hanmail.net</option>
					</select>
					</div>
				</div>
				<div class="form-group" align="left">
					<label for="tel">전화번호</label>
					<div id="tel" class="custom-control-inline">
					<select class="form-control" id="tel1" name="tel1">
						<option value="010">010</option>
						<option value="02">02</option>
						<option value="031">031</option>
						<option value="032">032</option>
						<option value="041">041</option>
						<option value="051">051</option>
						<option value="061">061</option>
					</select> _
					<input type="text" class="form-control" id="tel2" name="tel2" value="<%=memberDetailDto.getTel2()%>"> _
					<input type="text" class="form-control" id="tel3" name="tel3" value="<%=memberDetailDto.getTel3()%>">
					</div>
				</div>
				<div class="form-group" align="left">
					<label for="">주소</label><br>
					<div id="addressdiv" class="custom-control-inline">
						<input type="text" class="form-control" id="zipcode" name="zipcode" value="<%=memberDetailDto.getZipcode()%>" size="7" maxlength="5" readonly="readonly">
					</div>
					<input type="text" class="form-control" id="address" name="address" value="<%=memberDetailDto.getAddress()%>" readonly="readonly">
					<input type="text" class="form-control" id="address_detail" name="address_detail" value="<%=memberDetailDto.getAddressDetail()%>">
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-primary" id="registerBtn" onclick="javascript:modifyRegister();">정보수정</button>
				</div>
			</form>
		</div>
	</div>
	
<%@ include file="/user/member/zipSearch.jsp" %> 
<%@ include file="/template/footer.jsp" %> 