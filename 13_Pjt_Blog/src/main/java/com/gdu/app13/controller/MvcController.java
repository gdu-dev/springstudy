package com.gdu.app13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	/*
	@GetMapping("/header.do")
	public String header() {
	  return "layout/header";
	}
	*/
	
}
