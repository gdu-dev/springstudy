<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>
    <h1>회원관리</h1>
    <div>
      <label for="email">이메일</label>
      <input type="text" id="email">
    </div>
    <div>
      <label for="name">이름</label>
      <input type="text" id="name">
    </div>
    <div>
      <label for="none">선택안함</label>
      <input type="radio" name="gender" id="none" value="none" checked>
      <label for="man">남자</label>
      <input type="radio" name="gender" id="man" value="man">
      <label for="woman">여자</label>
      <input type="radio" name="gender" id="woman" value="woman">
    </div>
    <div>
      <input type="text" id="zonecode" placeholder="우편번호">
      <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
      <input type="text" id="address" placeholder="주소"><br>
      <input type="text" id="detailAddress" placeholder="상세주소">
      <input type="text" id="extraAddress" placeholder="참고항목">
      <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
      <script>
        function execDaumPostcode() {
          new daum.Postcode({
            oncomplete: function(data) {
              // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
              // 각 주소의 노출 규칙에 따라 주소를 조합한다.
              // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
              var addr = ''; // 주소 변수
              var extraAddr = ''; // 참고항목 변수
              // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
              if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
              } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
              }
              // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
              if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                  extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                  extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                  extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById('extraAddress').value = extraAddr;
              } else {
                document.getElementById('extraAddress').value = '';
              }
              // 우편번호와 주소 정보를 해당 필드에 넣는다.
              document.getElementById('zonecode').value = data.zonecode;
              document.getElementById('address').value = addr;
              // 커서를 상세주소 필드로 이동한다.
              document.getElementById('detailAddress').focus();
            }
          }).open();
        }
      </script>
    </div>
    <div>
      <button id="btn-init">초기화</button>
      <button id="btn-register">등록</button>
      <button id="btn-modify">수정</button>
      <button id="btn-remove">삭제</button>      
    </div>
    
    <hr>
    
    <div>
      <table border="1">
        <thead>
          <tr>
            <td>선택</td>
            <td>이메일</td>
            <td>이름</td>
            <td>성별</td>
            <td>버튼</td>
          </tr>
        </thead>
        <tbody id="members"></tbody>
        <tfoot>
          <tr>
            <td colspan="5" id="paging"></td>
          </tr>
        </tfoot>
      </table>
    </div>
    
  </div>
  
  <script src="${contextPath}/resources/js/member.js"></script>
  <script>
  
    // jQuery 객체 선언
    var email = $('#email');
    var mName = $('#name');
    var zonecode = $('#zonecode');
    var address = $('#address');
    var detailAddress = $('#detailAddress');
    var extraAddress = $('#extraAddress');
    var btnInit = $('#btn-init');
    var btnRegister = $('#btn-register');
    var btnModify = $('#btn-modify');
    var btnRemove = $('#btn-remove');
  
    // 함수 표현식 (함수 만들기)
    const fnInit = ()=>{
      email.val('');
      mName.val('');
      $('#none').prop('checked', true);
      zonecode.val('');
      address.val('');
      detailAddress.val('');
      extraAddress.val('');
    }

    const fnRegisterMember = ()=>{
      $.ajax({
        // 요청
        type: 'POST',
        url: '${contextPath}/members',
        contentType: 'application/json',  // 보내는 데이터의 타입
        data: JSON.stringify({            // 보내는 데이터 (문자열 형식의 JSON 데이터)
          'email': email.val(),
          'name': mName.val(),
          'gender': $(':radio:checked').val(),
          'zonecode': zonecode.val(),
          'address': address.val(),
          'detailAddress': detailAddress.val(),
          'extraAddress': extraAddress.val()
        }),
        // 응답
        dataType: 'json'  // 받는 데이터 타입
      }).done(resData=>{  // resData = {"insertCount": 2}
        if(resData.insertCount === 2){
          alert('정상적으로 등록되었습니다.');
          fnInit();
        }
      }).fail(jqXHR=>{
        alert(jqXHR.responseText);
      })
    }
    
    // 함수 호출 및 이벤트
    fnInit();
    btnInit.on('click', fnInit);
    btnRegister.on('click', fnRegisterMember);
  
  </script>

</body>
</html>