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

	$(function(){
		
		// 원글 달기 결과 메시지
		if('${addResult}' != ''){
			if('${addResult}' == '1') {
				alert('원글이 달렸습니다.');
			} else {
				alert('원글 달기가 실패했습니다.');
			}
		}
		
		// 게시글 삭제 결과 메시지
		if('${removeResult}' != ''){
			if('${removeResult}' == '1') {
				alert('게시글이 삭제되었습니다.');
			} else {
				alert('게시글 삭제가 실패했습니다.');
			}
		}
		
		// 답글 달기 결과 메시지
		if('${addReplyResult}' != ''){
			if('${addReplyResult}' == '1') {
				alert('답글이 달렸습니다.');
			} else {
				alert('답글 달기가 실패했습니다.');
			}
		}
		
		// 삭제 버튼 이벤트
		$('.frm_remove').on('submit', function(event){
			if(confirm('BBS를 삭제할까요?') == false){
				event.preventDefault();
				return;
			}
		})
		
		// 답글 작성 화면 표시/숨기기
		$('.btn_reply').on('click', function(){

			// 작성화면
			let write = $(this).closest('.list').next();  // write는 jQuery객체이다. (jQuery wrapper가 필요 없다.)

			// 작성화면이 blind를 가지고 있다 = 다른 작성화면이 열려 있다
			if(write.hasClass('blind')){
				
				$('.write').addClass('blind');  // 모든 작성화면을 닫자
				write.removeClass('blind');     // 현재 작성화면을 열자

			// 작성화면이 blind를 가지고 있지 않다 = 현재 작성화면이 열려 있다
			} else {
				write.addClass('blind');        // 현재 작성화면을 닫자
			}
			
		})
		
	})

</script>
<style>
	.blind {
		display: none;
	}
</style>
</head>
<body>

	<div>
		<a href="${contextPath}/bbs/write.do">BBS작성하러가기</a>
	</div>
	
	<hr>
	
	<div>
		<div>${pagination}</div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>IP</td>
					<td>작성일자</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bbsList}" var="bbs" varStatus="vs">
					<c:if test="${bbs.state == 1}">
						<!-- 게시글 내용 -->
						<tr class="list">
							<td>${beginNo - vs.index}</td>
							<td>${bbs.writer}</td>
							<td>
								<!-- DEPTH에 의한 들여쓰기 -->
								<c:forEach begin="1" end="${bbs.depth}" step="1">&nbsp;&nbsp;&nbsp;</c:forEach>
								<!-- 답글은 [Re] 표시하기 -->
								<c:if test="${bbs.depth > 0}">[Re]</c:if>
								<!-- 제목 -->
								${bbs.title}
								<!-- 답글작성하기 버튼 -->
								<input type="button" value="답글" class="btn_reply">
							</td>
							<td>${bbs.ip}</td>
							<td>${bbs.createdAt}</td>
							<td>
								<form class="frm_remove" method="post" action="${contextPath}/bbs/remove.do">
									<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
									<button>삭제</button>
								</form>
							</td>
						</tr>
						<!-- 답글 작성 화면 -->
						<tr class="write blind">
							<td colspan="6">
								<form method="post" action="${contextPath}/bbs/reply/add.do">
									<div>
										<label for="writer">작성자</label>
										<input id="writer" name="writer" required="required">
									</div>
									<div>
										<label for="title">제목</label>
										<input id="title" name="title" required="required">
									</div>
									<div>
										<button>답글달기</button>
										<!-- 원글의 depth, groupNo, groupOrder를 함께 보낸다. -->
										<input type="hidden" name="depth" value="${bbs.depth}">
										<input type="hidden" name="groupNo" value="${bbs.groupNo}">
										<input type="hidden" name="groupOrder" value="${bbs.groupOrder}">
									</div>
								</form>
							</td>
						</tr>
					</c:if>
					<c:if test="${bbs.state == 0}">
						<tr>
							<td>${beginNo - vs.index}</td>
							<td colspan="5">삭제된 게시글입니다.</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>


