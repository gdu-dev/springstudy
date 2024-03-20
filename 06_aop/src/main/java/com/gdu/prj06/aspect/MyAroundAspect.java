package com.gdu.prj06.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class MyAroundAspect {

  // PointCut : 언제 동작하는가?
  @Pointcut("execution (* com.gdu.prj06.controller.*Controller.*(..))")
  public void setPointCut() {}
  
  // Advice : 무슨 동작을 하는가?
  /*
   * Around Advice 메소드 작성 방법
   * 1. 반환타입 : Object
   * 2. 메소드명 : 마음대로
   * 3. 매개변수 : ProceedingJoinPoint 타입 객체
   */

  @Around("setPointCut()")
  public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    
    log.info("{}", "-".repeat(80));                // 동작 이전 (@Before 이전)
    
    Object obj = proceedingJoinPoint.proceed();    // 동작 시점
    
    log.info("{}\n", "-".repeat(80));              // 동작 이후 (@After 이후)
    
    return obj;
    
  }
  
}
