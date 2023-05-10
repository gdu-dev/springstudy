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
	function fnList(){
		location.href = '${contextPath}/upload/list.do';
	}
	function fnFileCheck(vThis){

		// 최대 크기 10MB
		let maxSize = 1024 * 1024 * 10;
		
		// 첨부된 파일 목록
		let files = vThis.files;
		
		// 첨부된 파일 순회(크기 체크 + 첨부된 파일명 표시)
		$('#fileList').empty();
		$.each(files, function(i, file){
			
			// 크기 체크
			if(file.size > maxSize){
				alert('각 첨부파일의 최대 크기는 10MB입니다.');
				$(vThis).val('');  // 첨부된 파일을 모두 지운다.
				$('#fileList').text('첨부 파일의 목록이 이 곳에 표시됩니다');
				return;
			}
			
			// 첨부된 파일명 표시
			$('#fileList').append('<div>' + file.name + '</div>');
			
		})
		
	}
	function fnRemoveAttach(attachNo){
		if(confirm('이 첨부 파일을 삭제하시겠습니까?') == false){
			return;
		}
		location.href = '${contextPath}/upload/removeAttach.do?uploadNo=${upload.uploadNo}&attachNo=' + attachNo;
	}
</script>
</head>
<body>

	<div>
		<h1>UPLOAD 게시글 편집하기</h1>
		<form method="post" enctype="multipart/form-data" action="${contextPath}/upload/modify.do">
			<div>
				<label for="uploadTitle">제목</label>
				<input type="text" id="uploadTitle" name="uploadTitle" value="${upload.uploadTitle}">
			</div>
			<div>
				<label for="uploadContent">내용</label><br>
				<textarea id="uploadContent" name="uploadContent" rows="5" cols="30">${upload.uploadContent}</textarea>
			</div>
			<div>
				<label for="files">첨부 추가</label>
				<input type="file" id="files" name="files" multiple="multiple" onchange="fnFileCheck(this)">
				<div id="fileList">첨부 파일의 목록이 이 곳에 표시됩니다</div>
			</div>
			<hr>
			<div>
				<input type="hidden" name="uploadNo" value="${upload.uploadNo}">
				<button>편집완료</button>
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div>
		<h4>현재 첨부 목록</h4>
		<c:if test="${empty attachList}">
			<div>첨부된 파일이 없습니다.</div>
		</c:if>
		<c:if test="${not empty attachList}">		
			<div>
				<c:forEach items="${attachList}" var="attach">
					<div>
						<c:if test="${attach.hasThumbnail == 1}">
							<img src="${contextPath}/upload/display.do?attachNo=${attach.attachNo}">
						</c:if>
						<c:if test="${attach.hasThumbnail == 0}">
							<img src="${contextPath}/resources/images/attach1.png" width="50px">
						</c:if>
						${attach.originName}
						<input type="button" value="삭제" onclick="fnRemoveAttach(${attach.attachNo})">
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
	
</body>
</html>