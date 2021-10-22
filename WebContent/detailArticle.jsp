<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
	#btn{
		border-top-left-radius: 10px;
		border-top-right-radius: 10px;
		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;
		margin-right: -4px;
	}
	#btn_group button{
		border: 3px solid skyblue;
		background-color: rgba(0,0,0,0);
		color: skyblue;
		padding: 5px;
	}
</style>

</head>
<body>
	<table class="bbs" width="1300" border="2" bgcolor="D8D8D8" padding-left:"250":padding-bottom:"0">
		<colgroup>
			<col width="80" /> <%--No --%>
			<col width="100" /> <%--No --%>
			<col width="100" /> <%--No --%>
			<col width="100" /> <%--No --%>
			<col width="100" /> <%--No --%>
			<col width="100" /> <%--No --%>
		</colgroup>
		<thead>
			<tr rowspab="3">카테고리 선택</tr>
		</thead>
		<tbody>
			<tr>
				<p>
					<td>제목:</td>
					<td colspan="5"> ${requestScope.articles.subject } </td>
				</p>
			</tr>
			<tr>
				<p>
					<td>작성자:</td>
					<td colspan="5"> ${requestScope.articles.nickname } </td>
				</p>
			</tr>
			<tr>
				<p>
					<td>작성일:</td>
					<td colspan="5"> ${requestScope.articles.writedate } </td>
				</p>
			</tr>

			<tr>
				<td height="400">내용:</td>
				<td colspan="5"> ${requestScope.articles.content }</td>
			</tr>
			<tr height="100">
				<div id="btn_group">
				 	<td><button id="btn" type="button" onclick="location.href='링크주소';">글쓰기</button></td>
				 	<td><button id="btn" type="button" onclick="location.href='링크주소';">목록</button></td>
				 	<td><button id="btn" type="button" onclick="location.href='링크주소';">좋아요</button></td>
				 	</tab>
				 	<td><button id="btn" type="button" onclick="location.href='링크주소';">수정</button></td>
				 	<td><button id="btn" type="button" onclick="location.href='링크주소';">삭제</button></td>
			 	</div>
			 </tr>
			<tr height="200"> 
				<td colspan="4">
					<form>
						<p><textarea cols="200" rows="10"></textarea></p>
					</form>
				</td>
				<td><button id="btn" type="button" onclick="location.href='링크주소';">등록</button></td>
				
			</tr>
			<tr height="200"> 
				<td colspan="6">댓글 목록창</td>>
			</tr>
		</tbody>
	</table>
</body>
</html>

