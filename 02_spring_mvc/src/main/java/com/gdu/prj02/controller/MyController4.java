package com.gdu.prj02.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.prj02.dto.BlogDto;

@Controller
public class MyController4 {

  @RequestMapping("/blog/list.do")
  public String list(Model model) {
    
    // DB 에서 select 한 결과
    List<BlogDto> blogList = Arrays.asList(
          new BlogDto(1, "제목1"),
          new BlogDto(2, "제목2"),
          new BlogDto(3, "제목3")
        );
    
    // Model 에 저장한 값은 forward 할 때 전달된다.
    model.addAttribute("blogList", blogList);
    
    // 기본 이동 방식은 forward 방식이다.
    return "blog/list";
    
  }
  
}
