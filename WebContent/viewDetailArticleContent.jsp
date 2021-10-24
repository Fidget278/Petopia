<%-- 게시글 세부 조회 --%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*, model.article.ArticleVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Content</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
<link href="./css/viewMainContent.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="content">
		<!-- Content 내용 여기에 추가 -->
		<table class="bbs" width="800" height="900" border="2" bgcolor="D8D8D8">
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
				 		<button id="btn" type="button" onclick="location.href='viewWriteArticleForm.do';">글쓰기</button>
				 	</td>
				 	<td>목록</td>
				 	<td>좋아요</td>
				 	</tab>
				 	<td>
					 	<c:url var="modifyUrl" value="/viewModifyArticleForm.do">
					 		<c:param name="articleNo" value="${param.articleNo }"/>
					 	</c:url>
				 		<button id="modifyBtn" type="button" onclick="location.href='${modifyUrl}';">수정</button>
				 	</td>
				 	<td>
				 		<form>
					 		<c:url var="removeUrl" value="removeArticle.do">
					 			<c:param name="articleNo" value="${param.articleNo }"/>
					 		</c:url>
					 		<button id="reomveBtn" type="button" onclick="location.href='${removeUrl}';">삭제</button>
				 		</form>
				 	</td>
			 	</div>
			 </tr>
			 <%-- 댓글 --%>
			<tr height="200"> 
				<form>
					<td colspan="4">
						<c:url var="writeReplyUrl" value="writeReply.do">
							<%--댓글이 작성된 게시글 번호 --%>
							<c:param name="articleNo" value="${param.articleNo }"/>
						</c:url>
						<p><textarea cols="200" rows="10"></textarea></p>
					</td>
					<td>
						<button id="writeFormBtn" type="button" onclick="location.href='${writeReplyUrl}';">등록</button>
					</td>
				</form>
			</tr>
			<tr height="200"> 
				<td colspan="6">댓글 목록창</td>>
			</tr>
		</tbody>
	</table>
	<%-- 첨부파일 출력. --%>
	<div class="file">
		<c:if test="${empty requestScope.articles.fileList }">등록된 파일이 없습니다.
		</c:if>

		<c:if test="${not empty requestScope.articles.fileList }">
			<th>파일명</th><th>파일크기</th>
			<c:forEach var="file" items="${requestScope.articles.fileList }">
				<td>${file.originalFileName }</td>
				<td>${file.fileSize } bytes</td>
			</c:forEach>
		</c:if>				
			
	</div>
		
	</div>
</body>
</html>