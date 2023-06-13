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
<style>
  .blind {
    display: none;
  }
  .lblCheck {
    cursor: pointer;
    padding-left: 20px;
    background-image: url('../../resources/images/check1.png');
    background-size: 16px 16px;
    background-repeat: no-repeat;
    background-position: 0 3px;
  }
  .lblCheck:hover {
    cursor: pointer;
    color: gray;
  }
  .lblCheck:active {
    color: silver;
  }
  .checkAll:checked + label, .checkOne:checked + label {
    background-image: url('../../resources/images/check2.png');
  }
</style>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>

  // 전역 변수
  var verifyEmail = false;
  
  // 이메일 중복 체크
  function fnCheckEmail(){
    $('#btnVerifyEmail').on('click', function(){
      $.ajax({
        type: 'get',
        url: '${contextPath}/user/verifyEmail.do',
        data: 'email=' + $('#email').val(),
        dataType: 'json',
        success: function(resData){  // resData = {"enableEmail": true} 또는 {"enableEmail": false}
          verifyEmail = resData.enableEmail;
          if(verifyEmail){
            alert('사용할 수 있는 이메일입니다.');
            $('#btnVerifyEmail').prop('disabled', true);
          } else {
        	  alert('사용할 수 없는 이메일입니다. 다른 이메일을 사용하세요.');
          }
        }
      })
    })
  }
  
  // 회원가입
  function fnNaverJoin(){
    $('#frmNaverJoin').on('submit', function(event){
      if(verifyEmail == false){
        alert('가입을 위해서 이메일 중복확인이 필요합니다.');
        event.preventDefault();
        return;
      } else if($('#service').is(':checked') == false || $('#privacy').is(':checked') == false){
        alert('필수 약관에 동의해야만 가입할 수 있습니다.');
        event.preventDefault();
        return;
      }
    })
  }
  
  // 전체선택 체크박스
  function fnCheckAll(){
    $('#checkAll').on('click', function(){
      $('.checkOne').prop('checked', $(this).prop('checked'));
    })
  }
  
  // 개별선택 체크박스
  function fnCheckOne(){
    let chkOne = $('.checkOne');  // 모든 개별선택
    chkOne.on('click', function(){
      let chkCnt = 0;
      for(let i = 0; i < chkOne.length; i++){
        chkCnt += $(chkOne[i]).prop('checked');
      }
      $('#checkAll').prop('checked', chkCnt == chkOne.length);
    })
  }
  
  // 함수 호출
  $(function(){
    fnCheckEmail();
    fnNaverJoin();
    fnCheckAll();
    fnCheckOne();
  })

</script>
</head>
<body>

  <div>
  
    <h1>회원 가입</h1>
  
    <div>* 표시는 필수 입력사항입니다.</div>
    
    <hr>
    
    <form id="frmNaverJoin" method="post" action="${contextPath}/user/naver/join.do">
    
      <input type="hidden" name="id" value="${profile.id}">
    
      <div>
        <label for="name">이름*</label>
        <input type="text" name="name" id="name" value="${profile.name}">
      </div>
      
      <div>
        <span>성별*</span>
        <input type="radio" name="gender" id="none" value="NO" checked="checked">
        <label for="none">선택 안함</label>
        <input type="radio" name="gender" id="male" value="M">
        <label for="male">남자</label>
        <input type="radio" name="gender" id="female" value="F">
        <label for="female">여자</label>
        <script>
          $(':radio[name="gender"][value="${profile.gender}"]').prop('checked', true);
        </script>
      </div>
    
      <div>
        <label for="mobile">휴대전화*</label>
        <input type="text" name="mobile" id="mobile" value="${profile.mobile}">
      </div>
    
      <div>
        <label for="birthyear">생년월일*</label>
        <input type="text" name="birthyear" id="birthyear" value="${profile.birthyear}">
        <input type="text" name="birthmonth" value="${profile.birthdate.substring(0, 2)}">
        <input type="text" name="birthdate" value="${profile.birthdate.substring(2)}">
      </div>
      
      <div>
        <label for="email">이메일*</label>
        <input type="text" name="email" id="email" value="${profile.email}">
        <input type="button" value="이메일중복체크" id="btnVerifyEmail">
      </div>
      
      <hr>
      
      <!-- 약관 -->
      <div>
        <input type="checkbox" id="checkAll" class="checkAll blind">
        <label for="checkAll" class="lblCheck">모두 동의</label>
      </div>
      <div>----------</div>
      <div>
        <input type="checkbox" id="service" class="checkOne blind">
        <label for="service" class="lblCheck">이용약관 동의(필수)</label>
        <div>
          <textarea>본 약관은 ...</textarea>
        </div>
      </div>
      <div>
        <input type="checkbox" id="privacy" class="checkOne blind">
        <label for="privacy" class="lblCheck">개인정보수집 동의(필수)</label>
        <div>
          <textarea>개인정보보호법에 따라 ...</textarea>
        </div>
      </div>
      <div>
        <input type="checkbox" id="location" name="location" class="checkOne blind">
        <label for="location" class="lblCheck">위치정보수집 동의(선택)</label>
        <div>
          <textarea>위치정보 ...</textarea>
        </div>
      </div>
      <div>
        <input type="checkbox" id="event" name="event" class="checkOne blind">
        <label for="event" class="lblCheck">이벤트 동의(선택)</label>
        <div>
          <textarea>이벤트 ...</textarea>
        </div>
      </div>
      
      <hr>
      
      <div>
        <button>가입하기</button>
      </div>
    
    </form>
  
  </div>
  
</body>
</html>