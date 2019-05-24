<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<style>
	div.boardlist {
		width: 70%;
	}
	
	div.boardlist>h3{
		font-weight: bold;
		text-align: center;
	}

	div.boardlist>div.pageInfo {
		text-align:right;
		font-style: italic;
	}
	
	div.boardlist>div.table {
		display:table;
		margin: 10 auto;
		width: 90%;
	}
	
	div.boardlist>div.table>div.tr {
		display: table-row;
	}
	
	div.boardlist>div.table>div.tr>div.th {
		display:table-cell;
		font-weight: bold;
		text-align: center;
	}
	
	div.boardlist>div.table>div.tr>div.td {
		display:table-cell;
	}
	
	div.boardlist>div.table, div.boardlist div.th, div.boardlist div.td {
		border: 1px solid #93DAFF; border-collapse: collapse;
	}
	
	div.boardlist>div.pagegroup {
    	width: 90%; 
	}
	
	div.boardlist>div.pagegroup>ul {
		margin: 0 auto;
	}
	
	div.boardlist>div.pagegroup>ul>li {
	    list-style: none;
	    display: inline-block;
	}
	
	div.boardlist>div.pagegroup a {
	    margin:10px;
	    text-decoration: none;    
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 
<script>
$(function() {
	// show detail board information
	$("div.boardlist>div.table>div.tr").click(function() {
		if($(this).index() != 0) {
			var board_seq = $(this).find("div.board_seq").html().trim();
			$.ajax({
				url: 'boarddetail',
				method: 'get',
				data: 'board_seq='+board_seq,
				success:function(result) {
					$("section").html(result.trim());
				}
			});
		}
		return false;
	});
	
	// show current page
	$("div.boardlist>div.pagegroup a").click(function() {
		var currentPage = $(this).attr("href");
		$.ajax({
			url: 'boardlist',
			method: 'get',
			data: 'currentPage='+currentPage,
			success: function(result) {
				$("section").html(result.trim());
			}
		});
		return false;
	});
});
</script>

<c:set var="bean" value="${requestScope.pagebean}"/>

<div class="boardlist">
	<h3>게시글 목록</h3>
	<div class="pageInfo">현재페이지:${bean.currentPage}page, 총페이지:${bean.totalPage}page</div>
	<div class="table">
	    <div class="tr">
			<div class="th board_seq">글번호</div>
			<div class="th board_subject">글제목</div>
			<div class="th board_writer">작성자</div>
			<div class="th board_viewcont">조회수</div>
	    </div>
	    
	    <c:forEach var="board" items="${bean.list}">
	    	<div class="tr">
	    		<div class="td board_seq">${board.board_seq}</div>
	    		<div class="td board_subject">${board.board_subject}</div>
	    		<div class="td board_writer">${board.board_writer}</div>
	    		<div class="td board_viewcont">${board.board_viewcount}</div>
	    	</div>
	    </c:forEach>   
	</div>
	<div class="pagegroup"> 
		<ul>
			<c:if test="${bean.startPage != 1}">
				<li><a href="${bean.startPage - 1}">◀</a></li>
			</c:if>
			<c:forEach begin="${bean.startPage}" end="${bean.endPage}" step="1" var="i">
				<c:choose>
					<c:when test="${i == bean.currentPage}">
						<li>${bean.currentPage}</li>
					</c:when>
					<c:otherwise>
						 <li><a href="${i}">${i}</a></li> 
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${bean.endPage != bean.totalPage}">
				<li><a href="${bean.endPage + 1}">▶</a></li>
			</c:if>
		</ul>   
  	</div>   
</div>