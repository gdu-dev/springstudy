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
<script src="${contextPath}/resources/js/lib/moment-with-locales.js"></script>
<script>

  function fnFindId() {
	  $('#btnFindId').on('click', function(){
    	$.ajax({
        type: 'post',
        url: '${contextPath}/user/findId.do',
        contentType: 'application/json',  // 보내는 데이터가 JSON이라는 의미입니다. 
        data: JSON.stringify({            // 보내는 데이터의 파라미터 이름이 없기 때문에 컨트롤러에서 parameter를 받는 request.getParameter(), @RequestParam, 커맨드 객체 모두 사용할 수 없습니다.
          name: $('#name').val(),         // 컨트롤러에서는 @RequestBody와 객체 또는 Map을 통해서 받아야 합니다.
          email: $('#email').val()
        }),
        dataType: 'json',
        success: function(resData) {
          if(resData.findUser != null) {
            let id = resData.findUser.id;
            id = id.substring(0, 3) + '*****';
            moment.locale('ko-KR');
            $('#findResult').html('회원님의 아이디는 ' + id + '입니다.<br>(가입일 : ' + moment(resData.findUser.joinedAt).format("YYYY년 MM월 DD일 a h:mm:ss") + ')');
          } else {
        	  $('#findResult').html('일치하는 회원이 없습니다. 입력 정보를 확인하세요.');
          }
        }
      });
    });
  }

  $(function(){
	  fnFindId();
	});

</script>
</head>
<body>

  <div>

    <h1>아이디 찾기</h1>

    <div>
      <label for="name">*이름</label>
      <input type="text" name="name" id="name">
    </div>
    <div>
      <label for="email">*이메일</label>
      <input type="text" name="email" id="email">
    </div>
    <div>
      <input type="button" value="아이디찾기" id="btnFindId">
    </div>
    <div>
      <a href="${contextPath}/user/login.form">로그인</a> |
      <a href="${contextPath}/user/findPw.form">비밀번호 찾기</a> |
      <a href="${contextPath}/user/agree.form">회원가입</a>
    </div>
    
  </div>
  
  <hr>
  
  <div id="findResult"></div>

</body>
</html>