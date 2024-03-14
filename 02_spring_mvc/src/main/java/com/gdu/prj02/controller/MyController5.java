package com.gdu.prj02.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyController5 {

  /*
   * redirect 방법
   * 
   * 1. return "redirect:요청주소";
   * 2. HttpServletResponse response 를 이용한 응답 만들기
   */
  
  // Spring 4 이후 @GetMapping / @PostMapping / @PutMapping / @DeleteMapping 등 사용 가능
  
  @GetMapping("/faq/add.do")
  public String add(RedirectAttributes redirectAttributes) {
    
    // add 결과
    int addResult = Math.random() < 0.5 ? 1 : 0;
    
    // add 결과를 flash attribute 로 저장하면 redirect 경로에서 확인이 가능하다.
    // 성공 : "/faq/list.do" 요청으로 이동하는 faq/list.jsp 에서 addResult 값을 확인할 수 있다.
    // 실패 : "/main.do" 요청으로 이동하는 index.jsp 에서 addResult 값을 확인할 수 있다.
    redirectAttributes.addFlashAttribute("addResult", addResult);
    
    // add 결과에 따른 이동
    String path = addResult == 1 ? "/faq/list.do" : "/main.do";
    
    // 이동
    return "redirect:" + path;
    
  }
  
  @GetMapping("/faq/list.do")
  public String list() {
    return "faq/list";
  }
  
  @GetMapping("/faq/modify.do")
  public void modify(HttpServletRequest request, HttpServletResponse response) {
    
    // modify 결과
    int modifyResult = Math.random() < 0.5 ? 1 : 0;
    
    // 응답 만들기
    response.setContentType("text/html; charset=UTF-8");
    try {      
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if(modifyResult == 1) {
        out.println("alert('수정되었습니다.');");
        out.println("location.href='" + request.getContextPath() + "/faq/list.do';");
      } else {
        out.println("alert('실패했습니다.');");
        out.println("history.back();");
      }
      out.println("</script>");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
}
