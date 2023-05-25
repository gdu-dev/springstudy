package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BlogService {
  public void loadBlogList(HttpServletRequest request, Model model);
  public void addBlog(HttpServletRequest request, HttpServletResponse response);
  public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest);
  public int increaseHit(int blogNo);
  public void loadBlog(int blogNo, Model model);
}
