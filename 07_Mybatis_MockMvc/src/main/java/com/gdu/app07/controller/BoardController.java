package com.gdu.app07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gdu.app07.service.BoardServiceImpl;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService;

	/*
		데이터(속성) 저장 방법
		1. forward  : Model에 attribute로 저장한다.
		2. redirect : RedirectAttributes에 flashAttribute로 저장한다.
	*/
	
	// getBoardList() 서비스가 반환한 List<BoardDTO>를 /WEB-INF/views/board/list.jsp로 전달한다.
	@GetMapping("/list.do")
	public String list(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	
	// 호랑이 시절 ModelAndView 클래스
	/*
	@GetMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardService.getBoardList());
		mav.setViewName("board/list");
		return mav;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
}







