package com.gdu.app07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
}
