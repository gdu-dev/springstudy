package com.gdu.prj01.xml03;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyDao {

  private Connection con;
  private MyConnection myConnection;
  
  private Connection getConnection() {
    Connection con = null;
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml03/app-context.xml");
    myConnection = ctx.getBean("myConnection", MyConnection.class);
    con = myConnection.getConnection();
    ctx.close();
    return con;
  }
  
  private void close() {
    try {
      if(con != null) {
        con.close();
        System.out.println(myConnection.getUser() + " 접속해제되었습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
}
