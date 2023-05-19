package com.gdu.app12.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app12.domain.UserDTO;

public interface UserService {

  public Map<String, Object> verifyId(String id);
  public Map<String, Object> verifyEmail(String email);
  public Map<String, Object> sendAuthCode(String email);
  public void join(HttpServletRequest request, HttpServletResponse response);
  public void login(HttpServletRequest request, HttpServletResponse response);
  public void autologin(HttpServletRequest request, HttpServletResponse response);
  public void logout(HttpServletRequest request, HttpServletResponse response);
  public void leave(HttpServletRequest request, HttpServletResponse response);
  public void sleepUserHandle();
  public void restore(HttpServletRequest request, HttpServletResponse response);
  public boolean checkPw(String id, String pw);
  public UserDTO getUserById(String id);
  //public Map<String, Object> findUser(UserDTO userDTO);
  
  
  
  
  
}
