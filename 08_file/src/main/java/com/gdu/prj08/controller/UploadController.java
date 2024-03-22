package com.gdu.prj08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.prj08.service.UploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UploadController {

  private final UploadService uploadService;
  
  @PostMapping("/upload1.do")
  public String upload1(MultipartHttpServletRequest multipartRequest
                      , RedirectAttributes redirectAttributes) {
    int insertCount = uploadService.upload1(multipartRequest);
    redirectAttributes.addFlashAttribute("insertCount", insertCount);
    return "redirect:/main.do";
  }
  
}
