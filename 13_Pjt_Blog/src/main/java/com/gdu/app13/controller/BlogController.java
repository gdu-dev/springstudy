package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app13.service.BlogService;

@RequestMapping("/blog")
@Controller
public class BlogController {

  @Autowired
  private BlogService blogService;
  
  @GetMapping("/list.do")
  public String list(HttpServletRequest request, Model model) {
    blogService.loadBlogList(request, model);
    return "blog/list";
  }
  
  @GetMapping("/write.form")
  public String write() {
    return "blog/write";
  }
  
  @PostMapping("/add.do")
  public void add(HttpServletRequest request, HttpServletResponse response) {
    blogService.addBlog(request, response);
  }
  
  @ResponseBody
  @PostMapping(value="/imageUpload.do", produces="application/json")
  public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest) {
    return blogService.imageUpload(multipartRequest);
  }
  
  @GetMapping("/increseHit.do")
  public String increseHit(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo) {
    int increaseResult = blogService.increaseHit(blogNo);
    if(increaseResult == 1) {
      return "redirect:/blog/detail.do?blogNo=" + blogNo;
    } else {
      return "redirect:/blog/list.do";
    }
  }
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo
                     , Model model) {
    blogService.loadBlog(blogNo, model);
    return "blog/detail";
  }
  
  
  
  
  
  
  
}
