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
				// 요청
				type: 'get',
				url: '${contextPath}/papago.do',
				data: 'source=' + $('#source').val() + '&target=' + $('#target').val() + '&text=' + $('#text').val(),
				// 응답
				dataType: 'json',
				success: function(resData){
					$('#translatedText').text(resData.message.result.translatedText);
				},
				error: function(jqXHR){
					if(jqXHR.status == 503){  // HttpStatus.SERVICE_UNAVAILABLE는 503이다.
						alert('파파고 서비스 사용이 불가합니다. 입력 정보를 확인하세요.');
					}
				}
			})
		})
	</script>
	
	<hr>
	
	<h1>주말에 풀어보기</h1>
	<div>
		<form id="frm_search">
			<div>
				<label for="display">검색결과건수</label>
				<select name="display" id="display">
					<option value="10" selected>10</option>
					<option value="20">20</option>
					<option value="40">40</option>
					<option value="60">60</option>
					<option value="80">80</option>
					<option value="100">100</option>
				</select>
			</div>
			<div>			
				<input type="radio" name="sort" id="sim" value="sim" checked><label for="sim">유사도순</label>
				<input type="radio" name="sort" id="date" value="date"><label for="date">날짜순</label>
				<input type="radio" name="sort" id="asc" value="asc"><label for="asc">낮은가격순</label>
				<input type="radio" name="sort" id="dsc" value="dsc"><label for="dsc">높은가격순</label>
			</div>
			<div>
				<label for="query">검색어 입력</label>
				<input type="text" name="query" id="query">	
				<input type="button" value="검색" onclick="fnSearch()">		
			</div>
		</form>
	</div>
	<hr>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>상품명</td>
					<td>썸네일</td>
					<td>최저가</td>
					<td>판매처</td>
				</tr>
			</thead>
			<tbody id="products"></tbody>
		</table>
	</div>
	<script>
		function fnSearch(){
			$.ajax({
				// 요청
				type: 'get',
				url: '${contextPath}/search.do',
				data: $('#frm_search').serialize(),
				// 응답
				dataType: 'json',
				success: function(resData){
					$('#products').empty();
					$.each(resData.items, function(i, product){
						var tr = '<tr>';
						tr += '<td><a href="' + product.link + '">' + product.title + '</a></td>'; 
						tr += '<td><a href="' + product.link + '"><img width="200px" src="' + product.image + '" alt="' + product.title + '"></a></td>';
						tr += '<td>' + product.lprice + '</td>';
						tr += '<td>' + product.mallName + '</td>';
						tr += '</tr>';
						$('#products').append(tr);
					})
				},
				error: function(jqXHR){
					alert(jqXHR.responseText);
				}
			})
		}
	</script>

</body>
</html>