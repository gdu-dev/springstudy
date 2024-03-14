package com.gdu.prj02.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
  
  @RequestMapping("/blog/detail.do")
  public String detail(@RequestParam(value="blogNo"
                                   , required=false
                                   , defaultValue="0") int blogNo, Model model) {
    
    // DB 에서 가져온 데이터
    BlogDto blog = BlogDto.builder()
                        .blogNo(blogNo)
                        .title("제목" + blogNo)
                      .build();
    
    // JSP 로 전달할 데이터
    model.addAttribute("blog", blog);
    
    // blog/detail.jsp 로 forward
    return "blog/detail";
    
  }
  
  // @RequestMapping(value="/blog/add.do", method=RequestMethod.POST)
  public String add(BlogDto blog) {  // 커맨드 객체의 Model 저장 방식 : 클래스 타입을 camelCase 로 변경해서 저장한다. (BlogDto -> blogDto 로 변경해서 저장)
    
    // blog/addResult.jsp 로 forward
    return "blog/addResult";
    
  }
  
  @RequestMapping(value="/blog/add.do", method=RequestMethod.POST)
  public String add2(@ModelAttribute("blog") BlogDto blog) {  // @ModelAttribute : 커맨드 객체가 Model 에 저장되는 이름을 지정할 때 사용한다.
    return "blog/addResult";
  }

}
