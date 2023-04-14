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

	function fnBmi1(){
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/second/bmi1',
			data: $('#frm').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData){                  // resData : {"bmi": 22, "obesity": "정상", "weight": 1, "height": 1}
				$('#bmi').text(resData['bmi']);          // resData.bmi     == resData['bmi']
				$('#obesity').text(resData['obesity']);  // resData.obesity == resData['obesity']
			},
			error: function(jqXHR){
				$('#bmi').text('');
				$('#obesity').text('');
				// alert(jqXHR.responseText + '(' + jqXHR.status + ')');
				if(jqXHR.status == 500){
					alert('몸무게와 키 입력을 확인하세요.');
				}
			}
		})
	}
	
	function fnBmi2(){
		let weight = $('#weight').val();
		if(weight == '' || Number(weight) < 0 || isNaN(weight)) {
			alert('몸무게를 확인하세요.');
			return;
		}
		let height = $('#height').val();
		if(height == '' || Number(height) < 0 || isNaN(height)) {
			alert('키를 확인하세요.');
			return;
		}
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/second/bmi2',
			data: $('#frm').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData){                  // resData : {"bmi": 22, "obesity": "정상"}
				$('#bmi').text(resData['bmi']);          // resData.bmi     == resData['bmi']
				$('#obesity').text(resData['obesity']);  // resData.obesity == resData['obesity']
			},
			error: function(jqXHR){
				$('#bmi').text('');
				$('#obesity').text('');
				// alert(jqXHR.responseText);
				if(jqXHR.status == 400) {  // 400은 BAD REQUEST를 의미한다.
					alert('몸무게와 키는 0일 수 없습니다.');
				}
			}
		})
	}

</script>
</head>
<body>
	
	<div>
		<form id="frm">
			<div>
				<label for="weight">몸무게</label>
				<input id="weight" name="weight" placeholder="kg">
			</div>
			<div>
				<label for="height">키</label>
				<input id="height" name="height" placeholder="cm">
			</div>
			<div>
				<input type="button" value="BMI계산1" onclick="fnBmi1()">
				<input type="button" value="BMI계산2" onclick="fnBmi2()">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div>
		<h3>비만도</h3>
		<ul>
			<li>18.5미만 : 저체중</li>
			<li>24.9미만 : 정상</li>
			<li>29.9미만 : 과체중</li>
			<li>30 초과  : 비만</li>
		</ul>
		<div>체질량지수(BMI) : <span id="bmi"></span></div>
		<div>비만도 : <span id="obesity"></span></div>
	</div>
	
</body>
</html>