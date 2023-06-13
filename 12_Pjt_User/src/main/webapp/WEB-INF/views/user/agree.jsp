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
    background-image: url('../resources/images/check1.png');
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
    background-image: url('../resources/images/check2.png');
  }
</style>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
  
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

  // 취소하면 이전 페이지로 돌아간다.
  function fnCancel(){
    $('#btnCancel').on('click', function(){      
      history.back();
    })
  }
  
  // 가입 페이지로 이동하기(frmAgree의 submit)
  function fnFrmAgreeSubmit(){
    $('#frmAgree').on('submit', function(event){
      if( $('#service').is(':checked') == false || $('#privacy').is(':checked') == false ){
        alert('필수 약관에 동의해야만 가입할 수 있습니다.');
        event.preventDefault();
        return;
      }
    })
  }
  
  // 함수 호출
  $(function(){
    fnCheckAll();
    fnCheckOne();
    fnCancel();
    fnFrmAgreeSubmit();
  })
  
</script>
</head>
<body>

  <div>
  
    <h1>약관 동의하기</h1>
    
    <form id="frmAgree" action="${contextPath}/user/join.form">
    
      <div>
        <input type="checkbox" id="checkAll" class="checkAll blind">
        <label for="checkAll" class="lblCheck">모두 동의</label>
      </div>
      
      <div>--------------------------------</div>
      
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
      
      <!--
        1. 체크한 경우     : 파라미터 location이 on값을 가지고 전달된다.
        2. 체크 안 한 경우 : 파라미터 location이 전달되지 않는다.
      -->
      <div>
        <input type="checkbox" id="location" name="location" class="checkOne blind">
        <label for="location" class="lblCheck">위치정보수집 동의(선택)</label>
        <div>
          <textarea>위치정보 ...</textarea>
        </div>
      </div>
      
      <!--
        1. 체크한 경우     : 파라미터 event가 on값을 가지고 전달된다.
        2. 체크 안 한 경우 : 파라미터 event가 전달되지 않는다.
      -->
      <div>
        <input type="checkbox" id="event" name="event" class="checkOne blind">
        <label for="event" class="lblCheck">이벤트 동의(선택)</label>
        <div>
          <textarea>이벤트 ...</textarea>
        </div>
      </div>
    
      <hr>
      
      <div>
        <input type="button" value="취소" id="btnCancel">
        <button>다음</button>
      </div>
    
    </form>
    
  </div>
  
</body>
</html>