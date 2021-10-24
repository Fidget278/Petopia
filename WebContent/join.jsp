<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='ko'>

<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
</head>
<body>

	<!-- 아이디 -->
	<div class="form-group">
		<label for="user_id">아이디</label> <input type="text"
			class="form-control" id="user_id" name="user_id" placeholder="ID"
			required>
		<div class="check_font" id="id_check"></div>
	</div>
	<!-- 비밀번호 -->
			<div class="form-group">
				<label for="user_pw">비밀번호</label>
					<input type="password" class="form-control" id="user_pw" name="user_pw" placeholder="PASSWORD" required>
				<div class="check_font" id="pw_check"></div>
			</div>
			<!-- 비밀번호 재확인 -->
			<div class="form-group">
				<label for="user_pw2">비밀번호 확인</label>
					<input type="password" class="form-control" id="user_pw2" name="user_pw2" placeholder="Confirm Password" required>
				<div class="check_font" id="pw2_check"></div>
			</div>
			<!-- 이름 -->
			<div class="form-group">
				<label for="user_name">이름</label>
					<input type="text" class="form-control" id="user_name" name="user_name" placeholder="Name" value="${param.nickname}" required>
				<div class="check_font" id="name_check"></div>
			</div>
			<!-- 생년월일 -->
			<div class="form-group required">
				<label for="user_birth">생년월일</label>
					<input type="text" class="form-control" id="user_birth" name="user_birth" placeholder="ex) 19990415" required>
				<div class="check_font" id="birth_check"></div>
			</div>
			
			<script>
			//모든 공백 체크 정규식
	var empJ = /\s/g;
	//아이디 정규식
	var idJ = /^[a-z0-9]{4,12}$/;
	// 비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,12}$/; 
	// 이름 정규식
	var nameJ = /^[가-힣]{2,6}$/;
	// 이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	// 휴대폰 번호 정규식
	var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
	
	
	// 이름에 특수문자 들어가지 않도록 설정
	$("#user_name").blur(function() {
		if (nameJ.test($(this).val())) {
				console.log(nameJ.test($(this).val()));
				$("#name_check").text('');
		} else {
			$('#name_check').text('이름을 확인해주세요');
			$('#name_check').css('color', 'red');
		}
	});
	
	
	// 휴대전화
	$('#user_phone').blur(function(){
		if(phoneJ.test($(this).val())){
			console.log(nameJ.test($(this).val()));
			$("#phone_check").text('');
		} else {
			$('#phone_check').text('휴대폰번호를 확인해주세요 :)');
			$('#phone_check').css('color', 'red');
		}
	});

	
	// 생일 유효성 검사
	var birthJ = false;
	
	// 생년월일	birthJ 유효성 검사
	$('#user_birth').blur(function(){
		var dateStr = $(this).val();		
	    var year = Number(dateStr.substr(0,4)); // 입력한 값의 0~4자리까지 (연)
	    var month = Number(dateStr.substr(4,2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
	    var day = Number(dateStr.substr(6,2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
	    var today = new Date(); // 날짜 변수 선언
	    var yearNow = today.getFullYear(); // 올해 연도 가져옴
		
	    if (dateStr.length <=8) {
			// 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
		    if (1900 > year || year > yearNow){
		    	
		    	$('#birth_check').text('생년월일을 확인해주세요 :)');
				$('#birth_check').css('color', 'red');
		    	
		    }else if (month < 1 || month > 12) {
		    		
		    	$('#birth_check').text('생년월일을 확인해주세요 :)');
				$('#birth_check').css('color', 'red'); 
		    
		    }else if (day < 1 || day > 31) {
		    	
		    	$('#birth_check').text('생년월일을 확인해주세요 :)');
				$('#birth_check').css('color', 'red'); 
		    	
		    }else if ((month==4 || month==6 || month==9 || month==11) && day==31) {
		    	 
		    	$('#birth_check').text('생년월일을 확인해주세요 :)');
				$('#birth_check').css('color', 'red'); 
		    	 
		    }else if (month == 2) {
		    	 
		       	var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		       	
		     	if (day>29 || (day==29 && !isleap)) {
		     		
		     		$('#birth_check').text('생년월일을 확인해주세요 :)');
					$('#birth_check').css('color', 'red'); 
		    	
				}else{
					$('#birth_check').text('');
					birthJ = true;
				}//end of if (day>29 || (day==29 && !isleap))
		     	
		    }else{
		    	
		    	$('#birth_check').text(''); 
				birthJ = true;
			}//end of if
			
			}else{
				//1.입력된 생년월일이 8자 초과할때 :  auth:false
				$('#birth_check').text('생년월일을 확인해주세요 :)');
				$('#birth_check').css('color', 'red');  
			}
		}); //End of method /*
	
	
	
		/ 가입하기 실행 버튼 유효성 검사!
		var inval_Arr = new Array(5).fill(false);
		$('#reg_submit').click(function(){
			// 비밀번호가 같은 경우 && 비밀번호 정규식
			if (($('#user_pw').val() == ($('#user_pw2').val()))
					&& pwJ.test($('#user_pw').val())) {
				inval_Arr[0] = true;
			} else {
				inval_Arr[0] = false;
			}
			// 이름 정규식
			if (nameJ.test($('#user_name').val())) {
				inval_Arr[1] = true;	
			} else {
				inval_Arr[1] = false;
			}
			// 이메일 정규식
			if (mailJ.test($('#user_email').val())){
				console.log(phoneJ.test($('#user_email').val()));
				inval_Arr[2] = true;
			} else {
				inval_Arr[2] = false;
			}
			// 휴대폰번호 정규식
			if (phoneJ.test($('#user_phone').val())) {
				console.log(phoneJ.test($('#user_phone').val()));
				inval_Arr[3] = true;
			} else {
				inval_Arr[3] = false;
			}
			// 생년월일 정규식
			if (birthJ) {
				console.log(birthJ);
				inval_Arr[4] = true;
			} else {
				inval_Arr[4] = false;
			}
			
			var validAll = true;
			for(var i = 0; i < inval_Arr.length; i++){
				
				if(inval_Arr[i] == false){
					validAll = false;
				}
			}
			
			if(validAll){ // 유효성 모두 통과
				alert('갓민수님께서 인증 이메일을 보냈으니 확인해주세요!');
				
			} else{
				alert('입력한 정보들을 다시 한번 확인해주세요 :)')
				
			}
		});
	
	
		// 아이디 유효성 검사(1 = 중복 / 0 != 중복)
		$("#user_id")
				.blur(
						function() {
							// id = "id_reg" / name = "userId"
							var user_id = $('#user_id').val();
							$
									.ajax({
										url : '${pageContext.request.contextPath}/user/idCheck?userId='
												+ user_id,
										type : 'get',
										success : function(data) {
											console.log("1 = 중복o / 0 = 중복x : "
													+ data);

											if (data == 1) {
												// 1 : 아이디가 중복되는 문구
												$("#id_check").text(
														"사용중인 아이디입니다 :p");
												$("#id_check").css("color",
														"red");
												$("#reg_submit").attr(
														"disabled", true);
											} else {

												if (idJ.test(user_id)) {
													// 0 : 아이디 길이 / 문자열 검사
													$("#id_check").text("");
													$("#reg_submit").attr(
															"disabled", false);

												} else if (user_id == "") {

													$('#id_check').text(
															'아이디를 입력해주세요 :)');
													$('#id_check').css('color',
															'red');
													$("#reg_submit").attr(
															"disabled", true);

												} else {

													$('#id_check')
															.text(
																	"아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
													$('#id_check').css('color',
															'red');
													$("#reg_submit").attr(
															"disabled", true);
												}

											}
										},
										error : function() {
											console.log("실패");
										}
									});
						});
	
	
	

		
	</script>
	</body>
	</html>
	