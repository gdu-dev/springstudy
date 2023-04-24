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
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<script>
	
	$(function(){
		fnChkAll();
		fnChkOne();
		fnRemoveList();
	})
	
	// 전체선택 체크박스
	function fnChkAll(){
		$('#chk_all').on('click', function(){
			$('.chk_one').prop('checked', $(this).prop('checked'));
		})
	}
	
	// 개별선택 체크박스
	function fnChkOne(){
		let chkOne = $('.chk_one');  // 모든 개별선택
		chkOne.on('click', function(){
			let chkCnt = 0;
			for(let i = 0; i < chkOne.length; i++) {
				chkCnt += $(chkOne[i]).prop('checked');
			}
			$('#chk_all').prop('checked', chkCnt == chkOne.length);
		})
	}
	
	// 선택 항목 삭제
	function fnRemoveList(){
		$('#frm_remove_list').on('submit', function(event){
			if(confirm('선택한 게시글을 모두 삭제할까요?') == false){
				event.preventDefault();
				return;
			}
			if($('.chk_one:checked').length == 0){
				alert('선택된 게시글이 없습니다.');
				event.preventDefault();
				return;
			}
		})
	}
	
</script>
</head>
<body>

	<div>
		<a href="${contextPath}/board/write.do">새글작성하기</a>
	</div>
	
	<div>
		<form id="frm_remove_list" action="${contextPath}/board/removeList.do" method="post">
			<div>
				<button>선택삭제</button>
			</div>
			<table border="1">
				<thead>
					<tr>
						<td>
							<label for="chk_all" id="lbl_chk_all">전체선택</label>
							<input type="checkbox" id="chk_all">
						</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일자</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty boardList}">
						<tr>
							<td colspan="4">첫 게시글의 주인공이 되어 보세요!</td>
						</tr>
					</c:if>
					<c:if test="${not empty boardList}">					
						<c:forEach items="${boardList}" var="b">
							<tr>
								<td><input type="checkbox" class="chk_one" name="boardNoList" value="${b.boardNo}"></td>
								<td><a href="${contextPath}/board/detail.do?boardNo=${b.boardNo}">${b.title}</a></td>
								<td>${b.writer}</td>
								<td>${b.createdAt}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
</html>