<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETOPIA - 로그인</title>
 <script src="https://code.jquery.com/jquery-3.5.0.js"></script>       
</head>
<body>
	
	<div>
		<label for="email">이메일 : </label>
 		<input type="text" name="email" id="email">
		<label for="password">비밀번호 : </label>
 		<input type="text" name="password" id="password">
		<h1></h1>
 		<button type="button" id="loginBtn">로그인</button>
 	</div>
	<!--  비동기 메시시 처리 방식 -->
	<script>      

        const getAjax = function(url, email, password) {
            // resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
            return new Promise( (resolve, reject) => {
                $.ajax({                        
                    url: url,
                    method: 'POST',
                    dataType: 'json',
                    data: {
                     	email: email,
                    	password: password
                    },
                    success: function(data) {  // 비동기 작업 성공 시 호출                  	
                        resolve(data);
                    }, 
                    error: function(e) {  // 비동기 작업 실패 시 호출                  	
                        reject(e);
                    }
                });
            });
        }   
        
		//async : 해당 함수가 비동기 작업을 처리한다는 걸 명시
        async function requestProcess(url, email, password) {
            try {				// await 다음에는 비동기 처리 작업이 와야함.
                const result = await getAjax(url, email, password);
                
                console.log(result);
                
                if( result.isSuccess == 0){
                    $('h1').text(result.failText);	
                } else if (result.isSuccess == 1) {
                	location.href = result.url;
                }
                
                  
            } catch (error) {
                console.log("error : ", error);   
            }
        }
        
        
        
	    $('#loginBtn').on('click', function() {				
			const email = $('#email').val();
	    	const password = $('#password').val();
	    	requestProcess('/petopiaWebApp/login.do', email, password);
	   
	    });
	</script>
</body>
</html>