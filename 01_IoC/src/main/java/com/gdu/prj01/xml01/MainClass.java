package com.gdu.prj01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    // appCtx.xml 읽기
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml01/appCtx.xml");
    
    // appCtx.xml 에 등록한 빈(bean) 가져오기
    Calculator calculator = ctx.getBean("calculator", Calculator.class);
    
    // 가져온 빈(bean) 사용하기
    calculator.add(10, 20);
    calculator.sub(10, 5);
    calculator.mul(10, 3);
    calculator.div(10, 4);
    
    // appCtx.xml 닫기
    ctx.close();

  }

}
