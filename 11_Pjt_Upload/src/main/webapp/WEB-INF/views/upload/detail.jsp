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
	var frm;
	$(function(){
		frm = $('#frm');
	})
	function fnEditUpload(){
		frm.prop('action', '${contextPath}/upload/editUpload.do');
		frm.submit();
	}
	function fnRemoveUpload(){
		if(confirm('게시글을 삭제하면 모든 첨부 파일이 함께 삭제됩니다. 그래도 삭제하시겠습니까?') == false){
			return;
		}
		frm.prop('action', '${contextPath}/upload/removeUpload.do');
		frm.submit();
	}
	let modifyResult = '${modifyResult}';
	if(modifyResult != ''){
		if(modifyResult == '1'){
			alert('UPLOAD 게시글 수정 성공');
		} else {
			alert('UPLOAD 게시글 수정 실패');
		}
	}
</script>
</head>
<body>

	<div>
		<h1>${upload.uploadNo}번 UPLOAD 게시글</h1>
		<ul>
			<li>제목 : ${upload.uploadTitle}</li>
			<li>내용 : ${upload.uploadContent}</li>
			<li>작성일자 : ${upload.createdAt}</li>
			<li>수정일자 : ${upload.modifiedAt}</li>
		</ul>
		<form id="frm" method="post">
			<input type="hidden" name="uploadNo" value="${upload.uploadNo}">
			<input type="button" value="편집" onclick="fnEditUpload()">
			<input type="button" value="삭제" onclick="fnRemoveUpload()">
		</form>
	</div>
	
	<hr>
	
	<div>
		<h4>첨부 목록 및 다운로드</h4>
		<c:if test="${empty attachList}">
			<div>첨부된 파일이 없습니다.</div>
		</c:if>
		<c:if test="${not empty attachList}">		
			<div>
				<c:forEach items="${attachList}" var="attach">
					<div>
						<a href="${contextPath}/upload/download.do?attachNo=${attach.attachNo}">
							<c:if test="${attach.hasThumbnail == 1}">
								<img src="${contextPath}/upload/display.do?attachNo=${attach.attachNo}">
							</c:if>
							<c:if test="${attach.hasThumbnail == 0}">
								<img src="${contextPath}/resources/images/attach1.png" width="50px">
							</c:if>
							${attach.originName}
						</a>
					</div>
				</c:forEach>
				<div>
					<a href="${contextPath}/upload/downloadAll.do?uploadNo=${upload.uploadNo}">모두 다운로드</a>
				</div>
			</div>
		</c:if>
	</div>
	
</body>
</html>