<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <h1>게시판</h1>
  
  <c:forEach items="${boardList}" var="board">
    <div>${board.boardNo}</div>
    <div><a href="${contextPath}/board/detail.do?boardNo=${board.boardNo}">${board.title}</a></div>
    <div>${board.contents}</div>
    <hr>
  </c:forEach>

</body>
</html>