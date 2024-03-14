<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>faq 목록</title>
<script>
  function fnAddResult(){
	  let addResult = '${addResult}';
	  if(addResult !== '' && addResult === '1'){
		  alert('정상적으로 등록되었습니다.');
	  }
  }
  fnAddResult();
</script>
</head>
<body>

</body>
</html>