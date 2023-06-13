package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app13.service.GoodService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/good")
@Controller
public class GoodController {

	private final GoodService goodService;
	
	@ResponseBody
	@GetMapping(value="/getGoodCheckState.do", produces="application/json")
	public Map<String, Object> getGoodCheckState(HttpServletRequest request) {
		return goodService.getGoodCheckState(request);
	}
	
	@ResponseBody
	@GetMapping(value="/getGoodCount.do", produces="application/json")
	public Map<String, Object> getGoodCount(int blogNo) {
		return goodService.getGoodCount(blogNo);
	}
	
	@ResponseBody
	@GetMapping(value="/mark.do", produces="application/json")
	public Map<String, Object> mark(HttpServletRequest request) {
		return goodService.mark(request);
	}
	
}
