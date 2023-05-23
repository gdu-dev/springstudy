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
<title>${param.title eq null ? 'Welcome' : param.title}</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/init.css" />
</head>
<body>

  <c:if test="${sessionScope.loginId eq null}">
    <form method="post" action="${contextPath}/user/login.do">
      <div><input type="text" name="id" placeholder="ID"></div>
      <div><input type="password" name="pw" placeholder="Password"></div>
      <div><button>로그인</button></div>
    </form>
  </c:if>
  
  <c:if test="${sessionScope.loginId ne null}">
    ${sessionScope.loginId}님 반갑습니다
    <input type="button" value="로그아웃" onclick="fnLogout()">
  </c:if>

  <hr>
  
  <a href="${contextPath}/blog/list.do">블로그</a>

</body>
</html>