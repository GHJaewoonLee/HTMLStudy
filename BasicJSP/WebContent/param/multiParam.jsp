<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>다중 파라미터 전달</title>
	</head>
	<body>
		<div align="center">
			<h3>JSP로 (다중)파라미터 전달.</h3>
			<form method="post" action="/BasicJSP/param/mparam.jsp">
				<table>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name" id="name2"></td>
					</tr>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id"></td>
					</tr>
					<tr>
						<td>연령</td>
						<td>
							<select name="age">
								<option value="10">10대이하
								<option value="20">20대
								<option value="30">30대
								<option value="40">40대
								<option value="50">50대이상
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">좋아하는 과일?</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<label><input type="checkbox" name="fruit" value="사과">사과</label>
							<label><input type="checkbox" name="fruit" value="바나나">바나나</label>
							<label><input type="checkbox" name="fruit" value="수박">수박</label>
							<label><input type="checkbox" name="fruit" value="딸기">딸기</label>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="submit" value="전송">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>