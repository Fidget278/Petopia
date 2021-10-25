<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	회원가입 </title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>
	$(document).ready(function() {
		
		//function
      	const getAjax = function(url, email) {
			
      		return new Promise((function(resolve, reject) => {
          		$.ajax({
      				url: url,
      				method: 'POST'
      				dataType: 'json',
      				data: {
      					email: email
      				},
      				async: true,
      				success: function(data) {						
						resolve(data);
					},
					error: function(e) {						
						reject(e);
					}
   			
      			});
          		
      		}); 
      		
		}
      	
      	async function sendProcess(url, email) {
      		var result = await getAjax(url, email);
      		console.log(result);
      		if(result) {
      			
      		} else {
      			
      			
      		}
		}
		
      	
      	$('#idcheckbtn').on("click", function() {
      		const email = $('#email').val();
      		console.log('id : ', id);
      		
      		sendProcess('${pageContext.request.contextPath}/idCheck.do', email);
      		
      	});
		
	});

</script>
</head>
<body>
<h1>회원가입</h1>
	<form action="${pageContext.request.contextPath}/join.do" method="post">
		아이디(이메일)	<input type="email" name="email" id="email"> <button type="button" id="idcheckbtn">중복 확인</button>
		비밀번호 <input type="password" name="password"> <br>
		닉네임 <input type="text" name="nickname"> 
		<br>
		<input type="submit" value="회원가입 하기">
	</form>


</body>
</html>