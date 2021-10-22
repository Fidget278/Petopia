<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>애완동물 카페</title>
<link href="./css/viewFrameSidebar.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="sidebar">
		<article class="sidebar-profile">
			<section class="sidebar-profile-firstSection">
				<div class="firstSection-inner">
					<!-- 프로필 수정 화면으로 이동 -->
					<input type="image" src="./img/profile.jpg" alt="Profile">
					
					<div class="firstSection-inner-profileInfo">
						<div>
						
							<a href="#">${sessionScope.user.nickname }</a> <a href="${pageContext.request.contextPath}/managerIndex.do">${sessionScope.user.grade }</a>
						</div>
						<div class="span-right">
							<div class="div-span-flex">
								<span class="span-block">방문횟수 : </span> <span>${sessionScope.user.visits } 회</span>
							</div>
							<div class="div-span-flex">
								<span class="span-block">작성 글 : </span> <span>${sessionScope.user.docs } 개</span>
							</div>
							<div class="div-span-flex">
								<span class="span-block">작성 댓글 : </span> <span>${sessionScope.user.comms } 개</span>
							</div>

						</div>
					</div>
				</div>
				<div class="lastSection-inner">
					<a href="#"> <span>비밀번호 변경</span>
					</a> <a href="#"> <span>로그아웃</span>
					</a>
				</div>
			</section>
		</article>
		<article class="sidebar-btn">
			<section>
				<span>쪽지함</span>
				<span onclick="location.href='${pageContext.request.contextPath}/managerStatistics.do'">통계</span>
			</section>
			<section>
				<span>카페 글쓰기</span> <span>전체 글 보기</span>
			</section>
		</article>
		<article class="sidebar-list">
			<div class="sidebar-list-groupTitle" id="group1">
				<span>새로운 그룹</span>
			</div>

			<ul class="sidebar-list-groupList" id="group-list1">
				<li class="sidebar-list-groupList-content">새로운 게시판 1</li>
				<li class="sidebar-list-groupList-content">새로운 게시판 2</li>
				<li class="sidebar-list-groupList-content">새로운 게시판 3</li>
			</ul>

			<div class="sidebar-list-groupTitle" id="group2">
				<span>새로운 그룹2</span>
			</div>

			<ul class="sidebar-list-groupList" id="group-list2">
				<li class="sidebar-list-groupList-content">새로운 게시판 1</li>
				<li class="sidebar-list-groupList-content">새로운 게시판 2</li>
				<li class="sidebar-list-groupList-content">새로운 게시판 3</li>
			</ul>
			
			<div class="sidebar-list-groupTitle" id="group2">
				<span>새로운 그룹2</span>
			</div>

			<ul class="sidebar-list-groupList" id="group-list2">
				<li class="sidebar-list-groupList-content">새로운 게시판 1</li>
				<li class="sidebar-list-groupList-content">새로운 게시판 2</li>
				<li class="sidebar-list-groupList-content">새로운 게시판 3</li>
			</ul>
		</article>
	</div>
</body>
</html>