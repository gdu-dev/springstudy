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
		${productCount}개 제품이 있습니다.
	</div>
	
	<hr>
	
	<div>
		<c:forEach items="${productList}" var="product">
			<ul>
				<li>제품번호 ${product.prodNo}</li>
				<li>제품이름 ${product.prodName}</li>
			</ul>
			<hr>
		</c:forEach>
	</div>
	
</body>
</html>








