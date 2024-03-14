<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>

  <%-- MyController2 --%>
  <div>
    <a href="${contextPath}/board/list.do">board 목록</a>
  </div>
  
  <%-- MyController3 --%>
  <div>
    <a href="${contextPath}/article/detail1.do?article_no=10">article 상세1</a>
    <a href="${contextPath}/article/detail2.do">article 상세2</a>
    <a href="${contextPath}/article/detail3.do?article_no=10">article 상세3</a>
  </div>
  
  <%-- MyController4 --%>
  <div>
    <a href="${contextPath}/blog/list.do">블로그 목록</a>
  </div>
  
  <%-- MyController5 --%>
  <div>
    <a href="${contextPath}/faq/add.do">faq 등록</a>
  </div>
  

</body>
</html>