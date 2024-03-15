package com.gdu.prj04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

  @GetMapping(value= {"/", "/main.do"})
  public String welcome() {
    return "index";
  }
  
}
