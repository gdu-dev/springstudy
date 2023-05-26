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
  var verifyPw = false;
  var verifyRePw = false;
  var verifyName = false;
  var verifyMobile = false;
  var verifyEmail = false;
  
  
  // 함수 정의
  
  function fnEditPwInit(){
	  $('#pw').val('');
	  $('#rePw').val('');
	  $('#msgPw').val('');
	  $('#msgRePw').val('');
  }
  
  function fnToggleEditPwArea(){
    $('#editPw').hide();
    $('#btnOpenEditPw').on('click', function(){
    	fnEditPwInit();
    	$('#btnOpenEditPw').hide();
      $('#editPw').show();
    });
    $('#btnCloseEditPw').on('click', function(){
    	fnEditPwInit();
    	$('#btnOpenEditPw').show();
    	$('#editPw').hide();
    });
  }
  
  function fnCheckPw(){
	  $('#pw').on('keyup', function(){
		  let pw = $(this).val();
		  let pwLength = pw.length;
		  let validCount = /[a-z]/.test(pw)         //   소문자를 가지고 있으면 true(1), 없으면 false(0)
				             + /[A-Z]/.test(pw)         //   대문자를 가지고 있으면 true(1), 없으면 false(0)
		                 + /[0-9]/.test(pw)         //     숫자를 가지고 있으면 true(1), 없으면 false(0)
		                 + /[^a-zA-Z0-9]/.test(pw); // 특수문자를 가지고 있으면 true(1), 없으면 false(0)
		  verifyPw = (pwLength >= 4) && (pwLength <= 20) && (validCount >= 3);
		  if(verifyPw){
			  $('#msgPw').text('사용 가능한 비밀번호입니다.');
		  } else {
			  $('#msgPw').text('4~20자, 소문자+대문자+숫자+특수문자 사용 가능, 3개 이상 조합');
		  }
	  })
  }
  
  function fnCheckPwAgain(){
    $('#rePw').on('keyup', function(){
    	let pw = $('#pw').val();
      let rePw = $(this).val();
      verifyRePw = (rePw != '') && (rePw == pw);
      if(verifyRePw){
        $('#msgRePw').text('');
      } else {
        $('#msgRePw').text('비밀번호 입력을 확인하세요.');
      }
    })
  }
  
  function fnCheckName(){
	  $('#name').on('keyup', function(){
		  verifyName = $(this).val() != '';
	  })
  }
  
  function fnCheckMobile(){
	  $('#mobile').on('keyup', function(){
		  let mobile = $(this).val();
		  let regMobile = /^010[0-9]{7,8}$/;
		  verifyMobile = regMobile.test(mobile);
		  if(verifyMobile){
			  $('#msgMobile').text('');
		  } else {
			  $('#msgMobile').text('휴대전화 입력을 확인하세요.');			  
		  }
	  })
  }
  
  // 6. 년/월/일
  function fnCreateDate(){
	  // 년도(100년 전 ~ 1년 후)
	  let year = new Date().getFullYear();
    let strYear = '<option value="">년도</option>';
    for(let y = year - 100; y <= year + 1; y++){
      strYear += '<option value="' + y + '">' + y + '</option>';
    }
    $('#birthyear').append(strYear);
    $('#birthyear').val('${loginUser.birthyear}').prop('selected', true);
	  // 월(1 ~ 12월)
    let strMonth = '<option value="">월</option>';
    for(let m = 1; m <= 12; m++){
      if(m < 10){
        strMonth += '<option value="0' + m + '">' + m + '월</option>';
      } else {
        strMonth += '<option value="' + m + '">' + m + '월</option>';
      }
    }
    $('#birthmonth').append(strMonth);
    $('#birthmonth').val('${loginUser.birthdate.substring(0,2)}').prop('selected', true);
	  // 일
    $('#birthdate').empty();
    $('#birthdate').append('<option value="">일</option>');
    let endDay = 0;
    let strDay = '';
    switch($('#birthmonth').val()){
    case '02':
      endDay = 29; break;
    case '04': case '06': case '09': case '11':
      endDay = 30; break;
    default:
      endDay = 31; break;
    }
    for(let d = 1; d <= endDay; d++){
      if(d < 10){
        strDay += '<option value="0' + d + '">' + d + '일</option>';
      } else {
        strDay += '<option value="' + d + '">' + d + '일</option>';
      }
    }
    $('#birthdate').append(strDay);
    $('#birthdate').val('${loginUser.birthdate.substring(2)}').prop('selected', true);
  }
  
  // 7. 이메일 검사 및 인증코드 전송
  function fnCheckEmail(){
	  
	  $('#btnGetCode').on('click', function(){
		  
		  // 입력한 이메일
		  let email = $('#email').val();
		  
		  new Promise(function(resolve, reject){
			  
			  // 정규식 
			  let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]{2,}(\.[a-zA-Z]{2,6}){1,2}$/;
			  //
			  //                  gt_min     @ naver         (.com)
			  //                                             (.co)(.kr)
			  
			  // 정규식 검사
			  verifyEmail = regEmail.test(email);
			  if(verifyEmail == false){
				  reject(1);  // catch 메소드에 정의된 function을 호출한다. 인수로 1을 전달한다.
				  return;
			  }
			  
			  // 이메일 중복 체크
			  $.ajax({
				  type: 'get',
				  url: '${contextPath}/user/verifyEmail.do',
				  data: 'email=' + email,
				  dataType: 'json',
				  success: function(resData){  // resData = {"enableEmail": true} 또는 {"enableEmail": false}
					  if(resData.enableEmail){
    				  resolve();  // then 메소드에 정의된 function을 호출한다.
					  } else {
						  reject(2);  // catch 메소드에 정의된 function을 호출한다. 인수로 2을 전달한다.
					  }
				  }
				})
			  
		  }).then(function(){
			  
			  // 이메일로 인증번호를 보내는 ajax
			  $.ajax({
				  type: 'get',
				  url: '${contextPath}/user/sendAuthCode.do',
				  data: 'email=' + email,
				  dataType: 'json',
				  success: function(resData){  // resData = {"authCode": "6T43G9"}  사용자에게 전송한 인증코드를 의미
					  
					  alert(email + "으로 인증코드를 전송했습니다.");
					  
					  // 메일로 받은 인증코드 입력 후 인증하기 버튼을 클릭한 경우
					  $('#btnVerifyCode').on('click', function(){
						  
						  verifyEmail = (resData.authCode == $('#authCode').val());  // 사용자에게 전송한 인증코드 == 사용자가 입력한 인증코드값
						  if(verifyEmail) {
							  alert('인증되었습니다.');
						  } else {
							  alert('인증에 실패했습니다.');
						  }
						  
					  })
					  
				  },
				  error: function(jqXHR){
					  alert('인증번호가 발송되지 않았습니다.');
					  verifyEmail = false;
				  }
			  })
			  
		  }).catch(function(number){
			  
			  let msg = '';
			  switch(number){
			  case 1:
				  msg = '이메일 형식이 올바르지 않습니다.';  // 정규식 실패
				  break;
			  case 2:
				  msg = '이미 사용 중인 이메일입니다.';      // 이메일 중복 체크 실패
				  break;
			  }
			  $('#msgEmail').text(msg);
			  verifyEmail = false;
			  
		  })
		  
	  })
	  
  }
	
  // 8. submit (회원가입)
  function fnJoin(){

	  $('#frmJoin').on('submit', function(event){
		  
		  if(verifyId == false){
			  alert('아이디를 확인하세요.');
			  event.preventDefault();
			  return;
		  } else if(verifyPw == false || verifyRePw == false){
        alert('비밀번호를 확인하세요.');
        event.preventDefault();
        return;
      } else if(verifyName == false){
        alert('이름을 확인하세요.');
        event.preventDefault();
        return;
      } else if(verifyMobile == false){
        alert('휴대전화번호를 확인하세요.');
        event.preventDefault();
        return;
      } else if($('#birthyear').val() == '' || $('#birthmonth').val() == '' || $('#birthdate').val() == ''){
        alert('생년월일을 확인하세요.');
        event.preventDefault();
        return;
      } else if(verifyEmail == false){
        alert('가입을 위해서 이메일 인증이 필요합니다.');
        event.preventDefault();
        return;
      }
		  
	  })
	  
  }
  
  // 함수 호출
  $(function(){
	  fnToggleEditPwArea();
	  fnCheckPw();
	  fnCheckPwAgain();
	  fnCheckName();
	  fnCheckMobile();
	  fnCreateDate();
	  fnCheckEmail();
	  fnJoin();
  })

</script>
</head>
<body>

  <div>
  
    <h1>마이페이지</h1>
    
    <div>
      <input type="button" value="비밀번호편집화면열기" id="btnOpenEditPw">
    </div>
    <div id="editPw">
      <form id="frmPw" method="post" action="${contextPath}/user/modifyPw.do">
        <!-- 비밀번호 -->
        <div>
          <label for="pw">비밀번호</label>
          <input type="password" name="pw" id="pw">
          <span id="msgPw"></span>
        </div>
        <!-- 비밀번호 재확인 -->
        <div>
          <label for="rePw">비밀번호 확인</label>
          <input type="password" id="rePw">
          <span id="msgRePw"></span>
        </div>
        <div>
          <input type="button" value="비밀번호편집화면닫기" id="btnCloseEditPw">
          <button>비밀번호수정완료</button>
        </div>
      </form>
    </div>
    
    <hr>

    <div>* 표시는 필수 입력사항입니다.</div>
    
    
    <form id="frmEdit" method="post" action="${contextPath}/user/modifyInfo.do">
    
      <div>아이디 ${loginUser.id}</div>
      <div>가입일 ${loginUser.joinedAt}</div>
      
      <div>
        <label for="name">이름*</label>
        <input type="text" name="name" id="name" value="${loginUser.name}">
      </div>
      <div>
        <span>성별*</span>
        <input type="radio" name="gender" id="none" value="NO">
        <label for="none">선택 안함</label>
        <input type="radio" name="gender" id="male" value="M">
        <label for="male">남자</label>
        <input type="radio" name="gender" id="female" value="F">
        <label for="female">여자</label>
      </div>
      <script>
        $(':radio[name="gender"][value="${loginUser.gender}"]').prop('checked', true);
      </script>
      
      <div>
        <label for="mobile">휴대전화*</label>
        <input type="text" name="mobile" id="mobile" value="${loginUser.mobile}">
        <span id="msgMobile"></span>
      </div>
    
      <div>
        <label for="birthyear">생년월일*</label>
        <select name="birthyear" id="birthyear"></select>
        <select name="birthmonth" id="birthmonth"></select>
        <select name="birthdate" id="birthdate"></select>
      </div>
      
      <div>
        <input type="text" onclick="execDaumPostcode()" name="postcode" id="postcode" placeholder="우편번호" readonly="readonly" value="${loginUser.postcode}">
        <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
        <input type="text" name="roadAddress" id="roadAddress" placeholder="도로명주소" value="${loginUser.roadAddress}">
        <input type="text" name="jibunAddress" id="jibunAddress" placeholder="지번주소" value="${loginUser.jibunAddress}"><br>
        <span id="guide" style="color:#999;display:none"></span>
        <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소" value="${loginUser.detailAddress}">
        <input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목" value="${loginUser.extraAddress}">
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script>
            // 본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
            function execDaumPostcode() {
                new daum.Postcode({
                    oncomplete: function(data) {
                        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
        
                        // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                        var roadAddr = data.roadAddress; // 도로명 주소 변수
                        var extraRoadAddr = ''; // 참고 항목 변수
        
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                            extraRoadAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if(data.buildingName !== '' && data.apartment === 'Y'){
                           extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if(extraRoadAddr !== ''){
                            extraRoadAddr = ' (' + extraRoadAddr + ')';
                        }
        
                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        document.getElementById('postcode').value = data.zonecode;
                        document.getElementById("roadAddress").value = roadAddr;
                        document.getElementById("jibunAddress").value = data.jibunAddress;
                        
                        // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                        if(roadAddr !== ''){
                            document.getElementById("extraAddress").value = extraRoadAddr;
                        } else {
                            document.getElementById("extraAddress").value = '';
                        }
        
                        var guideTextBox = document.getElementById("guide");
                        // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                        if(data.autoRoadAddress) {
                            var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                            guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                            guideTextBox.style.display = 'block';
        
                        } else if(data.autoJibunAddress) {
                            var expJibunAddr = data.autoJibunAddress;
                            guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                            guideTextBox.style.display = 'block';
                        } else {
                            guideTextBox.innerHTML = '';
                            guideTextBox.style.display = 'none';
                        }
                    }
                }).open();
            }
        </script>
      </div>
      
      <div>
        <label for="email">이메일*</label>
        <input type="text" name="email" id="email" value="${loginUser.email}">
        <span id="msgEmail"></span>
      </div>
      
      <div>
        <div>위치정보 동의여부</div>
        <input type="radio" name="location" id="locationOn" value="on"><label for="locationOn">동의함</label>
        <input type="radio" name="location" id="locationOff" value="off"><label for="locationOff">동의 안함</label>
        <div>프로모션 동의여부</div>
        <input type="radio" name="event" id="eventOn" value="on"><label for="eventOn">동의함</label>
        <input type="radio" name="event" id="eventOff" value="off"><label for="eventOff">동의 안함</label>
        <script>
          if('${loginUser.agreecode}' == '1' || '${loginUser.agreecode}' == '3'){
            $(':radio[name="location"][value="on"]').prop('checked', true);       
          } else {
            $(':radio[name="location"][value="off"]').prop('checked', true);
          }
          if('${loginUser.agreecode}' == '2' || '${loginUser.agreecode}' == '3'){
            $(':radio[name="event"][value="on"]').prop('checked', true);        
          } else {
            $(':radio[name="event"][value="off"]').prop('checked', true);
          }
        </script>
      </div>
      
      <hr>
      
      <div>
        <button>개인정보수정완료</button>
        <input type="button" value="취소하기" ID="btnCancel">
        <input type="button" value="회원탈퇴" ID="btnLeave">
      </div>
    
    </form>
  
  </div>
  
</body>
</html>