package com.gdu.prj01.xml02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml02/appCtx.xml");
    
    Student student = (Student) ctx.getBean("student");
    
    System.out.println(student.getScores());
    System.out.println(student.getContacts());
    System.out.println(student.getFriends());

    ctx.close();
    
  }

}
