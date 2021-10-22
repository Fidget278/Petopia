<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
	select{
		width: 400px;
		padding: 10px;
		
		font-size:20pt
	}
	img {
		width: 15px;
		height: 25px;
		object-fit:cover;
	}
	
</style>
</head>
<body>
	<table class="bbs" width="1300" border="2" bgcolor="D8D8D8" padding-left:"250":padding-bottom:"0">
		<thead>
			<tr><h1>게시글 작성</h1></tr>
		</thead>
		<tbody>
				<%-- 게시판 선택 --%>
			<form action="writeArticle.do" accept-charset="utf-8" name="boardSelect">
					<tr>
						<td>
						<div class="selectBoard">
							<select id="boardSelect" name="boardSelect">
								<option value="0">게시판선택</option>
								<option value="1">영장류</option>
								<option value="2">파충류</option>
								<option value="3">류</option>
							</select>
						</div>
					</td>
					<td colspan="2">
						<input type="submit" value="등록">
					</td>
				</tr>
				<br>
				<tr colspan="3">
					<td>
						<textarea name="subject" cols="200" rows="10">제목을 입력하세요</textarea>
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
						<button type="button" class="btn_image" id="textsBtn"><img src="./clip.png"></button>
					</td>
				</tr>
				<tr  colspan="3">
					<td>
						<form>
							<textarea name="content" cols="300" rows="10">내용을 입력해주세요</textarea>
						</form>
					</td>
				</tr>
				
			</form>
		</tbody>
	</table>
</body>
</html>