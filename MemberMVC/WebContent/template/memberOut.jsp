<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript">
			function deleteMember() {
				if (confirm("탈퇴하시겠습니까?")) {
					document.location.href = "<%=root%>/user?act=deletemember";
				}
			}
</script>