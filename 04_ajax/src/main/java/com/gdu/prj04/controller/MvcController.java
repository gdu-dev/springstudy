package com.gdu.prj04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

  @GetMapping(value= {"/", "/main.do"})
  public String welcome() {
    return "index";
  }
  
  @GetMapping("/exercise1.do")
  public void exercise1() { }
  
  @GetMapping("/exercise2.do")
  public String exercise2() {
    return "exercise2";
  }
  
  @GetMapping("/exercise3.do")
  public String exercise3() {
    return "exercise3";
  }

}
