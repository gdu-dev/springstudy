package com.gdu.app13.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app13.domain.BlogDTO;
import com.gdu.app13.domain.MemberDTO;
import com.gdu.app13.mapper.BlogMapper;

@Service
public class BlogServiceImpl implements BlogService {

  @Autowired
  private BlogMapper blogMapper;
  
  @Override
  public void loadBlogList(HttpServletRequest request, Model model) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addBlog(HttpServletRequest request, HttpServletResponse response) {
    
    // 요청 파라미터
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    // DB로 보낼 BlogDTO 만들기
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberNo(memberNo);
    BlogDTO blogDTO = new BlogDTO();
    blogDTO.setTitle(title);
    blogDTO.setContent(content);
    blogDTO.setMemberDTO(memberDTO);
    
    // DB로 BlogDTO 보내기 (삽입)
    int addResult = blogMapper.addBlog(blogDTO);
    
    // 응답
    try {
      
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if(addResult == 1) {
        out.println("alert('블로그가 작성되었습니다.');");
        out.println("location.href='" + request.getContextPath() + "/blog/list.do';");
      } else {
        out.println("alert('블로그 작성이 실패했습니다.');");
        out.println("history.back();");
      }
      out.println("</script>");
      out.flush();
      out.close();
      
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

  }

}
