<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 상세</title>
</head>
<body>

  <div>${blog.blogNo}번 블로그</div>
  <div>${blog.title}</div>
  
  <form method="POST" 
        action="${contextPath}/blog/add.do">
    <div>
      <label for="blogNo">블로그번호</label>
      <input type="text" id="blogNo" name="blogNo">
    </div>
    <div>
      <label for="title">제목</label>
      <input type="text" id="title" name="title">
    </div>
    <div>
      <button type="submit">전송</button>
    </div>
  </form>

</body>
</html>