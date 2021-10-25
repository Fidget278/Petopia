
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Content</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
<link href="css/viewMainContent.css" rel="stylesheet" type="text/css">
<link href="css/viewStatisticsTotalContent.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div class="content-inner">
		<div class="content-header">
			<table>
				<tr>
					<td
						onclick="location.href='${pageContext.request.contextPath}/managerStatisticsDaily.do'">일일
						통계</td>
					<td
						onclick="location.href='${pageContext.request.contextPath}/managerStatisticsTotal.do'">총
						통계</td>
				</tr>
			</table>
		</div>
		<div class="content-text">
			<table>
				<thead>
					<tr>
						<th>총 회원 수</th>
						<th>총 게시글 수</th>
						<th>총 조회 수</th>
						<th>총 방문자 수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${requestScope.totalList.getTotalMemberCount() }</td>
						<td>${requestScope.totalList.getTotalBoardCount() }</td>
						<td>${requestScope.totalList.getTotalViewCount() }</td>
						<td>${requestScope.totalList.getTotalVisitorsCount() }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		function daily(url) {

			// ajax option

			var ajaxOption = {

				url : url,

				async : true,

				type : "POST",

				dataType : "html",

				cache : false

			};

			$.ajax(ajaxOption).done(function(data) {

				// main 영역 삭제

				$('#main').children().remove();

				// main 영역 교체

				$('#main').html(data);
				$('.content-text').text("여기는 일일통계");

			});

		}
		function total(url) {

			// ajax option

			var ajaxOption = {

				url : url,

				async : true,

				type : "POST",

				dataType : "html",

				cache : false

			};

			$.ajax(ajaxOption).done(function(data) {

				// main 영역 삭제

				$('#main').children().remove();

				// main 영역 교체

				$('#main').html(data);
				$('.content-text').text("여기는 총통계");

			});

		}
	</script>
</body>
</html>