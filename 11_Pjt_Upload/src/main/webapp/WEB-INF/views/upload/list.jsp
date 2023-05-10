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
<script>
	let addResult = '${addResult}';
	if(addResult != ''){
		if(addResult == '1'){
			alert('UPLOAD 게시글 등록 성공');
		} else {
			alert('UPLOAD 게시글 등록 실패');
		}
	}
	let removeResult = '${removeResult}';
	if(removeResult != ''){
		if(removeResult == '1'){
			alert('UPLOAD 게시글 삭제 성공');
		} else {
			alert('UPLOAD 게시글 삭제 실패');
		}
	}
</script>
</head>
<body>

	<div>
		<a href="${contextPath}/upload/write.do">게시글 작성하러 가기</a>
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<caption>${pagination}</caption>
			<thead>
				<tr>
					<td>UPLOAD게시글번호</td>
					<td>제목</td>
					<td>작성일자</td>
					<td>첨부개수</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${uploadList}" var="upload">
					<tr>
						<td>${upload.uploadNo}</td>
						<td><a href="${contextPath}/upload/detail.do?uploadNo=${upload.uploadNo}">${upload.uploadTitle}</a></td>
						<td>${upload.createdAt}</td>
						<td>${upload.attachCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>