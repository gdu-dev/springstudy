package com.gdu.prj02.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.prj02.dto.UserDto;

@Controller
public class MyController6 {

  @GetMapping("/user/login1.do")
  public String login1(HttpServletRequest request) {
    
    // HttpSession 구하기
    HttpSession session = request.getSession();
    
    // session 에 저장할 객체
    UserDto user = new UserDto(1, "min@naver.com");
    
    // session 에 객체 저장하기
    session.setAttribute("user", user);
    
    // 메인 페이지로 이동
    return "redirect:/main.do";
    
  }
  
  @GetMapping("/user/logout1.do")
  public String logout1(HttpServletRequest request) {
    
    // HttpSession 구하기
    HttpSession session = request.getSession();
    
    // session 의 모든 정보 지우기
    session.invalidate();
    
    // 메인 페이지로 이동
    return "redirect:/main.do";
    
  }
  
  
  
  
}
