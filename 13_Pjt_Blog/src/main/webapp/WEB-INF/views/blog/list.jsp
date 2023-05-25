<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="블로그" />
</jsp:include>

<div>

  <h1>블로그 목록</h1>
  
  <c:if test="${sessionScope.loginId ne null}">
    <div>
      <input type="button" value="블로그 작성하기" onclick="fnWrite()">
    </div>
  </c:if>
  
  <div>
    <table border="1">
      <thead>
        <tr>
          <td>번호</td>
          <td>제목</td>
          <td>조회수</td>
          <td>작성자</td>
          <td>작성일</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${blogList}" var="blog" varStatus="vs">
          <tr>
            <td>${beginNo - vs.index}</td>
            <td>${blog.title}</td>
            <td>${blog.hit}</td>
            <td>${blog.memberDTO.id}</td>
            <td>${blog.createdAt}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

</div>

<script>
  function fnWrite(){
	  location.href = '${contextPath}/blog/write.form';
  }
</script>

</body>
</html>