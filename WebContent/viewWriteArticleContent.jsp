<%-- 게시글 작성 --%>

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
		<table class="bbs" width="800" border="2" bgcolor="D8D8D8" padding-left:"250":padding-bottom:"0">
		<thead>
			<tr><h1>게시글 작성</h1></tr>
		</thead>
		<tbody>
				
			<%--<form action="writeArticle.do" accept-charset="utf-8" name="boardSelect"> --%>
			<form action="${pageContext.request.contextPath }/uploadFile" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="boardNo" value="${parma.boardNo }">
					<tr>
						<td>
						<%-- 게시판 선택 --%>
						<div class="selectBoard">
							<select id="boardSelect" name="boardSelect">
								<option value="0">게시판선택</option>
								<option value="1">영장류</option>
								<option value="2">파충류</option>
								<option value="3">류</option>
							</select>
						</div>
					</td>
					<%--<td colspan="2">
						<input type="submit" value="등록">
					</td> --%>
				</tr>
				<br>
				<tr colspan="3">
					<td>
						<textarea class="form-control" id="subject" name="subject" cols="100" rows="5">제목을 입력하세요</textarea>
					</td>
				</tr>
				<tr height="60">
					<td>
						<button type="button" class="btn_image" id="movieBtn"><img src="./video.png"></button>
					</td>
					<td>
						<button type="button" class="btn_image" id="imgBtn"><img src="./camera.jpg"></button>
					</td>
					<td>
						<input type="file" class="form-control" name="fileList" id="file" multiple style="font-size: 13px;">
					</td>
				</tr>
				<tr  colspan="3">
					<td>
						<div class="wa-3">
							<label for="content"></label>
							<textarea class="form-control" id="content" name="content" cols="100" rows="10" placeholder="내용을 입력해 주세요"></textarea>
						</div>
					</td>
				</tr>
				<button type="submit" class="btn btn-sm btn-primary" id="wrtieBtn">등록</button>
				
			</form>
		</tbody>
	</table>
	</div>
</body>
</html>