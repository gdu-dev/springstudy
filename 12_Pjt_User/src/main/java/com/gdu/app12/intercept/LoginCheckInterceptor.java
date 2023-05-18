package com.gdu.app12.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

  // 로그인 여부를 확인해서
  // 로그인이 되어 있지 않으면 로그인 페이지로 이동시키는 인터셉터
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    HttpSession session = request.getSession();
    
    // 로그인 여부 확인
    if(session != null && session.getAttribute("loginId") == null) {
      
      // 응답
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("if(confirm('로그인이 필요한 기능입니다. 로그인하시겠습니까?')){");
      out.println("location.href='" + request.getContextPath() + "/user/login.form';");
      out.println("} else {");
      out.println("history.back();");
      out.println("}");
      out.println("</script>");
      out.flush();
      out.close();
      
      return false;  // 컨트롤러의 요청이 처리되지 않는다.
      
    }
    
    return true;     // 컨트롤러의 요청이 처리된다.
    
  }
  
}
