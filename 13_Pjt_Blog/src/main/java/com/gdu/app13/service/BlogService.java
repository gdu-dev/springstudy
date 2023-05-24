package com.gdu.app13.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BlogService {
  public void loadBlogList(HttpServletRequest request, Model model);
  public void addBlog(HttpServletRequest request, HttpServletResponse response);
}
