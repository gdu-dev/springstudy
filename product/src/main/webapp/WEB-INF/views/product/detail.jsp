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
	let modifyResult = '${modifyResult}';
	if(modifyResult != ''){
		if(modifyResult == '1'){
			alert('수정 성공!');
		} else {
			alert('수정 실패!');
		}
	}
</script>
</head>
<body>

	<ul>
		<li>제품번호 ${productDTO.prodNo}</li>
		<li>제품이름 ${productDTO.prodName}</li>
		<li>제품가격 ${productDTO.prodPrice}</li>
		<li>제조일자 ${productDTO.prodMadeAt}</li>
	</ul>
	
	<form method="post" action="${contextPath}/product/edit.do">
		<input type="hidden" name="prodNo" value="${productDTO.prodNo}">
		<input type="hidden" name="prodName" value="${productDTO.prodName}">
		<input type="hidden" name="prodPrice" value="${productDTO.prodPrice}">
		<input type="hidden" name="prodMadeAt" value="${productDTO.prodMadeAt}">
		<button>편집</button>
		<input type="button" value="목록" onclick="fnList()">
		<script>
			function fnList(){
				location.href = '${contextPath}/product/list.do';
			}
		</script>
	</form>
	
</body>
</html>