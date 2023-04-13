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

	function fn1(){
	
		$('#result').empty();
		
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/first/ajax1',
			data: 'name=' + $('#name').val() + '&age=' + $('#age').val(),
			// 응답
			dataType: 'json',
			success: function(resData){  // resData = {"name": "민경태", "age": 46}
				let str = '<ul>';
				str += '<li>' + resData.name + '</li>';
				str += '<li>' + resData.age + '</li>';
				str += '</ul>';
				$('#result').append(str);
			},
			error: function(jqXHR){
				$('#result').text(jqXHR.responseText);
			}
		})
		
	}
	
	function fn2(){
		
		$('#result').empty();
		
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/first/ajax2',
			data: $('#frm').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData){  // resData = {"name": "민경태", "age": 46}
				let str = '<ul>';
				str += '<li>' + resData.name + '</li>';
				str += '<li>' + resData.age + '</li>';
				str += '</ul>';
				$('#result').append(str);
			}
		})
		
	}
	
	function fn3(){
		
		$('#result').empty();
		
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/first/ajax3',
			data: $('#frm').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData){  // resData = {"name": "민경태", "age": 46}
				let str = '<ul>';
				str += '<li>' + resData.name + '</li>';
				str += '<li>' + resData.age + '</li>';
				str += '</ul>';
				$('#result').append(str);
			}
		})
		
	}
	
</script>
</head>
<body>
	
	<div>
		<form id="frm">
			<div>
				<label for="name">이름</label>
				<input id="name" name="name">
			</div>
			<div>
				<label for="age">나이</label>
				<input id="age" name="age">
			</div>
			<div>
				<input type="button" value="전송1" onclick="fn1()">
				<input type="button" value="전송2" onclick="fn2()">
				<input type="button" value="전송3" onclick="fn3()">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div id="result"></div>
	
</body>
</html>