<%-- memberList.jsp --%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.grade.GradeVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급 목록 조회</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
<link href="css/viewMainContent.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
	
	<script>
	const getAjax = function(url, num) {
		return new Promise((resolve, reject) => {
			
			$.ajax({
				url : url,
				method : "POST",
				dataType : "json",
				data : {
					gradeNo : num
				},
				async : true,
				success : function(data) {
					resolve(data);
				},
				error : function(e) {
					reject(e);
				}
			});
		});
	}
	
	async function sendProcess(url, num) {
		try{
			const result = await getAjax(url, num);
			
			if(result == null) {
				alert("해당 데이터가 없습니다.");
			}
			else {
				
			}
		} catch (e) {
			console.log(e);
		}	
	}
		function addRow() {
			var Row = Grade.insertRow();
			Row.onmouseover = function() {
				Grade.clickedRowIndex = this.rowIndex;
			}
			
			var Cell1 = Row.insertCell();
			Cell1.innerHTML = "<input type='text' name='gradeNo'>"
			
			var Cell2 = Row.insertCell();
			Cell2.innerHTML = "<input type='text' name='name'>"
			
			var Cell3 = Row.insertCell();
			Cell3.innerHTML = "<button type='button' id='checkBtn'>확인</button>"
			
			var Cell4 = Row.insertCell();
			Cell4.innerHTML = "<input type='text' name='docs'>"
			
			var Cell5 = Row.insertCell();
			Cell5.innerHTML = "<input type='text' name='comms'>"
				
			var Cell6 = Row.insertCell();
				
			var Cell7 = Row.insertCell();
			Cell7.innerHTML = "<button type='button' onclick='delRow()'>삭제</button>"
		}
		
		function delRow(obj) {
			var com = confirm("정말로 삭제하시겠습니까?");
			if(com) {
			var tr = obj.parentNode.parentNode;
			tr.parentNode.removeChild(tr);
			return obj.val();
			}
		}
		
		function check(obj) {
			var tr = obj.parentNode.parentNode;
			var num = $('tr [name="gradeNo"]').val()
			const url = "viewGradeBoardList.do";
			sendProcess(url, num);
		}
		
	</script>
</head>


<body>
	<div class="content">
	<form action="modifyGradeList.do" method="GET">
		<button type="button" value="삭제" onclick="addRow()">등급 추가</button>
		<table id="Grade">
			<thead>
				<tr>
					<th>번호</th>
					<th>등급 이름</th>
					<th>열람 가능</th>
					<th>게시글 갯수</th>
					<th>댓글 갯수</th>
					<th>등급 회원 수</th>
					<th>비고</th>
				</tr>
			</thead>
			
			<tbody id="body">
				<c:forEach var="grade" items="${grades}" varStatus="loop">	
					<tr>
						<td><input type="text" placeholder="${grade.gradeNo}" name="gradeNo" value="${grade.gradeNo}"></td>
						<td><input type="text" placeholder="${grade.name}" name="name" value="${grade.name}"></td>
						<td><button type="button" onclick='check(this)'>확인</button></td>
						<td><input type="text" placeholder="${grade.docs}" name="docs" value="${grade.docs}"></td>
						<td><input type="text" placeholder="${grade.comms}" name="comms" value="${grade.comms}"></td>
						<td>${grade.person}명</td>
						<td><button type="button" value="${grade.gradeNo}" onclick='delRow(this)'>삭제</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="submit">저장</button>
	</form>
	</div>
</body>
</html>