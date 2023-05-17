package com.gdu.app12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

	@GetMapping(value={"/", "/index.do"})
	public String welcome() {
		return "index";
	}
	
}
