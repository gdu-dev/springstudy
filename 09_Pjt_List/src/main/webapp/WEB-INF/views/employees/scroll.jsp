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
	var page = 1;       // 현재 페이지 번호
	var totalPage = 0;  // 전체 페이지 개수
	var timerId;        // setTimeout() 타이머 함수의 반환값
	
	// DB에서 목록을 가져오는 함수
	function fnGetEmployees(){
		// 목록을 가져오는 ajax 처리
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/employees/scroll.do',
			data: 'page=' + page,  // page=1, page=2, page=3, ... 으로 동작
			// 응답
			dataType: 'json',
			success: function(resData){  // resData = {"employees": [{}, {}, {}, ...], "totalPage": 12}
				// 전체 페이지 개수를 전역 변수 totalPage에 저장
				totalPage = resData.totalPage;
				// 스크롤을 통해서 목록을 9개씩 가져올때마다 page가 증가해야 한다.	
				page++;
				// 화면에 뿌리기
				// $.each(배열, function(인덱스, 요소){})
				$.each(resData.employees, function(i, employee){
					let str = '<div class="employee">';
					str += '<div>사원번호: ' + employee.employeeId + '</div>';
					str += '<div>사원명: ' + employee.firstName + ' ' + employee.lastName + '</div>';
					str += '<div>부서명: ' + employee.deptDTO.departmentName + '</div>';
					str += '</div>';
					$('.employees').append(str);
				})
			}
		})
	}  // fnGetEmployees
	
	// 함수 호출(스크롤 이벤트 이전 첫 목록을 가져온다.)
	fnGetEmployees();
	
	// 스크롤 이벤트
	$(window).on('scroll', function(){

		if(timerId) {
			clearTimeout(timerId);  // setTimeout()이 동작했다면(목록을 가져왔다면) setTimeout()의 재동작을 취소한다. (동일 목록을 가져오는 것을 방지한다.)
		}
		
		// timerId = setTimeout(function(){}, 200);  // 200밀리초(0.2초) 후에 function()을 수행한다.
		timerId = setTimeout(function(){			
			let scrollTop = $(this).scrollTop();        // 스크롤 된 길이
			let windowHeight = $(this).height();        // 화면 높이(브라우저의 크기)
			let documentHeight = $(document).height();  // 문서 높이
			if((scrollTop + windowHeight + 50) >= documentHeight) {  // 스크롤이 바닥에 닿기 전 50px 정도 위치(스크롤이 충분히 바닥까지 내려왔음)
				if(page > totalPage){  // 마지막 페이지를 보여 준 상태에서는 스크롤이 이동해도 더 이상 요청하지 않는다.					
					// 최종 로딩 숨기기
					$('.loading_wrap').addClass('blind');
					return;
				}
				fnGetEmployees();
			}
		}, 200);  // 시간 결정을 각자 알아서 임의로 조정해도 된다.
		
	})
	
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
	.blind {  /* 반드시 .loading_wrap 이후에 작성 */
		/* display: none; */
		visibility: hidden;
	}
</style>
</head>
<body>

	<div>
		<a href="${contextPath}/employees/search.do">사원 조회 화면으로 이동</a>
	</div>
	
	<h1>사원 목록</h1>
	
	<!-- 사원 목록 보여주는 곳 -->
	<div class="employees"></div>
	
	<!-- loading.gif 보여주는 곳 -->
	<div class="loading_wrap">
		<div class="loading"></div>
	</div>
	
</body>
</html>