<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Semantic.html</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
		<style>
			header {
				background-image: url("https://docs.gimp.org/2.4/images/tutorials/quickie-scale-example.jpg");
				background-repeat: no-repeat;
				background-position: right;
				width: 100%;
				height: 100%;
			}
			
			nav>ul {
				list-style: none;
				padding: 0px;
			}
			
			nav>ul>li {
				display: inline-block;
			}
			
			footer {
				position: fixed;
				bottom: 0px;
				width: 100%;
				background-color: gray;
			}
		</style>
		
		<script>
			$(function(){
				// DOM 트리에서 nav>ul>li>a 객체 찾기
				var aArr = $("nav>ul>li>a");
				
				$(aArr).click(function(){
					var vUrl = $(this).attr("href");
					if (vUrl == 'logout') {
						$.ajax({
							url: vUrl,
							method: 'get',
							success: function (result) {
										alert(result.trim());
										location.href="Semantic.jsp";
									 }
						});
					} else {
						$.ajax({
							url: vUrl,
							method: 'get',
							success: function (result) {
										$("section").html(result.trim());
									 }
						});
					}
					return false;
				});
			});
		</script>
	</head>
	<body>
		<!-- semantic tag : 의미상의 태그 (실제로 header 태그를 쓴다고 해서 맨 위에 위치하는 것은 아님 : 기능을 담당하는 tag가 아니기 때문) -->
		<header><h1>My Web</h1></header>
		<nav>메뉴
			<jsp:include page="Menu.jsp"/>
		</nav>
		<section>본문</section>
		<footer>사업자</footer>
	</body>
</html>