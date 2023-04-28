package com.gdu.app10.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app10.service.BbsService;

@RequestMapping("/bbs")
@Controller
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	@GetMapping("/list.do")
	public String list(HttpServletRequest request, Model model) {
		bbsService.loadBbsList(request, model);
		return "bbs/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "bbs/write";
	}
	
	@PostMapping("/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int addResult = bbsService.addBbs(request);
		redirectAttributes.addFlashAttribute("addResult", addResult);
		return "redirect:/bbs/list.do";
	}
	
	@PostMapping("/remove.do")
	public String remove(int bbsNo, RedirectAttributes redirectAttributes) {  // @RequestParam 생략
		int removeResult = bbsService.removeBbs(bbsNo);
		redirectAttributes.addFlashAttribute("removeResult", removeResult);
		return "redirect:/bbs/list.do";
	}
	
	@PostMapping("/reply/add.do")
	public String replyAdd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int addReplyResult = bbsService.addReply(request);
		redirectAttributes.addFlashAttribute("addReplyResult", addReplyResult);
		return "redirect:/bbs/list.do";
	}
	
}
