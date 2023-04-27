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
		
		// 삭제 버튼 이벤트
		$('.frm_remove').on('submit', function(event){
			if(confirm('BBS를 삭제할까요?') == false){
				event.preventDefault();
				return;
			}
		})
		
	})

</script>
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
						<tr>
							<td>${beginNo - vs.index}</td>
							<td>${bbs.writer}</td>
							<td>${bbs.title}</td>
							<td>${bbs.ip}</td>
							<td>${bbs.createdAt}</td>
							<td>
								<form class="frm_remove" method="post" action="${contextPath}/bbs/remove.do">
									<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
									<button>삭제</button>
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


