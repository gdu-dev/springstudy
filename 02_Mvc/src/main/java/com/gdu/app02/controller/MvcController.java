package com.gdu.app02.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

	
	/*
		@RequestMapping을 대체하는 새로운 애너테이션(Spring4 이후)
		1. @GetMapping
		2. @PostMapping
		3. @PutMapping
		4. @DeleteMapping
	*/
	
		
	/* 1. HttpServletRequest로 요청 파라미터 처리하기 */
	
	@GetMapping("/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		
		// name의 전달이 없으면 "홍길동"이 사용된다.
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("name"));
		String name = opt1.orElse("홍길동");
		
		// age의 전달이 없으면 "0"이 사용된다.
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("age"));
		int age = Integer.parseInt(opt2.orElse("0"));
		
		/*
			Model model
			1. 스프링에서 사용하는 데이터(속성) 전달 객체이다.
			2. Model-2(Jsp/Servlet)에서는 HttpServletRequest request 객체를 사용해서 데이터를 전달하지만,
			   스프링에서는 Model model 객체를 사용한다.
			3. forward할 데이터를 Model의 addAttribute() 메소드로 저장한다.
		*/
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		// 기본 이동 방식은 forward이다.
		return "mvc/detail";  // 실제 처리 경로  :  /WEB-INF/views/mvc/detail.jsp
		
		/*
			참고. redirect로 이동하기
			return "redirect:이동할경로";
		*/
		
	}
	
}
