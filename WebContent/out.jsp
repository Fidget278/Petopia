<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	회원 탈퇴 </title>
</head>
<body>
<h1>회원 탈퇴</h1>
	<form action="${pageContext.request.contextPath}/out.do" method="post">
		<input type="hidden" name = "memberNo" value = "${sessionScope.user.no }" />
		비밀번호 확인 <input type="password" name="password"> <br>
		
		<br>
		<input type="submit" value="회원 탈퇴">
	</form>


</body>
</html>