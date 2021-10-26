<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	비밀번호 재설정 </title>
</head>
<body>
<h1>비밀번호 재설정</h1>
	<form action="${pageContext.request.contextPath}/resetpassword.do" method="post">
		새로운 비밀번호	<input type="password" name="newpassword"> <br>
		비밀번호 확인 <input type="password" name="checkNewpassword"> <br>

		<input type="submit" value="비밀번호 변경">
	</form>

</body>
</html>