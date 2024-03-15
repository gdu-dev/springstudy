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
    <button type="button" onclick="fnBoardList()">목록갱신</button>
  </div>

  <hr>
  
  <div id="board-list"></div>

  <script>

    const fnBoardList = ()=>{
    	
      const options = {
        method: 'GET',
        cache: 'no-cache'
      }
      
      // const myPromise = fetch('${contextPath}/ajax3/list.do', options);
      // myPromise.then()
      
      fetch('${contextPath}/ajax3/list.do', options)
        .then(response=>response.json())
        .then(resData=>{
        	console.log(resData);
        })
      
    }
  
  </script>

</body>
</html>