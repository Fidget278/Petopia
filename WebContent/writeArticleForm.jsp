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
			<tr>
				<td>
				<%-- 게시판 선택 --%>
					<div class="selectBoard">
						<select name="board">
							<option value="">게시판선택</option>
							<option value="영장류">영장류</option>
							<option value="파충류">파충류</option>
							<option value="류">류</option>
						</select>
					</div>
				</td>
				<td colspan="2">
					<button type="button" id= "writeBtn">등록</button>
				</td>
			</tr>
			<br>
			<tr colspan="3">
				<td>
					<form>
						<p><textarea cols="200" rows="5">제목 입력</textarea></p>
					</form>
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
						<p><textarea cols="200" rows="20">내용 입력</textarea></p>
					</form>
				</td>
			</tr>
			
		</tbody>
	</table>
</body>
</html>