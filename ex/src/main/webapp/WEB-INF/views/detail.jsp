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

	<!-- EL 사용이 가능한 영역 : pageContext, request, session, application -->
	
	<h1>${boardNo}</h1>
	<h1>${title}</h1>
	
	<!-- boardDTO -->
	<h1>a, location</h1>
	<h1>${boardDTO.boardNo}</h1>
	<h1>${boardDTO.title}</h1>
	
	<!-- board -->
	<h1>form</h1>
	<h1>${board.boardNo}</h1>
	<h1>${board.title}</h1>
	
</body>
</html>