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
    <form action="${contextPath}/upload1.do"
          method="post"
          enctype="multipart/form-data">
      <div>
        <input type="file" name="files" class="files" accept="image/*" multiple>
      </div>
      <div>
        <button type="submit">전송</button>
      </div>
    </form>
  </div>
  
  <h3>첨부 파일 목록</h3>
  <div id="file-list"></div>
  
  <hr>
  
  <div>
  
  </div>
  
  <script type="text/javascript">
  
    const fnFileCheck = ()=>{
    	$('.files').on('change', (evt)=>{
    		const limitPerSize = 1024 * 1024 * 10;
    		const limitTotalSize = 1024 * 1024 * 100;
    		let totalSize = 0;
    		const files = evt.target.files;
    		const fileList = document.getElementById('file-list');
    		for(let i = 0; i < files.length; i++){
    			if(files[i].size > limitPerSize){
    				alert('각 첨부 파일의 최대 크기는 10MB입니다.');
    				evt.target.value = '';
    				fileList.innerHTML = '';
    				return;
    			}
    			totalSize += files[i].size;
    			if(totalSize > limitTotalSize){
    				alert('전체 첨부 파일의 최대 크기는 100MB입니다.');
    				evt.target.value = '';
    				fileList.innerHTML = '';
    				return;
    			}
    			fileList.innerHTML += '<div>' + files[i].name + '</div>';
    		}
    	})
    }
    
    fnFileCheck();
  
  </script>
  
</body>
</html>