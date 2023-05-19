package com.gdu.app12.intercept;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.gdu.app12.domain.UserDTO;
import com.gdu.app12.mapper.UserMapper;

@Component
public class AutologinIntercepter implements HandlerInterceptor {

  // 로그인이 안 된 상태이고,
  // 쿠키에 autologinId 값이 존재하는 경우에
  // 자동 로그인을 수행하는 인터셉터
  
  @Autowired
  private UserMapper userMapper;
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession();
    
    if(session != null && session.getAttribute("loginId") == null) {  // 로그인이 되어 있는가?
      
      Cookie cookie = WebUtils.getCookie(request, "autologinId");
      
      if(cookie != null) {  // 쿠키 autologinId가 존재하는가?
        
        String autologinId = cookie.getValue();
        UserDTO loginUserDTO = userMapper.selectAutologin(autologinId);
        if(loginUserDTO != null) {
          session.setAttribute("loginId", loginUserDTO.getId());
        }
        
      }
      
    }
    
    return true;  // 인터셉터를 동작 시킨 뒤 컨트롤러를 계속 동작시킨다.
    
  }
  
}