<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<style>
table {
	border-collapse: collapse;
	border-spacing: none;
}

table, tr, th, td {
	border: 1px solid black;
}

a { 
	color : black;
	text-decoration: none;
}

a:hover {
	color : blue;
}
</style>
<body>
<table>
	<thead>
	<tr>
	<th>번호</th><th>보낸사람</th><th>내용</th><th>보낸날짜</th><th>읽은날짜</th><th>선택</th>
	</tr>
	</thead>
	<tbody>
	 <c:forEach var = "note" items = "${requestScope.NoteList }" varStatus = "loop">
			<c:url var="url" value="/noteDetailBoard.do">
				<c:param name="isRecieve" value = "1" /> 
				<c:param name="no" value = "${pageScope.note.note_no}" /> 
			</c:url>
			<tr>
				<%-- <td>${requestScope.totalPostCount - (param.currentPage -1) * requestScope.postSize - loop.index }</td>--%>
				<td>${fn:length(requestScope.NoteList) - pageScope.loop.count + 1 }</td> <!-- 번호 --> 
				<td>${note.counterpart_nickname }</td>
				<td><a href = "${pageScope.url}">${note.content}</a></td>
				<td>${note.sendDate }</td>
				<c:if test= "${ note.read }">
				<td>${note.readDate }</td>
				</c:if>				
				<c:if test= "${ note.read == false }">
				<td>읽지않음</td>
				</c:if>				
				
				<td><input type="checkbox" name="deleteNote" value="${ note.note_no }"></td> 
			</tr> 
		</c:forEach> 
	</tbody>
	</table>
	<button type = "button" id = "delete">삭제</button>
	<script>
	$('#delete').on('click', function() {	
		let chk_arr = [];

		$("input[name=deleteNote]:checked").each(function() {
			var chk = $(this).val();
			chk_arr.push(chk);
			console.log(chk);
		}); 
	});
	</script>
</body>
</html>