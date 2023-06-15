package com.gdu.app06.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j   // private static final Logger log = LoggerFactory.getLogger(AfterAspect.class);
@Aspect
@Component
public class AfterAspect {
	
  // 포인트컷 : 모든 컨트롤러의 모든 메소드
  @Pointcut("execution(* com.gdu.app06.controller.*Controller.*(..))")
  public void setPointCut() { }  // 이 메소드는 이름만 제공한다. 아무 이름이나 써도 되고, 본문도 필요가 없다.
	
  // @After 어드바이스 : 메소드 실행 후에 동작한다.
  @After("setPointCut()")
  public void afterAdvice(JoinPoint joinPoint) {  
    
    // 로그 출력, 치환 문자 {} 사용
    log.debug("END  ==================================================================================\n");
    
  }
	
}