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
<!-- cdnjs.com 에서 jquery-cookie 검색 후 jquery-cookie 라이브러리 포함 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
  
  // $.cookie() : jquery-cookie 라이브러리가 필요함

  // 로그인하기
  function fnLogin(){
    
    $('#frmLogin').on('submit', function(event){
      
      // 아이디, 비밀번호 공백 검사
      if($('#id').val() == '' || $('#pw').val() == ''){
        alert('아이디와 비밀번호를 모두 입력하세요.');
        event.preventDefault();
        return;
      }
      
      // 아이디 기억을 체크하면 rememberId 쿠키에 입력된 아이디를 저장
      if($('#chkRememberId').is(':checked')){
        $.cookie('rememberId', $('#id').val());
      } else {
        $.cookie('rememberId', '');
      }
      
    });
    
  }
  
  // 쿠키에 기억된 아이디 보여주기(아이디 기억)
  function fnDisplayRememberId(){
    
    // rememberId 쿠키에 저장된 아이디를 가져와서 표시
    
    let rememberId = $.cookie('rememberId');
    if(rememberId == ''){
      $('#id').val('');
      $('#chkRememberId').prop('checked', false);
    } else {
      $('#id').val(rememberId);
      $('#chkRememberId').prop('checked', true);
    }
    
  }
  
  $(function(){
    fnLogin();
    fnDisplayRememberId();
  });
  
</script>
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
        <input type="checkbox" id="chkRememberId">
        <label for="chkRememberId">아이디 기억</label>
        <input type="checkbox" name="chkAutologin" id="chkAutologin">
        <label for="chkAutologin">자동 로그인</label>
      </div>
    
    </form>
      
    <div>
      <a href="${contextPath}/user/findId.form">아이디 찾기</a> | 
      <a href="${contextPath}/user/findPw.form">비밀번호 찾기</a> |
      <a href="${contextPath}/user/agree.form">회원가입</a>
    </div>
  
  </div>
  
</body>
</html>