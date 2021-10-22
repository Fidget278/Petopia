
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
</head>
<body>
	<div class="content">
		<table>
			<thead>
				<tr>
					<th>날짜</th>
					<th>일일 게시글 수</th>
					<th>일일 방문자 수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="daily" items="${requestScope.dailyList}"
					varStatus="status">
					<tr>
						<td><c:set var="now" value="<%=new java.util.Date()%>" /> <c:set
								var="sysYear">
								<fmt:formatDate value="${now}" pattern="yyyy" />
							</c:set></td>
						<td>${daily.getDailyBoardCount }</td>
						<td>${daily.getDailyVisitorsCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>