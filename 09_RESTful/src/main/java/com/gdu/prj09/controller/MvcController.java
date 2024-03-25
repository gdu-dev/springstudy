package com.gdu.prj09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;

@Slf4j
@Controller
public class MvcController {

  @GetMapping(value={"/", "/main.do"})
  public String welcome() {
    log.info("you can confirm value = {}", StructuredArguments.value("key", "value"));
    return "index";
  }

}
