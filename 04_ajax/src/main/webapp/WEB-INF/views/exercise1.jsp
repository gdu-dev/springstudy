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
</head>
<body>

  <div>
    <button type="button" id="btn-list">목록갱신</button>
  </div>

  <hr>
  
  <div id="board-list"></div>

  <script>
  
    const fnBoardList = ()=>{
    	$('#btn-list').on('click', (evt)=>{
    		$.ajax({
    			/* 요청 */
    			type: 'GET',
    			url: '${contextPath}/ajax1/list.do',
    			/* 응답 */
    			dataType: 'json',
    			success: (resData)=>{
    				console.log(resData);
    			}
    		})
    	})
    }
    
    fnBoardList();
  
  </script>

</body>
</html>