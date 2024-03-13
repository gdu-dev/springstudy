package com.gdu.prj01.anno02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    Student student = (Student) ctx.getBean("student");
    
    System.out.println(student.getScores());
    System.out.println(student.getContacts());
    System.out.println(student.getFriends());

    ctx.close();
    
  }

}
