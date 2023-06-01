<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="${blog.blogNo}번 블로그" />
</jsp:include>

<style>
  .blind {
    display: none;
  }
  .invisible {
    visibility: hidden;
  }
  .enable_link {
    cursor: pointer;
  }
  .pagination {
    margin: 0 5px;
  }
</style>

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
    <span id="blogGoodCount"></span>
  </div>
  <script>
  
  </script>
  
  <div>
    <form id="frmAddComment">
      <input type="text"   name="content" id="content" placeholder="댓글 작성해 주세요">
      <input type="hidden" name="blogNo" value="${blog.blogNo}">
      <input type="hidden" name="memberNo" value="${sessionScope.loginNo}">
      <input type="button" value="작성완료" id="btnAddComment">
    </form>
    <script>
      // 내가 예전에 "좋아요"를 누른 게시글인지 체크하는 함수
      function fnGoodCheckState(){
    	  $.ajax({
    		  type: 'get',
    		  url: '${contextPath}/good/getGoodCheckState.do',
    		  data: 'blogNo=${blog.blogNo}',
    		  dataType: 'json',
    		  success: function(resData){         // resData = {"userGoodCount": 0}
    			  if(resData.userGoodCount == 0){   // "좋아요"를 안 누른 게시글이면 하얀하트(heart1.png) 표시, 아니면 빨간하트(heart2.png) 표시
    				  $('#heart').html('<img src="${contextPath}/resources/images/heart1.png" width="15px">');
    				  $('#good').removeClass("goodChecked");
    			  } else {
    				  $('#heart').html('<img src="${contextPath}/resources/images/heart2.png" width="15px">');
    				  $('#good').addClass("goodChecked");
    			  }
    		  }
    	  })
      }
      // 게시글의 "좋아요" 개수를 표시하는 함수
      function fnGoodCount(){
    	  $.ajax({
    		  type: 'get',
    		  url: '${contextPath}/good/getGoodCount.do',
    		  data: 'blogNo=${blog.blogNo}',
    		  dataType: 'json',
    		  success: function(resData){  // resData = {"blogGoodCount": 10}
    			  $('#blogGoodCount').empty();
    			  $('#blogGoodCount').html(resData.blogGoodCount + '개');
    		  }
    	  })
      }
      // "좋아요"를 눌렀을 때 동작하는 함수
      function fnGoodPress(){
    	  $('#btnGood').on('click', function(){
    		  // 로그인을 해야 "좋아요"를 누를 수 있다.
    		  if('${sessionScope.loginId}' == ''){
      		  if(confirm('해당 기능은 로그인이 필요한 기능입니다. 로그인할까요?')){
              location.href = '${contextPath}/index.do';
            }
    		  }
    		  // 셀프 "좋아요" 방지
    		  if('${sessionScope.loginId}' == '${blog.memberDTO.id}'){
    			  alert('본인의 게시글에는 "좋아요"를 누를 수 없습니다.');
    			  return;
    		  }
      		// "좋아요" 선택/해제 상태에 따른 아이콘 변경
          $('#good').toggleClass("goodChecked");
          if ($('#good').hasClass("goodChecked")) {
            $('#heart').html('<img src="${contextPath}/resources/images/heart2.png" width="15px">');
          } else {
            $('#heart').html('<img src="${contextPath}/resources/images/heart1.png" width="15px">');              
          }
          // "좋아요" DB 처리
          $.ajax({
            type: 'get',
            url: '${contextPath}/good/mark.do',
            data: 'blogNo=${blog.blogNo}',
            dataType: 'json',
            success: function(resData){  // resData = {"isSuccess", true}
              if(resData.isSuccess) {
            	  fnGoodCount();             
              }
            }
          });
    	  })
      }
      
      function fnLoginCheck(){
    	  $('#content').on('focus', function(){
    		  if('${sessionScope.loginId}' == ''){
    			  if(confirm('해당 기능은 로그인이 필요한 기능입니다. 로그인할까요?')){
    				  $(this).blur();  // 포커스 없애기
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
    			  /******************* 댓글 목록 만들기 *******************/
    			  $('#commentList').empty();
    			  $.each(resData.commentList, function(i, comment){
    				  var str = '<div>';
    				  if(comment.state == -1){
    					  if(comment.depth == 0){    						  
      					  str += '<span>삭제된 댓글입니다.';
    					  } else {
    						  str += '<span style="margin-left: 30px;">삭제된 답글입니다.';
    					  }
    				  } else {
    					  if(comment.depth == 0){
    						  str += '<span>';
    					  } else {
    						  str += '<span style="margin-left: 30px;">';
    					  }
      				  str += comment.memberDTO.name;
      				  str += ' - ' + comment.content;
      				  if('${sessionScope.loginId}' != ''){
      					  if('${sessionScope.loginId}' == comment.memberDTO.id && comment.state == 1){
      						  str += '<input type="button" value="삭제" class="btnCommentRemove" data-comment_no="' + comment.commentNo + '">';
      					  } else if('${sessionScope.loginId}' != comment.memberDTO.id && comment.depth == 0){
      						  str += '<input type="button" value="답글" class="btnOpenReply">';
      					  }
      				  }
      				  str += '<div class="replyArea blind">';
      				  /******************* 답글달 때 전송할 데이터는 4개(content, blogNo, groupNo, memberNo) *******************/
      				  str += '  <form class="frmReply">';
      				  str += '    <input type="text"   name="content"  class="replyContent" placeholder="답글을 작성해 주세요">';
      				  str += '    <input type="hidden" name="blogNo"   value="' + comment.blogNo + '">';
      				  str += '    <input type="hidden" name="groupNo"  value="' + comment.groupNo + '">';
      				  str += '    <input type="hidden" name="memberNo" value="${sessionScope.loginNo}">';  // 수업 때 잘못 구현한 부분
      				  str += '    <input type="button" value="답글작성완료" class="btnAddReply">';
      				  str += '  </form>';
      				  /*********************************************************************************************************/
      				  str += '</div>';
    				  }
    				  $('#commentList').append(str);
    			  })
    			  /******************* pagination 만들기 *******************/
    			  /* 모든 페이지 링크에는 data-page 속성에 이동할 페이지 번호를 저장해 둔다. */
    			  /* 모든 페이지 링크를 클릭하면 data-page 속성에 저장된 페이지 번호를 전역변수 page에 저장한 뒤 해당 page 값을 이용해 새로운 목록을 요청한다. */
    			  $('#pagination').empty();
    			  var pageUtil = resData.pageUtil;
    			  var str2 = '<div>';
    			  // 이전 블록
    			  if(pageUtil.beginPage == 1){
    				  str2 += '<span class="invisible">◀</span>';
    			  } else {
    				  str2 += '<span class="enable_link pagination" data-page="' + (pageUtil.beginPage - 1) + '">◀</span>';
    			  }
    			  // 페이지 번호
    			  for(let p = pageUtil.beginPage; p <= pageUtil.endPage; p++){
    				  if(p == page){
    					  str2 += '<strong class="pagination">' + p + '</strong>';
    				  } else {
    					  str2 += '<span class="enable_link pagination" data-page="' + p + '">' + p + '</span>';
    				  }
    			  }
    			  // 다음 블록
    			  if(pageUtil.endPage == pageUtil.totalPage){
    				  str2 += '<span class="invisible">▶</span>';
    				} else {
    					str2 += '<span class="enable_link pagination" data-page="' + (pageUtil.endPage + 1) + '">▶</span>';
    				}
    			  str2 += '</div>';
    			  $('#pagination').append(str2);
    		  }  // success
    	  })  // ajax
      }
      
      function fnChangePage(){
    	  $(document).on('click', '.enable_link', function(){
    		  page = $(this).data('page');
    		  fnCommentList();
    	  })
      }
      
      function fnToggleReplyArea(){
    	  $(document).on('click', '.btnOpenReply', function(){
    		  $(this).next().toggleClass('blind');
    	  })
      }
      
      function fnAddReply(){
    	  $(document).on('click', '.btnAddReply', function(){
    		  if($(this).prevAll('.replyContent').val() == ''){  // $(this).prevAll('.replyContent') : 클릭한 .btnAddReply 버튼의 이전 요소 중에서 class="replyContent"인 요소 (답글을 작성하는 input 요소에 class="replyContent"가 추가되어 있음)
    			  alert('답글 내용을 입력하세요.');
    			  return;
    		  }
    		  $.ajax({
    			  type: 'post',
    			  url: '${contextPath}/comment/addReply.do',
    			  data: $(this).parent('.frmReply').serialize(),  // 클릭한 .btnAddReply 버튼의 부모 <form class="frmReply">을 의미한다.
    			  dataType: 'json',
    			  success: function(resData){  // resData = {"isAdd": true}
    				  if(resData.isAdd){
    					  alert('답글이 등록되었습니다.');
    					  fnCommentList();
    				  }
    			  }
    		  })
    	  })
      }
      
      // 좋아요
      fnGoodCheckState();
      fnGoodCount();
      fnGoodPress();
      
      // 댓글
      fnLoginCheck();
      fnAddComment();
      fnCommentList();
      fnChangePage();
      
      // 답글
      fnToggleReplyArea();
      fnAddReply();
      
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