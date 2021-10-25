<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJax</title>
 <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>
	<h1>AJAX댓글</h1>
	<hr>
	<%-- 댓글 입력 영역 --%>
	<div id="reply_add">
		<table class="reply_table">
			<tr>
				<td class="title">댓글내용</td>
				<td class="input"><textarea row="3" cols="50" id="content"><</textarea>
			</tr>
			<tr>
				<td colspan="2" class="btn"><button type="button" id="add_btn">댓글 등록</button>
			</tr>
		</table>
		<div id="add_message">&nbsp;</div>
	</div>
	
	<%-- 댓글 목록 출력 영역 --%>
	<div id="reply_list"></div>
	
	<script type="text/javascript">
		<%-- 문서 시작하자마자 가장먼저 실행 댓글 목록 불러오기 --%>
		loadReply();
		
		// 댓글 목록을 제공하는 jsp문서를 요청하여 응답받아 출력하는 함수
		function loadReply(){
			$.ajax({
				type: "GET",
				url: "reply_list.jsp",
				dataType: "xml",
				success: function(xmlDoc){
					// 코드확인
					var code=$(xmlDoc).find("code").text();
					if (code=="success"){
						//data를 배열로 저장 >> 꼭 JSON객체로 변환처리해야됨 (안할시 obj로 인식)
						var replyArray=JSON.parse($(xmlDoc).find("data").text());
						
						// 댓글 출력목록의 초기화
						$("#reply_list").children().remove();
						
						// 반복지시자 이용하여 출력 >> obj로 인식하니가 $넣어서 접근
						$(replyArray).each(function(){
							// 수정을 위해 각각의 div에 고유값인 id를 부여 >> 이떄 num활용
							// 삭제를 위해 num부여 >> 이떄도 num 활용
							<%--$("#reply_list").append("<div id="reply_" +this.num+"'class='reply' num='"+t) --%>
						});
					} else {
						var message=$(xmlDoc).find("message").text();
						$("#reply_list").html("<div class='no_reply'>" + message + "</div>");
					}
				},
				error: function(xhr){
					alert("ERROR CODE :" xhr.status);
				}
			});
			
			// click 이벤트 등록
			$("#add_btn").click(function(){
				// 입력값 유효성 검사
				var content=$("#content").val();
				if(content=""){
					$("#add_message").html("내용을 입력해주세요");
					$("#content").focus();
					return;
				}
				
				// 입력태그 초기화
				$("#content").val("");
			
			})
			
			// ajax로 기능 요청 및 응답처리
			$.ajax({
				type: "POST",
				url: "reply_add.jsp",
				data: "&content=" + content,
				dataType: "xml",
				success: function(xmlDoc){
					var code=$(xmlDoc).find("code").text();
					if(code="success"){
						loadReply();
					}
				},
				error: function(xhr){
					alert("ERROR CODE : " + xhr.status);
				}
				
			});
			
		}
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>