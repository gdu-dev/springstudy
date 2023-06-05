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
    fnFindPw();
  });
  
  function fnFindPw(){
    $('#btnFindPw').on('click', function(){
      new Promise(function(resolve, reject){
        if($('#id').val() == ''){
          reject('아이디를 입력하세요.');
          return;
        }
        $.ajax({
          type: 'post',
          url: '${contextPath}/user/findPw.do',
          contentType: 'application/json',
          data: JSON.stringify({
            'id': $('#id').val()
          }),
          dataType: 'json',
          success: function(resData){
            if(resData.findUser != null){
              resolve(resData.findUser);
            } else {
              reject('일치하는 회원 정보가 없습니다.');
            }
          }
        });    
      }).then(function(findUser){
        $.ajax({
          type: 'post',
          url: '${contextPath}/user/sendTempPw.do',
          contentType: 'application/json',
          data: JSON.stringify({
        	  'id': $('#id').val(),
            'email': findUser.email
          }),
          dataType: 'json',
          success: function(resData){
            if(resData.pwUpdateResult == 1){
              alert('등록된 이메일로 임시비밀번호가 발송되었습니다.');
              location.href = '${contextPath}/index.do';
            } else {
            	alert('임시비밀번호 발급이 실패했습니다.');
            }
          }
        });
      }).catch(function(msg){
        alert(msg);
      });
    });
  }

</script>
</head>
<body>
  
  <h1>비밀번호 찾기</h1>
  
  <div>
      <div>
        <label for="id">*아이디</label>
        <input type="text" id="id">
      </div>
      <div>
        <input type="button" value="임시비밀번호 발급" id="btnFindPw">
      </div>
      <div>
        <a href="${contextPath}/user/login.form">로그인</a> |
        <a href="${contextPath}/user/findId.form">아이디 찾기</a> |
        <a href="${contextPath}/user/agree.form">회원가입</a>
      </div>
  </div>
  
</body>
</html>