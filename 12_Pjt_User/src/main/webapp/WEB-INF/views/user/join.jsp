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

  // 전역 변수 (각종 검사 통과 유무를 저장하는 변수)
  var verifyId = false;
  
  // 함수 정의
  
  // 1. 아이디 검사(정규식 + 중복)
  function fnCheckId(){
	  
	  $('#id').on('keyup', function(){
		  
		  // 입력한 아이디
		  let id = $(this).val();
		  
		  // 정규식 (5~40자, 소문자+숫자+하이픈(-)+밑줄(_) 사용 가능, 첫 글자는 소문자+숫자 사용 가능)
		  let regId = /^[a-z0-9][a-z0-9-_]{4,39}$/;
		  
		  // 정규식 검사
		  verifyId = regId.test(id);
		  if(verifyId == false){
			  $('#msgId').text('5~40자, 소문자+숫자+하이픈(-)+밑줄(_) 사용 가능, 첫 글자는 소문자+숫자 사용 가능');
			  return;  // 여기서 함수 실행을 종료한다. (이후에 나오는 ajax(중복 체크) 실행을 막기 위해서)
		  }
		  
		  // 아이디 중복 체크 ajax
		  $.ajax({
			  type: 'get',
			  url: '${contextPath}/user/verifyId.do',
			  data: 'id=' + id,
			  dataType: 'json',
			  success: function(resData){  // resData = {"enableId": true} 또는 {"enableId": false}
				  verifyId = resData.enableId;
			    if(verifyId){
					  $('#msgId').text('사용 가능한 아이디입니다.');
				  } else {
					  $('#msgId').text('이미 사용 중인 아이디입니다.');
				  }
			  }
		  })
		  
	  })
	  
  }
  
  // 함수 호출
  $(function(){
	  fnCheckId();
  })

</script>
</head>
<body>

  <div>
  
    <h1>회원 가입</h1>
  
    <div>* 표시는 필수 입력사항입니다.</div>
    
    <hr>
    
    <form id="frmJoin" method="post" action="${contextPath}/user/join.do">
    
      <!-- agree.jsp에서 전달된 location, event 속성 -->
      <input type="hidden" name="location" value="${location}">
      <input type="hidden" name="event" value="${event}">
    
      <div>
        <label for="id">아이디*</label>
        <input type="text" name="id" id="id">
        <span id="msgId"></span>
      </div>
      
      <div>
        <label for="pw">비밀번호*</label>
        <input type="password" name="pw" id="pw">
        <span id="msgPw"></span>
      </div>
      
      <div>
        <label for="rePw">비밀번호 확인*</label>
        <input type="password" id="rePw">
        <span id="msgRePw"></span>
      </div>
      
      <div>
        <label for="name">이름*</label>
        <input type="text" name="name" id="name">
      </div>
      
      <div>
        <span>성별*</span>
        <input type="radio" name="gender" id="none" value="NO" checked="checked">
        <label for="none">선택 안함</label>
        <input type="radio" name="gender" id="male" value="M">
        <label for="male">남자</label>
        <input type="radio" name="gender" id="female" value="F">
        <label for="female">여자</label>
      </div>
    
      <div>
        <label for="mobile">휴대전화*</label>
        <input type="text" name="mobile" id="mobile">
        <span id="msgMobile"></span>
      </div>
    
      <div>
        <label for="birthyear">생년월일*</label>
        <select name="birthyear" id="birthyear"></select>
        <select name="birthmonth" id="birthmonth"></select>
        <select name="birthdate" id="birthdate"></select>       
      </div>
      
      <div>
      </div>
      
      <div>
        <label for="email">이메일*</label>
        <input type="text" name="email" id="email">
        <input type="button" value="인증번호받기" id="btnGetCode">
        <span id="msgEmail"></span><br>
        <input type="text" id="authCode" placeholder="인증코드 입력">
        <input type="button" value="인증하기" id="btnVerifyCode">
      </div>
      
      <hr>
      
      <div>
        <button>가입하기</button>
        <input type="button" value="취소하기">
      </div>
    
    </form>
  
  </div>
  
</body>
</html>