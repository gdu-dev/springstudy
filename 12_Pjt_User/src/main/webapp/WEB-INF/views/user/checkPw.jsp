<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	
	function fnCheckPwCancel(){
		$('#btnCancel').on('click', function(){
		  history.back();
		})
	}
	
  function fnCheckPw(){
    $('#btnCheckPw').on('click', function(){
      $.ajax({
        type: 'post',
        url: '${contextPath}/user/checkPw.do',
        data: 'id=${sessionScope.loginId}&pw=' + $('#pw').val(),
        dataType: 'json',
        success: function(resData){
          if(resData.isCorrect){
          	location.href = '${contextPath}/user/mypage.do';
          } else {
        	  alert('비밀번호를 확인하세요.');
          }
        }
      })
    })
  }
  
  $(function(){
	  fnCheckPwCancel();
    fnCheckPw();
  });
	
</script>
</head>
<body>

	<div>
	
		<div>개인정보보호를 위해서 비밀번호를 다시 한 번 입력하세요</div>
		
		<div>
			<label for="pw">비밀번호</label>
			<input type="password" id="pw">
		</div>
		
		<div>
			<input type="button" value="취소" id="btnCancel">
			<input type="button" value="확인" id="btnCheckPw">
		</div>
		
	</div>

</body>
</html>