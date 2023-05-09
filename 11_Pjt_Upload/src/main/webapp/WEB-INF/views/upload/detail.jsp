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
</head>
<body>

	<div>
		<h1>${upload.uploadNo}번 UPLOAD 게시글</h1>
		<ul>
			<li>제목 : ${upload.uploadTitle}</li>
			<li>내용 : ${upload.uploadContent}</li>
			<li>작성일자 : ${upload.createdAt}</li>
			<li>수정일자 : ${upload.modifiedAt}</li>
		</ul>
	</div>
	
	<hr>
	
	<div>
		<h4>첨부 목록 및 다운로드</h4>
		<div>
			<c:forEach items="${attachList}" var="attach">
				<div>
					<c:if test="${attach.hasThumbnail == 1}">
						<img src="${contextPath}/upload/display.do?attachNo=${attach.attachNo}">
					</c:if>
					<c:if test="${attach.hasThumbnail == 0}">
						<img src="${contextPath}/resources/images/attach1.png" width="50px">
					</c:if>
					${attach.originName}
				</div>
			</c:forEach>
		</div>
	</div>
	
</body>
</html>