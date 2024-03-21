package com.gdu.prj07.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 인터셉터
 * 
 * 1. Controller 의 요청과 응답을 가로챈다.
 * 2. 동작 순서
 *     view - filter - DispatcherServlet - interceptor - controller - service - dao - db
 *           (web.xml)      (servlet-context.xml)
 * 3. 생성 방법
 *    1) HandlerInterceptor 인터페이스 구현 (권장)
 *    2) HandlerInterceptorAdaptor 클래스 상속
 * 4. 주요 메소드
 *    1) preHandle()       : 요청 이전에 동작할 코드 (요청을 막을 수 있다.)
 *    2) postHandle()      : 요청 이후에 동작할 코드
 *    3) afterCompletion() : View 처리가 끝난 이후에 동작할 코드
 */

public class ContactInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    // preHandle() 메소드 반환값
    // 1. true  : 요청을 처리한다.
    // 2. false : 요청을 처리하지 않는다.
    
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    out.println("alert('인터셉터가 동작했습니다.')");
    out.println("history.back()");
    out.println("</script>");
    
    return false;  // 컨트롤러로 요청이 전달되지 않는다.
    
  }
  
}
