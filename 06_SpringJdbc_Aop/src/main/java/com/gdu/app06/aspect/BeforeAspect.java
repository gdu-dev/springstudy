package com.gdu.app06.aspect;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class BeforeAspect {
	
  // 로거
  private static final Logger log = LoggerFactory.getLogger(BeforeAspect.class);

  // 포인트컷 : 모든 컨트롤러의 모든 메소드
	@Pointcut("execution(* com.gdu.app06.controller.*Controller.*(..))")
	public void setPointCut() { }  // 이 메소드는 이름만 제공한다. 아무 이름이나 써도 되고, 본문도 필요가 없다.
	
	// @Before 어드바이스 : 메소드 실행 전에 동작한다.
	@Before("setPointCut()")
	public void beforeAdvice(JoinPoint joinPoint) {  
		
	  // HttpServletRequest
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = servletRequestAttributes.getRequest();
    
    // HttpServletRequest -> Map으로 변환하면, 모든 파라미터가 Map의 Key로 변환되므로 모든 파라미터를 반복문으로 순회할 수 있다.
    Map<String, String[]> map = request.getParameterMap();
    
    // 콘솔에 출력할 형태 만들기 -> [파라미터명=값]
    String params = "";
    if(map.isEmpty()) {
      params += "[No Parameter]";
    } else {
      for(Entry<String, String[]> entry : map.entrySet()) {
        params += "[" + entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "]";
      }
    }
    
		// 로그 출력, 치환 문자 {} 사용
    log.debug("{}", joinPoint.getTarget().getClass().getName());       // 컨트롤러명
    log.debug("{} {}", request.getMethod(), request.getRequestURI());  // 요청 방식, 요청 URI
    log.debug("{}", joinPoint.getSignature().getName() + "()");        // 실행된 메소드명() - 포인트컷명
    log.debug("{}", params);                                           // 요청 파라미터

	}
	
}