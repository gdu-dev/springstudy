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
  function fnLeave(){
    if(confirm('동일한 아이디로 재가입이 불가능합니다. 회원 탈퇴하시겠습니까?')){
      location.href = '${contextPath}/user/leave.do';
    }
  }
</script>
</head>
<body>

  <div>
    
    <!-- 로그인이 안 된 상태 -->
    <c:if test="${sessionScope.loginId == null}">    
      <a href="${contextPath}/user/agree.form">회원가입</a>
      <a href="${contextPath}/user/login.form">로그인</a>
    </c:if>
    
    <!-- 로그인이 된 상태 -->
    <c:if test="${sessionScope.loginId != null}">
      <div>
        <a href="${contextPath}/user/checkPw.form">${sessionScope.loginId}</a>님 반갑습니다 ♥
      </div>
      <div>
        <a href="${contextPath}/user/logout.do">로그아웃</a>
        <a href="javascript:fnLeave()">회원탈퇴</a>
      </div>
    </c:if>
    
  </div>
  
</body>
</html>