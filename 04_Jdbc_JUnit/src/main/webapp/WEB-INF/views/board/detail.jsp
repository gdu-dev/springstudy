<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<script>
	function fnRemove(){
		if(confirm('삭제할까요?')){
			location.href = '${contextPath}/board/remove.do?board_no=${b.board_no}';
		}
	}
	function fnList(){
		location.href = '${contextPath}/board/list.do';
	}
</script>
</head>
<body>

	<div>
		<h1>${b.board_no}번 게시글 상세보기</h1>
		<div>제목 : ${b.title}</div>
		<div>작성자 : ${b.writer}</div>
		<div>작성일 : ${b.created_at}</div>
		<div>수정일 : ${b.modified_at}</div>
		<div>${b.content}</div>
	</div>
	
	<div>
		<input type="button" value="편집" onclick="">
		<input type="button" value="삭제" onclick="fnRemove()">
		<input type="button" value="목록" onclick="fnList()">
	</div>
	
</body>
</html>