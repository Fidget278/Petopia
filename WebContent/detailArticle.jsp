<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.article.ArticleVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
	.btn{ 
		font-size: 2rem;
		color:blue;
		padding:10px 20px 10px 20px;
		margin: 20px;
		display:inline-block;
		border-radius: 10px;
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
				<div id="btn">
				 	<td>
				 		<button id="btn" type="button" onclick="location.href='writeArticleForm.do';">글쓰기</button>
				 	</td>
				 	<td>목록</td>
				 	<td>좋아요</td>
				 	</tab>
				 	<td>수정</td>
				 	<td>삭제</td>
			 	</div>
			 </tr>
			<tr height="200"> 
				<td colspan="4">
					<form>
						<p><textarea cols="200" rows="10"></textarea></p>
					</form>
				</td>
				<td><button id="writeFormBtn" type="button" onclick="location.href='링크주소';">등록</button></td>
				
			</tr>
			<tr height="200"> 
				<td colspan="6">댓글 목록창</td>>
			</tr>
		</tbody>
	</table>
</body>
</html>

