<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	회원 탈퇴 </title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>
	
	$(document).ready(function() {
		var duplicate = false;
		//function
      	const getAjax = function(url, password) {
			
      		return new Promise( (resolve, reject) => {
          		$.ajax({
      				url: url,
      				method: 'POST',
      				dataType: 'json',
      				data: {
      					password: password
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
      		
		};
		
      	//[시작] 아이디 중복검사 (db확인)
      	async function sendProcess(url, password) {
      		try {
      			var result = await getAjax(url, password);
      			
      			if (result.isEmail == 'true') {  //이메일이 존재하는 경우
      				$('#errmsgid').html("존재하는 아이디입니다.");
      				$('#errmsgid').css("color", "red");
      				duplicate = true;
      			} else if (result.isEmail == 'false') {  //이메일이 존재하지 않는 경우
      				$('#errmsgid').html("사용 가능한 아이디입니다.");
      				$('#errmsgid').css("color", "green");
      				duplicate = false;
      			}
      		}
      		catch(e) {
      			console.log(e);
      		}      		
		} //[시작] 아이디 중복검사 (db확인)
		      	
		//아이디 중복확인 버튼 눌렀을 시 실행되는 코딩 [시작]
      	$('#idcheckbtn').on("click", function(event) {
      		const email = $('#email').val();
      		console.log('id : ', email);
      
      		if(CheckEmail(email)){
      			sendProcess('${pageContext.request.contextPath}/idCheck.do', email);
      		} else {
      			alert("올바른 이메일 주소를 입력해주세요.");
      		}
      	}); //아이디 중복확인 버튼 눌렀을 시 실행되는 코딩 [끝]
      	 
      	//이메일 형식으로 유효성 검사 [시작]
      	function CheckEmail(str){                                                 
      		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
			if(!reg_email.test(str)) {                            
			
				return false;         
			
			} else {                       
				return true;         
			}                            
		} //이메일 형식으로 유효성 검사 [끝]

       //[시작] input 박스 커서 선택 시 "~입력해주세요." 에러메세지 없애기
      	//이메일 인풋박스 선택시 "이메일을 입력해주세요." 경고 문구 "    "로 공백 만들기
      	$( "#email" ).focus(function() {
      		$('#errmsgid').html("	");
      	});
        //비밀번호 인풋박스 선택시 "비밀번호를 입력해주세요." 경고 문구 "    "로 공백 만들기
      	$( "#password" ).focus(function() {
      		$('#errmsgpw').html("	");
      	});
        //닉네임 인풋박스 선택시 "닉네임을 입력해주세요." 경고 문구 "    "로 공백 만들기 
      	$( "#nickname" ).focus(function() {
      		$('#errmsgnn').html("	");
      	}); 
        //[끝] input 박스 커서 선택 시 "~입력해주세요." 에러메세지 없애기
      	
      	
        //[시작] 회원가입 버튼 눌렀을 시 발생하는 코드 
      	$('#joinForm').submit(function(event) {
      	  
      		 //인풋박스 값에 대한 변수 정의
             const email = $('#email').val(); //이메일 인풋박스에 value(값)을 가져온걸 email이라 부르겠다.
             const password = $('#password').val(); //패스워드 인풋박스에 value(값)을 가져온걸 password이라 부르겠다.
             const nickname = $('#nickname').val(); //닉네임 인풋박스에 value(값)을 가져온걸 nickname이라 부르겠다.
            
             //인풋박스가 빈칸일때 경고 문구 노출
             if (email == "") { //email 인풋박스가 공백일 때
            	 $('#errmsgid').html("아이디를 입력해주세요."); //errmsgid 공간(div)에 "아이디를 입력해주세요." 문구 노출
   				 $('#errmsgid').css("color", "red");  //errmsgid는 빨간색 글씨로 css 처리하겠다.
   				 return false; //if에 맞는 조건에 걸리면 밑에거 건너 뛰어라 (지금 당장 내가 에러나서 뭘 발생해야겠으니 밑에까지 갈 필요도 없다.)
             }
                        
             
             if (password == "") { //password 인풋박스가 공백일 때
            	 $('#errmsgpw').html("비밀번호를 입력해주세요.");//errmsgpw 공간(div)에 "비밀번호를 입력해주세요." 문구 노출
   				 $('#errmsgpw').css("color", "red"); //errmsgpw는 빨간색 글씨로 css 처리하겠다.
   				 return false; 
             } 
             
             if (nickname == "") { //nickname 인풋박스가 공백일 때
            	 $('#errmsgnn').html("닉네임을 입력해주세요.");//errmsgnn 공간(div)에 "비밀번호를 입력해주세요." 문구 노출
   				 $('#errmsgnn').css("color", "red");//errmsgnn는 빨간색 글씨로 css 처리하겠다.
   				 return false;
             }
             
             if(duplicate == true){ //중복된 아이디를 입력 시에 
        			$('#errmsgid').html("아이디 중복확인을 해주세요."); //errmsgid 공간(div)에 "아이디 중복확인을 해주세요."
     				$('#errmsgid').css("color", "red");//errmsgid는 빨간색 글씨로 css 처리하겠다.
        			return false;
        			 
              }	 //[끝] 회원가입 버튼 눌렀을 시 발생하는 코드 
              
            alert("회원가입이 완료되었습니다.")
            return true; //모든게 끝나고 return
      		
         	});
         	
      	});
</script>
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