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
		<a href="${contextPath}/employees/pagination.do">페이징</a>
	</div>
	<div>
		<a href="${contextPath}/employees/scroll.page">스크롤</a>
	</div>
	<div>
		<a href="${contextPath}/employees/search.do">사원 조회 화면으로 이동</a>
	</div>
	
</body>
</html>