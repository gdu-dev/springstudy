package com.gdu.prj10.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.prj10.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

  private final BoardService boardService;
  
  @PostMapping(value="/summernote/imageUpload.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> summernoteImageUpload(MultipartHttpServletRequest multipartRequest) {
    return boardService.summernoteImageUpload(multipartRequest);
  }
  
  @PostMapping("/board/register.do")
  public String register(HttpServletRequest request) {
    System.out.println(request.getParameter("contents"));
    return "redirect:/main.do";
  }
  
}
