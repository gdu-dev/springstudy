<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
  .board-wrap {
    width: 320px;
    margin: 10px auto;
    display: flex;
    flex-wrap: wrap; 
  }
  .board {
    width: 150px;
    height: 150px;
    border: 1px solid gray;
    cursor: pointer;
    margin: 0 5px 5px 0;
  }
  .board:hover {
    background-color: beige;
  }
</style>
</head>
<body>

  <div>
    <button type="button" onclick="fnBoardList()">목록갱신</button>
  </div>

  <hr>
  
  <div id="board-list"></div>

  <script>
  
    const fnBoardList = ()=>{
      $.ajax({
        /* 요청 */
        type: 'GET',
        url: '${contextPath}/ajax2/list.do',
        /* 응답 */
        dataType: 'json'
      }).done((resData)=>{
        const boardList = $('#board-list');
        boardList.empty();
        let result = '<div class="board-wrap">';
        $.each(resData, (i, board)=>{
          result += '<div class="board" data-board-no="' + board.boardNo + '"><div>' + board.boardNo + '</div><div>' + board.title + '</div><div>' + board.contents + '</div></div>';
        })
        result += '</div>';
        boardList.append(result);
      })
    }
  
  </script>
  
  <script>
    
    $(document).on('click', '.board', (evt)=>{
      const boardNo = evt.currentTarget.dataset.boardNo;  // $(evt.currentTarget).data('boardNo')
      $.ajax({
        /* 요청 */
        type: 'GET',
        url: '${contextPath}/ajax2/detail.do',
        data: 'boardNo=' + boardNo,
        /* 응답 */
        dataType: 'json'
      }).done((resData)=>{
        alert(resData.boardNo + ', ' + resData.title + ', ' + resData.contents);
        // 모달창
      })
    })
      
  </script>

</body>
</html>