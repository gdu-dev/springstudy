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
<style>
	.papago {
		display: flex;
		justify-content: space-between;
		width: 1380px;
		margin: 0 auto;
	}
	.source_area, .target_area {
		width: 640px;
	}
	.btn_area {
		width: 50px;
		line-height: 320px;
		text-align: center;
	}
	#text, #translatedText {
		width: 100%;
		height: 300px;
		border: 1px solid gray;
		outline: 0;
		font-size: 24px;
	}
	#text:focus, #translatedText:focus {
		border: 1px solid skyblue;
	}
</style>
</head>
<body>
	
	<div class="papago">
		<div class="source_area">
			<div>
				<select id="source">
					<option value="ko">한국어</option>
					<option value="en">영어</option>
					<option value="ja">일본어</option>
				</select>
			</div>
			<div>
				<textarea id="text"></textarea>
			</div>
		</div>
		<div class="btn_area">
			<input type="button" id="btn_translate" value="번역">
		</div>
		<div class="target_area">
			<div>
				<select id="target">
					<option value="ko">한국어</option>
					<option value="en">영어</option>
					<option value="ja">일본어</option>
				</select>
			</div>
			<div>
				<textarea id="translatedText"></textarea>
			</div>
		</div>
	</div>
	
	<script>
		$('#btn_translate').on('click', function(){
			if($('#text').val() == ''){
				alert('번역할 텍스트를 입력하세요');
				$('#text').focus();
				return;
			}
			$.ajax({
				type: 'get',
				url: '/01_Servlet/PapagoServlet',
				data: 'source=' + $('#source').val() + '&target=' + $('#target').val() + '&text=' + $('#text').val(),
				dataType: 'json',
				success: (resData)=>{
					$('#translatedText').text(resData.message.result.translatedText);
				}
			})
		})
	</script>

</body>
</html>