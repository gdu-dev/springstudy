package com.gdu.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

	@GetMapping("/")  // http://localhost:9090/myapp 요청인 경우에 동작한다. (context path 경로)
	/*
	@GetMapping("/board")  // http://localhost:9090/myapp/board 요청인 경우에 동작한다.
	@GetMapping("board")   // http://localhost:9090/myapp/board 요청인 경우에 동작한다.
	*/
	
	// 반환타입 String : 이동할 jsp 이름을 반환한다. 반환된 이름은 servlet-context.xml의 ViewResolver에 의해서 rendering 된다.(prefix + 반환값 + suffix)
	public String index() {
		return "index";
	}
	
	
	
	// <a>, location
	@GetMapping("/detail.do")
	public void getDetail(HttpServletRequest request) {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		System.out.println(boardNo + ", " + title);
	}
	
	@PostMapping("/detail.do")
	public void postDetail(HttpServletRequest request) {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		System.out.println(boardNo + ", " + title);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
