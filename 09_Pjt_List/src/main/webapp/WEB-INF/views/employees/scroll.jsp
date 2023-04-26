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
	
	// 전역 변수
	var page = 1;
	
	// DB에서 목록을 가져오는 함수
	function fnGetEmployees(){
		// 목록 숨기기
		$('.employees').hide();
		// 로딩 보여주기
		$('.loading_wrap').show();
		// 목록을 가져오는 ajax 처리
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/employee/scroll.do',
			data: 'page=' + page,  // page=1, page=2, page=3, ... 으로 동작
			// 응답
			dataType: 'json',
			success: function(resData){
				page++;  // 스크롤을 통해서 목록을 9개씩 가져올때마다 page가 증가해야 한다.
				// 화면에 뿌리기
				
				// 목록 보여주기
				$('.employees').show();
				// 로딩 숨기기
				$('.loading_wrap').hide();
			}
		})
	}
	
</script>
<style>
	div {
		box-sizing: border-box;
	}
	.employees {
		width: 1000px;
		margin: 0 auto;
		display: flex;
		flex-wrap: wrap;
	}
	.employee {
		width: 300px;
		height: 300px;
		border: 1px solid gray;
		margin: 10px;
		text-align: center;
		padding-top: 120px;
	}
	.loading_wrap {
		display: flex;            /* justify-content, align-items 속성 사용을 위해 설정 */
		justify-content: center;  /* class="loading"의 가로 가운데 정렬 */
		align-items: center;      /* class="loading"의 세로 가운데 정렬 */
		min-height: 80vh;         /* 최소 높이를 화면 높이의 80%로 설정 */
	}
	.loading {
		width: 300px;
		height: 300px;
		background-image: url('../resources/images/loading.gif');
		background-size: 300px 300px;
		background-repeat: no-repeat;
	}
</style>
</head>
<body>

	<div>
		<a href="${contextPath}/employees/search.form">사원 조회 화면으로 이동</a>
	</div>
	
	<h1>사원 목록</h1>
	
	<!-- 사원 목록 보여주는 곳 -->
	<div class="employees">
		
	</div>
	
	<!-- loading.gif 보여주는 곳 -->
	<div class="loading_wrap">
		<div class="loading"></div>
	</div>
	
</body>
</html>