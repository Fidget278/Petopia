<%-- viewDetailMember.jsp --%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.member.MemberVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 상세 조회</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
<link href="css/viewMainContent.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="content">
	<h3>회원 정보 수정</h3>
	<c:url var="listUrl" value="/viewMemberList.do"/>
	<a href="${listUrl}">Back</a>
	
	<!-- 표 -->
	<form action="modifyBan.do">
	<table border = "1">
		<tr>
			<td>ID</td>
			<td>${member.email}</td>
			<td>등급</td>
			<td>${member.grade}</td>
		</tr>
		
		<tr>
			<td>닉네임</td>
			<td>${member.nickname}</td>
			<td>게시글 수</td>
			<td>${member.docs}</td>
		</tr>
		
		<tr>
			<td>가입 날짜</td>
			<td>${member.regDate}</td>
			<td>댓글 수</td>
			<td>${member.comms}</td>
		</tr>
		
		<tr>
			<td>최근 방문 날짜</td>
			<td>${member.lastDate}</td>
			<td>방문 횟수</td>
			<td>${member.visits}</td>
		</tr>
		
		<tr>
			<td>탈퇴 여부</td>
			<td>${member.isMember}</td>
			<td>활동 중지</td>
			<td>
				${member.ban}
				<select id="banSelect">
					<option value="7d">7일</option>
					<option value="1d">1일</option>
					<option value="1m">1분</option>
				</select>
			</td>
		</tr>
	</table>
	
	<button type="submit" id="saveBtn">저장</button>
	<button type="button" id="outBtn">회원 탈퇴</button>
	</form>
</div>
</body>
</html>