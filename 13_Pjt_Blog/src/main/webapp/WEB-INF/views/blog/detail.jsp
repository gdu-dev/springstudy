<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="${blog.blogNo}번 블로그" />
</jsp:include>

<div>

  <!-- 블로그 구역 -->

  <h1>${blog.title}</h1>
  
  <div>
    <div>작성자 : ${blog.memberDTO.name}</div>
    <div>작성일 : ${blog.createdAt}</div>
    <div>수정일 : ${blog.modifiedAt}</div>
    <div>조회수 : ${blog.hit}</div>
  </div>
  
  <hr>
  
  <div>${blog.content}</div>
  
  <div>
    <form id="frmBtn" method="post">
      <input type="hidden" name="blogNo" value="${blog.blogNo}">
      <c:if test="${sessionScope.loginId eq blog.memberDTO.id}">
        <input type="button" value="편집" id="btnEdit">
        <input type="button" value="삭제" id="btnRemove">
      </c:if>
      <input type="button" value="목록" id="btnList">
    </form>
    <script>
      function fnEdit(){
        $('#btnEdit').on('click', function(){
      	  $('#frmBtn').attr('action', '${contextPath}/blog/edit.do');
      	  $('#frmBtn').submit();
        })    	
      }
      function fnRemove(){
    	  $('#btnRemove').on('click', function(){
    		  if(confirm('블로그를 삭제하면 모든 댓글이 함께 삭제됩니다. 삭제할까요?')){
    			  $('#frmBtn').attr('action', '${contextPath}/blog/remove.do');
    			  $('#frmBtn').submit();
    		  }
    	  })
      }
      function fnList(){
    	  $('#btnList').on('click', function(){
    		  location.href = '${contextPath}/blog/list.do';
    	  })
      }
      fnEdit();
      fnRemove();
      fnList();
    </script>
  </div>
  
  <hr>
  
  <!-- 댓글 구역 -->
  
  <div id="btnGood" style="width: 100px; border: 1px solid silver;">
    <span id="heart"></span>
    <span id="good">좋아요</span>
    <span id="goodCount"></span>
  </div>
  <script>
  
  </script>
  
  <div>
    <form id="frmAddComment">
      <input type="text" name="content" id="content" placeholder="댓글 작성해 주세요">
      <input type="hidden" name="blogNo" value="${blog.blogNo}">
      <input type="hidden" name="memberNo" value="${sessionScope.loginNo}">
      <input type="button" value="작성완료" id="btnAddComment">
    </form>
    <script>
      function fnLoginCheck(){
    	  $('#content').on('focus', function(){
    		  if('${sessionScope.loginId}' == ''){
    			  if(confirm('로그인이 필요한 기능입니다. 로그인할까요?')){
    				  location.href = '${contextPath}/index.do';
    			  }
    		  }
    	  })
      }
      function fnAddComment(){
    	  $('#btnAddComment').on('click', function(){
    		  if($('#content').val() == ''){
    			  alert('댓글 내용을 입력하세요.');
    			  return;
    		  }
    		  $.ajax({
    			  type: 'post',
    			  url: '${contextPath}/comment/addComment.do',
    			  data: $('#frmAddComment').serialize(),
    			  dataType: 'json',
    			  success: function(resData){  // resData = {"isAdd": true}
    				  if(resData.isAdd){
    					  alert('댓글이 등록되었습니다.');
    					  $('#content').val('');
    					  fnCommentList();  // 댓글 목록을 가져와서 화면에 만드는 함수
    				  }
    			  }
    		  })
    	  })
      }
      
      // 전역 변수
      var page = 1;
      
      function fnCommentList(){
    	  $.ajax({
    		  type: 'get',
    		  url: '${contextPath}/comment/list.do',
    		  data: 'blogNo=${blog.blogNo}&page=' + page,
    		  dataType: 'json',
    		  success: function(resData){  // resData = { "commentList": [{}, {}, ...], "pageUtil": {beginPage: 1, endPage: 5, ...} }
    			  $('#commentList').empty();
    			  $.each(resData.commentList, function(i, comment){
    				  var str = '<div>';
    				  str += '<span>' + comment.memberDTO.name;
    				  str += '<span>' + comment.content;
    				  $('#commentList').append(str);
    			  })
    		  }
    	  })
      }
      
      fnLoginCheck();
      fnAddComment();
      fnCommentList();
    </script>
  </div>
  
  <div>
    <div id="commentList"></div>
    <div id="pagination"></div>
  </div>
  <script>
  
  </script>

</div>

</body>
</html>