<%-- 참고 블로그https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=myeongdms55&logNo=220946120801 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='ko'>

<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
</head>
<body>
	<%--emailCheck.jsp : 이메일 중복 체크 --%>
	<form id="server" action="emailCheck.jsp" method="post"
		class="form-validate form-horizontal well"
		enctype="multipart/form-data">
		<style>
@media ( min-width :767px) {
	.registration {
		max-width: 400px;
	}
}
</style>

		<div class="container">
			<div class="registration mx-auto d-block w-100">
				<div class="page-header text-center">
					<h1>회원가입</h1>
				</div>


				<fieldset>

					<div class="form-group">

						<label for="id">아이디(이메일 주소)</label> <input type="email"
							class="form-control " id="id"> <input type="button"
							id="id_request" value="중복 체크" onclick="idCheck()"
							class="btn btn-info">


					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">비밀번호</label> <input
							type="password" class="form-control" id="exampleInputPassword1">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">비밀번호 확인</label> <input
							type="password" class="form-control" id="exampleInputPassword1">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">닉네임</label> <input type="text"
							class="form-control" id="exampleInputPassword1">
					</div>

					<div class="d-flex justify-content-between align-items-center">
						<div class="form-group d-flex justify-content-start">
							<button type="submit" class="btn btn-primary">회원가입 하기</button>
						</div>

					</div>
				</fieldset>
	</form>
	<div id="idcheck"></div>

	<script type="text/javascript">
		window.onload = function() {
			//1. 아이디 중복 체크
			document.getElementById("id_request").onclick = function() {
				var gsWin = window.open("about:blank", "winName",
						"width=400,height=300,top=100,left=200");
				var fr = document.getElementById("server");
				fr.action = "idcheck.jsp";
				fr.target = "winName";
				fr.submit();
			}
		}
	</script>
	</div>
	</div>

</body>
</html>