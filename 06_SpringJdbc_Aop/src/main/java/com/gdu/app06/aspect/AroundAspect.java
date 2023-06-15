package com.gdu.app06.aspect;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j  // private static final Logger log = LoggerFactory.getLogger(AroundAspect.class);
@Aspect
@Component
public class AroundAspect {

  // 포인트컷 : 모든 컨트롤러의 모든 메소드
  @Pointcut("execution(* com.gdu.app06.controller.*Controller.*(..))")
	public void setPointCut() { }
  
  // @Around 어드바이스 : 메소드 실행 전/후에 동작한다.
	@Around("setPointCut()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {  // throws Exception 아님
		
	  // 로그 출력, 치환 문자 {} 사용
    log.debug("START================================================================================");  // 어드바이스 실행 이전
		Object obj = proceedingJoinPoint.proceed();                                                          // 어드바이스 실행 (예외처리가 필요한코드)
		log.debug("{}", DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss").format(LocalDateTime.now()));   // 어드바이스 실행 이후

		return obj;
		
	}
	
}