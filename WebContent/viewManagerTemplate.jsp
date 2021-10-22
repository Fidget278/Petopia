<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="viewheader" value="${param.viewheader}.jsp" />
<c:set var="content" value="${param.content}.jsp" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>반려동물 카페</title>
<link href="css/viewFrameMain.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div class="inner">
		<jsp:include page="${viewheader}" flush="false" />
		<div class="main">
			<jsp:include page="${content}" flush="false" />
		</div>
		<jsp:include page="viewManagerFooter.jsp" />
	</div>
</body>
</html>