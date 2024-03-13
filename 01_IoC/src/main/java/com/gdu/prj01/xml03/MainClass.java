package com.gdu.prj01.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml03/app-context.xml");
    MyController myController = ctx.getBean("myController", MyController.class);
    myController.add();
    ctx.close();

  }

}
