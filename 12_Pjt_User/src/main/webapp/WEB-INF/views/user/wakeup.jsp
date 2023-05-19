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

  $(function(){
	  
	  // 휴면해제
	  $('#btnWakeup').on('click', function(){
		  location.href = '${contextPath}/user/restore.do';
	  })
	  
	  // 취소
	  $('#btnCancel').on('click', function(){
		  location.href = '${contextPath}/index.do';
	  })
	  
  })
  
</script>
</head>
<body>

  <div>
    
    <h1>휴면계정안내</h1>
    <div>
      안녕하세요. ${sessionScope.sleepUserId}님은 1년 이상 로그인하지 않아 관련 법령에 의해서 휴면회원으로 전환되었습니다.
    </div>
    <div>
      휴면해제를 위해서 휴면해제 버튼을 클릭하세요.
      <input type="button" value="휴면해제" id="btnWakeup">
      <input type="button" value="취소" id="btnCancel">
    </div>
    
  </div>
  
</body>
</html>