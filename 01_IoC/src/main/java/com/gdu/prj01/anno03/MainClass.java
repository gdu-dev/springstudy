package com.gdu.prj01.anno03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    MyController myController = ctx.getBean("myController", MyController.class);
    myController.add();
    ctx.close();

  }

}
