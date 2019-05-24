<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<c:set var="detail" value="${requestScope.boarddetail}"/>

<div>
	<table border="1">
		<thead>
			<tr>
				<th>글 번호</th><th>제목</th><th>작성자</th><th>작성일자</th><th>조회수</th>
			</tr>
			<tr>
				<td>${detail.board_seq}</td>
				<td>${detail.board_subject}</td>
				<td>${detail.board_writer}</td>
				<td>${detail.board_date}</td>
				<td>${detail.board_viewcount}</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><strong>내용</strong></td>
				<td colspan="4"><pre>${detail.board_contents}</pre></td>
			</tr>
		</tbody>
	</table>
</div>