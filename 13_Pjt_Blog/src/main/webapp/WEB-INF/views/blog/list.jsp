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

</div>

<script>
  function fnWrite(){
	  location.href = '${contextPath}/blog/write.form';
  }
</script>

</body>
</html>