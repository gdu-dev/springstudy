package com.gdu.app11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/upload")
@Controller
public class UploadController {

	@GetMapping("/list.do")
	public String list() {
		return "upload/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "upload/write";
	}
	
}
