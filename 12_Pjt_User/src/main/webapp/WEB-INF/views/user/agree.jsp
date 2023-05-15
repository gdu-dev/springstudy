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
  function fnBack(){
    history.back();
  }
</script>
</head>
<body>

  <div>
  
    <h1>약관 동의하기</h1>
    
    <form id="frmAgree" action="${contextPath}/user/join.jsp">
    
      <div>
        <input type="checkbox" id="checkAll">
        <label for="checkAll">모두 동의</label>
      </div>
      
      <hr>
      
      <div>
        <input type="checkbox" id="service">
        <label for="service" >이용약관 동의(필수)</label>
        <div>
          <textarea>본 약관은 ...</textarea>
        </div>
      </div>
      
      <div>
        <input type="checkbox" id="privacy">
        <label for="privacy">개인정보수집 동의(필수)</label>
        <div>
          <textarea>개인정보보호법에 따라 ...</textarea>
        </div>
      </div>
      
      <div>
        <input type="checkbox" id="location" name="location" >
        <label for="location">위치정보수집 동의(선택)</label>
        <div>
          <textarea>위치정보 ...</textarea>
        </div>
      </div>
      
      <div>
        <input type="checkbox" id="event" name="event">
        <label for="event">이벤트 동의(선택)</label>
        <div>
          <textarea>이벤트 ...</textarea>
        </div>
      </div>
      
      <hr>
      
      <div>
        <input type="button" value="취소" onclick="fnBack()">
        <button>다음</button>
      </div>
    
    </form>
    
  </div>
  
</body>
</html>