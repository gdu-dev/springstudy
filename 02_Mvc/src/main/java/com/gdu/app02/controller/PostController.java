package com.gdu.app02.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

	@GetMapping("/post/detail.do")
	public String detail(HttpServletRequest request) throws Exception {  // name, age 파라미터가 있다.
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		System.out.println("/post/detail.do");
		System.out.println("name: " + name + ", age: " + age);
		
		// return "redirect:이동경로";
		return "redirect:/post/list.do?name=" + URLEncoder.encode(name, "UTF-8") + "&age=" + age;  //  /post/list.do 매핑으로 이동하시오! name, age 파라미터를 다시 붙인다!
		
	}
	
	@GetMapping("/post/list.do")
	public String list(HttpServletRequest request,  // name, age 파라미터가 있다.
			           Model model) {
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		//  /WEB-INF/views/post/list.jsp로 forward 하겠다!
		return "post/list";
		
	}
	
	
	@GetMapping("/post/detail.me")
	public String detailMe(HttpServletRequest request,
			               RedirectAttributes redirectAttributes) {  // Redirect할 때 속성(Attriute)을 전달하는 스프링 인터페이스
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// Redirect 경로까지 전달되는 속성 : Flash Attribute
		redirectAttributes.addFlashAttribute("name", name);  // addAttribute()가 아님을 주의하세요!
		redirectAttributes.addFlashAttribute("age", age);
		
		return "redirect:/post/list.me";
		
	}
	
	@GetMapping("/post/list.me")
	public String listMe() {  // Flash Attribute는 Redirect 경로까지 자동으로 전달되므로 별도의 코드가 필요하지 않다.
		return "post/list";
	}
	
}
