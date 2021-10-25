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
    		
    		 const getAjax = function(url, no, content) {
                // resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
                return new Promise( (resolve, reject) => {
                    $.ajax({                        
                        url: url,
                        method: 'GET',
                        dataType: 'json',
                        data: {
                        	no: no,
                        	content: content
                        },
                        success: function(data) {                    	
                            resolve(data);
                        }, 
                        error: function(e) {                    	
                            reject(e);
                        }
                    });
                });
            }   
    		 
    		const removeReply = function(url, no) {
                // resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
                return new Promise( (resolve, reject) => {
                    $.ajax({                        
                        url: url,
                        method: 'GET',
                        dataType: 'json',
                        data: {
                        	no: no                            
                        },
                        success: function(data) {                    	
                            resolve(data);
                        }, 
                        error: function(e) {                    	
                            reject(e);
                        }
                    });
                });
            }   
    		
            async function requestProcess(url, no, content) {
                try {
                    
                	let replyList = null;
                	if(content != null || content != '') {
                		replyList = await getAjax(url, no, content);	
                	} else {
                		replyList = await removeReply(url, no);
                	}
                	     
                                       
                    $('#ListReply').html("");
                   
				 	let htmlStr = [];
				 	for(let i = 0; i< replyList.length; i++) {
				 		htmlStr.push('<table id=' + replyList[i].replyNo +'>');
				 		htmlStr.push('<tbody>');
				 		htmlStr.push('<tr>');
				 		htmlStr.push('<td>' + replyList[i].nickname + '</td>');
				 		htmlStr.push('<td>' + replyList[i].writeday + '</td>');
				 		htmlStr.push('</tr>');		
				 		htmlStr.push('<tr>');	
				 		htmlStr.push('<td colspan="2" class="cmtContent">' + replyList[i].content + '</td>');
				 		htmlStr.push('</tr>');
				 		htmlStr.push('<tr>');	
				 		htmlStr.push('<td colspan="2">');
				 		htmlStr.push('<button class="modifyFormBtn" type="button">수정</button>&nbsp;');		
				 		htmlStr.push('<button class="removeBtn" type="button">삭제</button>');			
				 		htmlStr.push('</td>');					
				 		htmlStr.push('</tr>');
				 		htmlStr.push('</tbody>');
				 		htmlStr.push('</table>');				 		
				 	}         
				 	
				 	$('#ListReply').html(htmlStr.join(""));
                } catch (error) {
                    console.log("error : ", error);   
                }
            }
          //댓글 달기
            $('#addReplyBtn').on('click', function() {
            	const articleNo = '${param.articleNo}';
            	const content = $('#addContent').val();
           		console.log('ajax전');
            	requestProcess('${pageContext.request.contextPath}/addReply.do', articleNo, content);
            	console.log('ajax후');
            });
            
            
            
            $('#ListReply').on('click', '.modifyFormBtn', function() {                
            	const no = $(this).parents('table').attr('id');
            	$('#modifyReply').insertAfter('#' + no);                	
            	const content = $(this).parents('tbody').find('.Content').text();                
            	$('#modifyReplyContent').val(content);
            	$('#no').val(no);
            	$('#modifyReply').show();
            	$('#' + no).hide();                	
            });
            

            //댓글 삭제
            $('#ListReply').on('click', '.removeBtn', function() {                
            	const no = $(this).parents('table').attr('id');
            	requestProcess('${pageContext.request.contextPath}/removeReply.do', no);        	
            });
            
            
            //댓글 취소
            $('#cancel').on('click', function() {
            	const no = $('#no').val();
            	$('#' + no).show();    
            	$('#modifyReply').hide();
            	$('#modifyReply').insertAfter('#addReply');
            });
            
            //댓글 수정
            $('#modifyBtn').on('click', function() {
            	const no = $('#no').val();
            	const content = $('#modifyReplyContent').val();
            	requestProcess('${pageContext.request.contextPath}/modifyReply.do', no, content);   
            
            });
				
            
            //댓글 삭제
            $('#ListReply').on('click', '.removeBtn', function() {                
            	const no = $(this).parents('table').attr('id');
            	         	
            }); 
    
	});
	

</script>
</head>
<body>
	<div class="content">
		<!-- Content 내용 여기에 추가 -->
		<table class="bbs" width="800" height="500" border="2" bgcolor="D8D8D8">
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
		</tbody>
	</table>
	</div>
	</br>
	<%-- 첨부파일 출력. --%>
	<div class="file">
		<c:if test="${empty requestScope.articles.fileList }">등록된 파일이 없습니다.
		</c:if>

		<c:if test="${not empty requestScope.articles.fileList }">
			<th>파일명</th><th>파일크기</th>
			<c:forEach var="file" items="${requestScope.articles.fileList }">
				<c:url var="downloadUrl" value="/fileDownload">
					<c:param name="originalFileName" value="${file.originalFileName}"/>
					<c:param name="systemFileName" value="${file.systemFileName}"/>
				</c:url>
				<td><a href="${downloadUrl}">${file.originalFileName }</a></td>
			</c:forEach>
			<c:forEach var="file" items="${requestScope.articles.fileList }">
				<td>${file.originalFileName }</td>
				<td>${file.fileSize } bytes</td>
			</c:forEach>
		</c:if>				
	</div>
	
	 <%-- 댓글 --%>
	 <div class="ListReply">
		<c:forEach var="reply" items="${requestScope.replyList }">
			<table id="${reply.replyNo }" width="800" height="500">
				<tbody>
					<tr>
						<td>${reply.nickname }</td>
						<td>${reply.writedate }</td>
					</tr>
					<tr>
						<td colspan="2" class="replyContent">${reply.content }</td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="modifyFormBtn" type="button">수정</button>
							<button class="removeBtn" type="button">삭제</button>
						</td>
					</tr>
				</tbody>			
			</table>
		</c:forEach>
	</div>
	<%-- 댓글 달기 --%>
	<div id="addReply">
		<div>
			<textarea id="addContent" rows="5" cols="50" placeholder="댓글을 입력해주세오 ."></textarea>
		</div>
		<div>
			<button id="addReplyBtn">댓글 달기</button>
		</div>
	</div>
	
	<%-- 댓글 수정--%>
	<div id="modifyReply" style="display:none;">
		<div>
			<input type="hidden" id="no"/>
			<textarea id="modifyReplyContent" rows="5" cols="50" placeholder="댓글을 입력해주세오 ."></textarea>
		</div>
		<div>
			<button id="cancel">취소</button>
			<button id="modifyBtn">수정하기</button>
		</div>
	</div>	
</body>
</html>