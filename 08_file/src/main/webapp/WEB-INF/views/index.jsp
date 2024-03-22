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
        <input type="text" name="writer" placeholder="작성자">
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
  
    <div>
      <input type="file" id="input-files" class="files" multiple>
    </div>
    <div>
      <input type="text" id="input-writer" placeholder="작성자">
    </div>
    <div>
      <button type="button" id="btn-upload">전송</button>
    </div>
  
  </div>
  
  <script type="text/javascript">
  
    const fnFileCheck = ()=>{
    	$('.files').on('change', (evt)=>{
    		const limitPerSize = 1024 * 1024 * 10;
    		const limitTotalSize = 1024 * 1024 * 100;
    		let totalSize = 0;
    		const files = evt.target.files;
    		const fileList = document.getElementById('file-list');
    		fileList.innerHTML = '';
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
    
    const fnAfterInsertCheck = ()=>{
    	const insertCount = '${insertCount}';
    	if(insertCount !== ''){
    		if(insertCount === '1'){
    			alert('저장되었습니다.');
    		} else {
    			alert('저장실패했습니다.');
    		}
    	}
    }
    
    const fnAsyncUpload = ()=>{
    	const inputFiles = document.getElementById('input-files');
    	const inputWriter = document.getElementById('input-writer');
    	let formData = new FormData();
    	for(let i = 0; i < inputFiles.files.length; i++){
    		formData.append('files', inputFiles.files[i]);
    	}
    	formData.append('writer', inputWriter);
    	fetch('${contextPath}/upload2.do', {
    		method: 'POST',
    		body: formData
    	}).then(response=>response.json())
    	  .then(resData=>{  /* resData = {"success": 1} 또는 {"success": 0} */
    		  if(resData.success === 1){
    			  alert('저장되었습니다.');
    		  } else {
    			  alert('저장실패했습니다.');
    		  }
    	  })
    }
    
    const fnAsyncUpload2 = ()=>{
      const inputFiles = document.getElementById('input-files');
      const inputWriter = document.getElementById('input-writer');
      let formData = new FormData();
      for(let i = 0; i < inputFiles.files.length; i++){
        formData.append('files', inputFiles.files[i]);
      }
      formData.append('writer', inputWriter);
      $.ajax({
    	  type: 'POST',
    	  url: '${contextPath}/upload2.do',
    	  contentType: false,  // content-type header를 생성하지 않도록 설정
    	  data: formData,      // FormData 객체를 서버로 전달
    	  processData: false,  // 전달되는 데이터가 객체인 경우 객체를 {property: value} 형식의 문자열로 자동으로 변환해서 전달하는데 이를 방지하는 옵션
    	  dataType: 'json'
      }).done(resData=>{
        if(resData.success === 1){
          alert('저장되었습니다.');
        } else {
          alert('저장실패했습니다.');
        }
      })
    }
    
    fnFileCheck();
    fnAfterInsertCheck();
    document.getElementById('btn-upload').addEventListener('click', fnAsyncUpload2);
  
  </script>
  
</body>
</html>