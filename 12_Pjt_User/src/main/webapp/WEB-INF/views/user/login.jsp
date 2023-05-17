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
  
    <h1>로그인</h1>
    
    <form id="frmLogin" method="post" action="${contextPath}/user/login.do">
      
      <!-- 로그인 이후에 이동할 주소 -->
      <input type="hidden" name="url" value="${url}">
      
      <div>
        <label for="id">아이디</label>
        <input type="text" name="id" id="id">
      </div>
      
      <div>
        <label for="pw">비밀번호</label>
        <input type="password" name="pw" id="pw">
      </div>
      
      <div>     
        <button>로그인</button>
      </div>
      
      <div>
        <label for="chkRememberId">
          <input type="checkbox" id="chkRememberId">
          아이디 기억
        </label>
        <label for="autoLogin">
          <input type="checkbox" name="chkAutoLogin" id="chkAutoLogin">
          자동 로그인
        </label>
      </div>
    
    </form>
      
    <div>
      <a href="${contextPath}/user/findId.jsp">아이디 찾기</a> | 
      <a href="${contextPath}/user/findPw.jsp">비밀번호 찾기</a> |
      <a href="${contextPath}/user/agree.jsp">회원가입</a>
    </div>
  
  </div>
  
</body>
</html>