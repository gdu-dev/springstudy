package com.gdu.app12.aspect;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@EnableAspectJAutoProxy
@Component
public class ConfirmLoginAspect {

  // 포인트컷 : ConfirmLoginAspect를 동작시키는 메소드들
  @Pointcut("execution(* com.gdu.app12.controller.*Controller.requiredLogin_*(..))")
  public void requiredLogin() { }
  
  // 조인포인트(컨트롤러의 모든 메소드)를 모두 받아온 다음 그 중에서 포인트컷에 해당하는 메소드(requiredLogin_으로 시작하는 메소드)를 동작시키기 전에 실행되는 requiredLoginHandler 메소드
  @Before("requiredLogin()")
  public void requiredLoginHandler(JoinPoint joinPoint) throws Exception {
    
    // 로그인 확인을 위해서 HttpServletRequest를 구한 뒤 HttpSession을 구한다.
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = servletRequestAttributes.getRequest();
    HttpSession session = request.getSession();
    
    // 로그인 여부 확인
    if(session.getAttribute("loginId") == null) {
      
      // 응답을 위해서 HttpServletResponse를 구한다.
      HttpServletResponse response = servletRequestAttributes.getResponse();
      
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
      
    }
    
  }
  
  // ConfirmLoginAspect 실행 이후에 컨트롤러의 메소드가 실행되기 때문에
  // 컨트롤러의 메소드 실행을 막을 수 있는 인터셉터로 변경한다. (사용되지 않는 클래스)
  
}