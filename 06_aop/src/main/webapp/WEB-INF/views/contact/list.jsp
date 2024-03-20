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
    <a href="${contextPath}/contact/write.do">작성화면열기</a>
  </div>
  
  <hr>
  
  <div>
    <table border="1">
      <thead>
        <tr>
          <td>순번</td>
          <td>이름</td>
          <td>연락처</td>
          <td></td>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${contactList}" var="contact" varStatus="vs">
          <tr>
            <td>${vs.count}</td>
            <td>${contact.name}</td>
            <td>${contact.mobile}</td>
            <td>
              <button type="button" class="btn-detail" data-contact-no="${contact.contactNo}">상세</button>
              <button type="button" class="btn-remove">삭제</button>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
  <script>
  
    $('.btn-detail').on('click', (evt)=>{
    	const contactNo = evt.target.dataset.contactNo;
    	location.href = '${contextPath}/contact/detail.do?contact-no=' + contactNo;
    })
  
    $('.btn-remove').on('click', (evt)=>{
    	if(confirm('연락처를 삭제할까요?')) {    		
      	const contactNo = $(evt.target).prev().data('contactNo');
      	location.href = '${contextPath}/contact/remove.do?contact-no=' + contactNo;  	
    	}
    })
  
  </script>

</body>
</html>