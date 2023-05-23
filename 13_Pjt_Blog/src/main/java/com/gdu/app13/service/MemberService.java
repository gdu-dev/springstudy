package com.gdu.app13.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface MemberService {
  public void login(HttpServletRequest request);
  public void logout(HttpSession session);
}
