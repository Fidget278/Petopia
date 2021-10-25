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
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
	$(document).ready(function() {
		
	});
	

</script>
</head>
<body>
	<div class="content">
		<!-- Content 내용 여기에 추가 -->
		<table class="bbs" width="800" height="900" border="2" bgcolor="D8D8D8">
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
				 	<td>
				 		<c:url var="backUrl" value="/viewListArticleContent.do?boardNo=${param.boardNo }"/>
				 		<button id="backBtn" type="button" onclick="location.href='${backUrl}';">목록</button>
				 	</td>
				 	<td>좋아요</td>
				 	<td>
					 	<c:url var="modifyUrl" value="/viewModifyArticleForm.do">
					 		<c:param name="articleNo" value="${param.articleNo }"/>
					 		<c:param name="boardNo" value="${param.boardNo }"/>
					 	</c:url>
				 		<button id="modifyBtn" type="button" onclick="location.href='${modifyUrl}';">수정</button>
				 	</td>
				 	<td>
				 		<form>
					 		<c:url var="removeUrl" value="removeArticle.do">
					 			<c:param name="articleNo" value="${param.articleNo }"/>
					 			<c:param name="boardNo" value="${param.boardNo }"/>
					 		</c:url>
					 		<button id="reomveBtn" type="button" onclick="location.href='${removeUrl}';">삭제</button>
				 		</form>
				 	</td>
			 	</div>
			 </tr>
			 <%-- 댓글 --%>
			<tr height="200"> 
				<td colspan="4">
					<textarea class="form-control" id="replyContent" cols="100" rows="5"></textarea>
				</td>
				<td>
					<%--<button onclick="writeReply(${param.articleNo},${sessionScope.user.no },${sessionScope.user.nickname })" class="btn btn-primary pull-right">등록</button>--%>
					<button onclick="writeReply()" class="btn btn-primary pull-right">등록</button>
				</td>
			</tr>
			
			<tr>
				<div class="reply_List"></div>
			</tr>
			<script>
				// 댓글 작성					
				function writeReply(){
				
					var data ={
						articleNo: articleNo,
						memberNo: memberNo,
						nickname: nickname,
						content:$("#replyContent").val();
					}
					
					$.ajax({
						type:"post",
						url:"commandPactoryurl",
						data: data,
					});
					
				};
			</script>
		</tbody>
	</table>
	</div>
	<%-- 첨부파일 출력. --%>
	<div class="file">
		<c:if test="${empty requestScope.articles.fileList }">등록된 파일이 없습니다.
		</c:if>

		<c:if test="${not empty requestScope.articles.fileList }">
			<th>파일명</th><th>파일크기</th>
<<<<<<< HEAD
			<c:forEach var="file" items="${requestScope.articles.fileList }">
				<c:url var="downloadUrl" value="/fileDownload">
					<c:param name="originalFileName" value="${file.originalFileName}"/>
					<c:param name="systemFileName" value="${file.systemFileName}"/>
				</c:url>
				<td><a href="${downloadUrl}">${file.originalFileName }</a></td>
=======
	q		<c:forEach var="file" items="${requestScope.articles.fileList }">
				<td>${file.originalFileName }</td>
>>>>>>> branch 'feature/24' of https://github.com/Fidget278/petopia.git
				<td>${file.fileSize } bytes</td>
			</c:forEach>
		</c:if>				
<<<<<<< HEAD
=======
	</div>	
>>>>>>> branch 'feature/24' of https://github.com/Fidget278/petopia.git
</body>
</html>